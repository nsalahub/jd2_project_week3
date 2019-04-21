package com.gmail.salahub.nikolay.jd2_project_week3.repository.exception;

import java.sql.SQLException;

public class InsertItemAuditRepositoryException extends RuntimeException {
    public InsertItemAuditRepositoryException(String massage, Throwable e) {
        super(massage, e);
    }
}
