package ma.ensa.accountManagement_service.controllers;

import ma.ensa.accountManagement_service.model.Portefeuille;
import ma.ensa.accountManagement_service.feign.PortefeuilleClientFeign;
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

    @GetMapping("/portefeuille/{clientId}")
    public ResponseEntity<Portefeuille> testFeign(@PathVariable Long clientId) {
        return ResponseEntity.ok(portefeuilleClient.getPortefeuilleByClientId(clientId));
    }
}
