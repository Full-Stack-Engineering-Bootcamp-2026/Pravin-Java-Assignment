package com.cdac.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.entities.File;
import com.cdac.repository.FileRepository;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;



@Service
@org.springframework.transaction.annotation.Transactional
@RequiredArgsConstructor
public class FileService {
  
    
    private final S3Client s3Client;

    @Value("${backblaze.bucketName}")
    private  String bucketName;

    @Value("${backblaze.endpoint}")
    private  String endpoint;

            private final FileRepository fileRepository;


            public File saveFile( MultipartFile file)  throws IOException{
                 if (file.isEmpty()) {
             throw new IOException("File is empty!");
        }


                String originalName = file.getOriginalFilename();
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + extension;

PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(uniqueFileName)
                .contentType(file.getContentType())
                .build();
                 s3Client.putObject(request,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

       
        String fileUrl = endpoint + "/" + bucketName + "/" + uniqueFileName;
        File filePersist = new File();
        filePersist.setFilePath(fileUrl);
       
        return  fileRepository.save(filePersist);
            
            }


}
