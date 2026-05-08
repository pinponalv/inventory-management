package com.pinpon.inventory.management.purchaseOrder.service;

import com.pinpon.inventory.management.inventory.entity.Inventory;
import com.pinpon.inventory.management.inventory.repository.IInventoryRepository;
import com.pinpon.inventory.management.inventoryMovement.entity.InventoryMovement;
import com.pinpon.inventory.management.inventoryMovement.entity.enums.TypeMovement;
import com.pinpon.inventory.management.inventoryMovement.repository.IMovementRepository;
import com.pinpon.inventory.management.product.entity.Product;
import com.pinpon.inventory.management.product.repository.IProductRepository;
import com.pinpon.inventory.management.purchaseOrder.dto.CreateDTO;
import com.pinpon.inventory.management.purchaseOrder.dto.ResponseDTO;
import com.pinpon.inventory.management.purchaseOrder.dto.UpdateDTO;
import com.pinpon.inventory.management.purchaseOrder.entity.PurchaseOrder;
import com.pinpon.inventory.management.purchaseOrder.mapper.PurchaseOrderMapper;
import com.pinpon.inventory.management.purchaseOrder.repository.IPurchaseOrderRepository;
import com.pinpon.inventory.management.supplier.entity.Supplier;
import com.pinpon.inventory.management.supplier.repository.ISupplierRepository;
import com.pinpon.inventory.management.user.entity.User;
import com.pinpon.inventory.management.user.repository.IUserRepository;
import com.pinpon.inventory.management.warehouse.entity.Warehouse;
import com.pinpon.inventory.management.warehouse.repository.IWarehouseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {
    @Autowired
    private IPurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ISupplierRepository supplierRepository;

    @Autowired
    private IMovementRepository movementRepository;

    @Autowired
    private IWarehouseRepository warehouseRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IInventoryRepository inventoryRepository;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;


    @Transactional
    @Override
    public ResponseDTO createPurchaseOrder(Long userId,Long warehouseId,Long productId, Long supplierId,CreateDTO createDTO) {
        //get entitys
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not found"));
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new RuntimeException("supplier not found"));
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(() -> new RuntimeException("warehouse not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        //save purchase
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProducts(product);
        purchaseOrder.setSupplier(supplier);
        purchaseOrder.setStock(createDTO.getStock());
        purchaseOrder.setPurchasePrice(createDTO.getPurchasePrice());
        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);

        //create movement
        InventoryMovement movement = new InventoryMovement();
        movement.setProduct(product);
        movement.setWarehouse(warehouse);
        movement.setUser(user);
        movement.setSupplier(supplier);
        movement.setQuantity(createDTO.getStock());
        movement.setTypeMovement(TypeMovement.IN);
        movementRepository.save(movement);


        //update inventory
        Optional<Inventory> optionalInventory =
                inventoryRepository.findByProductIdAndWarehouseId(product.getId(), warehouse.getId());

        if(optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();
            inventory.setStock(inventory.getStock() +  createDTO.getStock());
            inventoryRepository.save(inventory);
        }else {
            Inventory inventory = new Inventory();
            inventory.setProduct(product);
            inventory.setWarehouse(warehouse);
            inventory.setStock(createDTO.getStock());
            inventoryRepository.save(inventory);
        }


        return purchaseOrderMapper.toDTO(savedPurchaseOrder);
    }

    @Override
    public List<ResponseDTO> getPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        return purchaseOrders.stream().map(purchaseOrderMapper::toDTO).toList();
    }

    @Override
    public ResponseDTO getPurchaseOrderById(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("purchase order not found"));
        return purchaseOrderMapper.toDTO(purchaseOrder);
    }


    @Override
    public ResponseDTO updatePurchaseOrder(Long id, UpdateDTO updateDTO) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("purchase order not found"));


        if(updateDTO.getStock() != null) {
            purchaseOrder.setStock(updateDTO.getStock());
        }
        if (updateDTO.getPurchasePrice() != null) {
            purchaseOrder.setPurchasePrice(updateDTO.getPurchasePrice());
        }


        PurchaseOrder updatedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        return purchaseOrderMapper.toDTO(updatedPurchaseOrder);
    }

    @Override
    public void deletePurchaseOrder(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}
