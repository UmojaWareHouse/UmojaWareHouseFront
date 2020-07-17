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
public class ClsBanque  implements IActionManager{
    private int id;
    private String banque, numero_compte, numBorderau;
    private float montant;
    private Date date_update;

    public ClsBanque() {
    }

    public ClsBanque(int id, String banque, String numero_compte, String numBorderau, float montant, Date date_update) {
        this.id = id;
        this.banque = banque;
        this.numero_compte = numero_compte;
        this.numBorderau = numBorderau;
        this.montant = montant;
        this.date_update = date_update;
    }

    public String getBanque() {
        return banque;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }

    public String getNumero_compte() {
        return numero_compte;
    }

    public void setNumero_compte(String numero_compte) {
        this.numero_compte = numero_compte;
    }

    public String getNumBorderau() {
        return numBorderau;
    }

    public void setNumBorderau(String numBorderau) {
        this.numBorderau = numBorderau;
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
