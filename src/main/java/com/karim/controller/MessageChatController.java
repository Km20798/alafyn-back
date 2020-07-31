package com.karim.controller;

import com.karim.model.ChatMessage;
import com.karim.services.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

// ----------------- Anotations -----------------------
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/notifications")
// ----------------- Satrt Controller -----------------
public class MessageChatController {

	@Autowired
	private ChatMessageService service ;
	
	// ------------ Send Notifications ----------------
	
	@GetMapping("/{recever}")
	public ResponseEntity<List<ChatMessage>> getAllByRecever(@PathVariable String recever){
		List<ChatMessage> msgs = service.getAllByResever(recever);
		if(msgs.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ChatMessage>>(msgs , HttpStatus.OK);
			
	}
	
	@GetMapping("/{recever}/{seen}")
	public ResponseEntity<List<ChatMessage>> getAllByRecever(@PathVariable String recever , @PathVariable Boolean seen){
		List<ChatMessage> msgs = service.getSeenMessage(recever, seen);
		if(msgs.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ChatMessage>>(msgs , HttpStatus.OK);
			
	}
	
	@PostMapping
	public ResponseEntity<ChatMessage> addChatMessage(@RequestBody ChatMessage chatMessage){
		service.addNewMessage(chatMessage);
		return new ResponseEntity<ChatMessage>(chatMessage , HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id){
		service.update(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
    @MessageMapping("/chat")
    @SendTo("/topic/mes")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        System.out.println("send message " + chatMessage.getContent() + chatMessage.getSender());
        Date time = new Date();
        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setContent(chatMessage.getContent());
        chatMessage1.setSender(chatMessage.getSender());
        chatMessage1.setTime(time);
        return chatMessage1;
    }
    // ---------------- End Controller ------------------    
}