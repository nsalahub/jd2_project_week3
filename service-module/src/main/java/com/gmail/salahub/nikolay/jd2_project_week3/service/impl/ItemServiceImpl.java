package com.gmail.salahub.nikolay.jd2_project_week3.service.impl;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.AuditItemRepository;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.ItemRepository;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.connection.ConnectionHandler;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.*;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.ActionItem;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.AuditItem;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Item;
import com.gmail.salahub.nikolay.jd2_project_week3.service.ItemService;
import com.gmail.salahub.nikolay.jd2_project_week3.service.converter.ItemConverter;
import com.gmail.salahub.nikolay.jd2_project_week3.service.excepton.InsertItemServiceException;
import com.gmail.salahub.nikolay.jd2_project_week3.service.excepton.ItemServiceSelectedException;
import com.gmail.salahub.nikolay.jd2_project_week3.service.excepton.UpdateItemServiceException;
import com.gmail.salahub.nikolay.jd2_project_week3.service.model.ItemDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.salahub.nikolay.jd2_project_week3.service.util.ServiceMassageUtil.*;
import static com.gmail.salahub.nikolay.jd2_project_week3.repository.util.MassageRepositoryUtil.*;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository;
    ItemConverter itemConverter;
    ConnectionHandler connectionHandler;


    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           ItemConverter itemConverter,
                           ConnectionHandler connectionHandler) {
        this.itemRepository = itemRepository;
        this.itemConverter = itemConverter;
        this.connectionHandler = connectionHandler;

    }

    private Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Override
    public ItemDTO add(ItemDTO itemDTO) {
        try (Connection connection = connectionHandler.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Item item = itemConverter.fromDTO(itemDTO);
                Item itemWithId = itemRepository.add(connection, item);
                connection.commit();
                return itemConverter.toDTO(itemWithId);
            } catch (SQLException | InsertItemRepositoryException | InsertItemAuditRepositoryException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                throw new InsertItemServiceException(INSERT_ITEM_SERVICE_ERROR_MASSAGE, e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new GettingConnectionException(ERROR_WITH_GETTING_CONNECTION_MASSAGE, e);
        }
    }

    @Override
    public List<ItemDTO> getItems() {
        List<ItemDTO> itemDTOS = new ArrayList<>();
        try (Connection connection = connectionHandler.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Item> items = itemRepository.getItems(connection);
                for (Item item : items) {
                    itemDTOS.add(itemConverter.toDTO(item));
                }
                connection.commit();
            } catch (SQLException | SelectItemRepositoryException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                throw new ItemServiceSelectedException(SELECT_ITEM_SERVICE_ERROR_MASSAGE, e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new GettingConnectionException(ERROR_WITH_GETTING_CONNECTION_MASSAGE, e);
        }
        return itemDTOS;
    }

    @Override
    public int update(Long id, String status) {
        try (Connection connection = connectionHandler.getConnection()) {
            try {
                connection.setAutoCommit(false);
                int returnedValueString = itemRepository.update(connection, id, status);
                connection.commit();
                return returnedValueString;
            } catch (UpdateItemRepositoryException | SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                throw new UpdateItemServiceException(UPDATE_ITEM_REPOSITORY_EXCEPTION, e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new GettingConnectionException(ERROR_WITH_GETTING_CONNECTION_MASSAGE, e);
        }
    }
}