package com.blo.serviceimpl;

import java.util.List;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.blo.entities.Category;
import com.blo.repositories.CategoryRepo;
import com.blo.services.CategoryService;
import com.blo.userDto.CategoryDto;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired 
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category saveCategory = null;
		try {
			Category category = mapper.map(categoryDto, Category.class);
			saveCategory = categoryRepo.save(category);
			if (ObjectUtils.isEmpty(saveCategory)) {
				return null;
			}

		} catch (Exception e) {
			log.error("Error:{}", e.getMessage());
		}
		CategoryDto map = mapper.map(saveCategory, CategoryDto.class);
		return map;
	}
	

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Optional<Category> categoryOptional = categoryRepo.findById(categoryDto.getCategoryId());
		
		
		if (categoryOptional.isPresent()) {
			Category category = categoryOptional.get();

			if (categoryDto.getCategoryId() != null) {
				category.setCategoryId(categoryDto.getCategoryId());
			}
			if (categoryDto.getCategoryTitle() != null) {
				category.setCategoryTitle(categoryDto.getCategoryTitle());
			}
			if (categoryDto.getCategoryDescription() != null) {
				category.setCategoryDescription(categoryDto.getCategoryDescription());
			}
			
		
			Category saveCategory = categoryRepo.save(category);
		return mapper.map(saveCategory, CategoryDto.class);
	}
	return categoryDto;
	}
	

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		try {
			Optional<Category> category = categoryRepo.findById(categoryId);
		} catch (Exception e) {
			log.error("Error:{}",e.getMessage());
		}
		 CategoryDto map = mapper.map(categoryId, CategoryDto.class);
		return map;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<CategoryDto> categoryDto = null;
		
		try {
			
			List<Category> allCategories = categoryRepo.findAll();
			categoryDto = allCategories.stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
			if (CollectionUtils.isEmpty(allCategories)) {
				return null;
			}
		} catch (Exception e) {
			log.error("Error:{}",e.getMessage());
		}
		return categoryDto;
	}

	
}
