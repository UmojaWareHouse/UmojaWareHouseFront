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
public class ClsDepense_Sortie  implements IActionManager{
    private int id;
    private String motif;
    private float montant;
    private Date date_depense;
    private ClsMois mois;
    private ClsAnnee annee;

    public ClsDepense_Sortie() {
    }

    public ClsDepense_Sortie(int id, String motif, float montant, Date date_depense, ClsMois mois, ClsAnnee annee) {
        this.id = id;
        this.motif = motif;
        this.montant = montant;
        this.date_depense = date_depense;
        this.mois = mois;
        this.annee = annee;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate_depense() {
        return date_depense;
    }

    public void setDate_depense(Date date_depense) {
        this.date_depense = date_depense;
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
