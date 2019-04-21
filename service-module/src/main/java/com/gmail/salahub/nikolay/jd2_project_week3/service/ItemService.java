package com.gmail.salahub.nikolay.jd2_project_week3.service;

import com.gmail.salahub.nikolay.jd2_project_week3.service.model.ItemDTO;

import java.util.List;

public interface ItemService {

    ItemDTO add(ItemDTO item);

    List<ItemDTO> getItems();

    int update(Long id, String status);
}
