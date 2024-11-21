package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.security.JwtUtil;
import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        } else {
            return jwtUtil.generateToken(user);
        }
    }

    public User register(String name, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        return userService.createUser(name, email, encodedPassword);
    }
}
