package ma.ensa.accountManagement_service.controllers;



import ma.ensa.accountManagement_service.dto.LoginRequest;
import ma.ensa.accountManagement_service.entities.UserApp;
import ma.ensa.accountManagement_service.responce.AuthenticationResponse;
import ma.ensa.accountManagement_service.services.authService.AuthService;
import ma.ensa.accountManagement_service.services.authService.JwtService;
import ma.ensa.accountManagement_service.services.authService.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private AuthService authService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsImpl loadUserByUsername;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        UserApp userApp = new UserApp();
        userApp.setUsername(loginRequest.getUsername());
        userApp.setPassword(loginRequest.getPassword());
        AuthenticationResponse response = authService.authenticate(userApp);
        System.out.println("Message: " + response.getResponseType());
        System.out.println("Token: " + response.getToken());
        System.out.println("Role: " + response.getRole());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
        String username = jwtService.extractUsername(token);
        UserDetails userDetails = loadUserByUsername.loadUserByUsername(username);
        boolean isValid = jwtService.isValid(token, userDetails);

        return isValid
                ? ResponseEntity.ok("Token is valid")
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }


}
