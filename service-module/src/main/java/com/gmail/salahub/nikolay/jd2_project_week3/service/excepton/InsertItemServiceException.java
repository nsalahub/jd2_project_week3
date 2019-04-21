package com.gmail.salahub.nikolay.jd2_project_week3.service.excepton;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.InsertItemRepositoryException;

public class InsertItemServiceException extends RuntimeException {
    public InsertItemServiceException(String message, Throwable e) {
        super(message, e);
    }
}
