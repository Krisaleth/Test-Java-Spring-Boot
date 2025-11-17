package com.k23cnt2.kntlab07.controller;

import com.k23cnt2.kntlab07.entity.Book;
import com.k23cnt2.kntlab07.entity.BookStatus;
import com.k23cnt2.kntlab07.service.BookService;
import com.k23cnt2.kntlab07.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("allStatuses", BookStatus.values());
        model.addAttribute("newBook", new Book());
        return "book/index";
    }


    @PostMapping("/save")
    public String addNew(@ModelAttribute("book") Book book, @RequestParam("imageFile") MultipartFile multipartFile) throws IOException {

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            String fileExtension = "";
            try {
                fileExtension = fileName.substring(fileName.lastIndexOf("."));
            }
            catch (Exception e) {

            }

            String newFileName = fileName + fileExtension;

            book.setImgUrl(newFileName);
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(newFileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException e) {
                throw new IOException("Không thể lưu file: " + newFileName,e);
            }
        }
        else {
            if (book.getId() == null) {
                book.setImgUrl("default.jpg");
            }
        }

        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/error";
        }
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("allStatuses", BookStatus.values());
        model.addAttribute("book", book);
        return "book/form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("book") Book bookFromForm,
                         @RequestParam("imageFile") MultipartFile multipartFile) throws IOException {

        Book existingBook = bookService.findById(id);

        existingBook.setName(bookFromForm.getName());
        existingBook.setPrice(bookFromForm.getPrice());
        existingBook.setAuthor(bookFromForm.getAuthor());
        existingBook.setCategory(bookFromForm.getCategory());
        existingBook.setStatus(bookFromForm.getStatus());

        if (!multipartFile.isEmpty()) {
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String fileExtension = "";
            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            } catch (Exception e) { /* Bỏ qua */ }

            String newFileName = UUID.randomUUID().toString() + fileExtension;

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(newFileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

                existingBook.setImgUrl(newFileName);

            } catch (IOException e) {
                throw new IOException("Không thể lưu file mới: " + newFileName, e);
            }
        }
        bookService.save(existingBook);

        return "redirect:/book";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        Book book = bookService.findById(id);

        if (book == null) {
            return "redirect:/error";
        }
        try {
            if (book.getImgUrl() != null && !book.getImgUrl().isEmpty()) {
                String uploadDir = "uploads/";
                Path imagePath = Paths.get(uploadDir).resolve(book.getImgUrl());

                if (Files.exists(imagePath)) {
                    Files.delete(imagePath);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi xóa file ảnh: " + book.getImgUrl() + e.getMessage());
        }

        bookService.delete(id);

        return "redirect:/book";
    }

}
