/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.CategoryService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.ErrorDataResult;
import com.project.eshop.core.utilities.results.ErrorResult;
import com.project.eshop.core.utilities.results.Result;
import com.project.eshop.core.utilities.results.SuccessDataResult;
import com.project.eshop.core.utilities.results.SuccessResult;
import com.project.eshop.dataAccess.CategoryRepository;
import com.project.eshop.entities.concretes.Category;
import com.project.eshop.entities.dto.CategoryDto;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Emre Yıldırım
 */
@Service
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<Category>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return new SuccessDataResult<>(categories, "Categories listed successfully");
    }

    @Override
    public DataResult<Category> getById(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return new SuccessDataResult<>(category.get(), "Category found");
        }
        return new ErrorDataResult<>("Category not found");
    }

    @Override
    public Result add(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
        return new SuccessResult("Category added successfully");
    }

    @Override
    public Result update(CategoryDto categoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getId());
        if (categoryOptional.isEmpty()) {
            return new ErrorResult("Category not found");
        }
        Category category = categoryOptional.get();
        category.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(category);
        return new SuccessResult("Category updated successfully");
    }

    @Override
    public Result delete(long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return new SuccessResult("Category deleted successfully");
        }
        return new ErrorResult("Category not found");
    }
}
