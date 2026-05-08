package com.pinpon.inventory.management.warehouse.service;

import com.pinpon.inventory.management.warehouse.dto.CreateWarehouseDTO;
import com.pinpon.inventory.management.warehouse.dto.ResponseWarehouseDTO;
import com.pinpon.inventory.management.warehouse.dto.UpdateWarehouseDTO;
import com.pinpon.inventory.management.warehouse.entity.Warehouse;
import com.pinpon.inventory.management.warehouse.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService implements IWarehouseService {
    @Autowired
    private IWarehouseRepository warehouseRepository;

    @Override
    public ResponseWarehouseDTO createWarehouse(CreateWarehouseDTO requestDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(requestDTO.getName());
        warehouse.setAddress(requestDTO.getAdress());

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);


        return new ResponseWarehouseDTO(
                savedWarehouse.getId(),
                savedWarehouse.getName(),
                savedWarehouse.getAddress()
        );
    }

    @Override
    public ResponseWarehouseDTO updateWarehouse(Long id, UpdateWarehouseDTO requestDTO) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("warehouse not found id " + id));

        if(requestDTO.getName() != null) {
            warehouse.setName(requestDTO.getName());
        }

        if(requestDTO.getAdress() != null) {
            warehouse.setAddress(requestDTO.getAdress());
        }
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return new  ResponseWarehouseDTO(
                savedWarehouse.getId(),
                savedWarehouse.getName(),
                savedWarehouse.getAddress()
        );
    }

    @Override
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    @Override
    public List<ResponseWarehouseDTO> getWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        List<ResponseWarehouseDTO> responseWarehouses = new ArrayList<>();

        for(Warehouse warehouse : warehouses) {
            ResponseWarehouseDTO responseWarehouseDTO = new ResponseWarehouseDTO(
                    warehouse.getId(),
                    warehouse.getName(),
                    warehouse.getAddress()
            );
            responseWarehouses.add(responseWarehouseDTO);
        }
        return responseWarehouses;
    }

    @Override
    public ResponseWarehouseDTO getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("warehouse not found id " + id));

        return new  ResponseWarehouseDTO(
                warehouse.getId(),
                warehouse.getName(),
                warehouse.getAddress()
        );
    }

    @Override
    public ResponseWarehouseDTO getWarehouseByName(String name) {
        Warehouse warehouse = warehouseRepository.getWarehouseByName(name);

        if(warehouse == null) {
            new RuntimeException("this name of warehouse no exist");
        }

        return new   ResponseWarehouseDTO(
                warehouse.getId(),
                warehouse.getName(),
                warehouse.getAddress()
        );
    }
}
