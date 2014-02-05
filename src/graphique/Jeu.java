/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author chrisictiantelemaque
 */
public class Jeu {

    private JFrame jeuFenetre;
    private JButton demarrer;
    private JButton quitter;
    private JLabel titreDuJeu;
    private JLabel imageLabel;
    private JLabel nomJoueurLabel;
    private JTextField champTexteNomJoueur;
    private JPanel panel;
    private ActionListener paramDemarer;
    private ActionListener paramQuitter;
    private String nomJouer;

    public Jeu() {

        nomJouer = "Joueur";
        
        jeuFenetre = new JFrame("Jeu Arithmetique");
        jeuFenetre.setBounds(400, 200, 600, 500);
        jeuFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jeuFenetre.setLayout(null);

        demarrer = new JButton("Demarrer");
        demarrer.setBounds(60, 300, 85, 20);
        demarrer.setFont(new Font("Times", Font.ITALIC, 14));

        quitter = new JButton("Quitter");
        quitter.setBounds(60, 350, 85, 20);
        quitter.setFont(new Font("Times", Font.ITALIC, 14));

        panel = (JPanel) jeuFenetre.getContentPane();

        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 600, 500);
        imageLabel.setIcon(new ImageIcon("./src/image/love math.jpg"));
        // source image http://www.mtlsd.org/washington_elementary/images/love%20math.jpg


        titreDuJeu = new JLabel("Jeu Arithmetique");
        titreDuJeu.setBounds(230, 0, 200, 40);
        titreDuJeu.setFont(new Font("Times", Font.ITALIC, 26));

        nomJoueurLabel = new JLabel("Tappez votre nom");
        nomJoueurLabel.setBounds(60, 410, 200, 20);
        nomJoueurLabel.setFont(new Font("Times", Font.ITALIC, 18));

        champTexteNomJoueur = new JTextField("");
        champTexteNomJoueur.setBounds(200, 400, 150, 40);
        champTexteNomJoueur.setEditable(true);

        paramDemarer = demarrer();
        paramQuitter = quitter();

    }

    /*
     * ajoute des actions dans les bouttons
     * ajoute les composants dans le panel
     */
    public void AjouterContenu() {

        quitter.addActionListener(paramQuitter);
        demarrer.addActionListener(paramDemarer);

        panel.add(demarrer);
        panel.add(quitter);
        panel.add(titreDuJeu);
        panel.add(nomJoueurLabel);
        panel.add(champTexteNomJoueur);


        panel.add(imageLabel);


    }

    /*
     * affiche l'ensemble de la fenetre  jeuFenetre
     */
    public void AfficherFenetre() {
        jeuFenetre.setVisible(true);
    }

    /*
     * ferme la fenetre jeuFenetre
     * @return un ActionListener
     */
    public ActionListener quitter() {

        ActionListener quitter = new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                jeuFenetre.dispose();
            }
        };

        return quitter;

    }

    /*
     * Ouvre une nouvelle fenetre qui contient les nveau du jeu
     * @return un ActionListener
     */
    public ActionListener demarrer() { 

        ActionListener demarer = new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                
                 String verifNom =  champTexteNomJoueur.getText();
                 if(verifNom.length() != 0)nomJouer = verifNom;
                 final Level niveau = new Level(nomJouer);
                 niveau.AjouterContenu();
                jeuFenetre.dispose();
                niveau.AfficherFenetre();
            }
        };

        return demarer;

    }
    
    
    
    
}
