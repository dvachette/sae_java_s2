package iut.g3s2e2.demo;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;




/**
 * A simple Swing demo.
 */
public class DemoSwing {
    /**
     * Run the specified example.
     * @param example the example to run
     * Must be one of the following:
     * - frame
     * - label
     * - button
     * @see #exampleFrame()
     * @see #exampleLabel()
     * @see #exampleButton()
     */
    public static void run(String example) {
        switch (example) {
            case "frame":
                SwingUtilities.invokeLater(() -> {
                    exampleFrame();
                });
                break;
            case "label":
                SwingUtilities.invokeLater(() -> {
                    exampleLabel();
                });
                break;
            case "button":
                SwingUtilities.invokeLater(() -> {
                    exampleButton();
                });
                break;
            case "textField":
                SwingUtilities.invokeLater(() -> {
                    exampleTextField();
                });
                break;
            default:
                System.out.println("Unknown example: " + example);
        }
    }

    public static void exampleFrame() {
        JFrame frame = new JFrame("Demo Swing - basic frame"); // Création d'une fenêtre (JFrame) avec un titre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lorsque l'utilisateur ferme la fenêtre, l'application s'arrête
        frame.setSize(250, 250); // Configuration de la taille de la fenêtre
        frame.setVisible(true); // Affichage de la fenêtre
    }

    public static void exampleLabel() {
        JFrame frame = new JFrame("Demo Swing - label"); // Création d'une fenêtre (JFrame) avec un titre
        JLabel label = new JLabel("Hello, world!"); // Création d'un label (JLabel) avec un texte
        frame.add(label); // Ajout du label à la fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lorsque l'utilisateur ferme la fenêtre, l'application s'arrête
        frame.setSize(250, 250); // Configuration de la taille de la fenêtre
        frame.setVisible(true); // Affichage de la fenêtre
    }

    public static void exampleButton() {
        JFrame frame = new JFrame("Demo Swing - button"); // Création d'une fenêtre (JFrame) avec un titre
        JButton button = new JButton("Click me!"); // Création d'un bouton (JButton) avec un texte
        button.addActionListener(e -> { // Ajout d'un écouteur d'événement pour le clic sur le bouton (quand le bouton est cliqué, la fonction suivante est exécutée)
            System.out.println("Button clicked!"); // Affichage d'un message dans la console
        });
        frame.add(button); // Ajout du bouton à la fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lorsque l'utilisateur ferme la fenêtre, l'application s'arrête
        frame.setSize(250, 250); // Configuration de la taille de la fenêtre
        frame.setVisible(true); // Affichage de la fenêtre
    }

    public static void exampleTextField() {
        JFrame frame = new JFrame("Demo Swing - text field"); // Création d'une fenêtre (JFrame) avec un titre
        JTextField textField = new JTextField("Type here"); // Création d'un champ de texte (JTextField) avec un texte par défaut
        frame.add(textField); // Ajout du champ de texte à la fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lorsque l'utilisateur ferme la fenêtre, l'application s'arrête
        frame.setSize(250, 250); // Configuration de la taille de la fenêtre
        frame.setVisible(true); // Affichage de la fenêtre
    }

    
}
