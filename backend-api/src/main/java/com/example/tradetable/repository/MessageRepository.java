package com.example.tradetable.repository;

import com.example.tradetable.entity.Message;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByProviderId(Long providerId);
    //Query to find message by comment substring
    @Query("SELECT m FROM Message m WHERE m.provider.id = :providerId AND m.comment LIKE %:substring%")
    List<Message> findByProviderIdAndCommentContaining(Long providerId, String substring);
}
