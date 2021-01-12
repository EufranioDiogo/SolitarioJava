package main;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import javax.swing.ImageIcon;
/**
 *
 * @Autor ed
 * Free Use - Livre_Uso
 */
public class Tabuleiro extends Pilha {
    
    public Tabuleiro(int x, int y, int initSize) {
        super(x, y);
        super.setSize(72, 450);
        super.setOpaque(false);
        Carta cartaAux;
        
        for (int i = 0; i < initSize; ++i) {
            cartaAux = PainelDoJogo.getBaralhoPrincipal().pop();
            push(cartaAux);
        }

        if (initSize > 0) {
            top().showFace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.drawLine(0, 0, this.getWidth(), 0);
        g2d.drawLine(0, 0, 0, 96);
        g2d.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, 96);

        g2d.setPaint(new GradientPaint(36, 0, new Color(255, 255, 255, 160), 36, 60, new Color(0, 0, 0, 0)));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        int cardYPos = 0;
        
        if (!this.isEmpty()) {
            for (int i = 0; i < tamanho(); i++) {
                if (i + 1 == tamanho()) {
                    this.busca(i).setCartaVirada(false);
                }
                g2d.drawImage(this.busca(i).getImage(), 0, cardYPos, 72, 96, this);
                cardYPos += 20;
            }
        }
    }

    public void movimentaCartaDeBaralhoSecundarioParaTabuleiroDestino(BaralhoSecundario source, Carta carta) {
        if (this.accepts(carta)) {
            this.push(source.pop());
        }
        source = null;
    }
    
    public void movimentaCartaDeTabuleiroOrigemParaTabuleiroDestino(Tabuleiro destination, Carta carta, int indexCartaSelecionada) {
        if (!this.isEmpty() || carta.getValue() == 13) {
            if (destination.accepts(carta)) {
                Pilha pilhaAux = new Pilha();
                
                int quantCartasASeremRemovidas = this.tamanho() - indexCartaSelecionada;
                
                while(quantCartasASeremRemovidas > 0) {
                    pilhaAux.push(this.pop());
                    quantCartasASeremRemovidas--;
                }
                
                while(pilhaAux.tamanho() > 0) {
                    destination.push(pilhaAux.pop());
                }
            }
        }

        if(!this.isEmpty()) {
            this.top().setCartaVirada(false);
            this.top().showFace();
        }
    }

    public boolean accepts(Carta carta) {
        if (!this.isEmpty()) {
            return this.top().getValue() == carta.getValue() + 1
            && !this.top().isCor() == carta.isCor();
        }
        return carta.getValue() == 13;
    }

    public boolean movimentarCartaDoTabuleiroParaSolucaoBaralho(SolucaoBaralho destination, Carta carta) {
        if (destination.accepts(carta)) {
            destination.push(this.pop());
            if (!this.isEmpty()) {
                this.top().setCartaVirada(false);
                this.top().showFace();
            }
            return true;
        }
        return false;
    }
    
    public int getCartaClicada(int y) {
        int i = 0;
        
        List<Integer> limitesSuperiores = new ArrayList<>();
        int posicaoDeInicioY = 150;
        int ALTURA_CARTA = 96;
        
        while(i < this.tamanho()) {
            limitesSuperiores.add(posicaoDeInicioY);
            posicaoDeInicioY += 20;
            i++;
        }
        
        int index = 0;
        i = this.tamanho() - 1;
        
        while (i >= 0) {
            if (limitesSuperiores.get(i) < y && y < limitesSuperiores.get(i) + ALTURA_CARTA) {
                index = i;
                break;
            }
            i--; // i++, erro que retornava carta mau selecionada porque ao inves de decrementar o valor de i aumentava e esse
            // não era o objectivo porque o i começa por marcar a ultima carta do baralho com o index tamanho - 1 e depois
            // tinha de recurar para pegar a anterior e assim sucessivamente
        }
        
        if (index > tamanho()) {
            return -1;
        }
        return index;
        
    }
}
