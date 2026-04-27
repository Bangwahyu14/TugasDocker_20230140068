package com.example.pertemuan6.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.pertemuan6.model.User;
import com.example.pertemuan6.repository.UserRepository;

@Controller
public class UserController {

    private final String USERNAME = "admin";
    private final String PASSWORD = "20230140068";

    @Autowired
    private UserRepository userRepository;

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
        // Ambil data dari database, bukan dari list sementara
        model.addAttribute("userList", userRepository.findAll());
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
        // Simpan data ke database
        userRepository.save(new User(nama, nim, jenisKelamin));
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}