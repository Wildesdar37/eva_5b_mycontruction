package myconstruction.example.myconstruction.repositories;

import org.springframework.data.repository.CrudRepository;

import myconstruction.example.myconstruction.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

  User findByUsername(String username);
  User findByEmail(String email);
  
}
