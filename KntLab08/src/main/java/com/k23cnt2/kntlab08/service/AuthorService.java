package com.k23cnt2.kntlab08.service;

import com.k23cnt2.kntlab08.entity.Author;
import com.k23cnt2.kntlab08.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Value("${app.upload.dir.author:uploads}")
    private String uploadDir;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    public String upload(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String fileExtension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            fileExtension = fileName.substring(dotIndex);
        }
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }
    public void deleteFile(String fileName) {
        try {
            Path file = Paths.get(uploadDir).resolve(fileName);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            System.err.println("Không xoá được ảnh cũ: " + e.getMessage());
        }
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author saveAuthor(Author author, MultipartFile multipartFile) throws IOException {
        String fileName = upload(multipartFile);
        author.setImgUrl(fileName);
        return authorRepository.save(author);


    }

    public Author updateAuthor(Long id, Author author, MultipartFile file) throws IOException {
        Author existingAuthor = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy tác giả!"));

        existingAuthor.setCode(author.getCode());
        existingAuthor.setName(author.getName());
        existingAuthor.setAddress(author.getAddress());
        existingAuthor.setDescription(author.getDescription());
        existingAuthor.setPhone(author.getPhone());
        existingAuthor.setEmail(author.getEmail());
        existingAuthor.setStatus(author.getStatus());

        if (file != null && !file.isEmpty()) {
            if (existingAuthor.getImgUrl() != null) {
                deleteFile(existingAuthor.getImgUrl());
            }
            String newFileName = upload(file);
            existingAuthor.setImgUrl(newFileName);
        }
        return authorRepository.save(existingAuthor);
     }

    public void deleteAuthorById(Long id){
        Author existingAuthor = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy tác giả!"));
        String fileToDelete = existingAuthor.getImgUrl();
        authorRepository.delete(existingAuthor);
        authorRepository.flush();
        if (fileToDelete != null) {
            deleteFile(fileToDelete);
        }
    }
}
