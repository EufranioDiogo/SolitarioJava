
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
        static protected DrawPanel gamePanel;
        
	public static final int PANEL_WIDTH = 640, PANEL_HEIGHT = 500;
	
	public Main() 
        {
            setTitle("Solitário");
            setSize(PANEL_WIDTH, PANEL_HEIGHT);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gamePanel = new DrawPanel();
            gamePanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            add(gamePanel);
            setResizable(false);
            setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new Main().setVisible(true);
	}
}
