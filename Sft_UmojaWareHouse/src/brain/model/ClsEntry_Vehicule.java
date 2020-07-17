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
public class ClsEntry_Vehicule  implements IActionManager{
    private String id;
    private Date date_entree;
    private String numero, plaque, nature_mese, declarant, agence, importateur;
    private float poids_reel;
    private Date date_sortie;
    private String etat_facturation;
    private ClsMois mois;
    private ClsAnnee annee;

    public ClsEntry_Vehicule() {
    }

    public ClsEntry_Vehicule(String id, Date date_entree, String numero, String plaque, String nature_mese, String declarant, String agence, String importateur, float poids_reel, Date date_sortie, String etat_facturation, ClsMois mois, ClsAnnee annee) {
        this.id = id;
        this.date_entree = date_entree;
        this.numero = numero;
        this.plaque = plaque;
        this.nature_mese = nature_mese;
        this.declarant = declarant;
        this.agence = agence;
        this.importateur = importateur;
        this.poids_reel = poids_reel;
        this.date_sortie = date_sortie;
        this.etat_facturation = etat_facturation;
        this.mois = mois;
        this.annee = annee;
    }

     

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate_entree() {
        return date_entree;
    }

    public void setDate_entree(Date date_entree) {
        this.date_entree = date_entree;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    
    
    public String getNature_mese() {
        return nature_mese;
    }

    public void setNature_mese(String nature_mese) {
        this.nature_mese = nature_mese;
    }

    public String getDeclarant() {
        return declarant;
    }

    public void setDeclarant(String declarant) {
        this.declarant = declarant;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getImportateur() {
        return importateur;
    }

    public void setImportateur(String importateur) {
        this.importateur = importateur;
    }

    public float getPoids_reel() {
        return poids_reel;
    }

    public void setPoids_reel(float poids_reel) {
        this.poids_reel = poids_reel;
    }

    public Date getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(Date date_sortie) {
        this.date_sortie = date_sortie;
    }

    public String getEtat_facturation() {
        return etat_facturation;
    }

    public void setEtat_facturation(String etat_facturation) {
        this.etat_facturation = etat_facturation;
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

    @Override
    public boolean Enregsitrer() throws Exception {
        return ClsController.fx_Validate(this);
    }

    @Override
    public boolean Supprimer() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
