package com.company.microservice_catalog.infrastructure.adapter.in.web;

import com.company.microservice_catalog.application.port.in.itemtype.CreateItemTypeUseCase;
import com.company.microservice_catalog.application.port.in.itemtype.DeleteItemTypeUseCase;
import com.company.microservice_catalog.application.port.in.itemtype.GetItemTypeUseCase;
import com.company.microservice_catalog.application.port.in.itemtype.UpdateItemTypeUseCase;
import com.company.microservice_catalog.domain.model.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api//v1/itemtype")
@RequiredArgsConstructor
public class ItemTypeController {

    private final CreateItemTypeUseCase createItemTypeUseCase;
    private final UpdateItemTypeUseCase updateItemTypeUseCase;
    private final GetItemTypeUseCase getItemTypeUseCase;
    private final DeleteItemTypeUseCase deleteItemTypeUseCase;


    @PostMapping("/create")
    public ResponseEntity<ItemType> create(@RequestBody ItemType itemType){

        ItemType createdItemType = createItemTypeUseCase.execute(itemType);

        return new ResponseEntity<>(createdItemType, HttpStatus.CREATED);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ItemType> update(@PathVariable("id") Long id, @RequestBody ItemType itemType){

        ItemType updatedItemType = updateItemTypeUseCase.execute(id, itemType);

        return new ResponseEntity<>(updatedItemType, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemType> getItemType(@PathVariable("id") Long id){

        Optional<ItemType> itemTypeFound = getItemTypeUseCase.getItemType(id);

        return new ResponseEntity<>(itemTypeFound.get(), HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<ItemType>> getAllItemType(){

        List<ItemType> getItemTypes = getItemTypeUseCase.getAllItemsType();

        return new ResponseEntity<>(getItemTypes, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){

        deleteItemTypeUseCase.execute(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
