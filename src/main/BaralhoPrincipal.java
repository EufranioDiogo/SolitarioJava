package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.smartcardio.Card;

/**
 *
 * @Autor ed
 * Free Use - Livre_Uso
 */
public class BaralhoPrincipal extends Pilha {
    public char type [] = {'d', 'h', 's', 'c'};
    public List<Carta> listaAuxiliar = new ArrayList<>();
    
    public BaralhoPrincipal(int x, int y){
        super(x, y);
        super.setSize(72, 96);
        for(char suit : type) {
            push(new Carta().generateCarta(suit));
        }

        while (this.tamanho() > 0) {
            listaAuxiliar.add(pop());
        }
        
        Collections.shuffle(listaAuxiliar);
        
        while(listaAuxiliar.size() > 0) {
            push(listaAuxiliar.remove(0));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.WHITE);
        g2d.drawRect(0, 0, 72, this.getHeight());

        if (!isEmpty()) {
            g2d.drawImage(Carta.getCardBack(), 0, 0, 72, this.getHeight(), this);
        }
    }
}
