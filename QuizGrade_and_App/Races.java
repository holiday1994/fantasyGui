/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizGrade_and_App.QuizGrade_and_App;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author huntw
 */
public class Races {
    int str;
    int intt;
    int agl;
    String name;
    
    public Races(String name, int str, int intt, int agl){
        this.name = "mike";
        if(str + intt + agl == 5){
        this.name = name;
        this.str = str;
        this.intt = intt;
        this.agl = agl;
        }
        
        else{
            JFrame frame = new JFrame("JOptionPane showMessageDialog example");
            JOptionPane.showMessageDialog(frame, "Your stats did not balance out to a +5, please re-enter");
            System.exit(0);
        }
    }
    
    public String getName(){
        return name;
    }
}
