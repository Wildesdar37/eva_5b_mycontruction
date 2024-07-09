package myconstruction.example.myconstruction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import myconstruction.example.myconstruction.models.User;
import myconstruction.example.myconstruction.repositories.UserRepository;


@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User createUser(String name, String email, String password) {
    if (userRepository.findByUsername(name) != null) {
      return null;
    }

    if (userRepository.findByEmail(email) != null) {
      return null;
    }

    User user = new User();
    user.setUsername(email);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    user.setEnabled(true);
    return userRepository.save(user);
  }

}
