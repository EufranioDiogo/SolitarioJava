package main;

import java.awt.Graphics;
import javax.smartcardio.Card;

/**
 *
 * @Autor ed
 * Free Use - Livre_Uso
 */
public class BaralhoSegundario extends Pilha{
    
    public BaralhoSegundario(int x, int y) {
        super(x, y);
        super.setSize(72, 96);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.isEmpty()) {
            g.drawImage(Carta.getCardOutline(), 0, 0, 72, this.getHeight(), this);
        }else {
            g.drawImage(this.top().getCardImage(), 0, 0, 72, this.getHeight(), this);
        }

    }
	
}
