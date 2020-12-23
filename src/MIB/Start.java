/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MIB;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author Hampus
 */
public class Start {
    
        private static InfDB idb;   

    
    public static void main(String args[]) {
        
        try{
          // Kollar vilken mapp som projektet är placerat i
          String aktuellMap = System.getProperty("user.dir"); 
          // Slår samman sökvägen med sökvägen till databasen
          String sokVag = aktuellMap + ("\\db\\MIBDB.FDB");
          // Instansierar och tilldelar idb databaskopplingen
          idb = new InfDB(sokVag);

        }
        // Fångar och skriver ut felmeddelande vid databaskoppling
        catch (InfException e) { 
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("Fel vid koppling" + e.getMessage());
        }
        
        new MainFrame(idb).setVisible(true);
        
        
    }
    
}
