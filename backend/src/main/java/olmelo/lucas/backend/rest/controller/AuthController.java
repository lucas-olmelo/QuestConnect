package olmelo.lucas.backend.rest.controller;

import lombok.RequiredArgsConstructor;
import olmelo.lucas.backend.config.security.JwtService;
import olmelo.lucas.backend.domain.entity.User;
import olmelo.lucas.backend.domain.enums.UserRole;
import olmelo.lucas.backend.domain.repository.UserRepository;
import olmelo.lucas.backend.rest.dto.LoginDTO;
import olmelo.lucas.backend.rest.dto.RegisterDTO;
import olmelo.lucas.backend.rest.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO body){
        User user = repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException(("Usuário não encontrado")));
        if (encoder.matches(body.password(), user.getPassword())){
            String token = jwtService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO body){
        Optional<User> user = repository.findByEmail(body.email());
        if (user.isEmpty()){
            User newUser = new User();
            newUser.setEmail(body.email());
            newUser.setFirstName(body.firstName());
            newUser.setLastName(body.lastName());
            newUser.setUsername(body.username());
            newUser.setPassword(encoder.encode(body.password()));
            newUser.setRole(UserRole.USER);
            repository.save(newUser);
            String token = jwtService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
