package com.example.pertemuan6.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.pertemuan6.model.User; // Import model User kamu

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final String USERNAME = "admin";
    private final String PASSWORD = "20230140068"; // Wajib ganti pakai NIM Farhat

    // Tempat menyimpan data sementara (Temporary) menggunakan class User
    private List<User> userList = new ArrayList<>();

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username atau Password salah");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        // Kirim data list User ke halaman home
        model.addAttribute("userList", userList);
        return "home";
    }

    @GetMapping("/form")
    public String formPage() {
        return "form";
    }

    @PostMapping("/submit-form")
    public String submitForm(@RequestParam String nama,
                             @RequestParam String nim,
                             @RequestParam String jenisKelamin) {
        // Tambahkan data baru ke dalam list User
        userList.add(new User(nama, nim, jenisKelamin));
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}