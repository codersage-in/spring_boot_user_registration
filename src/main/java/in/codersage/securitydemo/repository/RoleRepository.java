package in.codersage.securitydemo.repository;

import in.codersage.securitydemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role_student);
}
