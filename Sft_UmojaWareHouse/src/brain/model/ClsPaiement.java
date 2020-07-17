/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brain.model;


import brain.controller.ClsController;
import brain.controller.IActionManager;
import java.sql.Date;

/**
 *
 * @author Brain
 */
public class ClsPaiement implements IActionManager{
    private int id;
    private ClsFacturation facturation;
    private String libelle, declarant;
    private float montant;
    private Date date_paiement;
    private ClsMois mois;
    private ClsAnnee annee;
    private ClsUser User;

    public ClsPaiement() {
    }

    public ClsPaiement(int id, ClsFacturation facturation, String libelle, String declarant, float montant, Date date_paiement, ClsMois mois, ClsAnnee annee, ClsUser User) {
        this.id = id;
        this.facturation = facturation;
        this.libelle = libelle;
        this.declarant = declarant;
        this.montant = montant;
        this.date_paiement = date_paiement;
        this.mois = mois;
        this.annee = annee;
        this.User = User;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClsFacturation getFacturation() {
        return facturation;
    }

    public void setFacturation(ClsFacturation facturation) {
        this.facturation = facturation;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDeclarant() {
        return declarant;
    }

    public void setDeclarant(String declarant) {
        this.declarant = declarant;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate_paiement() {
        return date_paiement;
    }

    public void setDate_paiement(Date date_paiement) {
        this.date_paiement = date_paiement;
    }

    public ClsMois getMois() {
        return mois;
    }

    public void setMois(ClsMois mois) {
        this.mois = mois;
    }

    public ClsAnnee getAnnee() {
        return annee;
    }

    public void setAnnee(ClsAnnee annee) {
        this.annee = annee;
    }

    
    public ClsUser getUser() {
        return User;
    }

    public void setUser(ClsUser User) {
        this.User = User;
    }

    
    @Override
    public boolean Enregsitrer() throws Exception {
        return ClsController.fx_Validate(this);
    }

    @Override
    public boolean Supprimer() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
