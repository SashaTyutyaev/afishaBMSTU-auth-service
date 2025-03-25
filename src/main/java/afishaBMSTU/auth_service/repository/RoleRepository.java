package afishaBMSTU.auth_service.repository;

import afishaBMSTU.auth_service.model.Role;
import afishaBMSTU.auth_service.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByUserRole(Roles userRole);
}
