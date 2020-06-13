package com.karim.database;

import com.karim.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImagRepo extends JpaRepository<Image , Long> {
    Optional<Image> findByName(String name);

    Image findByIdAndName(long id , String name);
    void deleteByName(String name);
}
