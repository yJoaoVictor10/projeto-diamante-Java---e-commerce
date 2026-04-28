package br.com.fiap.e_commerce.repository;

import br.com.fiap.e_commerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByPriceGreaterThan(Double price, Pageable pageable);

    Page<Product> findByPriceLessThan(Double price, Pageable pageable);

    Page<Product> findByPriceBetween(Double min, Double max, Pageable pageable);
}
