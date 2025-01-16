package web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
public class UsersController {
    private final UserServiceImpl userService;

    @Autowired
    public UsersController(UserServiceImpl userService) {
        this.userService = userService;

    }

    @GetMapping("/")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/delete{id}")
    public String removeUserById(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.removeUserById(id);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/edit")
    public String editUserById(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id).get(0);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/save/{id}/{name}/{lastName}/{age}")
    public String saveUser(
            @PathVariable("id") Long id,
            @PathVariable("name") String name,
            @PathVariable("lastName") String lastName,
            @PathVariable("age") Byte age,
            Model model) {

        userService.saveUserById(id, name, lastName, age);
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";

    }
    @GetMapping("/add-user")
    public String addUserForm() {
        return "add-user";
    }

    @PostMapping("/create")
    public String createNewUser(@ModelAttribute User user, Model model) {
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "redirect:/";
    }
}




