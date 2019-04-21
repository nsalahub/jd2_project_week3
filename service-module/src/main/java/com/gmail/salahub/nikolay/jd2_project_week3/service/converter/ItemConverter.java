package com.gmail.salahub.nikolay.jd2_project_week3.service.converter;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Item;
import com.gmail.salahub.nikolay.jd2_project_week3.service.model.ItemDTO;


public interface ItemConverter {

    Item fromDTO (ItemDTO itemDTO);

    ItemDTO toDTO (Item item);
}
