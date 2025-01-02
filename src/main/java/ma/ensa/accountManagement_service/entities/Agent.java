package ma.ensa.accountManagement_service.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;


import java.time.LocalDate;
import java.util.List;


@Entity
public class Agent extends UserApp {
    private String numCIN;
    private LocalDate dateNaissance;
    private String adresse;
    private String numImmatriculeRegisterCommerce;
    private String numPattente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PieceJoint> piecesJointes;

    public void setNumCIN(String numCIN) {
        this.numCIN = numCIN;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNumImmatriculeRegisterCommerce(String numImmatriculeRegisterCommerce) {
        this.numImmatriculeRegisterCommerce = numImmatriculeRegisterCommerce;
    }

    public void setNumPattente(String numPattente) {
        this.numPattente = numPattente;
    }

    public void setPiecesJointes(List<PieceJoint> piecesJointes) {
        this.piecesJointes = piecesJointes;
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

    public String getNumImmatriculeRegisterCommerce() {
        return numImmatriculeRegisterCommerce;
    }

    public String getNumPattente() {
        return numPattente;
    }

    public List<PieceJoint> getPiecesJointes() {
        return piecesJointes;
    }
}