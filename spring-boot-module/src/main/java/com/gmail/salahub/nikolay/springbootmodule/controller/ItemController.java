package com.gmail.salahub.nikolay.springbootmodule.controller;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Status;
import com.gmail.salahub.nikolay.jd2_project_week3.service.ItemService;
import com.gmail.salahub.nikolay.jd2_project_week3.service.model.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String getItems(Model model, Model addModel) {
        List<ItemDTO> itemsDTO = itemService.getItems();
        ItemDTO addItem = new ItemDTO();
        model.addAttribute("itemsDTO", itemsDTO);
        addModel.addAttribute("itemDTO", addItem);
        return "items";
    }

    @PostMapping("/add")
    public String addItem(
            @Valid ItemDTO itemDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "items";
        }
        ItemDTO itemDTOwithId = itemService.add(itemDTO);
        logger.info(String.valueOf(itemDTOwithId.getId()));
        return "redirect:items";
    }

    @PostMapping("/update")
    public String updateItem(
            ItemDTO itemDTO
    ) {
        logger.info(String.valueOf(itemDTO.getId()));
        logger.info(String.valueOf(itemDTO.getStatus()));
        int resultNumberOfString = itemService.update(itemDTO.getId(), String.valueOf(itemDTO.getStatus()));
        logger.info(String.valueOf(resultNumberOfString));
        return "redirect:items";
    }
}
