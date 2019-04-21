package com.gmail.salahub.nikolay.jd2_project_week3.repository;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemRepository {
    Item add(Connection connection, Item item);

    List<Item> getItems(Connection connection);

    int update(Connection connection, Long id, String status);

}
