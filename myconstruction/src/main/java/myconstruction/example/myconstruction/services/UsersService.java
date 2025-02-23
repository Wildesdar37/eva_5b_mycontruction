package myconstruction.example.myconstruction.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import myconstruction.example.myconstruction.models.User;
import myconstruction.example.myconstruction.repositories.UserRepository;

@Service
public class UsersService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UsersService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        user.isEnabled(), true, true, true, authorities);
  }
}