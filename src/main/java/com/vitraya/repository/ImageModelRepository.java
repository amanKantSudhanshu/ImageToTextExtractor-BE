package com.vitraya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitraya.entity.ImageModel;

@Repository
public interface ImageModelRepository extends JpaRepository<ImageModel, Long> {
}
