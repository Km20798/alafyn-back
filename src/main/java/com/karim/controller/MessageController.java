package com.karim.controller;

import com.karim.services.MessageService;
import com.karim.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

// ------------------------ Anotations --------------------
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/messages")
// ------------------------- Start Controller -------------
public class MessageController {

	// ----------------- Attributes -----------------------	
    @Autowired
    private MessageService messageService ;

    // ----------------- Get All Messages ------------------
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessage(){
        List<Message> messages = messageService.getMessages();
        if (messages.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Message>>(messages , HttpStatus.OK);
    }

    //------------------------ Get Message by id ------------------
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable long id){
        Message message = messageService.getMessage(id);
        if (message == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Message>(message , HttpStatus.OK);
    }

    // ---------------------- Create new Message ------------------
    @PostMapping
    public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message){
        messageService.addMessage(message);
        return new ResponseEntity<Message>(message , HttpStatus.OK);
    }

    // --------------------- update Message by id --------------------
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable long id , @Valid @RequestBody Message message){
        Message currentMessage = messageService.getMessage(id);
        if (currentMessage == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        currentMessage.setMessage(message.getMessage());
        currentMessage.setEmail(message.getEmail());
        currentMessage.setDate(message.getDate());
        currentMessage.setUsername(message.getUsername());
        messageService.addMessage(currentMessage);
        return new ResponseEntity<Message>(currentMessage , HttpStatus.OK);
    }

    // --------------------- delete Message by id ---------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable long id){
        messageService.deleteMessage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // --------------------- delete All Messages -----------------
    @DeleteMapping
    public ResponseEntity<?> deleteAllMessage(){
        List<Message> messages = messageService.getMessages();
        if (messages.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        messageService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // -------------------------- End Controller ------------------
}
