package com.gmail.salahub.nikolay.jd2_project_week3.repository.exception;

public class InsertItemRepositoryException extends RuntimeException {
    public InsertItemRepositoryException(String massage, Throwable e) {
        super(massage, e);
    }
}
