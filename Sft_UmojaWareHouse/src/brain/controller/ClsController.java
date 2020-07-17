/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brain.controller;

import brain.model.ClsAgence;
import brain.model.ClsAnnee;
import brain.model.ClsBanque;
import brain.model.ClsClient_Fidele;
import brain.model.ClsDeclarant;
import brain.model.ClsDepense_Sortie;
import brain.model.ClsEntry_Vehicule;
import brain.model.ClsFacturation;
import brain.model.ClsFinance_Caisse;
import brain.model.ClsManutention;
import brain.model.ClsMois;
import brain.model.ClsPaiement;
import brain.model.ClsUser;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Brain
 */
public class ClsController {
    //IDelete deletes rows with id int format
    public static boolean fx_IDelete(String dbTable, int tbId) throws SQLException, ClassNotFoundException, Exception
    {
        PreparedStatement ps = DbConnect.connectDb().prepareStatement("DELETE FROM "+dbTable+" WHERE id = ?");
        ps.setInt(1, tbId);
        ps.executeUpdate();
        ps.close();
        DbConnect.disconnectDb();
        return true;
    }
    
    //IDelete deletes rows with String int format
    public static boolean fx_SDelete(String dbTable, String tbId) throws SQLException, ClassNotFoundException, Exception
    {
        PreparedStatement ps = DbConnect.connectDb().prepareStatement("DELETE FROM "+dbTable+" WHERE id = ?");
        ps.setString(1, tbId);
        ps.executeUpdate();
        ps.close();
        DbConnect.disconnectDb();
        return true;
    }
    
    public static boolean fx_Validate(Object obj) throws SQLException, ClassNotFoundException, Exception
    {
        if (obj instanceof ClsAnnee) {
            ClsAnnee annee = (ClsAnnee)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tAnnee ?,?");
            ps.setInt(1, annee.getId());
            ps.setString(2, annee.getAnnee());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;            
        }
        else if (obj instanceof ClsMois) {
            ClsMois mois = (ClsMois)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tMois ?,?");
            ps.setInt(1, mois.getId());
            ps.setString(2, mois.getMois());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;                        
        } 
        else if (obj instanceof ClsUser) {
            ClsUser user = (ClsUser)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tUser ?,?,?,?");
            ps.setInt(1, user.getId());
            ps.setString(2, user.getuName());
            ps.setString(3, user.getuPass());
            ps.setString(4, user.getuAccreditation());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;              
        } 
        else if (obj instanceof ClsFinance_Caisse) {
            ClsFinance_Caisse finance_caisse = (ClsFinance_Caisse)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tFinance_Caisse ?,?,?");
            ps.setInt(1, finance_caisse.getId());
            ps.setFloat(2, finance_caisse.getMontant());
            ps.setDate(3, finance_caisse.getDate_update());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;              
        } 
        else if (obj instanceof ClsBanque) {
            ClsBanque banque = (ClsBanque)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tBanque ?,?,?,?,?,?");
            ps.setInt(1, banque.getId());
            ps.setString(2, banque.getBanque());
            ps.setString(3, banque.getNumero_compte());
            ps.setString(4, banque.getNumBorderau());
            ps.setFloat(5, banque.getMontant());
            ps.setDate(6, banque.getDate_update());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;              
        }
        else if (obj instanceof ClsManutention) {
            ClsManutention manutention = (ClsManutention)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tManutention ?,?,?,?");
            ps.setInt(1, manutention.getId());
            ps.setString(2, manutention.getLibelle());
            ps.setFloat(3, manutention.getMontant());
            ps.setDate(4, manutention.getDate_update());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;              
        }
        else if (obj instanceof ClsAgence) {
            ClsAgence agence = (ClsAgence)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tAgence ?,?,?,?");
            ps.setInt(1, agence.getId());
            ps.setString(2, agence.getNom_agence());
            ps.setString(3, agence.getNumero_tel());
            ps.setString(4, agence.getEmail());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;                        
        } 
        else if (obj instanceof ClsClient_Fidele) {
            ClsClient_Fidele client_fidele = (ClsClient_Fidele)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tClient_Fidele ?,?,?,?");
            ps.setInt(1, client_fidele.getId());
            ps.setString(2, client_fidele.getNom_complet());
            ps.setString(3, client_fidele.getNumero_tel());
            ps.setString(4, client_fidele.getEmail());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;                        
        } 
        else if (obj instanceof ClsDeclarant) {
            ClsDeclarant declarant = (ClsDeclarant)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tDeclarant ?,?,?,?");
            ps.setInt(1, declarant.getId());
            ps.setString(2, declarant.getNom_complet());
            ps.setString(3, declarant.getNumero_tel());
            ps.setString(4, declarant.getEmail());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;                        
        }        
        else if (obj instanceof ClsDepense_Sortie) {
            ClsDepense_Sortie depense = (ClsDepense_Sortie)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tDepense_Sortie ?,?,?,?,?,?");
            ps.setInt(1, depense.getId());
            ps.setString(2, depense.getMotif());
            ps.setFloat(3, depense.getMontant());
            ps.setDate(4, depense.getDate_depense());
            ps.setString(5, depense.getMois().getMois());
            ps.setString(6, depense.getAnnee().getAnnee());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;                        
        }        
        else if (obj instanceof ClsPaiement) {
            ClsPaiement paiement = (ClsPaiement)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tPaiement ?,?,?,?,?,?,?,?,?");
            ps.setInt(1, paiement.getId());
            ps.setString(2, paiement.getFacturation().getId());
            ps.setString(3, paiement.getLibelle());
            ps.setString(4, paiement.getDeclarant());
            ps.setFloat(5, paiement.getMontant());
            ps.setDate(6, paiement.getDate_paiement());
            ps.setString(7, paiement.getMois().getMois());
            ps.setString(8, paiement.getAnnee().getAnnee());
            ps.setString(9, paiement.getUser().getuName());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;                        
        }        
        else if (obj instanceof ClsEntry_Vehicule) {
            ClsEntry_Vehicule entry_vehicule = (ClsEntry_Vehicule)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tEntry_Vehicule ?,?,?,?,?,?,?,?,?,?,?,?,?");
            ps.setString(1, entry_vehicule.getId());
            ps.setDate(2, entry_vehicule.getDate_entree());
            ps.setString(3, entry_vehicule.getNumero());
            ps.setString(4, entry_vehicule.getPlaque());
            ps.setString(5, entry_vehicule.getNature_mese());
            ps.setString(6, entry_vehicule.getDeclarant());
            ps.setString(7, entry_vehicule.getAgence());
            ps.setString(8, entry_vehicule.getImportateur());
            ps.setFloat(9, entry_vehicule.getPoids_reel());
            ps.setDate(10, entry_vehicule.getDate_sortie());
            ps.setString(11, entry_vehicule.getEtat_facturation());
            ps.setString(12, entry_vehicule.getMois().getMois());
            ps.setString(13, entry_vehicule.getAnnee().getAnnee());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;                        
        }
        else if (obj instanceof ClsFacturation) {
            ClsFacturation facturation = (ClsFacturation)obj;
            PreparedStatement ps = DbConnect.connectDb().prepareCall("EXECUTE SP_UPDATE_tFacturation ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
            ps.setString(1, facturation.getId());
            ps.setString(2, facturation.getEntry_Vehicule().getId());
            ps.setDate(3, facturation.getDate_sortie());
            ps.setString(4, facturation.getType_Facturation());
            ps.setString(5, facturation.getNom_client_fidele());
            ps.setString(6, facturation.getDescription());
            ps.setString(7, facturation.getPoids_facture());
            ps.setString(8, facturation.getNbre_jour());
            ps.setString(9, facturation.getParking());
            ps.setString(10, facturation.getSureveillance_1());
            ps.setString(11, facturation.getSureveillance_2());
            ps.setString(12, facturation.getManutention());
            ps.setString(13, facturation.getTva());
            ps.setString(14, facturation.getFrais_admin());
            ps.setFloat(15, facturation.getTotal());
            ps.setString(16, facturation.getMois().getMois());
            ps.setString(17, facturation.getAnnee().getAnnee());
            ps.executeUpdate();
            ps.close();
            DbConnect.disconnectDb();
            return true;                        
        }          
        return false;
    }
}