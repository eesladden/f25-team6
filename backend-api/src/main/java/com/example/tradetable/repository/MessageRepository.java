package com.example.tradetable.repository;

import com.example.tradetable.entity.Message;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByProviderIdAndCustomerId(Long providerId, Long customerId);
}
