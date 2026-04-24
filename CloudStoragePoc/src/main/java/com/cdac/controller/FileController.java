package com.cdac.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.entities.File;
import com.cdac.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<File> uploadFile(
            @RequestParam("file") MultipartFile file) throws IOException {

         System.out.println("hello");
       

       

        return ResponseEntity.ok(fileService.saveFile(file));
    }
}