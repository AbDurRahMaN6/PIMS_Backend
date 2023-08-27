package CrudApplication.Personal_Application.repositories;

import CrudApplication.Personal_Application.models.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DetailsRepository extends MongoRepository<UserDetails, String> {

//    List<UserDetails> findByContaining(String id);
    List<UserDetails> findByName(String name);
}