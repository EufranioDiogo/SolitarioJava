
package main;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @Autor ed
 * Free Use - Livre_Uso
 */
public class Main extends JFrame 
{
        static protected PainelDoJogo gamePanel;
        
	public static final int PANEL_WIDTH = 700, PANEL_HEIGHT = 700;
	
	public Main() 
        {
            setTitle("Solitário");
            setSize(PANEL_WIDTH, PANEL_HEIGHT);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gamePanel = new PainelDoJogo();
            gamePanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            add(gamePanel);
            setResizable(false);
            setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new Main().setVisible(true);
	}
}
