package bg.softuni.movie.web;

import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users/roles")
public class UserRoleController {

    private final UserService userService;

    public UserRoleController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/add")
    public String grantAuthorityForm(Model model) {

        if (!model.containsAttribute("users")) {
            model.addAttribute("users", userService.getAllUsers());
        }

        return "add-role";
    }

    @PostMapping("/add")
    public String grantAuthority(@RequestParam(value = "username", required = false) Long userId,
                                 @RequestParam(value = "role", required = false) String newRole,
                                 RedirectAttributes redirectAttributes) {

        if (userId == null || newRole == null) {
            redirectAttributes.addFlashAttribute("errors", "Set username and role");
            return "redirect:/users/roles/add";
        }

        userService.grantAuthority(userId, newRole);
        UserEntity userEntity = userService.findUserById(userId);

        redirectAttributes.addFlashAttribute("success",
                String.format("The %s role is assigned to: %s", userEntity.getUsername(), newRole.toUpperCase()));

        return "redirect:/users/roles/add";
    }
}
