package main;

import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class GameMoveListener extends MouseInputAdapter {
	
	private final BaralhoPrincipal baralhoPrincipal = DrawPanel.getBaralhoPrincipal();
	private BaralhoSegundario baralhoSecundario = null;
	private Tabuleiro pilhaDoTabuleiroSelecionado = null;
	private SolucaoBaralho solucaoBaralhoSelecionada = null;
	private Carta cartaSelecionado = null;
        private int indexDaCartaClicada;

        // Está precionando o mouse
	@Override
	public void mousePressed(MouseEvent e) {
            Component pressedComponent = e.getComponent().getComponentAt(e.getPoint());
            
            try {
               if(pressedComponent instanceof SolucaoBaralho) {
                    solucaoBaralhoSelecionada = (SolucaoBaralho) pressedComponent;
                    pilhaDoTabuleiroSelecionado = null;
                    baralhoSecundario = null;
                    cartaSelecionado = solucaoBaralhoSelecionada.top();
                }else if(pressedComponent instanceof Tabuleiro) {
                    pilhaDoTabuleiroSelecionado = (Tabuleiro) pressedComponent;
                    baralhoSecundario = null;
                    indexDaCartaClicada = pilhaDoTabuleiroSelecionado.getClickedCard(e.getY());
                    
                    if (indexDaCartaClicada > -1) {
                        cartaSelecionado = pilhaDoTabuleiroSelecionado.pilha[indexDaCartaClicada];

                        
                        for(SolucaoBaralho solucaoBaralho : DrawPanel.getSolucaoBaralho()) {
                            if(pilhaDoTabuleiroSelecionado.moveTo(solucaoBaralho, cartaSelecionado)) {
                                pilhaDoTabuleiroSelecionado = null;
                                break;
                            }
                        }
                    }
                }else if(pressedComponent instanceof BaralhoPrincipal) { // Movimento de retirada das cartas do baralho principal
                    pilhaDoTabuleiroSelecionado = null;
                    if(!baralhoPrincipal.isEmpty()) {
                        BaralhoSegundario waste = DrawPanel.getBaralhoSegundario();
                        waste.push(baralhoPrincipal.pop());
                        waste.top().showFace();
                        waste.repaint();
                    } else {
                        BaralhoSegundario baralhoSecundario = DrawPanel.getBaralhoSegundario();

                        if (baralhoSecundario.tamanho() > 0) {
                            while(baralhoSecundario.tamanho() > 0) {
                                baralhoPrincipal.push(baralhoSecundario.pop());
                            }
                        }
                    }
                }else if(pressedComponent instanceof BaralhoSegundario) {
                    pilhaDoTabuleiroSelecionado = null;
                    baralhoSecundario = DrawPanel.getBaralhoSegundario();
                    cartaSelecionado = baralhoSecundario.top();
                    if(cartaSelecionado != null) {
                        for(SolucaoBaralho foundation : DrawPanel.getSolucaoBaralho()) {
                            foundation.moveFromWaste(baralhoSecundario, cartaSelecionado);
                        }
                    }
                }
                e.getComponent().repaint();
            } catch (Exception erro) {
                System.out.println("Carta mau seleciona");
            }
	}

        //Soltou o mouse
	@Override
	public void mouseReleased(MouseEvent e) {
            if(cartaSelecionado != null) {
                
                Component releasedComponent = e.getComponent().getComponentAt(e.getPoint());
                
                if (releasedComponent instanceof Tabuleiro) {
                    if (baralhoSecundario != null) {
                        Tabuleiro destination = (Tabuleiro) releasedComponent;
                        if(!baralhoSecundario.isEmpty()) {
                            destination.moveFromWaste(baralhoSecundario, cartaSelecionado);
                        }
                        baralhoSecundario.repaint();
                        destination.repaint();
                    } else if (pilhaDoTabuleiroSelecionado != null) {
                        Tabuleiro tabuleiroOrigem = pilhaDoTabuleiroSelecionado;
                        Tabuleiro tabuleiroDestino = (Tabuleiro) releasedComponent;
                        tabuleiroOrigem.moveTo(tabuleiroDestino, cartaSelecionado, indexDaCartaClicada);
                        
                        System.out.println("Imprimindo valor em source: ");
                        for (int i = 0; tabuleiroOrigem.pilha[i] != null; i++) {
                            System.out.println(tabuleiroOrigem.pilha[i]);
                        }
                        
                        System.out.println("Imprimindo valor em destion: ");
                        for (int i = 0; tabuleiroDestino.pilha[i] != null; i++) {
                            System.out.println(tabuleiroDestino.pilha[i]);
                        }
                        
                        tabuleiroOrigem.repaint();
                        tabuleiroDestino.repaint();
                    } else if (solucaoBaralhoSelecionada != null) { 
                        SolucaoBaralho source = solucaoBaralhoSelecionada;
                        Tabuleiro destination = (Tabuleiro) releasedComponent;
                        source.moveTo(destination, cartaSelecionado);
                        source.repaint();
                        destination.repaint();
                    }
                }
            }

            e.getComponent().repaint();
            cartaSelecionado = null;
            solucaoBaralhoSelecionada = null;
            pilhaDoTabuleiroSelecionado = null;
            baralhoSecundario = null;
	}
	
	

}
