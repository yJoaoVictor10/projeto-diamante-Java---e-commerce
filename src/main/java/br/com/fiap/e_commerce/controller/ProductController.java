package br.com.fiap.e_commerce.controller;

import br.com.fiap.e_commerce.model.Product;
import br.com.fiap.e_commerce.repository.ProductRepository;
import br.com.fiap.e_commerce.service.ProductService;
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
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ProductRepository productRepository;

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getProducts(){
        log.info("Listando todas as categorias");
        return service.getProducts();

    }

    @GetMapping("/price/greater-than/{price}")
    public Page<Product> findGreaterThan(@PathVariable Double price, Pageable pageable) {
        return productRepository.findByPriceGreaterThan(price, pageable);
    }

    @GetMapping("/price/less-than/{price}")
    public Page<Product> findLessThan(@PathVariable Double price, Pageable pageable) {
        return productRepository.findByPriceLessThan(price, pageable);
    }

    @GetMapping("/price/between")
    public Page<Product> findBetween(
            @RequestParam Double min,
            @RequestParam Double max,
            Pageable pageable) {
        return productRepository.findByPriceBetween(min, max, pageable);
    }

    @PostMapping
    //@ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) { // @RequestBody converte para objeto java
        log.info("Cadastrando categoria...");
        var products = service.addProduct(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){ //@PathVariable está relacionado ao caminho da URL

        return service.getProductById(id)
                .map(ResponseEntity::ok) // reference method
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product newProduct){
        Product product = service.updateProduct(id, newProduct);
        return ResponseEntity.ok(newProduct);
    }
}
