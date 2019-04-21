package com.gmail.salahub.nikolay.jd2_project_week3.repository.impl;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.AuditItemRepository;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.InsertItemAuditRepositoryException;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.InsertItemRepositoryException;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.ResultSetItemException;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.AuditItem;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;

import static com.gmail.salahub.nikolay.jd2_project_week3.repository.util.MassageRepositoryUtil.*;

@Repository
public class AuditItemRepositoryImpl implements AuditItemRepository {

    private Logger logger = LoggerFactory.getLogger(AuditItemRepositoryImpl.class);

    @Override
    public AuditItem save(Connection connection, AuditItem item) {
        String query = "INSERT INTO AuditItem VALUES(NULL ,?,?,NOW())";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getActionItem().toString());
            preparedStatement.setLong(2, item.getItemId());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                AuditItem itemWithID = getItemAuditWithID(resultSet, item);
                return itemWithID;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new InsertItemAuditRepositoryException(ERROR_INSERT_AUDIT_ITEM_REPOSITORY, e);
        }
    }

    private AuditItem getItemAuditWithID(ResultSet resultSet, AuditItem item) {
        try {
            if (resultSet.next()) {
                item.setId(resultSet.getLong(1));
            }
            return item;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ResultSetItemException(RESULT_SET_ITEM_ERROR_MASSAGE, e);
        }
    }


}
