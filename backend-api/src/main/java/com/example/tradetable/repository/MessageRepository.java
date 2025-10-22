package com.example.tradetable.repository;

import com.example.tradetable.entity.Message;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByProviderId(Long providerId);
    //Still not working. Provider id and containing text search.
    @Query("SELECT m FROM Message m WHERE m.provider.id = :providerId AND LOWER(m.content) LIKE LOWER(CONCAT('%', :text, '%'))")
    List<Message> searchByProviderIdAndText(Long providerId, String text);
}
