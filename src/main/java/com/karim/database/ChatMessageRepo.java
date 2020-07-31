package com.karim.database;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.karim.model.ChatMessage;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long> {
	List<ChatMessage> findByRec(String rec);
	List<ChatMessage> findByRecAndSeen(String rec , Boolean seen);
}
