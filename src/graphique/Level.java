/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author chrisictiantelemaque
 */
public class Level {

    private JFrame levelFenetre;
    private JButton facile;
    private JButton moyen;
    private JButton difficile;
    private JButton extreme;
    private JLabel instructionDuJeu;
    private JPanel panel;
    private ActionListener paramFacile;
    private ActionListener paramMoyen;
    private ActionListener paramDifficile;
    private ActionListener paramExtreme;
    private String nomJouer;

    public Level(String nom) {
        
        nomJouer = nom;

        levelFenetre = new JFrame("Jeu Arithmetique");
        levelFenetre.setBounds(400, 200, 600, 500);
        levelFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelFenetre.setLayout(null);

        instructionDuJeu = new JLabel("Bienvenue "+nomJouer);
        instructionDuJeu.setBounds(230, 0, 400, 40);
        instructionDuJeu.setFont(new Font("Times", Font.ITALIC, 26));

        facile = new JButton("Facile");
        facile.setBounds(60, 100, 100, 20);
        facile.setFont(new Font("Times", Font.ITALIC, 18));

        moyen = new JButton("Moyen");
        moyen.setBounds(60, 200, 100, 20);
        moyen.setFont(new Font("Times", Font.ITALIC, 18));

        difficile = new JButton("Difficile");
        difficile.setBounds(60, 300, 100, 20);
        difficile.setFont(new Font("Times", Font.ITALIC, 18));

        extreme = new JButton("Extreme");
        extreme.setBounds(60, 400, 100, 20);
        extreme.setFont(new Font("Times", Font.ITALIC, 18));

        panel = (JPanel) levelFenetre.getContentPane();

        paramFacile = facile();
        paramMoyen = moyen();
        paramDifficile = difficile();
        paramExtreme = extreme();
    }

    

    public void AjouterContenu() {

        facile.addActionListener(paramFacile);
        moyen.addActionListener(paramMoyen);
        difficile.addActionListener(paramDifficile);
        extreme.addActionListener(paramExtreme);

        panel.add(facile);
        panel.add(moyen);
        panel.add(difficile);
        panel.add(extreme);
        panel.add(instructionDuJeu);


    }

    public void AfficherFenetre() {
        levelFenetre.setVisible(true);
    }

    public ActionListener facile() {
        final Jouer jouer = new Jouer(1);


        ActionListener demarer = new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                levelFenetre.dispose();
                jouer.setNomJoueur(nomJouer);
                jouer.AjouterContenu();
                jouer.AfficherFenetre();
                jouer.jouer();
            }
        };

        return demarer;

    }

    public ActionListener moyen() {

        final Jouer jouer = new Jouer(3);


        ActionListener demarer = new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                levelFenetre.dispose();
                jouer.setNomJoueur(nomJouer);
                jouer.AjouterContenu();
                jouer.AfficherFenetre();
                jouer.jouer();
            }
        };

        return demarer;

    }

    public ActionListener difficile() {

        final Jouer jouer = new Jouer(4);


        ActionListener demarer = new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                levelFenetre.dispose();
                jouer.setNomJoueur(nomJouer);
                jouer.AjouterContenu();
                jouer.AfficherFenetre();
                jouer.jouer();
            }
        };

        return demarer;

    }

    public ActionListener extreme() {

        final Jouer jouer = new Jouer(5);


        ActionListener demarer = new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                levelFenetre.dispose();
                jouer.setNomJoueur(nomJouer);
                jouer.AjouterContenu();
                jouer.AfficherFenetre();
                jouer.jouer();
            }
        };

        return demarer;

    }
}
