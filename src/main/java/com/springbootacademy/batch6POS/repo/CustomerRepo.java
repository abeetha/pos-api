package com.springbootacademy.batch6POS.repo;

import com.springbootacademy.batch6POS.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
     Optional<Customer> findByNicEquals(String nic);

     List<Customer> findAllByActiveStateEquals(boolean status);

     List<Customer> findAllByCustomerNameIsAndActiveStateIs(String name,boolean status);
}
