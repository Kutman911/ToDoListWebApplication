package kg.kut.os.controller.secured;

import kg.kut.os.entity.User;
import kg.kut.os.entity.UserRole;
import kg.kut.os.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class PrivateAdminController {
    private final UserService userService;

    @Autowired
    public PrivateAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getManagementPage(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("userName", user.getName());

        if (user.isSuperAdmin()) {
            List<User> candidatesToDelete = userService.findAllByRoleIn(List.of(UserRole.USER, UserRole.ADMIN));
            List<User> candidatesToUpgrade = candidatesToDelete.stream().
                    filter(User::isSimpleUser).toList();

            model.addAttribute("candidatesToDelete", candidatesToDelete);
            model.addAttribute("candidatesToUpgrade", candidatesToUpgrade);
        } else {
            List<User> candidatesToDelete = userService.findAllByRoleIn(List.of(UserRole.USER));
            model.addAttribute("candidatesToDelete", candidatesToDelete);
        }

        return "private/admin/management-page";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam long id) {
        User currentUser = userService.getCurrentUser();
        Optional<User> userToBeDeletedOptional = userService.findById(id);

        if (userToBeDeletedOptional.isEmpty()) {
            return "redirect:/admin";
        }

        User userToBeDeleted = userToBeDeletedOptional.get();


        if (!currentUser.isSuperAdmin() && userToBeDeleted.isAdmin()) {
            return "redirect:/admin";
        }

        if (userToBeDeleted.isSuperAdmin()) {
            return "redirect:/admin";
        }

        if (currentUser.isAdmin() && userToBeDeleted.isSuperAdmin()) {
            return "redirect:/admin";
        }

        if (currentUser.getId() == id) {
            return "redirect:/admin?error=CannotDeleteSelf";
        }

        userService.deleteById(id);
        return "redirect:/admin";
    }
}
