/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.modele.metier;


import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.metier.Labo;

/**
 *
 * @author btssio
 */
public class TestLabo {
    public static void main(String[] args) {
        Labo lab=null;
        //Test n°1: instanciation et accesseurs
        System.out.println("\nTest n°1: instanciation et accesseurs");
        lab = new Labo("GY","Gyverni","Merss Suzanne");
        System.out.println("Etat du labo: " + lab);
        
        // Test n°2 : mutateurs
        System.out.println("\nTest n°2 : mutateurs");
        lab.setCodeLabo("BC");
        lab.setNomLabo("Bichat");
        lab.setChefVente("Haykana Yosaku");
        System.out.println("Etat du labo : " + lab);
    }
}
