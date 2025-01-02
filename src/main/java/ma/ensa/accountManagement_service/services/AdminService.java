package ma.ensa.accountManagement_service.services;

import ma.ensa.accountManagement_service.entities.Agent;
import ma.ensa.accountManagement_service.entities.PieceJoint;
import ma.ensa.accountManagement_service.enums.Role;
import ma.ensa.accountManagement_service.responce.AuthenticationResponse;
import ma.ensa.accountManagement_service.services.authService.JwtService;
import ma.ensa.accountManagement_service.util.RandomUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminService {

    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private EmailService emailService;
    private CloudinaryService cloudinaryService;
    private AgentService agentService;

    public AdminService(PasswordEncoder passwordEncoder, JwtService jwtService, EmailService emailService, CloudinaryService cloudinaryService, AgentService agentService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.cloudinaryService = cloudinaryService;
        this.agentService = agentService;
    }



    public AuthenticationResponse createAgent(
            String nom,
            String prenom,
            String numCIN,
            LocalDate dateNaissance,
            String adresse,
            String email,
            String numTelephone,
            String numImmatriculeRegisterCommerce,
            String numPattente,
            List<MultipartFile> pieceJoints) {

        // Vérifier si l'email est déjà utilisé
        Optional<Agent> existingAgent = agentService.findByAdresseEmail(email);
        if (existingAgent.isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        // Générer un username et un mot de passe
        String username = RandomUtil.generateRandomUsername();
        String password = RandomUtil.generateRandomPassword();

        // Créer l'agent
        Agent agent = new Agent();
        agent.setNom(nom);
        agent.setPrenom(prenom);
        agent.setEmail(email);
        agent.setNumTelephone(numTelephone);
        agent.setNumCIN(numCIN);
        agent.setDateNaissance(dateNaissance);
        agent.setAdresse(adresse);
        agent.setNumPattente(numPattente);
        agent.setNumImmatriculeRegisterCommerce(numImmatriculeRegisterCommerce);

        // Upload des pièces jointes
        List<PieceJoint> piecesJointes = new ArrayList<>();
        for (MultipartFile file : pieceJoints) {
            try {
                Map<String, Object> uploadResult = cloudinaryService.uplaod(file);
                PieceJoint pieceJoint = new PieceJoint();
                pieceJoint.setName((String) uploadResult.get("original_filename"));
                pieceJoint.setPieceJointUrl((String) uploadResult.get("url"));
                pieceJoint.setPieceJointId((String) uploadResult.get("public_id"));
                piecesJointes.add(pieceJoint);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload piece joint: " + file.getOriginalFilename(), e);
            }
        }
        agent.setPiecesJointes(piecesJointes);

        // Enregistrer l'agent et générer un JWT
        agent.setUsername(username);
        agent.setPassword(passwordEncoder.encode(password));
        agent.setRole(Role.AGENT);
        agent = agentService.save(agent);

        String token = jwtService.generateToken(agent);

        // Envoyer un email avec les informations de connexion
        String message = String.format(
                "Bonjour %s %s !\n\nVotre compte a été créé avec succès. Voici vos identifiants de connexion :\n\n" +
                        "Username : %s\nMot de passe : %s\n\nMerci de garder ces informations en sécurité.",
                agent.getPrenom(),
                agent.getNom(),
                username,
                password
        );
        emailService.sendEmail(agent.getEmail(), "Création de votre compte", message);

        // Retourner la réponse
        return new AuthenticationResponse("Agent created successfully", token, agent.getRole().name());
    }
}

