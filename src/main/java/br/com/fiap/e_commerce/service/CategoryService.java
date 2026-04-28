package br.com.fiap.e_commerce.service;

import br.com.fiap.e_commerce.model.Category;
import br.com.fiap.e_commerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);

    }

    // optional - projeto do null pointer exception
    public Optional<Category> getCategoryById(Integer id) { // Optional para evitar Null Pointer Exception
        return categoryRepository.findById(id);
    }


    public void deleteCategory(Integer id) {
        var optionalCategory = getCategoryById(id);
        if(optionalCategory.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category não encontrado");
        }
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Integer id, Category newCategory) {
        var optionalCategory = getCategoryById(id);
        if(optionalCategory.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category não encontrado");
        }
        var category = optionalCategory.get();
        newCategory.setId(id);
        return categoryRepository.save(newCategory);
    }
}
