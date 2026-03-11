package com.company.microservice_catalog.infrastructure.adapter.in.web;

import com.company.microservice_catalog.application.port.in.category.CreateCategoryUseCase;
import com.company.microservice_catalog.application.port.in.category.DeleteCategoryUseCase;
import com.company.microservice_catalog.application.port.in.category.GetCategoryUseCase;
import com.company.microservice_catalog.application.port.in.category.UpdateCategoryUseCase;
import com.company.microservice_catalog.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api//v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;


    @PostMapping("/create")
    public ResponseEntity<Category> create(@RequestBody Category category){

        Category createdCategory = createCategoryUseCase.execute(category);

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") Long id, @RequestBody Category category){

        Category updatedCategory = updateCategoryUseCase.execute(id, category);

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id){

        Optional<Category> categoryFound = getCategoryUseCase.getCategory(id);

        return new ResponseEntity<>(categoryFound.get(), HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategory(){

        List<Category> getCategorys = getCategoryUseCase.getAllCategories();

        return new ResponseEntity<>(getCategorys, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){

        deleteCategoryUseCase.execute(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
