package com.karim.services;

import com.karim.database.ImagRepo;
import com.karim.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImagRepo repo ;

//    public void deleteImage(String name){
//
//            repo.deleteByName(name);
//        }
//    }
}
