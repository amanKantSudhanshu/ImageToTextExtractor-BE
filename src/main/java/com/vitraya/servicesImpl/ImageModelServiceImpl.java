package com.vitraya.servicesImpl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.vitraya.entity.ImageModel;
import com.vitraya.repository.ImageModelRepository;
import com.vitraya.services.ImageModelService;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class ImageModelServiceImpl implements ImageModelService {
    @Autowired
    private ImageModelRepository imageRepository;

    @Override
	@Transactional
    public ImageModel create(ImageModel imageModel) {
        return imageRepository.save(imageModel);
    }

    @Override
	@Transactional
    public String extractText(byte[] imageBytes) throws TesseractException, IOException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\ASUDHANS\\Desktop\\Interview\\Vitraya-Softwares\\Tess4J-3.4.8-src\\Tess4J\\tessdata"); // Path to tessdata directory

        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        BufferedImage bufferedImage = ImageIO.read(bais);

        return tesseract.doOCR(bufferedImage);
    }

    
    private String extractBoldText(String text) {
        StringBuilder boldTextBuilder = new StringBuilder();
        for (String word : text.split("\\s+")) {
            if (word.equals(word.toUpperCase()) && word.length() > 1) {
                boldTextBuilder.append(word).append(" ");
            }
        }
        return boldTextBuilder.toString().trim();
    }

    @Override
	@Transactional
    public ImageModel processImage(MultipartFile file) throws IOException, TesseractException, SQLException {
    	ImageModel imageModel = new ImageModel();
        imageModel.setName(file.getOriginalFilename());
        byte[] imageBytes = file.getBytes();
        String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        imageModel.setImage_extracted(imageBase64);

        // Extracting all the text using OCR
        String extractedText = extractText(imageBytes);
        imageModel.setTextExtracted(extractedText);

        // Extracting bold text
        String boldText = extractBoldText(extractedText);
        imageModel.setBoldText(boldText);

        return imageModel;
    }

    @Override
	@Transactional
    public List<ImageModel> getAllImages() {
        return imageRepository.findAll();
    }

	@Override
	public void deleteImage(Long id) {
		ImageModel image = imageRepository.findById(id).orElse(null);
		imageRepository.delete(image);
		
	}
}
