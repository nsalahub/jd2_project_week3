package com.gmail.salahub.nikolay.jd2_project_week3.converter.impl;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Item;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Status;
import com.gmail.salahub.nikolay.jd2_project_week3.service.converter.ItemConverter;
import com.gmail.salahub.nikolay.jd2_project_week3.service.converter.impl.ItemConverterImpl;
import com.gmail.salahub.nikolay.jd2_project_week3.service.model.ItemDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ItemConverterTest {

    private ItemConverter itemConverter;

    @Before
    public void init() {
        itemConverter = new ItemConverterImpl();
    }

    @Test
    public void shouldReturnItemWithSomeIdAfterPerformingDTOMethod() {
        Item item = new Item();
        item.setId(1L);
        ItemDTO itemDTO = itemConverter.toDTO(item);
        Assert.assertEquals(item.getId(), itemDTO.getId());
    }

    @Test
    public void shouldReturnItemWithSomeStatusAfterPerformingDTOMethod() {
        Item item = new Item();
        item.setStatus(Status.COMPLETED);
        ItemDTO itemDTO = itemConverter.toDTO(item);
        Assert.assertEquals(item.getStatus(), itemDTO.getStatus());
    }

    @Test
    public void shouldReturnItemWithSomeNameAfterPerformingDTOMethod() {
        Item item = new Item();
        item.setName("name");
        ItemDTO itemDTO = itemConverter.toDTO(item);
        Assert.assertEquals(item.getName(), itemDTO.getName());
    }
}
