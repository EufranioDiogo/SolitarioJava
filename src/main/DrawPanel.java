package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
    // Restruturando
    protected static int XShift = 80;
    public static Point DECK_POSITION = new Point(500, 20);
    public static Point TABLEAU_POSITION = new Point(20, 150);
    private static final int TABLEAU_OFFSET = 80;
    private static BaralhoPrincipal baralhoPrincipal;
    private static BaralhoSegundario baralhoSecundario;
    private static SolucaoBaralho[] solucaoBaralho;
    private static Tabuleiro[] tabuleiro;
    

    public DrawPanel()
    {
        super.setBackground(new Color(14, 121, 28));
        super.setLayout(null);
        this.construirCartasBaixo();
        GameMoveListener l = new GameMoveListener();
        addMouseListener(l);
        addMouseMotionListener(l);
    }
    
    @Override
    public void paintComponent( Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(0, 203, 63));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    
     public void construirCartasBaixo()
     {
        baralhoPrincipal = new BaralhoPrincipal(DECK_POSITION.x, DECK_POSITION.y);
        add(baralhoPrincipal);
        baralhoSecundario = new BaralhoSegundario(DECK_POSITION.x - XShift, DECK_POSITION.y);
        add(baralhoSecundario);
        solucaoBaralho = new SolucaoBaralho[4];

        for(int i = 0; i < solucaoBaralho.length; ++i) {
            solucaoBaralho[i] = new SolucaoBaralho(20 + XShift * i, 20, i + 1);
            add(solucaoBaralho[i]);
        }

        tabuleiro = new Tabuleiro[7];
        for(int tableauIndex = 1; tableauIndex <= tabuleiro.length; ++tableauIndex) {
            tabuleiro[tableauIndex - 1] = new Tabuleiro(TABLEAU_POSITION.x + TABLEAU_OFFSET * (tableauIndex - 1),
                            TABLEAU_POSITION.y,
                            tableauIndex);
            add(tabuleiro[tableauIndex - 1]);
        }
    }
    public static SolucaoBaralho[] getSolucaoBaralho() {
        return solucaoBaralho;
    }
    public static BaralhoSegundario getBaralhoSegundario() {
        return baralhoSecundario;
    }
    public static BaralhoPrincipal getBaralhoPrincipal() {
        return baralhoPrincipal;
    }
    
}