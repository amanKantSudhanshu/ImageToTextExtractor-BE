package com.vitraya.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vitraya.entity.ImageModel;
import com.vitraya.servicesImpl.ImageModelServiceImpl;

import net.sourceforge.tess4j.TesseractException;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*")
public class ImageModelController {

    @Autowired
    private ImageModelServiceImpl imageService;

    @PostMapping(value = "/saveImage", consumes = { "multipart/form-data" })
    public ResponseEntity<ImageModel> saveImage(@RequestPart("imageFile") MultipartFile file) {
        try {
        	ImageModel imageModel = new ImageModel();
			try {
				imageModel = imageService.processImage(file);
			} catch (SQLException e) {
				e.printStackTrace();
			}
            return new ResponseEntity<>(imageService.create(imageModel), HttpStatus.OK);
        } catch (IOException | TesseractException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/showAllImages")
    public ResponseEntity<List<ImageModel>> showAllImages() {
        return new ResponseEntity<>(imageService.getAllImages(), HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/delete/{id}")
	ResponseEntity<Void> delete(@PathVariable("id") final Long id){
    	imageService.deleteImage(id);
		return new ResponseEntity<>(HttpStatus.OK);
    }
}
