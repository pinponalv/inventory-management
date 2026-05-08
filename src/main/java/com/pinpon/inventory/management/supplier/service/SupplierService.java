package com.pinpon.inventory.management.supplier.service;

import com.pinpon.inventory.management.supplier.dto.CreateSupplierRequestDTO;
import com.pinpon.inventory.management.supplier.dto.SupplierResponseDTO;
import com.pinpon.inventory.management.supplier.dto.UpdateSupplierRequestDTO;
import com.pinpon.inventory.management.supplier.entity.Supplier;
import com.pinpon.inventory.management.supplier.repository.ISupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService implements ISupplierService {
    @Autowired
    private ISupplierRepository supplierRepository;

    @Override
    public SupplierResponseDTO createSupplier(CreateSupplierRequestDTO requestDTO) {
        Supplier supplier = new Supplier();
        supplier.setName(requestDTO.getName());
        supplier.setDescription(requestDTO.getDescription());
        supplier.setEmail(requestDTO.getEmail());
        supplier.setPhone(requestDTO.getPhone());

        Supplier supplierCreated = supplierRepository.save(supplier);

        return new  SupplierResponseDTO(
                supplierCreated.getId(),
                supplierCreated.getName(),
                supplierCreated.getDescription(),
                supplierCreated.getEmail(),
                supplierCreated.getPhone()
        );
    }

    @Override
    public List<SupplierResponseDTO> findAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<SupplierResponseDTO> supplierResponseDTOS = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            SupplierResponseDTO supplierResponseDTO = new  SupplierResponseDTO(
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getDescription(),
                    supplier.getEmail(),
                    supplier.getPhone()
            );
            supplierResponseDTOS.add(supplierResponseDTO);
        }
        return supplierResponseDTOS;
    }

    @Override
    public SupplierResponseDTO findSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found with id " + id));
        return new   SupplierResponseDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getDescription(),
                supplier.getEmail(),
                supplier.getPhone()
        );
    }

    @Override
    public SupplierResponseDTO findSupplierByEmail(String email) {
        Supplier supplier = supplierRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Supplier not found with email " + email));

        if(supplier.getEmail() == null){
            throw  new RuntimeException("Supplier not found with email " + email);
        }

        return new SupplierResponseDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getDescription(),
                supplier.getEmail(),
                supplier.getPhone()
        );
    }

    @Override
    public SupplierResponseDTO updateSupplier(Long id, UpdateSupplierRequestDTO requestDTO) {
        Supplier existingSupplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found with id " + id));

        existingSupplier.setName(requestDTO.getName());
        existingSupplier.setDescription(requestDTO.getDescription());
        existingSupplier.setEmail(requestDTO.getEmail());
        existingSupplier.setPhone(requestDTO.getPhone());

        Supplier updatedSupplier = supplierRepository.save(existingSupplier);
        return new   SupplierResponseDTO(
                updatedSupplier.getId(),
                updatedSupplier.getName(),
                updatedSupplier.getDescription(),
                updatedSupplier.getEmail(),
                updatedSupplier.getPhone()
        );
    }

    @Override
    public void deleteSupplierById(Long id) {
        supplierRepository.deleteById(id);
    }
}
