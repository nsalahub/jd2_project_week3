package com.gmail.salahub.nikolay.jd2_project_week3.service.aspects;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.AuditItemRepository;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.connection.ConnectionHandler;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.InsertItemAuditRepositoryException;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.UpdateItemRepositoryException;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.ActionItem;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.AuditItem;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Item;
import com.gmail.salahub.nikolay.jd2_project_week3.service.excepton.ItemAuditServiceException;
import com.gmail.salahub.nikolay.jd2_project_week3.service.excepton.ItemServiceSelectedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

import static com.gmail.salahub.nikolay.jd2_project_week3.service.util.ServiceMassageUtil.*;

@Aspect
@Component
public class AuditItemAspect {

    private static final Logger logger = LoggerFactory.getLogger(AuditItemAspect.class);

    AuditItemRepository auditItemRepository;
    ConnectionHandler connectionHandler;

    @Autowired
    AuditItemAspect(AuditItemRepository auditItemRepository, ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
        this.auditItemRepository = auditItemRepository;
    }


    @Pointcut("execution(* com.gmail.salahub.nikolay.jd2_project_week3.repository.ItemRepository.add(..))")
    public void addItemAction() {
    }

    @Pointcut("execution(* com.gmail.salahub.nikolay.jd2_project_week3.repository.ItemRepository.update(..))")
    public void updateItemAction() {
    }

    @AfterReturning(value = "addItemAction()", returning = "savedItem")
    public void addAuditItemAfterAdd(JoinPoint joinPoint, Item savedItem) {
        logger.info("----------------aspect started------------------");
        try (Connection connection = connectionHandler.getConnection()) {
            AuditItem auditItem = new AuditItem();
            auditItem.setActionItem(ActionItem.CREATED);
            auditItem.setItemId(savedItem.getId());
            auditItemRepository.save(connection, auditItem);
        } catch (SQLException | InsertItemAuditRepositoryException e) {
            logger.error(e.getMessage(), e);
            throw new ItemAuditServiceException(ITEM_AUDIT_ASPECT_ERROR_EXCEPTION, e);
        }
    }

    @After(value = "updateItemAction()")
    public void addAuditItemAfterUpdate(JoinPoint joinPoint) {
        logger.info("---------------aspect started -----------------");
        try (Connection connection = connectionHandler.getConnection()) {
            AuditItem auditItem = new AuditItem();
            Object[] args = joinPoint.getArgs();
            auditItem.setItemId((Long) args[1]);
            auditItem.setActionItem(ActionItem.CREATED);
        } catch (SQLException | UpdateItemRepositoryException e) {
            logger.error(e.getMessage(), e);
            throw new ItemAuditServiceException(ITEM_AUDIT_ASPECT_ERROR_EXCEPTION, e);
        }
    }
}
