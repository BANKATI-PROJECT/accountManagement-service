package ma.ensa.accountManagement_service.controllers;

import ma.ensa.accountManagement_service.responce.AuthenticationResponse;
import ma.ensa.accountManagement_service.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @PostMapping("/agent")
    public ResponseEntity<AuthenticationResponse> createAgent(@RequestParam String nom,
                                                              @RequestParam String prenom,
                                                              @RequestParam String numCIN,
                                                              @RequestParam LocalDate dateNaissance,
                                                              @RequestParam String adresse,
                                                              @RequestParam String email,
                                                              @RequestParam String numTelephone,
                                                              @RequestParam String numImmatriculeRegisterCommerce,
                                                              @RequestParam String numPattente,
                                                              @RequestParam List<MultipartFile> pieceJoints) {
        AuthenticationResponse response = adminService.createAgent(nom, prenom, numCIN, dateNaissance, adresse, email, numTelephone, numImmatriculeRegisterCommerce, numPattente, pieceJoints);
        return ResponseEntity.ok(response);
   }





}
