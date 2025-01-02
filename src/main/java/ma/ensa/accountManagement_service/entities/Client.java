package ma.ensa.accountManagement_service.entities;

import jakarta.persistence.*;
import ma.ensa.accountManagement_service.enums.TypeCompte;
import ma.ensa.accountManagement_service.model.Portefeuille;


@Entity
public class Client extends UserApp {

    @Transient
    private Portefeuille portefeuille;

    private String saveToken;
    private TypeCompte plafond;

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    public String getSaveToken() {
        return saveToken;
    }

    public TypeCompte getPlafond() {
        return plafond;
    }

    public void setPortefeuille(Portefeuille portefeuille) {
        this.portefeuille = portefeuille;
    }

    public void setSaveToken(String saveToken) {
        this.saveToken = saveToken;
    }

    public void setPlafond(TypeCompte plafond) {
        this.plafond = plafond;
    }
}
