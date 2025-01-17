package ma.ensa.accountManagement_service.services;

import ma.ensa.accountManagement_service.entities.Client;
import ma.ensa.accountManagement_service.repositories.ClientRepo;
import ma.ensa.accountManagement_service.requests.CreatePortfeuilleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Client getClientById(Long id) {
        return clientRepo.findById(id).get();
    }


    //modifcation de save token:
    public void updateSaveToken(Long id, String saveToken) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable avec l'ID : " + id));

        client.setSaveToken(saveToken);
        clientRepo.save(client);
    }
    public List<Client> getAllCliient(){
        return clientRepo.findAll();
    }
}
