package com.karim.controller;

import com.karim.database.ImagRepo;
import com.karim.model.Image;

import java.io.*;

import com.karim.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class imageController {

    @Autowired
    private ImagRepo repo;
    @Autowired
    private ImageService imageService;

//------------------------------ Upload Image ---------------------------------
    @PostMapping(value = "/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        Image image= new Image(file.getOriginalFilename() , file.getContentType() , file.getBytes());
        repo.save(image);
        return new ResponseEntity<Image>(image , HttpStatus.OK);
    }

//---------------------------------- get Image -------------------------------
    @GetMapping("/get/{imageName}")
    public ResponseEntity<Image> getImage(@PathVariable String imageName){
        Image image = null ;
        final Optional<Image> retervedImage = repo.findByName(imageName);
        try {
             image = new Image(retervedImage.get().getName(), retervedImage.get().getType(), retervedImage.get().getPicBytes());
        }catch (Exception e){
            System.out.println("Image " + imageName + " not Found");
        }
        return new ResponseEntity<Image>(image , HttpStatus.OK);
    }

//--------------------------------------------- Delete Image -------------------------
    @DeleteMapping("/deleteImage/{name}")
    public ResponseEntity<?>  deleteImage( @PathVariable String name){
        Optional<Image> myimage = repo.findByName(name);
        Image image = myimage.get();
        if(image == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.delete(image);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
