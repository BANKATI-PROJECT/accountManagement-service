package ma.ensa.accountManagement_service.controllers;

import ma.ensa.accountManagement_service.dto.CreateClientRequest;
import ma.ensa.accountManagement_service.responce.AuthenticationResponse;
import ma.ensa.accountManagement_service.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agent")
public class AgentController {
    @Autowired
    private AgentService agentService;


    @PostMapping("/{agentId}/client")
    public ResponseEntity<AuthenticationResponse> createClient(
            @PathVariable Long agentId,
            @RequestBody CreateClientRequest request) {
        AuthenticationResponse response = agentService.createClient(agentId, request);
        return ResponseEntity.ok(response);
    }
}
