package ma.ensa.accountManagement_service.feign;

import ma.ensa.accountManagement_service.model.Portefeuille;
import ma.ensa.accountManagement_service.requests.CreatePortfeuilleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "portefeuille-service")
public interface PortefeuilleClientFeign {

    @GetMapping("/api/portefeuilles/getByClientId/{clientId}")
    Portefeuille getPortefeuilleByClientId(@PathVariable("clientId") Long clientId);

    @PutMapping("/api/portefeuilles/updateByClientId/{clientId}")
    Portefeuille updatePortefeuille(@PathVariable("clientId") Long clientId, @RequestBody Portefeuille portefeuille);

    @PostMapping("/api/portefeuilles/createPortefeuille")
    Portefeuille createPostefeuille(@RequestBody CreatePortfeuilleRequest request);
}
