package com.gmail.salahub.nikolay.jd2_project_week3.repository.exception;

public class UpdateItemRepositoryException extends RuntimeException {
    public UpdateItemRepositoryException(String massage, Throwable e) {
        super(massage, e);
    }
}
