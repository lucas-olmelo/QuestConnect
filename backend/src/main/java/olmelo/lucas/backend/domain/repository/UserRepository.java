package olmelo.lucas.backend.domain.repository;

import olmelo.lucas.backend.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
