package com.gmail.salahub.nikolay.jd2_project_week3.service.converter.impl;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Item;
import com.gmail.salahub.nikolay.jd2_project_week3.service.converter.ItemConverter;

import com.gmail.salahub.nikolay.jd2_project_week3.service.model.ItemDTO;
import org.springframework.stereotype.Component;

@Component("itemConverter")
public class ItemConverterImpl implements ItemConverter {

    @Override
    public Item fromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setStatus(itemDTO.getStatus());
        return item;
    }

    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setStatus(item.getStatus());
        return itemDTO;
    }
}
