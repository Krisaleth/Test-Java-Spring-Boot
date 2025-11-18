package com.k23cnt2.kntlab08.controller;

import com.k23cnt2.kntlab08.entity.Author;
import com.k23cnt2.kntlab08.entity.AuthorStatus;
import com.k23cnt2.kntlab08.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("allStatus")
    public AuthorStatus[] populateAuthorStatus() {
        return AuthorStatus.values();
    }

    @GetMapping
    public String authorList(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("newAuthor", new Author());
        return "author/index";
    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute("author") Author author, @RequestParam("imageFile") MultipartFile file, Model model) {
        try {
            authorService.saveAuthor(author, file);
            model.addAttribute("success", "Thêm mới thành công");
        }
        catch(Exception e) {
            model.addAttribute("error", "Lỗi: " + e.getMessage());
        }
        return "redirect:/author";
    }

    @GetMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return "redirect:/error";
        }

        model.addAttribute("allStatuses", AuthorStatus.values());
        model.addAttribute("author", author);
        return "author/form";
    }

    @PostMapping("/edit/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @ModelAttribute("author") Author authorFromForm,
                               @RequestParam("imageFile") MultipartFile multipartFile,
                               RedirectAttributes redirectAttributes) {
        try {
            authorService.updateAuthor(id, authorFromForm, multipartFile);
            redirectAttributes.addFlashAttribute("message", "Cập nhật thành công!");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Lỗi upload ảnh: " + e.getMessage());
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/author";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return "redirect:/author";
    }
}