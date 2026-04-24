package com.pinpon.inventory.management.purchaseOrder.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {
    @Autowired
    private IPurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ISupplierRepository supplierRepository;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;


    //TODO: AGREGAR QUE SE GUARDE EL STOCK
    @Override
    public ResponseDTO createPurchaseOrder(Long productId, Long supplierId,CreateDTO createDTO) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not found"));
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new RuntimeException("supplier not found"));

        PurchaseOrder purchaseOrder = new PurchaseOrder();

        purchaseOrder.setProducts(product);
        purchaseOrder.setSupplier(supplier);
        purchaseOrder.setStock(createDTO.getStock());
        purchaseOrder.setPurchasePrice(createDTO.getPurchasePrice());

        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);

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

    //TODO: AGREGAR QUE SE QUITE EL STOCK ANTERIOR GUARDADO
    @Override
    public ResponseDTO updatePurchaseOrder(Long id, UpdateDTO updateDTO) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("purchase order not found"));

        PurchaseOrder existingPurchaseOrder = new PurchaseOrder();
        existingPurchaseOrder.setStock(updateDTO.getStock());
        existingPurchaseOrder.setPurchasePrice(updateDTO.getPurchasePrice());

        PurchaseOrder updatedPurchaseOrder = purchaseOrderRepository.save(existingPurchaseOrder);
        return purchaseOrderMapper.toDTO(updatedPurchaseOrder);
    }

    @Override
    public void deletePurchaseOrder(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}
