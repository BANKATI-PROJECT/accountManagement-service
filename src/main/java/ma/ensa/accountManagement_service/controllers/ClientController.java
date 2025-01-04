package ma.ensa.accountManagement_service.controllers;

import ma.ensa.accountManagement_service.entities.Client;
import ma.ensa.accountManagement_service.model.Portefeuille;
import ma.ensa.accountManagement_service.feign.PortefeuilleClientFeign;
import ma.ensa.accountManagement_service.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private PortefeuilleClientFeign portefeuilleClient;
    @Autowired
    private ClientService clientService;

    @GetMapping("/portefeuille/{clientId}")
    public ResponseEntity<Portefeuille> testFeign(@PathVariable Long clientId) {
        return ResponseEntity.ok(portefeuilleClient.getPortefeuilleByClientId(clientId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
