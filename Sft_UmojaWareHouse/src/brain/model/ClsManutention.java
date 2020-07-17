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
public class ClsManutention  implements IActionManager{
    private int id;
    private String libelle;
    private float montant;
    private Date date_update;

    public ClsManutention() {
    }

    public ClsManutention(int id, String libelle, float montant, Date date_update) {
        this.id = id;
        this.libelle = libelle;
        this.montant = montant;
        this.date_update = date_update;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate_update() {
        return date_update;
    }

    public void setDate_update(Date date_update) {
        this.date_update = date_update;
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
