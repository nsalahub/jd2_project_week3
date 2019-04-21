package com.gmail.salahub.nikolay.jd2_project_week3.repository;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.InsertItemAuditRepositoryException;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.AuditItem;

import java.sql.Connection;

public interface AuditItemRepository {
    AuditItem save(Connection connection, AuditItem item) throws InsertItemAuditRepositoryException;

}
