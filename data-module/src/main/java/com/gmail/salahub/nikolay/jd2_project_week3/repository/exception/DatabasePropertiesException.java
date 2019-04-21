package com.gmail.salahub.nikolay.jd2_project_week3.repository.exception;

import java.sql.SQLException;

public class DatabasePropertiesException extends RuntimeException {
    public DatabasePropertiesException(String propertyErrorMassage, SQLException e) {
        super(propertyErrorMassage, e);
    }
}
