/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MIB.Inloggning;

import MIB.MainFrameAgent;
import MIB.MainFrameAdmin;
import MIB.MainFrameAlien_1;
import MIB.Validering;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author Hampus
 */
public class Inloggning extends javax.swing.JPanel {

    
    private InfDB idb;
    private static int behorighet;
    MainFrameAgent mainFrame;
    MainFrameAdmin mainAlien;
    

    /**
     * Creates new form Inloggning
     * @param databas
     */
    public Inloggning(InfDB databas) {
        
        behorighet = 0;
        try{
         
          idb = databas;
        }
        catch (Exception ettUndantag) {        //som finns placerad i samma map som projektet självt 
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
        initComponents();
    }

    public Inloggning() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public Inloggning() {
//   
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfAnvandarnamn = new javax.swing.JTextField();
        tfLosenord = new javax.swing.JPasswordField();
        btnLoggaIn = new javax.swing.JButton();
        rbAgent = new javax.swing.JRadioButton();
        rbAlien = new javax.swing.JRadioButton();
        rbAdmin = new javax.swing.JRadioButton();

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 450));

        jLabel1.setText("Ange dina inloggningsuppgifter");

        tfAnvandarnamn.setColumns(10);
        tfAnvandarnamn.setText("Agent Z");

        tfLosenord.setColumns(10);
        tfLosenord.setText("jaja");

        btnLoggaIn.setText("Logga in");
        btnLoggaIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoggaInActionPerformed(evt);
            }
        });

        rbAgent.setBackground(new java.awt.Color(153, 153, 153));
        rbAgent.setText("Agent");
        rbAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAgentActionPerformed(evt);
            }
        });

        rbAlien.setBackground(new java.awt.Color(153, 153, 153));
        rbAlien.setText("Alien");
        rbAlien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlienActionPerformed(evt);
            }
        });

        rbAdmin.setBackground(new java.awt.Color(153, 153, 153));
        rbAdmin.setText("Admin");
        rbAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(btnLoggaIn))
                            .addComponent(tfLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfAnvandarnamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(rbAgent)
                        .addGap(18, 18, 18)
                        .addComponent(rbAlien)
                        .addGap(26, 26, 26)
                        .addComponent(rbAdmin))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jLabel1)))
                .addContainerGap(252, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbAgent)
                    .addComponent(rbAlien)
                    .addComponent(rbAdmin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfAnvandarnamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnLoggaIn)
                .addContainerGap(196, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoggaInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoggaInActionPerformed
        // TODO add your handling code here:
        String nam = "";
        String sqlF = "";
        String adminKoll = "" ;
        String anv = tfAnvandarnamn.getText();
        char[] los = tfLosenord.getPassword();
        String str = String.valueOf(los);
        
        // Kontroll av Admin-status
        String adminSqlFraga = "SELECT administrator from agent where namn = '" + anv + "'";
        try{
        adminKoll = idb.fetchSingle(adminSqlFraga);
        } catch (InfException exe) {
            JOptionPane.showMessageDialog(null, "Hittade ej admin-status");
        }

        
        
        
        if(behorighet == 1){
         sqlF = "select losenord from agent where namn = '" + anv + "'";
        }
        else if(behorighet == 2){
        sqlF = "select losenord from alien where namn = '" + anv + "'";
        }
        else if(behorighet == 3  && adminKoll.equals("J")){
        sqlF = "select losenord from agent where namn = '" + anv + "'";
        }
        else{
        JOptionPane.showMessageDialog(null, "Välj roll");
        }
        
       if(Validering.passwordFieldHarVarde(tfLosenord)){
          try {

            nam = idb.fetchSingle(sqlF);
            
            
            
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Felmeddelande!");
        }
        catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "Databasfel");
        }
        if(str.equals(nam)){
         JOptionPane.showMessageDialog(null, "Inloggad!");
         
            if(behorighet == 1){
            new MainFrameAgent(idb).setVisible(true);
            }
            else if(behorighet == 2){
            new MainFrameAlien_1(idb).setVisible(true);
            }
            else if(behorighet == 3){
            new MainFrameAdmin(idb).setVisible(true);
            }
        }
        else{
        JOptionPane.showMessageDialog(null, "Fel lösenord!");
        }        
       }
        
    }//GEN-LAST:event_btnLoggaInActionPerformed

    public static int getBehorighet() {
        return behorighet;
    }
    
    
    private void rbAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAgentActionPerformed
        // TODO add your handling code here:
        
        
        rbAdmin.setSelected(false);
        rbAlien.setSelected(false);
        behorighet = 1;
        
    }//GEN-LAST:event_rbAgentActionPerformed

    private void rbAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAdminActionPerformed
        // TODO add your handling code here:
        
        rbAgent.setSelected(false);
        rbAlien.setSelected(false);
        behorighet = 3;
    }//GEN-LAST:event_rbAdminActionPerformed

    private void rbAlienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlienActionPerformed
        // TODO add your handling code here:
        
        rbAdmin.setSelected(false);
        rbAgent.setSelected(false);
        behorighet = 2;
    }//GEN-LAST:event_rbAlienActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoggaIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbAdmin;
    private javax.swing.JRadioButton rbAgent;
    private javax.swing.JRadioButton rbAlien;
    private javax.swing.JTextField tfAnvandarnamn;
    private javax.swing.JPasswordField tfLosenord;
    // End of variables declaration//GEN-END:variables
}
