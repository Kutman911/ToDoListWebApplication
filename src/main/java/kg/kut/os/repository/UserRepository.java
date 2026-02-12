package kg.kut.os.repository;

import kg.kut.os.entity.User;
import kg.kut.os.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRoleInOrderById(Iterable<UserRole> roles);
    Optional<User> findByEmailIgnoreCase(String email);
    @Modifying
    @Query("update User u set u.role = :role where u.id = :id")
    void updateRole(@Param("role") UserRole newRole, long id);
}
