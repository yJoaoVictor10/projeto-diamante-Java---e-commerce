package br.com.fiap.e_commerce.controller;

import br.com.fiap.e_commerce.model.Supplier;
import br.com.fiap.e_commerce.repository.SupplierRepository;
import br.com.fiap.e_commerce.service.SupplierService;
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
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SupplierRepository supplierRepository;

    @Autowired
    private SupplierService service;

    @GetMapping
    public List<Supplier> getSuppliers(){
        log.info("Listando todas as categorias");
        return service.getSuppliers();

    }

    @GetMapping("/by-name/{name}")
    public Page<Supplier> findByName(@PathVariable String name, Pageable pageable){
        return supplierRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @PostMapping
    //@ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) { // @RequestBody converte para objeto java
        log.info("Cadastrando categoria...");
        var suppliers = service.addSupplier(supplier);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(suppliers);
    }

    @GetMapping("{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Integer id){ //@PathVariable está relacionado ao caminho da URL

        return service.getSupplierById(id)
                .map(ResponseEntity::ok) // reference method
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Integer id){
        service.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Integer id, @RequestBody Supplier newSupplier){
        Supplier supplier = service.updateSupplier(id, newSupplier);
        return ResponseEntity.ok(newSupplier);
    }
}
