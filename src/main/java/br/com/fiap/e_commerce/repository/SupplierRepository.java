package br.com.fiap.e_commerce.repository;

import br.com.fiap.e_commerce.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Page<Supplier> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
