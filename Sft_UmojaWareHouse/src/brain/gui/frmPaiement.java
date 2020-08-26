/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brain.gui;

import brain.controller.ClsHelper;
import brain.controller.DbConnect;
import brain.model.ClsPaiement;
import brain.model.ClsMois;
import brain.model.ClsAnnee;
import brain.model.ClsFacturation;
import brain.model.ClsUser;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Brain
 */
public class frmPaiement extends javax.swing.JFrame {
    
    ClsPaiement pay = new ClsPaiement();
    ClsFacturation fact = new ClsFacturation();
    ClsMois mois = new ClsMois();
    ClsAnnee an = new ClsAnnee();
    ClsUser user = new ClsUser();

    /**
     * Creates new form frmPaiement
     */
    public frmPaiement() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("UMOJA BUSINESS / PAIEMENT FACTURE");
        try {
            ClsHelper.Load_CmbBox(cmbAnnee, "SELECT annee FROM tAnnee ORDER BY annee DESC");
            ClsHelper.Load_CmbBox(cmbMois, "SELECT mois FROM tMois");
            ClsHelper.Load_CmbBox(cmbFacture, "SELECT id from tFacturation");
            ClsHelper.Load_TblData(tatPaiement, "SELECT p.id NUMERO_PAIEMENT, f.id FACTURE, p.libelle LIBELLE, "
                    + " p.montant MONTANT, p.date_paiement DATE_PAIEMENT, u.uName [USER] "
                    + "FROM tPaiement p "
                    + "INNER JOIN tFacturation f ON p.idFacture = f.id "
                    + "INNER JOIN tUser u ON p.idUser = u.id "
                    + "INNER JOIN tMois m ON p.idMois = m.id "
                    + "INNER JOIN tAnnee a ON p.idAnnee = a.id "
                    + "WHERE m.mois = '" + cmbMois.getSelectedItem().toString() + "' "
                    + "AND a.annee = '" + cmbAnnee.getSelectedItem().toString() + "'");
            
            txtId.setText("" + ClsHelper.Increment_ID("tPaiement"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error :\n" + e.getMessage(), "Entry Loading Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void getTotal(JLabel lbTot, String idFacture) {
        try {
            PreparedStatement ps = DbConnect.connectDb().prepareStatement("select total from tFacturation WHERE id = ?");
            ps.setString(1, idFacture);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lbTot.setText(rs.getFloat("total") + "");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error :\n" + e.getMessage(), "fetch_Entry Loading Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void getTotalPaye(JLabel lbDjPay, String idFacture) {
        try {
            PreparedStatement ps = DbConnect.connectDb().prepareStatement("select ISNULL(SUM(p.montant),0) "
                    + "as MONTANT_PAYE from tPaiement p where p.idFacture = ?");
            ps.setString(1, idFacture);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lbDjPay.setText(rs.getFloat("MONTANT_PAYE") + "");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error :\n" + e.getMessage(), "fetch_Entry Loading Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void getReste(JLabel lbTot, JLabel lbPaye, JLabel lbRest) {
        float tot = Float.valueOf(lbTot.getText());
        float pay = Float.valueOf(lbPaye.getText());
        
        float rest = tot - pay;
        lbRest.setText(rest + "");
    }
    
    private void getDeclarantByFacture(JLabel lbDec, String idFacture) {
        try {
            PreparedStatement ps = DbConnect.connectDb().prepareStatement("select e.declarant DECLARANT "
                    + "from  tFacturation f inner join tEntry_Vehicule e "
                    + "on e.id = f.idEntree_vehicule where f.id  = ?");
            ps.setString(1, idFacture);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lbDec.setText(rs.getString("DECLARANT"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error :\n" + e.getMessage(), "fetch_Entry Loading Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void changeStateEntry(JLabel lbRest, String myId) {
        double maValeur = Double.valueOf(lbRest.getText());
        if (maValeur > 0) {
            try {
                PreparedStatement ps = DbConnect.connectDb().prepareStatement("UPDATE tEntry_Vehicule "
                        + "SET etat_facturation = 'A Recouvrer' WHERE id = (SELECT e.id FROM tFacturation f "
                        + "RIGHT JOIN tEntry_Vehicule e ON e.id = f.idEntree_vehicule where f.id = ?)");
                ps.setString(1, myId);
                ps.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error :\n" + e.getMessage(), "changeStateEntry Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if (maValeur == 0) {
            try {
                PreparedStatement ps = DbConnect.connectDb().prepareStatement("UPDATE tEntry_Vehicule "
                        + "SET etat_facturation = 'Payé' WHERE id = (SELECT e.id FROM tFacturation f "
                        + "RIGHT JOIN tEntry_Vehicule e ON e.id = f.idEntree_vehicule where f.id = ?)");
                ps.setString(1, myId);
                ps.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error :\n" + e.getMessage(), "changeStateEntry Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tatPaiement = new javax.swing.JTable();
        lbTotal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbDejaPaye = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbReste = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbFacture = new javax.swing.JComboBox<>();
        txtId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtLibelle = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMontant = new javax.swing.JTextField();
        dt_paiement = new org.jdesktop.swingx.JXDatePicker();
        jLabel15 = new javax.swing.JLabel();
        cmbAnnee = new javax.swing.JComboBox<>();
        cmbMois = new javax.swing.JComboBox<>();
        lbDeclarant = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setViewportView(tatPaiement);

        lbTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotal.setText("xxx");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Total");

        lbDejaPaye.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbDejaPaye.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDejaPaye.setText("yyy");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Déjà payé");

        lbReste.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbReste.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbReste.setText("zzz");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Reste à payer");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Facture à payer");

        cmbFacture.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbFacture.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbFacturePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowRightShadow(false);
        txtId.setBorder(dropShadowBorder1);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Declarant");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("ID");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Libelle");

        txtLibelle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLibelle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowRightShadow(false);
        txtLibelle.setBorder(dropShadowBorder2);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Montant");

        txtMontant.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMontant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setShowRightShadow(false);
        txtMontant.setBorder(dropShadowBorder3);

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder4 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder4.setShowRightShadow(false);
        dt_paiement.setBorder(dropShadowBorder4);

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/brain/gui/img/accept.png"))); // NOI18N
        jLabel15.setText("Enregistrer Paiement");
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder5 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder5.setShowLeftShadow(true);
        dropShadowBorder5.setShowTopShadow(true);
        jLabel15.setBorder(dropShadowBorder5);
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
        });

        cmbAnnee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbAnnee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Année", "" }));
        cmbAnnee.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbAnneePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cmbMois.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbMois.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mois", "" }));
        cmbMois.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmbMoisPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        lbDeclarant.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbDeclarant.setText("Declarant");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbMois, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dt_paiement, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMontant, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtLibelle, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDejaPaye, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbReste, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbDeclarant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 116, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dt_paiement, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMois, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbReste, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDejaPaye, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDeclarant, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtLibelle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtMontant, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        if (dt_paiement.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Entrez la Date SVP", "Entry_Vehicule Error", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                
                if (Float.parseFloat(txtMontant.getText()) <= Float.parseFloat(lbReste.getText())) {
                    pay.setId(Integer.valueOf(txtId.getText()));
                    
                    fact.setId(cmbFacture.getSelectedItem().toString());
                    pay.setFacturation(fact);
                    pay.setLibelle(txtLibelle.getText());
                    pay.setDeclarant(lbDeclarant.getText());
                    pay.setMontant(Float.valueOf(txtMontant.getText()));
                    pay.setDate_paiement(new Date(dt_paiement.getDate().getYear(), dt_paiement.getDate().getMonth(), dt_paiement.getDate().getDate()));
                    mois.setMois(cmbMois.getSelectedItem().toString());
                    an.setAnnee(cmbAnnee.getSelectedItem().toString());
                    pay.setMois(mois);
                    pay.setAnnee(an);
                    user.setuName("admin");
                    pay.setUser(user);
                    if (pay.Enregsitrer()) {
                        JOptionPane.showMessageDialog(null, "Enregistré avec succès", "Paiement Message", JOptionPane.INFORMATION_MESSAGE);
                        clean();
                        ClsHelper.Load_TblData(tatPaiement, "SELECT * FROM tPaiement");
                        getTotal(lbTotal, cmbFacture.getSelectedItem().toString());
                        getTotalPaye(lbDejaPaye, cmbFacture.getSelectedItem().toString());
                        getReste(lbTotal, lbDejaPaye, lbReste);
                        txtId.setText("" + ClsHelper.Increment_ID("tPaiement"));
                        changeStateEntry(lbReste, cmbFacture.getSelectedItem().toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Montant invalider", "Paiement Message", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error :\n" + e.getMessage(), "Paiement Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jLabel15MouseClicked
    void clean() {
        txtMontant.setText(null);
        txtLibelle.setText(null);
        lbDeclarant.setText(null);
        txtLibelle.requestFocus();
    }
    private void cmbFacturePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbFacturePopupMenuWillBecomeInvisible
        getTotal(lbTotal, cmbFacture.getSelectedItem().toString());
        getTotalPaye(lbDejaPaye, cmbFacture.getSelectedItem().toString());
        getReste(lbTotal, lbDejaPaye, lbReste);
        getDeclarantByFacture(lbDeclarant, cmbFacture.getSelectedItem().toString());
    }//GEN-LAST:event_cmbFacturePopupMenuWillBecomeInvisible

    private void cmbMoisPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbMoisPopupMenuWillBecomeInvisible
        try {
            ClsHelper.Load_TblData(tatPaiement, "SELECT p.id NUMERO_PAIEMENT, f.id FACTURE, p.libelle LIBELLE, "
                    + " p.montant MONTANT, p.date_paiement DATE_PAIEMENT, u.uName [USER] "
                    + "FROM tPaiement p "
                    + "INNER JOIN tFacturation f ON p.idFacture = f.id "
                    + "INNER JOIN tUser u ON p.idUser = u.id "
                    + "INNER JOIN tMois m ON p.idMois = m.id "
                    + "INNER JOIN tAnnee a ON p.idAnnee = a.id "
                    + "WHERE m.mois = '" + cmbMois.getSelectedItem().toString() + "' "
                    + "AND a.annee = '" + cmbAnnee.getSelectedItem().toString() + "'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error :\n" + e.getMessage(), "Entry Loading Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_cmbMoisPopupMenuWillBecomeInvisible

    private void cmbAnneePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbAnneePopupMenuWillBecomeInvisible
        try {
            ClsHelper.Load_TblData(tatPaiement, "SELECT p.id NUMERO_PAIEMENT, f.id FACTURE, p.libelle LIBELLE, "
                    + " p.montant MONTANT, p.date_paiement DATE_PAIEMENT, u.uName [USER] "
                    + "FROM tPaiement p "
                    + "INNER JOIN tFacturation f ON p.idFacture = f.id "
                    + "INNER JOIN tUser u ON p.idUser = u.id "
                    + "INNER JOIN tMois m ON p.idMois = m.id "
                    + "INNER JOIN tAnnee a ON p.idAnnee = a.id "
                    + "WHERE m.mois = '" + cmbMois.getSelectedItem().toString() + "' "
                    + "AND a.annee = '" + cmbAnnee.getSelectedItem().toString() + "'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error :\n" + e.getMessage(), "Entry Loading Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_cmbAnneePopupMenuWillBecomeInvisible

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPaiement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPaiement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPaiement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPaiement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPaiement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbAnnee;
    private javax.swing.JComboBox<String> cmbFacture;
    private javax.swing.JComboBox<String> cmbMois;
    private org.jdesktop.swingx.JXDatePicker dt_paiement;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbDeclarant;
    private javax.swing.JLabel lbDejaPaye;
    private javax.swing.JLabel lbReste;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tatPaiement;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLibelle;
    private javax.swing.JTextField txtMontant;
    // End of variables declaration//GEN-END:variables
}
