package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import javax.swing.JPanel;

public class PainelDoJogo extends JPanel
{
    // Restruturando
    protected static int deslocamentoEmX = 90;
    public static Point POSICAO_BARALHO_PRINCIPAL = new Point(560, 20);
    public static Point POSICAO_INICIAL_TABULEIRO = new Point(20, 150);
    private static final int DESLOCAMENTO_TABULEIRO = 90;
    private static BaralhoPrincipal baralhoPrincipal;
    private static BaralhoSegundario baralhoSecundario;
    private static SolucaoBaralho[] solucaoBaralho;
    private static Tabuleiro[] tabuleiro;
    

    public PainelDoJogo()
    {
        super.setBackground(new Color(14, 121, 28));
        super.setLayout(null);
        this.construirEstruturaInicialJogo();
        OuvidorDoEventoDoMouse ouvidorDeEventos = new OuvidorDoEventoDoMouse();
        super.addMouseListener(ouvidorDeEventos);
        super.addMouseMotionListener(ouvidorDeEventos);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(0, 203, 63));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    
     public void construirEstruturaInicialJogo()
     {
        baralhoPrincipal = new BaralhoPrincipal(POSICAO_BARALHO_PRINCIPAL.x, POSICAO_BARALHO_PRINCIPAL.y);
        add(baralhoPrincipal);
        baralhoSecundario = new BaralhoSegundario(POSICAO_BARALHO_PRINCIPAL.x - deslocamentoEmX, POSICAO_BARALHO_PRINCIPAL.y);
        add(baralhoSecundario);
        solucaoBaralho = new SolucaoBaralho[4];

        for(int i = 0; i < solucaoBaralho.length; ++i) {
            solucaoBaralho[i] = new SolucaoBaralho(20 + deslocamentoEmX * i, 20, i + 1);
            add(solucaoBaralho[i]);
        }

        tabuleiro = new Tabuleiro[7];
        for(int tableauIndex = 1; tableauIndex <= tabuleiro.length; ++tableauIndex) {
            tabuleiro[tableauIndex - 1] = new Tabuleiro(POSICAO_INICIAL_TABULEIRO.x + DESLOCAMENTO_TABULEIRO * (tableauIndex - 1),
                            POSICAO_INICIAL_TABULEIRO.y,
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