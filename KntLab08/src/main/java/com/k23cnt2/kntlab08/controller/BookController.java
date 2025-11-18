package com.k23cnt2.kntlab08.controller;

import com.k23cnt2.kntlab08.entity.Book;
import com.k23cnt2.kntlab08.entity.BookStatus;
import com.k23cnt2.kntlab08.service.AuthorService;
import com.k23cnt2.kntlab08.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("allAuthor", authorService.getAllAuthors());
        model.addAttribute("allStatuses", BookStatus.values());
    }

    @GetMapping
    public String bookList(Model model) {
        model.addAttribute("books", bookService.getAllBook());
        model.addAttribute("newBook", new Book());
        return "book/index";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("newBook") Book book,
                             @RequestParam("imageFile") MultipartFile multipartFile,
                             Model model) {

        try {
            bookService.saveBook(book, multipartFile);
            model.addAttribute("success", "Thêm mới thành công!");
        }
        catch (Exception e) {
            model.addAttribute("error", "Lỗi: " + e.getMessage());

        }


        return "redirect:/book";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        if (book == null) {
            return "redirect:/error";
        }

        model.addAttribute("book", book);
        return "book/form";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @ModelAttribute("book") Book bookFromForm,
                             @RequestParam("imageFile") MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes) {
        try {
            bookService.updateBook(id, bookFromForm, multipartFile);
            redirectAttributes.addFlashAttribute("message", "Cập nhật thành công!");
        }
        catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }
        catch(Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }

        return "redirect:/book";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        if (book != null) {
            deleteFile(book.getImgUrl());
            bookService.delete(id);
        }
        return "redirect:/book";
    }

    private void deleteFile(String imgUrl) {
        if (imgUrl == null || imgUrl.isEmpty() || imgUrl.equals("default.jpg")) {
            return;
        }

        try {
            Path imagePath = Paths.get("uploads").resolve(imgUrl);
            if (Files.exists(imagePath)) {
                Files.delete(imagePath);
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi xóa file ảnh: " + imgUrl + e.getMessage());
        }
    }
}