package com.pinpon.inventory.management.purchaseOrder.service;

import com.pinpon.inventory.management.purchaseOrder.dto.ResponseDTO;
import com.pinpon.inventory.management.purchaseOrder.dto.CreateDTO;
import com.pinpon.inventory.management.purchaseOrder.dto.UpdateDTO;

import java.util.List;

public interface IPurchaseOrderService {
    ResponseDTO createPurchaseOrder(Long productId, Long supplierId, CreateDTO createDTO);
    List<ResponseDTO> getPurchaseOrders();
    ResponseDTO getPurchaseOrderById(Long id);
    ResponseDTO updatePurchaseOrder(Long id, UpdateDTO updateDTO);
    void deletePurchaseOrder(Long id);
}
