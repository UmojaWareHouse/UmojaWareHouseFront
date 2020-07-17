
package brain.model;

import brain.controller.ClsController;
import brain.controller.IActionManager;

public class ClsUser implements IActionManager{
    private int id;
    private String uName, uPass, uAccreditation;

    public ClsUser() {
    }

    public ClsUser(int id, String uName, String uPass, String uAccreditation) {
        this.id = id;
        this.uName = uName;
        this.uPass = uPass;
        this.uAccreditation = uAccreditation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPass() {
        return uPass;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    public String getuAccreditation() {
        return uAccreditation;
    }

    public void setuAccreditation(String uAccreditation) {
        this.uAccreditation = uAccreditation;
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
