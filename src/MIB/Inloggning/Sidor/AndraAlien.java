/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MIB.Inloggning.Sidor;

import MIB.Validering;
import java.util.ArrayList;
import oru.inf.InfDB;
import oru.inf.InfException;



/**
 *
 * @author Hampus
 */
public class AndraAlien extends javax.swing.JPanel {
    private InfDB idb;
    private ArrayList<String> sql;
    private boolean rasHarVarde;
    private String losen;
    private String sqlFragan;
    private int alienID;
    private String insertToTable;
    
    
    public AndraAlien(InfDB databasen) {
        initComponents();
        sqlFragan = "";
        idb = databasen;
        rasHarVarde =  false;
        sql = new ArrayList<>();
        losen = "";
        
        settAlienID();
        setRas();
        setPlats();
        jArv.setVisible(false);
        jArvInput.setVisible(false);
        
    }
    // DELETE FROM alien where alien_id = 4;
    public void uppdateraAlien(){
        setSql();
        String sql = "update alien set " + sqlFragan + " where alien_id = " + alienID; 
        String sqlArvWORM = "insert into WORM values(" + alienID+")";
        String sqlAnnanRas = "insert into " + cbRas.getSelectedItem().toString() + " values(" + alienID + ", " 
                                + jArvInput.getText().toString() + ")";
        System.out.println(sqlAnnanRas);
        try{ 
            // lägger till i databasen
            idb.update(sql);
        } catch (InfException e){
            
        }
        
        if(rasHarVarde){
        try{
                if(cbRas.getSelectedItem().toString().equals("WORM")){
                    searchAlienInDatabaseAndRemove();
                    idb.insert(sqlArvWORM);
                    
                } else if(cbRas.getSelectedItem().toString().equals("BOGLODITE") || cbRas.getSelectedItem().toString().equals("SQUID")){
                    searchAlienInDatabaseAndRemove();
                    idb.insert(sqlAnnanRas);
                    
                } else {
                    searchAlienInDatabaseAndRemove();
                }
        } catch(InfException e){
            System.out.println("Databas fel, ändra alien.");
        }
        }

    }
    
    public void searchAlienInDatabaseAndRemove(){
        alienID = Integer.parseInt(jComboBox1.getSelectedItem().toString());
        String q = "select alien_id from alien where alien_id = " + alienID;
        String Boglodite = "select alien_id from BOGLODITE where alien_id = " + alienID;
        String Worm = "select alien_id from WORM where alien_id = "+ alienID;
        String Squid = "select alien_id from SQUID where alien_id = " + alienID;
        
        
        try{
        if(idb.fetchSingle(Worm).equals(idb.fetchSingle(q))){
            idb.delete("delete from worm where alien_id = " + alienID);
        } else if(idb.fetchSingle(Boglodite).equals(idb.fetchSingle(q))){
            idb.delete("delete from Boglodite where alien_id = " + alienID);
        } else if(idb.fetchSingle(Squid).equals(idb.fetchSingle(q))){
            idb.delete("delete from Squid where alien_id = " + alienID);
        } 

        } catch (InfException e) {
            
        } catch (NullPointerException e) {
            
        }
    }
    
    public void setSql(){
        
        if(!tfNamn.getText().isEmpty()){
            sql.add("NAMN = '" + tfNamn.getText()+"'");    
        } 
        
        if(!tfTelefon.getText().isEmpty()){
            sql.add("TELEFON = '" + tfTelefon.getText()+"'");
        } 
        
        char[] password = pfLosenord.getPassword();
        losen = String.valueOf(password);
        if(!losen.isEmpty() && Validering.passwordSix(pfLosenord)){
            sql.add("LOSENORD = '" + losen+"'");
        } 
        
        if(!cbPlats.getSelectedItem().toString().equals("-")){
            String sqlAnsvarigAgent = "select agent_id from omradeschef where omrade = " +  cbPlats.getSelectedItem().toString();
            try{
                sqlAnsvarigAgent = idb.fetchSingle(sqlAnsvarigAgent);
            } catch (InfException e) {
                
            }
           sql.add("PLATS = '" + cbPlats.getSelectedItem().toString()+"'"); 
           sql.add("ANSVARIG_AGENT = '" + sqlAnsvarigAgent +"'");
        }
        
        if(!cbRas.getSelectedItem().toString().equals("-")){
           sql.add("RAS = '" + cbRas.getSelectedItem().toString()+"'");
        }
        
        alienID = Integer.parseInt(jComboBox1.getSelectedItem().toString());
        
        for(int i = 0; i < sql.size(); i++){
            
            sqlFragan = sqlFragan+ " " + sql.get(i);
            if(sql.size() - (i + 1) != 0 ){
                sqlFragan = sqlFragan + ", ";
            }
        }
         
    }


    public void settAlienID(){
        String fraga = "select Alien_id from alien";
        ArrayList<String>fyllCB = new ArrayList<>();
        
        try {
            fyllCB = idb.fetchColumn(fraga);
        } catch(InfException e){
            
        }
        for(String alienID : fyllCB) {
            jComboBox1.addItem(alienID);
        }
    }
    
    public void setRas(){ 
        cbRas.addItem("-");
        cbRas.addItem("Ingen ras");
        cbRas.addItem("BOGLODITE"); // LÄGG TILL FÖR BOOGIES
        cbRas.addItem("SQUID"); // LÄGG TILL FÖR ARMAR
        cbRas.addItem("WORM");
    }
        public void setPlats(){
             cbPlats.addItem("-");
        String fraga = "select omrades_id from omrade";
        ArrayList<String>fyllCB = new ArrayList<>();
        
        try {
            fyllCB = idb.fetchColumn(fraga);
            
        } catch(InfException e){
            
        }
        
        for(String alienID : fyllCB) {
            cbPlats.addItem(alienID);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfNamn = new javax.swing.JTextField();
        pfLosenord = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfTelefon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbRas = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnSpara = new javax.swing.JButton();
        cbPlats = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jArvInput = new javax.swing.JTextField();
        jArv = new javax.swing.JLabel();
        ValdAlien = new javax.swing.JLabel();

        jLabel1.setText("Ändra informationen om alien nedan");

        jLabel2.setText("Namn");

        tfNamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamnActionPerformed(evt);
            }
        });

        pfLosenord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfLosenordActionPerformed(evt);
            }
        });

        jLabel3.setText("Lösenord");

        jLabel4.setText("Telefon");

        tfTelefon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTelefonActionPerformed(evt);
            }
        });

        jLabel5.setText("Plats");

        cbRas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRasActionPerformed(evt);
            }
        });

        jLabel7.setText("Ras");

        btnSpara.setText("Spara");
        btnSpara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSparaActionPerformed(evt);
            }
        });

        cbPlats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPlatsActionPerformed(evt);
            }
        });

        jLabel6.setText("Välj en alien från Ett Alien_Id som du vill ändra");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jArvInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jArvInputActionPerformed(evt);
            }
        });

        jArv.setText("Om sant visa");

        ValdAlien.setText("jLabel8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(109, 109, 109))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSpara, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbRas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbPlats, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfTelefon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(pfLosenord, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfNamn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)))
                                .addGap(24, 24, 24)))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ValdAlien, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jArv, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jArvInput, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(ValdAlien)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(pfLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(tfTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPlats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jArv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbRas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jArvInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSpara)
                .addGap(29, 29, 29))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String sqlFraga = "select namn from alien where alien_id = " + jComboBox1.getSelectedItem().toString();
        try{
            ValdAlien.setText("Du har valt att ändra på alien "+ idb.fetchSingle(sqlFraga));
        } catch (InfException e) {
            
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void cbPlatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPlatsActionPerformed

    }//GEN-LAST:event_cbPlatsActionPerformed

    private void cbRasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRasActionPerformed
        try{
            // !cbPlats.getSelectedItem().equals("Ingen ras")
        if(!cbRas.getSelectedItem().equals("-")){ 
           rasHarVarde = true;
           jArv.setVisible(true);
            jArvInput.setVisible(true);  
            if(cbRas.getSelectedItem().equals("BOGLODITE")){
                jArv.setText("Ange hur många boogies");
            } else if(cbRas.getSelectedItem().equals("SQUID")) {
                jArv.setText("Ange hur många armar");
            } else {
                jArv.setVisible(false);
                jArvInput.setVisible(false);
            }
        } else {
            rasHarVarde = false;
            jArv.setVisible(false);
            jArvInput.setVisible(false);
        } 
        } catch (NullPointerException e) {
            
        }

    }//GEN-LAST:event_cbRasActionPerformed

    private void btnSparaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSparaActionPerformed
      //searchAlienInDatabaseAndRemove();
      uppdateraAlien();
            
        
    }//GEN-LAST:event_btnSparaActionPerformed

    private void tfNamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamnActionPerformed
       
    }//GEN-LAST:event_tfNamnActionPerformed

    private void tfTelefonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTelefonActionPerformed

    }//GEN-LAST:event_tfTelefonActionPerformed

    private void pfLosenordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfLosenordActionPerformed

    }//GEN-LAST:event_pfLosenordActionPerformed
    
    private void jArvInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jArvInputActionPerformed
       
      
    }//GEN-LAST:event_jArvInputActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ValdAlien;
    private javax.swing.JButton btnSpara;
    private javax.swing.JComboBox<String> cbPlats;
    private javax.swing.JComboBox<String> cbRas;
    private javax.swing.JLabel jArv;
    private javax.swing.JTextField jArvInput;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField pfLosenord;
    private javax.swing.JTextField tfNamn;
    private javax.swing.JTextField tfTelefon;
    // End of variables declaration//GEN-END:variables
}
