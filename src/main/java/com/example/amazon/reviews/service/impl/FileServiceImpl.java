package com.example.amazon.reviews.service.impl;

import com.example.amazon.reviews.service.FileService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<String> readFile(String path) {
        try {
            return new ArrayList<>(Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file with paths " + path);
        }
    }
}
