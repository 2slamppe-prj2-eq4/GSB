/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.modele.metier;

import modele.metier.Secteur;

/**
 *
 * @author btssio
 */
public class TestSecteur {
     public static void main(String[] args) {
        Secteur sec=null;
        //Test n°1: instanciation et accesseurs
        System.out.println("\nTest n°1: instanciation et accesseurs");
        sec = new Secteur("N","Nord");
        System.out.println("Etat du secteur: " + sec);
        
        // Test n°2 : mutateurs
        System.out.println("\nTest n°2 : mutateurs");
        sec.setCodeSec("S");
        sec.setLibelleSec("Sud");
        System.out.println("Etat du secteur : " + sec);
    }
}
