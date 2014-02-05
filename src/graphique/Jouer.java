/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author chrisictiantelemaque
 */
public class Jouer {

    private JFrame jouerFenetre;
    private JPanel panel;
    private JLabel score;
    private JLabel question;
    private JLabel reponse;
    private JLabel valider;
    private JLabel mention;
    private JTextField champTexteScore;
    private JTextField champTexteQuestion;
    private JTextField champTexteResponse;
    private JTextField champTexteValider;
    private JTextField champTexteMention;
    private JButton quitter;
    private ActionListener paramQuitter, paramReponse;
    private int level = 0;
    private int operande1 = 0, operande2 = 0, operande3 = 0;
    private int nbreOperateur = 1, reponseCorrecte = 0;
    private boolean nextChance = false, nextTurn = false;
    private int chance = 0, compteur = 0;
    private char operation1 = ' ', operation2 = ' ';
    private final int NBRE_OPERATEUR_MIN = 1;
    private final int NBRE_OPERATEUR_MAX = 2;
    private final String OPERATION_DE_POIDS_FORT = "*/%";
    private String nameUser;

    public Jouer(int difficulte) {
        level = difficulte;
        jouerFenetre = new JFrame("Jeu Arithmetique");
        jouerFenetre.setBounds(400, 200, 600, 500);
        jouerFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jouerFenetre.setLayout(null);

        score = new JLabel("Score");
        score.setBounds(10, 0, 200, 40);
        score.setFont(new Font("Times", Font.ITALIC, 26));

        champTexteScore = new JTextField("0");
        champTexteScore.setBounds(80, 0, 100, 40);
        champTexteScore.setEditable(false);

        mention = new JLabel("Mention");
        mention.setBounds(300, 0, 200, 40);
        mention.setFont(new Font("Times", Font.ITALIC, 26));

        champTexteMention = new JTextField("");
        champTexteMention.setBounds(400, 0, 100, 40);
        champTexteMention.setEditable(false);

        question = new JLabel("Question");
        question.setBounds(40, 150, 200, 40);
        question.setFont(new Font("Times", Font.ITALIC, 26));

        champTexteQuestion = new JTextField("");
        champTexteQuestion.setBounds(150, 150, 200, 40);
        champTexteQuestion.setEditable(false);

        reponse = new JLabel("Reponse");
        reponse.setBounds(40, 200, 200, 40);
        reponse.setFont(new Font("Times", Font.ITALIC, 26));

        champTexteResponse = new JTextField("");
        champTexteResponse.setBounds(150, 200, 200, 40);
        champTexteResponse.setEditable(true);

        valider = new JLabel("Valider");
        valider.setBounds(40, 250, 200, 40);
        valider.setFont(new Font("Times", Font.ITALIC, 26));

        champTexteValider = new JTextField("");
        champTexteValider.setBounds(150, 250, 400, 40);
        champTexteValider.setEditable(false);

        quitter = new JButton("Quitter");
        quitter.setBounds(500, 450, 85, 20);
        quitter.setFont(new Font("Times", Font.ITALIC, 14));

        panel = (JPanel) jouerFenetre.getContentPane();

        paramQuitter = quitter();
        paramReponse = repondre();
    }
    
    public void setNomJoueur(String nom) {
        nameUser = nom;
    }

    /*
     * ajoute des actions dans les bouttons et les chanmps de texte
     * ajoute les composants dans le panel
     */
    public void AjouterContenu() {

        quitter.addActionListener(paramQuitter);
        champTexteResponse.addActionListener(paramReponse);


        panel.add(score);
        panel.add(quitter);
        panel.add(question);
        panel.add(reponse);
        panel.add(valider);
        panel.add(champTexteQuestion);
        panel.add(champTexteResponse);
        panel.add(champTexteScore);
        panel.add(champTexteValider);
        panel.add(champTexteMention);
        panel.add(mention);



    }

    /*
     * affiche l'ensemble de la fenetre  jouerFenetre
     */
    public void AfficherFenetre() {
        jouerFenetre.setVisible(true);
    }

    /*
     * ferme la fenetre jouerFenetre
     * @return un ActionListener
     */
    public ActionListener quitter() {

        ActionListener quitter = new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                jouerFenetre.dispose();
            }
        };

        return quitter;

    }
    
    /*
     * Modifie la mention selon le score
     */
    public void mention(){
        float mention = (Integer.parseInt(champTexteScore.getText()) / 3) / compteur;
                        if (mention < 0.45) {
                            champTexteMention.setText("mediocre");
                        } else if ((mention >= 0.45) && (mention < 0.60)) {
                            champTexteMention.setText("passable");
                        } else if ((mention >= 0.60) && (mention < 0.75)) {
                            champTexteMention.setText("bien");

                        } else if ((mention >= 0.75) && (mention < 0.90)) {
                            champTexteMention.setText("tres bien");

                        } else if (mention >= 0.90) {
                            champTexteMention.setText("excellent");

                        }
    }

    /*
     * Permet au joueur de saisir sa reponse dans un champ de texte
     * @return un ActionListener
     */
    public ActionListener repondre() {

        ++compteur;
        ActionListener reponse;
        reponse = new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {

                try {
                    ++chance;
                    reponseCorrecte = determinerCorrection(operande1, operande2,
                            operande3, nbreOperateur, operation1, operation2);

                    int rcorrect = Integer.parseInt(champTexteResponse.getText());
                    if (rcorrect == reponseCorrecte) {

                        champTexteValider.setText("Bravo " + nameUser
                                + " ! Bonne reponse !");

                        champTexteScore.setText("" + (Integer.parseInt(champTexteScore.getText()) + 4 - chance));
                        mention();
                      
                        afficherQuestion();
                        chance = 0;

                    } else {
                        champTexteValider.setText("Desole " + nameUser
                                + " ce n'est pas la bonne reponse.");
                        
                    }
                    if (chance == 3) {
                        champTexteValider.setText(nameUser + " la reponse est : "
                                + reponseCorrecte);
                        mention();
                        chance = 0;
                        afficherQuestion();
                    }
                    champTexteResponse.setText("");

                } catch (Exception e) {
                    champTexteValider.setText("Reponse non numerique.");
                    champTexteResponse.setText("");
                    chance = 0;
                }
            }
        };
        return reponse;
    }

    /*
     * Affiche les equations.
     */
    public void afficherQuestion() {

        if (level == JeuArithmetique.getExtreme()) {
            nbreOperateur = nombreOperateur();
            switch (nbreOperateur) {
                case 1:
                    operation1 = JeuArithmetique.operationAuHasard();
                    if (operation1 == '^') {
                        operande1 = JeuArithmetique.operandeExposantAuHasard();
                        operande2 = JeuArithmetique.operandeCareeCubeAuHasard();
                    } else {
                        operande1 = JeuArithmetique.operandeAuHasard();
                        operande2 = JeuArithmetique.operandeAuHasard();
                    }

                    champTexteQuestion.setText(operande1 + " " + operation1
                            + " " + operande2 + " = ?");
                    break;
                case 2:
                    do {
                        operation1 = JeuArithmetique.operationAuHasard();
                        operation2 = JeuArithmetique.operationAuHasard();
                    } while (operation1 == '^' || operation2 == '^');
                    operande1 = JeuArithmetique.operandeAuHasard();
                    operande2 = JeuArithmetique.operandeAuHasard();
                    operande3 = JeuArithmetique.operandeAuHasard();
                    champTexteQuestion.setText(operande1 + " " + operation1
                            + " " + operande2 + " " + operation2
                            + " " + operande3 + " = ?");
                    break;
            }
        } else {
            operande1 = JeuArithmetique.operandeAuHasard();
            operande2 = JeuArithmetique.operandeAuHasard();
            operation1 = JeuArithmetique.operationAuHasard();
            champTexteQuestion.setText(operande1 + " " + operation1
                    + " " + operande2 + " = ?");
        }

    }

    /*
     * Lance le jeu selon le degre de difficulte saisie
     */
    public void jouer() {

        switch (level) {
            case 1:
                JeuArithmetique.choisirDegreDifficulte(
                        JeuArithmetique.getFacile());
                break;
            case 2:
                JeuArithmetique.choisirDegreDifficulte(
                        JeuArithmetique.getMoyen());
                break;
            case 3:
                JeuArithmetique.choisirDegreDifficulte(
                        JeuArithmetique.getDifficile());
                break;
            case 4:
                JeuArithmetique.choisirDegreDifficulte(
                        JeuArithmetique.getExtreme());
                break;
        }

        afficherQuestion();

    }

    
    public int nombreOperateur() {
        Random rand = new Random();
        return rand.nextInt(NBRE_OPERATEUR_MAX - NBRE_OPERATEUR_MIN + 1)
                + NBRE_OPERATEUR_MIN;
    }

    /**
     * Resous l equation demandee a l utilisateur
     *
     * @param operande1 Premier element de l operation (nombre entier)
     * @param operande2 Second element de l operation (nombre entier)
     * @param operande3 Troiseme element de l operation (nombre entier)
     * @param nbreOperateur Nombre d operateur dans l operation (1 ou 2)
     * @param operation1 Le symbole de la premiere operation (caractere)
     * @param operation2 Le symbole de la seconde operation (caractere)
     * @return
     */
    public int determinerCorrection(int operande1, int operande2,
            int operande3, int nbreOperateur, char operation1, char operation2) {
        int resultat = 0;

        switch (nbreOperateur) {
            case 1:
                resultat = determinerCorrectionUnOperateur(operande1, operande2, operation1);
                break;
            case 2:
                if (OPERATION_DE_POIDS_FORT.contains("" + operation1)) {
                    resultat = determinerCorrectionDeuxOperateurs(
                            operande1, operande2, operande3, operation1, operation2);
                } else if (OPERATION_DE_POIDS_FORT.contains("" + operation2)) {
                    resultat = determinerCorrectionDeuxOperateurs(
                            operande2, operande3, operande1, operation2, operation1);
                } else {
                    resultat = determinerCorrectionDeuxOperateurs(
                            operande1, operande2, operande3, operation1, operation2);
                }
        }
        return resultat;
    }

    /**
     * Resous l equation posee a l utilisateur (cas avec 1 operateur)
     *
     * @param operande1 Premier element de l operation (nombre entier)
     * @param operande2 Second element de l operation (nombre entier)
     * @param operation1 le symbole de l operation (caractere)
     * @return Un entier qui est le resultat de l operation
     */
    private int determinerCorrectionUnOperateur(int operande1,
            int operande2, char operation1) {
        int resultat = 0;
        switch (operation1) {
            case '+':
                resultat = operande1 + operande2;
                break;
            case '-':
                resultat = operande1 - operande2;
                break;
            case '*':
                resultat = operande1 * operande2;
                break;
            case '/':
                resultat = operande1 / operande2;
                break;
            case '%':
                resultat = operande1 % operande2;
                break;
            case '^':
                resultat = (int) Math.pow(operande1, operande2);
                break;
        }
        return resultat;
    }

    /**
     * Resous l equation posee a l utilisateur (cas avec 2 operateurs)
     *
     * @param operande1 Premier element de l operation (nombre entier)
     * @param operande2 Second element de l operation (nombre entier)
     * @param operande3 Troisieme element de l operation (nombre entier)
     * @param operation1 Premier operateur de l operation (caractere)
     * @param operation2 Second operateur de l operation (caractere)
     * @return Un entier qui est le resultat de l operation
     */
    private int determinerCorrectionDeuxOperateurs(int operande1,
            int operande2, int operande3, char operation1, char operation2) {
        int resultat = 0;
        resultat = determinerCorrectionUnOperateur(operande1, operande2, operation1);
        resultat = determinerCorrectionUnOperateur(resultat, operande3, operation2);
        return resultat;
    }
}
