package com.gmail.salahub.nikolay.jd2_project_week3;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.ItemRepository;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.connection.ConnectionHandler;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.InsertItemRepositoryException;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.UpdateItemRepositoryException;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.exception.UpdateRowsRepositoryException;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Item;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Status;
import com.gmail.salahub.nikolay.jd2_project_week3.service.ItemService;
import com.gmail.salahub.nikolay.jd2_project_week3.service.converter.ItemConverter;
import com.gmail.salahub.nikolay.jd2_project_week3.service.impl.ItemServiceImpl;
import com.gmail.salahub.nikolay.jd2_project_week3.service.model.ItemDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.util.List;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
public class ItemServiceTest {

    private ItemService itemService;
    @Mock
    private ItemConverter converter;
    @Mock
    private ConnectionHandler connectionHandler;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private Connection connection;

    private final String TEST_NAME = "testname";

    @Before
    public void init() {
        ItemRepository connectionService;
        itemService = new ItemServiceImpl(itemRepository,converter,connectionHandler );
        Mockito.when(connectionHandler.getConnection()).thenReturn(connection);
    }

    private List<Item> items = asList(new Item(1L, "name", Status.READY), new Item(2L, "name2", Status.READY));
    private List<ItemDTO> itemDTOS = asList(new ItemDTO(1L, "test", Status.READY),
            new ItemDTO(2L, "test", Status.READY));


    @Test
    public void shouldSaveItem() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(TEST_NAME);
        itemDTO.setStatus(Status.READY);
        Item item = new Item();
        item.setName(TEST_NAME);
        item.setStatus(Status.READY);
        Mockito.when(converter.toDTO(item)).thenReturn(itemDTO);
        Item savedItem = new Item(1L, TEST_NAME, Status.READY);
        Mockito.when(itemRepository.add(connection, item)).thenReturn(savedItem);
        ItemDTO savedDTO = new ItemDTO(1L, TEST_NAME, Status.READY);
        Mockito.when(converter.toDTO(savedItem)).thenReturn(savedDTO);
        Assert.assertNotNull(itemService.add(itemDTO));
    }

    @Test(expected = InsertItemRepositoryException.class)
    public void shouldThrowItemRepositoryException() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(TEST_NAME);
        itemDTO.setStatus(Status.READY);
        Item item = new Item();
        item.setName(TEST_NAME);
        item.setStatus(Status.READY);
        Mockito.when(converter.toDTO(item)).thenReturn(itemDTO);
        Mockito.when(itemRepository.add(connection, item)).thenReturn(null);
        itemService.add(itemDTO);
    }


    @Test
    public void shouldUpdateItemStatus() {
        Mockito.when(itemRepository.update(connection, 1L, "READY")).thenReturn(1);
        int result = itemService.update(1L, "READY");
        Assert.assertEquals(1L, result);
    }

    @Test(expected = UpdateItemRepositoryException.class)
    public void shouldThrowItemRepositoryExceptionIfUpdatedRowEqualsZero() {
        Mockito.when(itemRepository.update(connection, 1L, "READY")).thenReturn(0);
        int row = itemService.update(1L, "READY");
    }
}
