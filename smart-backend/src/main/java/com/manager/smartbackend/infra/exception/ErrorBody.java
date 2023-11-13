package com.manager.smartbackend.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorBody {
    private int status;
    private String message;
}
