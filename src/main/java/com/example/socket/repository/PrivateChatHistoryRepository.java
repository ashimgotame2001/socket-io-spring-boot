package com.example.socket.repository;

import com.example.socket.domain.PrivateChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface PrivateChatHistoryRepository extends JpaRepository<PrivateChatHistory, UUID> {
    Optional<PrivateChatHistory> findById(UUID chatCode);
}
