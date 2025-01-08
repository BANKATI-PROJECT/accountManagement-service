package ma.ensa.accountManagement_service.responce;


import ma.ensa.accountManagement_service.entities.Client;
import ma.ensa.accountManagement_service.entities.UserApp;

public class AuthenticationResponse {
    private String token;
    private String role;
    private String responseType;
    private Long idUser;
    private String username;
    private String nom;
    private String prenom;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public AuthenticationResponse(String responseType, String token, String role
            ,Long userId,String nom,String prenom,String username){
        this.responseType=responseType;
        this.token=token;
        this.role=role;
        this.nom=nom;
        this.prenom=prenom;
        this.username=username;
        this.idUser=userId;
    }

    public AuthenticationResponse() {
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}
