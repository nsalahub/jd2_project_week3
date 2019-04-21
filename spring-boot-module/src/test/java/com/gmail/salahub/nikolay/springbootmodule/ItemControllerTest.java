package com.gmail.salahub.nikolay.springbootmodule;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Item;
import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Status;
import com.gmail.salahub.nikolay.jd2_project_week3.service.ItemService;
import com.gmail.salahub.nikolay.jd2_project_week3.service.model.ItemDTO;
import com.gmail.salahub.nikolay.springbootmodule.controller.ItemController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

    @Mock
    private ItemService itemService;

    private ItemController itemController;

    private MockMvc mockMvc;

    private List<ItemDTO> itemsDTO = asList(new ItemDTO(1L, "name", Status.COMPLETED),
            new ItemDTO(2L, "another name", Status.READY));

    private ItemDTO itemDTOadd = new ItemDTO(1L, "name", Status.COMPLETED);

    @Before
    public void init() {
        itemController = new ItemController(itemService);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
        when(itemService.getItems()).thenReturn(itemsDTO);
    }


}
