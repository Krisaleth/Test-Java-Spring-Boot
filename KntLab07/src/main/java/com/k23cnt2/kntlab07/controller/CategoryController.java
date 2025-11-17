package com.k23cnt2.kntlab07.controller;

import com.k23cnt2.kntlab07.entity.Category;
import com.k23cnt2.kntlab07.service.CategoryService;
import com.k23cnt2.kntlab07.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("category_list", categoryList);
        model.addAttribute("allStatuses", Status.values());
        model.addAttribute("newCategory", new Category());
        return "category/index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return "redirect:/error";
        }
        model.addAttribute("category", category);
        model.addAttribute("allStatuses", Status.values());
        return "category/form";
    }

    @PatchMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("category") Category category) {
        category.setId(id);
        categoryService.save(category);
        return "redirect:/category";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/category";
    }


}
