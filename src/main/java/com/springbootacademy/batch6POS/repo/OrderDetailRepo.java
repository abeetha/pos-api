package com.springbootacademy.batch6POS.repo;

import com.springbootacademy.batch6POS.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetails,Integer> {
}
