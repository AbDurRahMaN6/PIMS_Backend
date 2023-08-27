package CrudApplication.Personal_Application.repositories;

import CrudApplication.Personal_Application.models.Role;
import CrudApplication.Personal_Application.models.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(UserRole name);
}
