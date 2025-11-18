package com.k23cnt2.kntlab08.service;

import com.k23cnt2.kntlab08.entity.Book;
import com.k23cnt2.kntlab08.repository.BookRepository;
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
public class BookService {
    private final BookRepository bookRepository;
    @Value("${app.upload.dir.book:uploads}")
    private String uploadDir;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
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

    public Book saveBook(Book book, MultipartFile file) throws IOException {
        String fileName = upload(file);
        book.setImgUrl(fileName);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book, MultipartFile file) throws IOException {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Quyển sách này không tồn tại!"));

        existingBook.setCode(book.getCode());
        existingBook.setQuantity(book.getQuantity());
        existingBook.setDescription(book.getDescription());
        existingBook.setName(book.getName());
        existingBook.setStatus(book.getStatus());

        if (file != null && !file.isEmpty()) {
            if (existingBook.getImgUrl() != null) {
                deleteFile(existingBook.getImgUrl());
            }
            String fileName = upload(file);
            existingBook.setImgUrl(fileName);
        }
        return bookRepository.save(existingBook);
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NullPointerException("Không tìm thấy sách"));
        String fileToDelete = book.getImgUrl();
        bookRepository.delete(book);
        bookRepository.flush();
        if (fileToDelete != null) {
            deleteFile(fileToDelete);
        }
    }
}