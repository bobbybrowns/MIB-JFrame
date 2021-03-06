/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MIB;

import MIB.Inloggning.MenyAlien;
import java.awt.BorderLayout;
import oru.inf.InfDB;

/**
 *
 * @author Hampus
 */
public class MainFrameAlien_1 extends javax.swing.JFrame {
    MenyAlien alienMeny;
    private static InfDB idb;

    /**
     * Creates new form MainFrameAlien
     */
    public MainFrameAlien_1(InfDB databas) {
        idb = databas;
        initComponents();
        alienMeny = new MenyAlien(idb);
        pnlAlienMeny.setLayout(new BorderLayout());
        pnlAlienMeny.add(alienMeny);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAlienMeny = new javax.swing.JPanel();
        pnlAlienHuvudPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout pnlAlienMenyLayout = new javax.swing.GroupLayout(pnlAlienMeny);
        pnlAlienMeny.setLayout(pnlAlienMenyLayout);
        pnlAlienMenyLayout.setHorizontalGroup(
            pnlAlienMenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
        );
        pnlAlienMenyLayout.setVerticalGroup(
            pnlAlienMenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlAlienHuvudPanelLayout = new javax.swing.GroupLayout(pnlAlienHuvudPanel);
        pnlAlienHuvudPanel.setLayout(pnlAlienHuvudPanelLayout);
        pnlAlienHuvudPanelLayout.setHorizontalGroup(
            pnlAlienHuvudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );
        pnlAlienHuvudPanelLayout.setVerticalGroup(
            pnlAlienHuvudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlAlienMeny, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAlienHuvudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAlienMeny, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlAlienHuvudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlAlienHuvudPanel;
    private javax.swing.JPanel pnlAlienMeny;
    // End of variables declaration//GEN-END:variables
}
