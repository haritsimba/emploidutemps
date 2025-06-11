package com.project.telecom.rnu.services;

import com.project.telecom.rnu.dtos.GroupeCreateDto;
import com.project.telecom.rnu.entities.Groupe;
import com.project.telecom.rnu.entities.Student;
import com.project.telecom.rnu.enumerations.OperationResponseStatus;
import com.project.telecom.rnu.mappers.GroupeMapper;
import com.project.telecom.rnu.repositories.CreneauRepository;
import com.project.telecom.rnu.repositories.GroupeRepository;
import com.project.telecom.rnu.repositories.StudentRepository;
import com.project.telecom.rnu.responses.OperationResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupeService {

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    CreneauRepository creneauRepository;

    public OperationResponse create(GroupeCreateDto dto) {
        Groupe groupe = new Groupe();
        groupe.setNom(dto.getNom());

        List<Student> students = studentRepository.findAllById(Arrays.asList(dto.getStudentIds()));
        for (Student s : students){
            List<Groupe> sgs = s.getGroupes();
            sgs.add(groupe);
            s.setGroupes(sgs);
        }
        groupe.setStudents(students);


        Groupe saved = groupeRepository.save(groupe);
        return new OperationResponse(OperationResponseStatus.OK, GroupeMapper.mapToDto(saved), "Groupe created");
    }

    public OperationResponse getAll() {
        return new OperationResponse(OperationResponseStatus.OK, GroupeMapper.mapListToDto(groupeRepository.findAll()), "All groupes");
    }

    public OperationResponse getById(Long id) {
        Optional<Groupe> found = groupeRepository.findById(id);
        return found.map(groupe -> new OperationResponse(OperationResponseStatus.OK, GroupeMapper.mapToDto(groupe), "Groupe found")).orElseGet(() -> new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Groupe not found"));
    }

    public OperationResponse update(Long id, GroupeCreateDto dto) {
        Optional<Groupe> opt = groupeRepository.findById(id);
        if (opt.isEmpty()) {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Groupe not found");
        }

        Groupe groupe = opt.get();
        groupe.setNom(dto.getNom());
        System.out.println(dto.getStudentIds());
        List<Student> students = studentRepository.findAllById(Arrays.asList(dto.getStudentIds()));
        groupe.setStudents(students);

        Groupe updated = groupeRepository.save(groupe);
        return new OperationResponse(OperationResponseStatus.OK, updated, "Groupe updated");
    }

    @Transactional
    public OperationResponse delete(Long id) {
        if (!groupeRepository.existsById(id)) {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Groupe not found");
        }


        Groupe groupe = groupeRepository.findById(id).get();
        for (Student student : groupe.getStudents()) {
            System.out.println(student.toString());
            student.getGroupes().remove(groupe);
        }
        groupe.getStudents().clear();

        creneauRepository.deleteAllByGroupe(groupe);
        groupeRepository.delete(groupe);
        return new OperationResponse(OperationResponseStatus.OK, null, "Groupe deleted");
    }
}
