package com.k23cnt2.kntlab08.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${app.upload.dir.author:uploads/authors}")
    private String uploadDirAuthor;

    @Value("${app.upload.dir.book:uploads/books}")
    private String uploadDirBook;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(uploadDirAuthor, "/uploads/authors/**", registry);
        exposeDirectory(uploadDirBook, "/uploads/books/**", registry);
    }

    private void exposeDirectory(String dirName, String pattern, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");

        registry.addResourceHandler(pattern)
                .addResourceLocations("file:/" + uploadPath + "/");
    }
}