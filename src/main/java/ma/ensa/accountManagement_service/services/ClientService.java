package ma.ensa.accountManagement_service.services;

import ma.ensa.accountManagement_service.entities.Client;
import ma.ensa.accountManagement_service.repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

   private ClientRepo clientRepo;
   @Autowired
   private final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();;

    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client modifierPassword(Long clientId, String nouveauPassword, String username) {
        Optional<Client> optionalClient =clientRepo.findById(clientId);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setPassword(passwordEncoder.encode(nouveauPassword));
            client.setUsername(username);
            clientRepo.save(client);
            return client;
        }else {
            throw new RuntimeException("client introuvable avec l'ID : " + clientId);
        }

    }

    public Optional<Client> findByAdresseEmail(String email){
        return clientRepo.findByEmail(email);
    }


    public Client save(Client client) {
        return clientRepo.save(client);
    }
}
