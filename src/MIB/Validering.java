/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MIB;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;


/**
 *
 * @author Hampus
 */
public class Validering {
    
    public static boolean kollaLosenord()
    {
    return true;
    }
    
    
    
    public static boolean textFaltHarVarde(JTextField rutaAttKolla)
    {
        boolean resultat = true;
        
        if(rutaAttKolla.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Inmatningsrutan är tom!");
            resultat = false;
            rutaAttKolla.requestFocus();
        }       
        
        return resultat;
    }
    
    public static boolean passwordFieldHarVarde(JPasswordField rutaAttKolla)
    {
        boolean resultat = true;
        char[] los = rutaAttKolla.getPassword();
        String str = String.valueOf(los);
        if(str.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ange ett lösenord!");
            resultat = false;
            rutaAttKolla.requestFocus();
        }       
        
        return resultat;
    }

    public static boolean convertStringToInt(JTextField ValjaRasArv){
       boolean resultat = true;
       
       try{   
           Integer.parseInt(ValjaRasArv.getText());
           if(Integer.parseInt(ValjaRasArv.getText()) <= 0) {
            resultat = false;
                JOptionPane.showMessageDialog(null, "Du måste ha ett värde som är större än 0.");
           }
       } catch (NumberFormatException e) {
           resultat = false;
           JOptionPane.showMessageDialog(null, "Ange ett heltal.");
       }
        return resultat;
    }
    
    public static boolean passwordSix(JPasswordField e){
        boolean sant = true;
        if(e.getText().length() > 6){
            sant = false;
            JOptionPane.showMessageDialog(null, "Ditt lösenord måste vara mindre 7");
        }
        return sant;
    }
    public static boolean onlyNumbersPhone(JTextField phone){
        boolean sant = true;
        if(!phone.getText().toString().matches("^[0-9]*$")){
            sant = false;
            JOptionPane.showMessageDialog(null, "Ditt telefonnummer får bara innehålla siffror");
        } else if(phone.getText().toString().length() >= 31) {
            JOptionPane.showMessageDialog(null, "Max 30 karaktärer.");
            sant = false;
        }
        return sant;
    }
    
    public static boolean matcharInteNamn(ArrayList<String> arry, String compare){
        boolean inteDubbla = true;
        if(arry.contains(compare)){
        inteDubbla = false;
        JOptionPane.showMessageDialog(null, "Det finns redan en agent med namnet " + compare);
    }
        return inteDubbla;
    }
}
