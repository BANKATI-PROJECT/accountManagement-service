package ma.ensa.accountManagement_service.services.authService;

import ma.ensa.accountManagement_service.entities.UserApp;
import ma.ensa.accountManagement_service.repositories.UserAppRepo;
import ma.ensa.accountManagement_service.responce.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserAppRepo userAppRepo;
    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthenticationResponse authenticate(UserApp request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserApp user = userAppRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        String token = jwtService.generateToken(user);
        return new AuthenticationResponse("authenticationSuccessful", token, user.getRole().name());

    }
}


