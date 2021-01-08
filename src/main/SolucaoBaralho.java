package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 *
 * @Autor ed
 * Free Use - Livre_Uso
 */
public class SolucaoBaralho extends Pilha {
    private int suit;
    
    public SolucaoBaralho(int x, int y, int i) {
        super(x, y);
        super.setSize(72, 96);
        this.suit = i;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        if(this.isEmpty()) {
            g2d.setColor(Color.WHITE);
            g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
        }else {
            g.drawImage(this.top().getCardImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public boolean movimentarCartaDoBaralhoSecundarioParaSolucaoBaralho(BaralhoSecundario source, Carta card) {
        if(accepts(card)) {
            this.push(card);
            source = null;
            return true;
        }
        return false;
    }

    public void movimentaCartaDeSolucaoBaralhoParaTabuleiroDestino(Tabuleiro destination, Carta card) {
        if(destination.accepts(card)) {
            destination.push(this.pop());
        }
    }
    public boolean accepts(Carta carta) {
        if(!this.isEmpty()) {
            return this.top().getValue() == carta.getValue() - 1 && this.top().getSimbolo() == carta.getSimbolo();
        }
        return carta.getValue() == 1;
    }

    private boolean intToSuit(String pSuit) {
        if (pSuit.equals("c")) {
            return this.suit == 3;
        } else if (pSuit.equals("s")) {
            return this.suit == 1;
        } else if (pSuit.equals("h")) {
            return this.suit == 2;
        } else if (pSuit.equals("d")) {
            return this.suit == 4;
        }
        return false;
    }

}
