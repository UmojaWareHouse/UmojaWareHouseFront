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
public class ClsFacturation  implements IActionManager{
    private String id;
    private ClsEntry_Vehicule entry_Vehicule;
    private Date date_sortie;
    private String type_Facturation, nom_client_fidele, description;
    private String poids_facture, nbre_jour, parking, 
            sureveillance_1, sureveillance_2, manutention, 
            tva, frais_admin;
    private float total;
    private ClsMois mois;
    private ClsAnnee annee;

    public ClsFacturation(String id, ClsEntry_Vehicule entry_Vehicule, Date date_sortie, String type_Facturation, String nom_client_fidele, String description, String poids_facture, String nbre_jour, String parking, String sureveillance_1, String sureveillance_2, String manutention, String tva, String frais_admin, float total, ClsMois mois, ClsAnnee annee) {
        this.id = id;
        this.entry_Vehicule = entry_Vehicule;
        this.date_sortie = date_sortie;
        this.type_Facturation = type_Facturation;
        this.nom_client_fidele = nom_client_fidele;
        this.description = description;
        this.poids_facture = poids_facture;
        this.nbre_jour = nbre_jour;
        this.parking = parking;
        this.sureveillance_1 = sureveillance_1;
        this.sureveillance_2 = sureveillance_2;
        this.manutention = manutention;
        this.tva = tva;
        this.frais_admin = frais_admin;
        this.total = total;
        this.mois = mois;
        this.annee = annee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClsEntry_Vehicule getEntry_Vehicule() {
        return entry_Vehicule;
    }

    public void setEntry_Vehicule(ClsEntry_Vehicule entry_Vehicule) {
        this.entry_Vehicule = entry_Vehicule;
    }

    public Date getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(Date date_sortie) {
        this.date_sortie = date_sortie;
    }

    public String getType_Facturation() {
        return type_Facturation;
    }

    public void setType_Facturation(String type_Facturation) {
        this.type_Facturation = type_Facturation;
    }

    public String getNom_client_fidele() {
        return nom_client_fidele;
    }

    public void setNom_client_fidele(String nom_client_fidele) {
        this.nom_client_fidele = nom_client_fidele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoids_facture() {
        return poids_facture;
    }

    public void setPoids_facture(String poids_facture) {
        this.poids_facture = poids_facture;
    }

    public String getNbre_jour() {
        return nbre_jour;
    }

    public void setNbre_jour(String nbre_jour) {
        this.nbre_jour = nbre_jour;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getSureveillance_1() {
        return sureveillance_1;
    }

    public void setSureveillance_1(String sureveillance_1) {
        this.sureveillance_1 = sureveillance_1;
    }

    public String getSureveillance_2() {
        return sureveillance_2;
    }

    public void setSureveillance_2(String sureveillance_2) {
        this.sureveillance_2 = sureveillance_2;
    }

    public String getManutention() {
        return manutention;
    }

    public void setManutention(String manutention) {
        this.manutention = manutention;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getFrais_admin() {
        return frais_admin;
    }

    public void setFrais_admin(String frais_admin) {
        this.frais_admin = frais_admin;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
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

    
    public ClsFacturation() {
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
