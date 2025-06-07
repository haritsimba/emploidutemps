package com.project.telecom.rnu.responses;

import com.project.telecom.rnu.enumerations.OperationResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperationResponse {
    OperationResponseStatus status;
    Object responseData;
    String responseMessage;
}
