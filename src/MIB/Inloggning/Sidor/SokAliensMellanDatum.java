/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MIB.Inloggning.Sidor;
import java.text.ParseException;
import java.time.Month; 
import javax.swing.JComboBox;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;
/**
 *
 * @author Hampus
 */
public class SokAliensMellanDatum extends javax.swing.JPanel {
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private Date datum = new Date();
    private InfDB idb;
    Date datumStart2 = null;
    Date datumSlut2 = null;
    /**
     * Creates new form SokAliensMellanDatum
     */
    public SokAliensMellanDatum(InfDB databasen) {
        initComponents();
        idb = databasen;
        setAr();
        setManad();
    }

    // ta in cb som parameter
    public boolean setLeapYear(int vilketAr){
        // använd cb in parameter som år
        boolean leapYear;
        int ar = vilketAr; //Integer.parseInt(cbSlutAr.getSelectedItem().toString());
        if(ar % 4 == 0)
        {
            if( ar % 100 == 0)
            {
                if ( ar % 400 == 0)
                    leapYear = true;
                else
                    leapYear = false;
            }
            else
                leapYear = true;
        }
        else {
            leapYear = false;
        }
        return leapYear;
    }
    
    public void setDag(int antalDagar, JComboBox boxen){
        boxen.removeAllItems();
        for(int i = 1; i <= antalDagar; i++){
            boxen.addItem(Integer.toString(i));
        }
        
    }

    
    public void setManad(){
        cbSlutManad.removeAllItems();
        cbStartManad.removeAllItems();
        for(int i = 1; i < 13; i++){
            cbSlutManad.addItem(Integer.toString(i));
            cbStartManad.addItem(Integer.toString(i));
        }
    }
    
    public void setAr(){
        cbSlutAr.removeAllItems();
        cbStartAr.removeAllItems();
        for(int i = 1990; i < 2022; i++){
            cbSlutAr.addItem(Integer.toString(i));
            cbStartAr.addItem(Integer.toString(i));
        }
    }
    
    public void setDayEfterManad(JComboBox ar, JComboBox boxManad, JComboBox dag){
        int vilkenManad = 0;
        Month manad;
        boolean skottAr = setLeapYear(Integer.parseInt(ar.getSelectedItem().toString()));
       try{     
            vilkenManad  = Integer.parseInt(boxManad.getSelectedItem().toString());
       } catch(NullPointerException e) {
            
       }
        switch(vilkenManad){
            case 1: 
                manad = Month.JANUARY;
                setDag(manad.length(skottAr), dag);
                System.out.println("1");
                break;
            case 2: 
                manad = Month.FEBRUARY;
                setDag(manad.length(skottAr), dag);
                System.out.println("2");
                break;
            case 3: 
                manad = Month.MARCH;
                setDag(manad.length(skottAr), dag);
                System.out.println("3");
                break;
            case 4:
                manad = Month.APRIL;
                setDag(manad.length(true), dag);
                System.out.println("4");
                break;
            case 5: 
                manad = Month.MAY;
                setDag(manad.length(skottAr), dag);
                System.out.println("5");
                break;
            case 6: 
                manad = Month.JUNE;
                setDag(manad.length(skottAr), dag);
                break;
            case 7: 
                manad = Month.JULY;
                setDag(manad.length(skottAr), dag);
                break;
            case 8: 
                manad = Month.AUGUST;
                setDag(manad.length(skottAr), dag);
                break;
            case 9: 
                manad = Month.SEPTEMBER;
                setDag(manad.length(skottAr), dag);
                break;
            case 10: 
                manad = Month.OCTOBER;
                setDag(manad.length(skottAr), dag);
                break;
            case 11: 
                manad = Month.NOVEMBER;
                setDag(manad.length(skottAr), dag);
                break;
            case 12: 
                manad = Month.DECEMBER;
                setDag(manad.length(skottAr), dag);
                break;
        }
    }
    
    public boolean validaDatum(){
        boolean godkantDatum = false;
        String datumStart1;
        String datumSlut1;
        datumStart2 = null;
        datumSlut2 = null;
                
        datumStart1 = cbStartAr.getSelectedItem().toString() + "-" + cbStartManad.getSelectedItem().toString() + "-"  + cbStartDag.getSelectedItem().toString(); 
        datumSlut1 = cbSlutAr.getSelectedItem().toString() + "-" + cbSlutManad.getSelectedItem().toString() + "-"  + cbSlutDag.getSelectedItem().toString();
        try{
        datumStart2 = formatter.parse(datumStart1);
        datumSlut2 = formatter.parse(datumSlut1);
        
        } catch (ParseException e) {
            
        }
        if(datumSlut2.before(datum)){
        if(datumStart2.before(datumSlut2) || datumStart2.equals(datumSlut2) || datumSlut2.equals(datum)){
            godkantDatum = true;
        } else {
            JOptionPane.showMessageDialog(null, "Ditt slut datum måste störe eller lika med ditt startdatum");
        }
        } else {
            JOptionPane.showMessageDialog(null, "Du kan inte ha ett datum större än " + datum);
        }

        return godkantDatum;
    }
    
    public void fyllTextSok(){
        taVisaAliens.append("");
        
       ArrayList<HashMap<String, String>> sokAlien = new ArrayList<HashMap<String,String>>();
       System.out.println(formatter.format(datumStart2) + " " + formatter.format(datumSlut2));
       String sqlFraga = "Select namn, alien_id from alien where registreringsdatum between '" + formatter.format(datumStart2) + "' and '" + formatter.format(datumSlut2) + "'";

       try{
           sokAlien = idb.fetchRows(sqlFraga);
       } catch (InfException e){
           
       } 
       taVisaAliens.append("Namn \t" + "Alien ID \n");
       for(HashMap<String, String> aliens : sokAlien){
           taVisaAliens.append(aliens.get("NAMN") + "\t");
           taVisaAliens.append(aliens.get("ALIEN_ID") + "\n");
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbStartDag = new javax.swing.JComboBox<>();
        cbStartManad = new javax.swing.JComboBox<>();
        cbStartAr = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbSlutAr = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbSlutManad = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbSlutDag = new javax.swing.JComboBox<>();
        btnSok = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taVisaAliens = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setText("Visa aliens registrerade mellan datum");

        jLabel2.setText("År:");

        jLabel3.setText("Månad:");

        jLabel4.setText("Dag:");

        cbStartManad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbStartManad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStartManadActionPerformed(evt);
            }
        });

        cbStartAr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("År:");

        cbSlutAr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Månad:");

        cbSlutManad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbSlutManad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSlutManadActionPerformed(evt);
            }
        });

        jLabel8.setText("Dag:");

        cbSlutDag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSlutDagActionPerformed(evt);
            }
        });

        btnSok.setText("Sök");
        btnSok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSokActionPerformed(evt);
            }
        });

        taVisaAliens.setColumns(20);
        taVisaAliens.setRows(5);
        jScrollPane1.setViewportView(taVisaAliens);

        jLabel5.setText("Ange startdatum");

        jLabel9.setText("Ange slutdatum ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(156, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(cbStartAr, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(41, 41, 41)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbStartManad, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbStartDag, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(cbSlutAr, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(41, 41, 41)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbSlutManad, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbSlutDag, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(106, 106, 106)
                            .addComponent(btnSok, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(49, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addGap(56, 56, 56)
                .addComponent(jLabel9)
                .addContainerGap(203, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jLabel1)
                    .addGap(29, 29, 29)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbStartAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbStartManad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbStartDag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(27, 27, 27)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbSlutAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbSlutManad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbSlutDag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnSok)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbStartManadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStartManadActionPerformed
        setDayEfterManad(cbStartAr, cbStartManad, cbStartDag);
        
        
    }//GEN-LAST:event_cbStartManadActionPerformed

    private void cbSlutManadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSlutManadActionPerformed
        setDayEfterManad(cbSlutAr,cbSlutManad, cbSlutDag);
    }//GEN-LAST:event_cbSlutManadActionPerformed

    private void cbSlutDagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSlutDagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSlutDagActionPerformed

    private void btnSokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSokActionPerformed
        System.out.println(validaDatum());
        if(validaDatum()){
            fyllTextSok();
        }
    }//GEN-LAST:event_btnSokActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSok;
    private javax.swing.JComboBox<String> cbSlutAr;
    private javax.swing.JComboBox<String> cbSlutDag;
    private javax.swing.JComboBox<String> cbSlutManad;
    private javax.swing.JComboBox<String> cbStartAr;
    private javax.swing.JComboBox<String> cbStartDag;
    private javax.swing.JComboBox<String> cbStartManad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taVisaAliens;
    // End of variables declaration//GEN-END:variables
}
