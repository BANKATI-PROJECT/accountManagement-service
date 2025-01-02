package ma.ensa.accountManagement_service.dto;


import ma.ensa.accountManagement_service.enums.TypeCompte;


public class CreateClientRequest {

    private String nom;
    private String prenom;
    private String email;
    private String numTelephone;
    private TypeCompte plafond;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public TypeCompte getPlafond() {
        return plafond;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public void setPlafond(TypeCompte plafond) {
        this.plafond = plafond;
    }
}
