package br.com.fiap.e_commerce.controller;

import br.com.fiap.e_commerce.model.Category;
import br.com.fiap.e_commerce.repository.CategoryRepository;
import br.com.fiap.e_commerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final CategoryRepository categoryRepository;

    @Autowired
    private CategoryService service;

    @GetMapping
    public List<Category> getCategories(){
        log.info("Listando todas as categorias");
        return service.getCategories();

    }

    @GetMapping("/by-name/{name}")
    public Page<Category> findByName(@PathVariable String name, Pageable pageable){
        return categoryRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @PostMapping
    //@ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Category> addCategory(@RequestBody Category category) { // @RequestBody converte para objeto java
        log.info("Cadastrando categoria...");
        var categorys = service.addCategory(category);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categorys);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id){ //@PathVariable está relacionado ao caminho da URL

        return service.getCategoryById(id)
                .map(ResponseEntity::ok) // reference method
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id){
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category newCategory){
        Category category = service.updateCategory(id, newCategory);
        return ResponseEntity.ok(newCategory);
    }
}
