package com.gmail.salahub.nikolay.jd2_project_week3.repository.impl;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.ItemRepository;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.*;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Item;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.salahub.nikolay.jd2_project_week3.repository.util.MassageRepositoryUtil.*;

@Repository("itemRepository")
public class ItemRepositoryImpl implements ItemRepository {

    private Logger logger = LoggerFactory.getLogger(ItemRepository.class);

    @Override
    public Item add(Connection connection, Item item) {
        String query = "INSERT INTO Item VALUES(NULL ,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getStatus().toString());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                Item itemWithID = getItemWithID(resultSet, item);
                return itemWithID;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new InsertItemRepositoryException(ERROR_INSERT_ITEM_REPOSITORY, e);
        }
    }

    @Override
    public List<Item> getItems(Connection connection) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT id,name,status FROM Item";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = getItemsResultSet(resultSet);
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SelectItemRepositoryException(SELECT_ITEM_EXCEPTION_ERROR, e);
        }
        return items;
    }

    @Override
    public int update(Connection connection, Long id, String status) {
        String query = "UPDATE Item SET status=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setLong(2, id);
            int returnedNumberString = preparedStatement.executeUpdate();
            if (returnedNumberString == 0){
                throw new UpdateRowsRepositoryException();
            }
            return returnedNumberString;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new UpdateItemRepositoryException(UPDATE_ITEM_REPOSITORY_EXCEPTION, e);
        }

    }

    private Item getItemsResultSet(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getLong("id"));
        item.setName(resultSet.getString("name"));
        item.setStatus(Status.valueOf(resultSet.getString("status")));
        return item;
    }

    private Item getItemWithID(ResultSet resultSet, Item item) {
        try {
            if (resultSet.next()) {
                item.setId(resultSet.getLong(1));
                item.setName(item.getName());
                item.setStatus(item.getStatus());
            }
            return item;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ResultSetItemException(RESULT_SET_ITEM_ERROR_MASSAGE, e);
        }
    }
}
