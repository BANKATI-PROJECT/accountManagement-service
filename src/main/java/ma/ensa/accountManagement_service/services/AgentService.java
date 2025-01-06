package ma.ensa.accountManagement_service.services;

import ma.ensa.accountManagement_service.dto.CreateClientRequest;
import ma.ensa.accountManagement_service.entities.Agent;
import ma.ensa.accountManagement_service.entities.Client;
import ma.ensa.accountManagement_service.enums.Role;
import ma.ensa.accountManagement_service.repositories.AgentRepo;
import ma.ensa.accountManagement_service.responce.AuthenticationResponse;
import ma.ensa.accountManagement_service.services.authService.JwtService;
import ma.ensa.accountManagement_service.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgentService {

    private AgentRepo agentRepo;
    private ClientService clientService;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.credential}")
    private String notificationTopic;

    public AgentService(AgentRepo agentRepo, ClientService clientService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.agentRepo = agentRepo;
        this.clientService = clientService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Agent save(Agent agent){
        return agentRepo.save(agent);
    }

    public Optional<Agent> findAgentByid(Long id){ return agentRepo.findById(id);}


    public AuthenticationResponse createClient(Long agentId, CreateClientRequest request) {
        String username = RandomUtil.generateRandomUsername();
        String password = RandomUtil.generateRandomPassword();

        Optional<Client> existingAgent = clientService.findByAdresseEmail(request.getEmail());
        if (existingAgent.isPresent()) {
            throw new RuntimeException("Email is already in use");
        }
        Client client = new Client();
        client.setNom(request.getNom());
        client.setPrenom(request.getPrenom());
        client.setEmail(request.getEmail());
        client.setNumTelephone(request.getNumTelephone());
        client.setPlafond(request.getPlafond());

        client.setUsername(username);
        client.setPassword(passwordEncoder.encode(password));
        client.setRole(Role.CLIENT);

        Optional<Agent> optionalAgent = findAgentByid(agentId);
        String token;
        if (optionalAgent.isPresent()) {
            client = clientService.save(client);
            token = jwtService.generateToken(client);
            // Publier l'événement dans Kafka
            String event = String.format("{ \"email\": \"%s\", \"username\": \"%s\", \"password\": \"%s\" }",
                    client.getEmail(), username, password);
            kafkaTemplate.send(notificationTopic, event);

        } else {
            throw new RuntimeException("Agence introuvable avec l'ID : " + agentId);
        }

        return new AuthenticationResponse("Client Created successfully ",token,client.getRole().name());
    }

    public Optional<Agent> findByAdresseEmail(String email) {
        return agentRepo.findAgentByEmail(email);
    }
}
