package ma.ensa.accountManagement_service.dto;


import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public class CreateAgentRequest {

    private String nom;
    private String prenom;
    private String numCIN;
    private LocalDate dateNaissance;
    private String adresse;
    private String email;
    private String numTelephone;
    private String numImmatriculeRegisterCommerce;
    private String numPattente;
    private List<MultipartFile> pieceJoints;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumCIN() {
        return numCIN;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public String getNumImmatriculeRegisterCommerce() {
        return numImmatriculeRegisterCommerce;
    }

    public String getNumPattente() {
        return numPattente;
    }

    public List<MultipartFile> getPieceJoints() {
        return pieceJoints;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumCIN(String numCIN) {
        this.numCIN = numCIN;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public void setNumImmatriculeRegisterCommerce(String numImmatriculeRegisterCommerce) {
        this.numImmatriculeRegisterCommerce = numImmatriculeRegisterCommerce;
    }

    public void setNumPattente(String numPattente) {
        this.numPattente = numPattente;
    }

    public void setPieceJoints(List<MultipartFile> pieceJoints) {
        this.pieceJoints = pieceJoints;
    }

    public CreateAgentRequest() {
    }

    public CreateAgentRequest(String nom, String prenom, String numCIN, LocalDate dateNaissance, String adresse, String email, String numTelephone, String numImmatriculeRegisterCommerce, String numPattente, List<MultipartFile> pieceJoints) {
        this.nom = nom;
        this.prenom = prenom;
        this.numCIN = numCIN;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.email = email;
        this.numTelephone = numTelephone;
        this.numImmatriculeRegisterCommerce = numImmatriculeRegisterCommerce;
        this.numPattente = numPattente;
        this.pieceJoints = pieceJoints;
    }
}
