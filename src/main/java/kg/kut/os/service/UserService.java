package kg.kut.os.service;

import jakarta.transaction.Transactional;
import kg.kut.os.entity.User;
import kg.kut.os.entity.UserRole;
import kg.kut.os.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public void updateRole(long id, UserRole newRole) {
        userRepository.updateRole(newRole, id);
    }

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new IllegalArgumentException("User with " + email + " not found"));
    }

    public List<User> findAllByRoleIn(Iterable<UserRole> roles) {
        return userRepository.findAllByRoleInOrderById(roles);
    }
}
