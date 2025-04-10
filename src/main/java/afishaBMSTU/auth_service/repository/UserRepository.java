package afishaBMSTU.auth_service.repository;

import afishaBMSTU.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);

    Optional<User> findByExternalId(UUID externalId);
}
