package ma.ensa.accountManagement_service.feign;

import ma.ensa.accountManagement_service.model.Portefeuille;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "portefeuille-service",url = "https://311c-105-73-96-213.ngrok-free.app")
public interface PortefeuilleClientFeign {

    @GetMapping("api/portefeuilles/getByClientId/{clientId}")
    Portefeuille getPortefeuilleByClientId(@PathVariable("clientId") Long clientId);

    @PutMapping("api/portefeuilles/updateByClientId/{clientId}")
    Portefeuille updatePortefeuille(@PathVariable("clientId") Long clientId, @RequestBody Portefeuille portefeuille);
}
