package com.example.tradetable.repository;

import com.example.tradetable.entity.Message;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByProviderId(Long providerId);
    List<Message> findByCustomerId(Long customerId);
    @Query("SELECT m FROM Message m WHERE m.provider.id = :providerId AND LOWER(m.content) LIKE LOWER(CONCAT('%', :text, '%'))")
    List<Message> searchByProviderIdAndText(Long providerId, String text);
    @Query("SELECT m FROM Message m WHERE m.customer.id = :customerId AND LOWER(m.content) LIKE LOWER(CONCAT('%', :text, '%'))")
    List<Message> searchByCustomerIdAndText(Long customerId, String text);
    @Query("SELECT m FROM Message m WHERE m.provider.id = :providerId AND m.customer.id = :customerId")
    List<Message> findConversationBetweenProviderAndCustomer(Long providerId, Long customerId);
}
