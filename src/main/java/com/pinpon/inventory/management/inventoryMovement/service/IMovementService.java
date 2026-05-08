package com.pinpon.inventory.management.inventoryMovement.service;

import com.pinpon.inventory.management.inventoryMovement.dto.CreateMovementDTO;
import com.pinpon.inventory.management.inventoryMovement.dto.ResponseMovementDTO;
import com.pinpon.inventory.management.inventoryMovement.dto.UpdateMovementDTO;
import java.util.List;

public interface IMovementService {
    //ResponseMovementDTO createMovement(Long productId, Long warehouseId, Long userId, Long supplierId,CreateMovementDTO requestDTO);
    List<ResponseMovementDTO> findAllMovement();
    ResponseMovementDTO findMovementById(Long id);
    ResponseMovementDTO updateMovement(Long id, UpdateMovementDTO requestDTO);
    void  deleteMovement(Long id);
}
