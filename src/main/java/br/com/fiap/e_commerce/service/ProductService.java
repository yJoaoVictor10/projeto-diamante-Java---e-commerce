package br.com.fiap.e_commerce.service;

import br.com.fiap.e_commerce.model.Product;
import br.com.fiap.e_commerce.repository.CategoryRepository;
import br.com.fiap.e_commerce.repository.ProductRepository;
import br.com.fiap.e_commerce.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public Product addProduct(Product product){

        if (product.getCategory() != null && product.getCategory().getId() != null) {
            var category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category não encontrada"));
            product.setCategory(category);
        }

        if (product.getSupplier() != null && product.getSupplier().getId() != null) {
            var supplier = supplierRepository.findById(product.getSupplier().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier não encontrado"));
            product.setSupplier(supplier);
        }

        return productRepository.save(product);
    }

    // optional - projeto do null pointer exception
    public Optional<Product> getProductById(Integer id) { // Optional para evitar Null Pointer Exception.
        return productRepository.findById(id);
    }


    public void deleteProduct(Integer id) {
        var optionalProduct = getProductById(id);
        if(optionalProduct.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product não encontrado");
        }
        productRepository.deleteById(id);
    }

    public Product updateProduct(Integer id, Product newProduct) {
        var optionalProduct = getProductById(id);
        if(optionalProduct.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product não encontrado");
        }
        var product = optionalProduct.get();
        newProduct.setId(id);
        return productRepository.save(newProduct);
    }
}
