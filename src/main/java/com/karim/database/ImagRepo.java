package com.karim.database;

import com.karim.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImagRepo extends JpaRepository<Image , Long> {
	// Find Optional Image By Name 
    Optional<Image> findByName(String name);
    // Find Image By Name And Id 
    Image findByIdAndName(long id , String name);
    // Delete Image By Name 
    void deleteByName(String name);
}
