package ma.ensa.accountManagement_service.services.authService;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import ma.ensa.accountManagement_service.entities.UserApp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    private final String SECRET_KEY = "810ef43b48ba0de6c8e6cf19f80463b9ab3911de2582a287601a3ad9e8a29491";

    public String generateToken(UserApp userApp) {
        Map<String, Object> claims = createClaims(userApp); // Ajoute les informations utilisateur dans les claims
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userApp.getUsername()) // Définit le sujet comme le nom d'utilisateur
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // Expiration après 24 heures
                .signWith(getSigninKey())
                .compact();
    }

    private Map<String, Object> createClaims(UserApp userApp) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userApp.getId());
        claims.put("nom", userApp.getNom());
        claims.put("prenom", userApp.getPrenom());
        claims.put("email", userApp.getEmail());
        claims.put("numTelephone", userApp.getNumTelephone());
        claims.put("role", userApp.getRole().name()); // Ajoute le rôle
        return claims;
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Map<String, Object> extractAllCustomClaims(String token) {
        Claims claims = extractAllClaims(token);
        claims.remove("sub");
        claims.remove("iat");
        claims.remove("exp");
        return claims;
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }
}