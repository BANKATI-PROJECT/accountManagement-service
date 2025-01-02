package ma.ensa.accountManagement_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class PieceJoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pieceJointUrl;
    private String pieceJointId;



    public PieceJoint(String name, String pieceJointUrl, String pieceJointId) {
        this.name = name;
        this.pieceJointUrl = pieceJointUrl;
        this.pieceJointId = pieceJointId;
    }

    public PieceJoint(Long id, String name, String pieceJointUrl, String pieceJointId) {
        this.id = id;
        this.name = name;
        this.pieceJointUrl = pieceJointUrl;
        this.pieceJointId = pieceJointId;
    }

    public PieceJoint() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPieceJointUrl() {
        return pieceJointUrl;
    }

    public String getPieceJointId() {
        return pieceJointId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPieceJointUrl(String pieceJointUrl) {
        this.pieceJointUrl = pieceJointUrl;
    }

    public void setPieceJointId(String pieceJointId) {
        this.pieceJointId = pieceJointId;
    }
}
