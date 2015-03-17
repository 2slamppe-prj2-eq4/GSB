/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoException;
import modele.dao.DaoPraticien;
import modele.dao.DaoRapportVisite;
import modele.metier.Praticien;
import modele.metier.RapportVisite;
import vue.VueRapportsDeVisites;

/**
 *
 * @author btssio
 */
public class ControleurRapportsVisites extends CtrlAbstrait{
    private List<RapportVisite> lesRapportsVisites;
    private List<Praticien> lesPraticiens;
    //indice pour charger les informations d'un rapport de visite
    private int indice = 0 ;
    //Format de date de type MM/dd/yy
    DateFormat format = new SimpleDateFormat("MM/dd/yy");
    
    VueRapportsDeVisites vue = new VueRapportsDeVisites(this);
   
    public ControleurRapportsVisites(CtrlPrincipal ctrlPrincipal) {
        super(ctrlPrincipal);
        
        //Ecouteur Bouton fermer
        vue.jButtonFermer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //System.out.println("Coucou");
                    rapportQuitter();
                } catch (Exception ex) {
                    Logger.getLogger(ControleurRapportsVisites.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //récupère la liste des praticiens
        try {
            lesPraticiens = DaoPraticien.selectAll();
            afficherListePraticiens(lesPraticiens);
        } catch (DaoException ex) {
            Logger.getLogger(ControleurRapportsVisites.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //récupère la liste des rapports de visites
        try {
            lesRapportsVisites = DaoRapportVisite.selectAll();
            afficherDetailsRapport(lesRapportsVisites);
        } catch (DaoException ex) {
            Logger.getLogger(ControleurRapportsVisites.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Ecouteur Bouton Details concernant un praticien
        vue.jButtonDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    detailsPraticien();
                } catch (Exception ex) {
                    Logger.getLogger(ControleurRapportsVisites.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //Ecouteur bouton précédent
        vue.jButtonPrecedent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rapportPrecedent();
            }           
        });
        //Ecouteur bouton suivant
        vue.jButtonSuivant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rapportSuivant();
            }           
        });
        
        //Ecouteur sur bouton nouveau
        vue.jButtonNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nouveauRapport();
            }           
        });
    }
    
    
    /**
     * réaction au clic sur le bouton FERMER de la vue
     * 
     *
     * @throws java.lang.Exception
     */
    public void rapportQuitter() throws Exception {
        this.getCtrlPrincipal().action(EnumAction.RAPPORT_RETOUR);
    }
    
    
       /**
     * Liste des Praticiens
     *
     * @param lesPraticiens : Liste de praticiens
     */
    public void afficherListePraticiens(List<Praticien> lesPraticiens) {
        getVue().jComboBoxPraticien.removeAllItems();
        for (Praticien praticien : lesPraticiens) {  
            getVue().jComboBoxPraticien.addItem(praticien.toString());
        }
    }
    
    /**
     * Afficher les informations concernant un rapport de visite sélectionné en fonction de son matricule
     * @param lesRapportsVisites 
     */
    public void afficherDetailsRapport(List<RapportVisite> lesRapportsVisites){
        //Rend les éléments non éditables
        Date date = new Date();
        getVue().jTextFieldNum.setEditable(false);
        getVue().jComboBoxPraticien.setEditable(false);
        getVue().jTextFieldDate.setText(format.format(date));
        getVue().jTextFieldDate.setEditable(false);
        getVue().jTextFieldMotif.setEditable(false);
        getVue().jTextAreaBilan.setEditable(false);
        getVue().jButtonNew.setVisible(true);
        getVue().jButtonEnregistrer.setVisible(false);
        
        //Affichage des informations sur les rapports de visites
        RapportVisite unRapport = lesRapportsVisites.get(indice);
        getVue().jTextFieldNum.setText(Integer.toString(unRapport.getNumRap()));
        getVue().jTextFieldDate.setText(format.format(unRapport.getDate()));
        getVue().jTextFieldMotif.setText(unRapport.getMotif());
        getVue().jTextAreaBilan.setText(unRapport.getBilan());
        Praticien unPraticien = unRapport.getPraticien();
        getVue().jComboBoxPraticien.setSelectedItem(unPraticien.toString());
    }
    
    /**
     * Charge le rapport de visite suivant
     */
    public void rapportSuivant(){
        indice = indice + 1;
        if (indice > lesRapportsVisites.size()-1) {
            indice = 0;
        }
        afficherDetailsRapport(lesRapportsVisites);
    }

    /**
     * Charge le rapport de visite précédent
     */
    public void rapportPrecedent(){
        indice = indice - 1;
        if (indice < 0) {
            indice = lesRapportsVisites.size()-1;
        }
        afficherDetailsRapport(lesRapportsVisites);
    }
    
    public void nouveauRapport(){
        Date date = new Date();
        getVue().jTextFieldNum.setText(" ");
        getVue().jTextFieldNum.setEditable(false);
        getVue().jComboBoxPraticien.setEditable(true);
        getVue().jComboBoxPraticien.setSelectedItem("Aucun");
        getVue().jTextFieldDate.setText(format.format(date));
        getVue().jTextFieldDate.setEditable(true);
        getVue().jTextFieldMotif.setText(" ") ;
        getVue().jTextFieldMotif.setEditable(true);
        getVue().jTextAreaBilan.setText(" ") ;
        getVue().jTextAreaBilan.setEditable(true);
        
        getVue().jButtonNew.setVisible(false);
        getVue().jButtonEnregistrer.setVisible(true);
    }
 
    
    
    /**
     * Affiche les détails sur les praticiens
     */
    public void detailsPraticien(){
          getCtrlPrincipal().action(EnumAction.PRATICIEN_AFFICHER);       
    }
    
    
    
    
    
    
    // ACCESSEURS et MUTATEURS Vue Rapports de visites
    @Override
    public VueRapportsDeVisites getVue() {
        return (VueRapportsDeVisites)vue;
    }

    public void setVue(VueRapportsDeVisites vue) {
        this.vue = vue;
    }
    
    
}
