package com.pinpon.inventory.management.inventory.service;

import com.pinpon.inventory.management.inventory.dto.CreateInventoryRequestDTO;
import com.pinpon.inventory.management.inventory.dto.ResponseInventoryDTO;
import com.pinpon.inventory.management.inventory.dto.UpdateInventoryRequestDTO;
import com.pinpon.inventory.management.inventory.entity.Inventory;
import com.pinpon.inventory.management.inventory.mapper.InventoryMapper;
import com.pinpon.inventory.management.inventory.repository.IInventoryRepository;
import com.pinpon.inventory.management.product.entity.Product;
import com.pinpon.inventory.management.product.repository.IProductRepository;
import com.pinpon.inventory.management.warehouse.entity.Warehouse;
import com.pinpon.inventory.management.warehouse.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService implements IInventoryService {
    @Autowired
    private IInventoryRepository inventoryRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IWarehouseRepository warehouseRepository;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    @Transactional
    public ResponseInventoryDTO createInventory(CreateInventoryRequestDTO requestDTO) {
        Product existingProduct = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("product not found"));

        Warehouse existingWarehouse = warehouseRepository.findById(requestDTO.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("warehouse not found"));

        Optional<Inventory> existing = inventoryRepository
                .findByProductIdAndWarehouseId(requestDTO.getProductId(), requestDTO.getWarehouseId());

        if(existing.isPresent()) {
            throw new RuntimeException("inventory already exists for this product and warehouse");
        }

        Inventory inventory = new Inventory();
        inventory.setProduct(existingProduct);
        inventory.setWarehouse(existingWarehouse);
        inventory.setStock(requestDTO.getStock());
        inventoryRepository.save(inventory);

        return inventoryMapper.toDTO(inventory);
    }

    @Override
    public List<ResponseInventoryDTO> findAll() {
        List<Inventory> inventorys = inventoryRepository.findAll();

        List<ResponseInventoryDTO> responseInventoryDTOs = new ArrayList<>();

        for(Inventory inventory : inventorys) {
            ResponseInventoryDTO responseInventoryDTO = new ResponseInventoryDTO(
                    inventory.getId(),
                    inventory.getProduct().getId(),
                    inventory.getProduct().getName(),
                    inventory.getWarehouse().getId(),
                    inventory.getWarehouse().getName(),
                    inventory.getStock()
            );
            responseInventoryDTOs.add(responseInventoryDTO);
        }
        return responseInventoryDTOs;
    }

    @Override
    public ResponseInventoryDTO findById(Long id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("inventory not found"));

        return new ResponseInventoryDTO(
                inventory.getId(),
                inventory.getProduct().getId(),
                inventory.getProduct().getName(),
                inventory.getWarehouse().getId(),
                inventory.getWarehouse().getName(),
                inventory.getStock()
        );
    }

    @Override
    @Transactional
    public ResponseInventoryDTO updateInventory(Long id, UpdateInventoryRequestDTO requestDTO) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("inventory not found"));

        inventory.setStock(requestDTO.getStock());
        inventoryRepository.save(inventory);
        return new  ResponseInventoryDTO(
                inventory.getId(),
                inventory.getProduct().getId(),
                inventory.getProduct().getName(),
                inventory.getWarehouse().getId(),
                inventory.getWarehouse().getName(),
                inventory.getStock()
        );
    }

    @Override
    @Transactional
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public ResponseInventoryDTO findByProductIdAndWarehouseId(Long productId, Long warehouseId) {
        Inventory inventory = inventoryRepository.findByProductIdAndWarehouseId(productId, warehouseId)
                .orElseThrow(( ) -> new RuntimeException("inventory not found"));
        return new  ResponseInventoryDTO(
                inventory.getId(),
                inventory.getProduct().getId(),
                inventory.getProduct().getName(),
                inventory.getWarehouse().getId(),
                inventory.getWarehouse().getName(),
                inventory.getStock()
        );
    }

    @Override
    public ResponseInventoryDTO findByProductName(String productName) {
        Inventory inventory = inventoryRepository.findByProductName(productName).orElseThrow(() -> new RuntimeException("inventory not found"));
        return new  ResponseInventoryDTO(
                inventory.getId(),
                inventory.getProduct().getId(),
                inventory.getProduct().getName(),
                inventory.getWarehouse().getId(),
                inventory.getWarehouse().getName(),
                inventory.getStock()
        );
    }

    @Override
    public ResponseInventoryDTO findByWarehouseName(String warehouseName) {
        Inventory inventory = inventoryRepository.findByWarehouseName(warehouseName).orElseThrow(() -> new RuntimeException("inventory not found"));

        return new   ResponseInventoryDTO(
                inventory.getId(),
                inventory.getProduct().getId(),
                inventory.getProduct().getName(),
                inventory.getWarehouse().getId(),
                inventory.getWarehouse().getName(),
                inventory.getStock()
        );
    }
}
