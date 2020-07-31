package com.karim.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karim.database.ChatMessageRepo;
import com.karim.model.ChatMessage;

@Service
public class ChatMessageService {

	@Autowired
	private ChatMessageRepo repo ;
	
	public ChatMessage addNewMessage(ChatMessage chatMessage) {
		return repo.save(chatMessage);
	}
	
	public ChatMessage getOne(long id) {
		return repo.findById(id).get();
	}
	
	public List<ChatMessage> getAllByResever(String recever){
		return repo.findByRec(recever);
	}
	
	public List<ChatMessage> getSeenMessage(String rec , Boolean seen){
		return repo.findByRecAndSeen(rec, seen);
	}
	
	public ChatMessage update(long id) {
		ChatMessage c = repo.findById(id).get();
		c.setSeen(true);
		repo.saveAndFlush(c);
		return c ;
	}	
}
