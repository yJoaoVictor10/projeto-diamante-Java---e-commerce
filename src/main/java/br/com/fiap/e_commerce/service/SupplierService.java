package br.com.fiap.e_commerce.service;

import br.com.fiap.e_commerce.model.Supplier;
import br.com.fiap.e_commerce.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getSuppliers(){
        return supplierRepository.findAll();
    }

    public Supplier addSupplier(Supplier supplier){
        return supplierRepository.save(supplier);

    }

    // optional - projeto do null pointer exception
    public Optional<Supplier> getSupplierById(Integer id) { // Optional para evitar Null Pointer Exception. Representa um valor que pode existir ou não
        return supplierRepository.findById(id);
       /*return repository.stream()
                .filter(supplier -> supplier.getId().equals(id) )
                .findFirst(); // retornar o primeiro elemento de um stream que satisfaça a condição aplicada anteriormente*/
    }


    public void deleteSupplier(Integer id) {
        var optionalSupplier = getSupplierById(id);
        if(optionalSupplier.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier não encontrado");
        }
        supplierRepository.deleteById(id);
    }

    public Supplier updateSupplier(Integer id, Supplier newSupplier) {
        var optionalSupplier = getSupplierById(id);
        if(optionalSupplier.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier não encontrado");
        }
        var supplier = optionalSupplier.get();
        //BeanUtils.copyProperties(newSupplier, supplier, "id"); //Útil para copiar um objeto e jogar em outro, o terceiro parâmetro (opcional) serve para ignorar uma propriedade
        newSupplier.setId(id);
        return supplierRepository.save(newSupplier);
    }
}
