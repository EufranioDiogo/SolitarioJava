package main;

import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class OuvidorDoEventoDoMouse extends MouseInputAdapter {
	
	private final BaralhoPrincipal baralhoPrincipal = PainelDoJogo.getBaralhoPrincipal();
	private BaralhoSegundario baralhoSecundario = null;
	private Tabuleiro pilhaDoTabuleiroSelecionado = null;
	private SolucaoBaralho solucaoBaralhoSelecionada = null;
	private Carta cartaSelecionado = null;
        private int indexDaCartaClicada;

        // Está precionando o mouse
	@Override
	public void mousePressed(MouseEvent evento) {
            Component elementoPressionado = evento.getComponent().getComponentAt(evento.getPoint());

            
            if(elementoPressionado instanceof SolucaoBaralho) {
                 solucaoBaralhoSelecionada = (SolucaoBaralho) elementoPressionado;
                 pilhaDoTabuleiroSelecionado = null;
                 baralhoSecundario = null;
                 cartaSelecionado = solucaoBaralhoSelecionada.top();
             }else if(elementoPressionado instanceof Tabuleiro) {
                 pilhaDoTabuleiroSelecionado = (Tabuleiro) elementoPressionado;
                 baralhoSecundario = null;
                 indexDaCartaClicada = pilhaDoTabuleiroSelecionado.getCartaClicada(evento.getY());

                 if (indexDaCartaClicada > -1) {
                     cartaSelecionado = pilhaDoTabuleiroSelecionado.pilha[indexDaCartaClicada];


                     for(SolucaoBaralho solucaoBaralho : PainelDoJogo.getSolucaoBaralho()) {
                         if(pilhaDoTabuleiroSelecionado.movimentarCartaDoTabuleiroParaSolucaoBaralho(solucaoBaralho, cartaSelecionado)) {
                             pilhaDoTabuleiroSelecionado = null;
                             break;
                         }
                     }
                 }
             }else if(elementoPressionado instanceof BaralhoPrincipal) { // Movimento de retirada das cartas do baralho principal
                 pilhaDoTabuleiroSelecionado = null;
                 if(!baralhoPrincipal.isEmpty()) {
                     baralhoSecundario = PainelDoJogo.getBaralhoSegundario();
                     baralhoSecundario.push(baralhoPrincipal.pop());
                     baralhoSecundario.top().showFace();
                     baralhoSecundario.top().setCartaVirada(false);
                     baralhoSecundario.repaint();
                 } else {
                     baralhoSecundario = PainelDoJogo.getBaralhoSegundario();
                     Carta cartaAuxiliar;

                     if (baralhoSecundario.tamanho() > 0) {
                         while(baralhoSecundario.tamanho() > 0) {
                             cartaAuxiliar = baralhoSecundario.pop();
                             cartaAuxiliar.setCartaVirada(true);
                             baralhoPrincipal.push(cartaAuxiliar);
                         }
                     }
                     baralhoSecundario.repaint();
                 }
             }else if(elementoPressionado instanceof BaralhoSegundario) {
                 pilhaDoTabuleiroSelecionado = null;
                 baralhoSecundario = PainelDoJogo.getBaralhoSegundario();
                 cartaSelecionado = baralhoSecundario.top();

                 // Conversar com o grupo
                 if(cartaSelecionado != null) {
                     for(SolucaoBaralho solucaoBaralho : PainelDoJogo.getSolucaoBaralho()) {
                         if(solucaoBaralho.movimentarCartaDoBaralhoSecundarioParaSolucaoBaralho(baralhoSecundario, cartaSelecionado)) {
                             solucaoBaralho = null;
                             baralhoSecundario.pop();
                             break;
                         }
                     }
                 }
             }
             evento.getComponent().repaint();
	}

        //Soltou o mouse
	@Override
	public void mouseReleased(MouseEvent evento) {
            if(cartaSelecionado != null) {
                
                Component elementoSelecionado = evento.getComponent().getComponentAt(evento.getPoint());
                
                if (elementoSelecionado instanceof Tabuleiro) {
                    if (baralhoSecundario != null) {
                        Tabuleiro tabuleiroDestino = (Tabuleiro) elementoSelecionado;
                        if(!baralhoSecundario.isEmpty()) {
                            tabuleiroDestino.movimentaCartaDeBaralhoSecundarioParaTabuleiroDestino(baralhoSecundario, cartaSelecionado);
                        }
                        baralhoSecundario.repaint();
                        tabuleiroDestino.repaint();
                    } else if (pilhaDoTabuleiroSelecionado != null) {
                        Tabuleiro tabuleiroOrigem = pilhaDoTabuleiroSelecionado;
                        Tabuleiro tabuleiroDestino = (Tabuleiro) elementoSelecionado;
                        tabuleiroOrigem.movimentaCartaDeTabuleiroOrigemParaTabuleiroDestino(tabuleiroDestino, cartaSelecionado, indexDaCartaClicada);
                        
                        tabuleiroOrigem.repaint();
                        tabuleiroDestino.repaint();
                    } else if (solucaoBaralhoSelecionada != null) { 
                        SolucaoBaralho solucaoBaralhoOrigem = solucaoBaralhoSelecionada;
                        Tabuleiro tabuleiroDestino = (Tabuleiro) elementoSelecionado;
                        solucaoBaralhoOrigem.movimentaCartaDeSolucaoBaralhoParaTabuleiroDestino(tabuleiroDestino, cartaSelecionado);
                        solucaoBaralhoOrigem.repaint();
                        tabuleiroDestino.repaint();
                    }
                }
            }

            evento.getComponent().repaint();
            cartaSelecionado = null;
            solucaoBaralhoSelecionada = null;
            pilhaDoTabuleiroSelecionado = null;
            baralhoSecundario = null;
	}
	
	

}
