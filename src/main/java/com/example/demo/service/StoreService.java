package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private PetRepository petRepository;

    public Optional<Order> save(Order order) {
        if (!storeRepository.existsById(order.getId()) && petRepository.existsById(order.getId())){
            storeRepository.save(order);
        }
        return Optional.of(order);
    }

    public Optional<Order> getOrder(int orderId) {
        return storeRepository.getById(orderId);
    }

    public void delete(int orderId) {
        Optional<Order> byId = storeRepository.getById(orderId);
        if (byId.isPresent()) {
            storeRepository.delete(byId.get());
        }
    }
}
