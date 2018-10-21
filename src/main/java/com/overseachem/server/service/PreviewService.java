package com.overseachem.server.service;

import com.overseachem.server.utils.CommandLine;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PreviewService {

    public Boolean upload(String uploadRoot, MultipartFile file) {
        System.out.println("[PreviewService->Upload("+ uploadRoot);
        Path path = null;
        path = Paths.get(uploadRoot,file.getOriginalFilename());
        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("file: " + path.getFileName().toString() + " upload complete!");
        return true;
    }

    public void renderByTexture(String blenderExePath, String blenderFilePath, String pythonFilePath, String textureFilePath, String renderOutputPath) {
        System.out.println(blenderExePath + " -b " + blenderFilePath + " -P " + pythonFilePath + " -- " + textureFilePath + " " + renderOutputPath);
        CommandLine.exeCmd(blenderExePath + " -b " + blenderFilePath + " -P " + pythonFilePath + " -- " + textureFilePath + " " + renderOutputPath);
    }
}
