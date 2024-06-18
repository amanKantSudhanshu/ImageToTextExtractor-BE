package com.vitraya.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vitraya.entity.ImageModel;

import net.sourceforge.tess4j.TesseractException;

public interface ImageModelService {

	String extractText(byte[] imageBytes) throws TesseractException, IOException;
	ImageModel processImage(MultipartFile file) throws IOException, TesseractException, SQLException;
	List<ImageModel> getAllImages();
	ImageModel create(ImageModel imageModel);
	void deleteImage(Long id);
}
