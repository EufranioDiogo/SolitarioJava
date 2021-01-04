package main;
import java.util.ArrayList;
import java.util.List;

public class Baralho {
    private final ListaLigada_1 baralho = new ListaLigada_1();
    private final ListaLigada_1 cartasRetiradas;
    private int size = 0;
    
    public Baralho(){
        this.cartasRetiradas = new ListaLigada_1();
        this.baralho.inserirTudo(new Carta().generateCarta('c'));
        this.baralho.inserirTudo(new Carta().generateCarta('d'));
        this.baralho.inserirTudo(new Carta().generateCarta('h'));
        this.baralho.inserirTudo(new Carta().generateCarta('s'));
        this.size = this.baralho.contList();
    }
    
    public void inserirCarta(int valor, char simbolo){
        if (baralho.contList()< 52) {
            this.baralho.inserirFim(new Carta(valor, simbolo, "" + valor));
            this.size++;
        }
    }
    
    public void inserirCarta(int valor, char simbolo, char reference){
        if (this.baralho.contList() < 52) {
            this.baralho.inserirFim(new Carta(valor, simbolo, "" + valor));
            this.size++;
        }
    }
    
    public Carta removerCarta(){
        if (this.size > 0) {
            this.size--;
            return this.baralho.removerFim();
        }
        return null;
    }
    
    public ListaLigada_1 removerCarta(int quant) {
        ListaLigada_1 auxListaCartas = new ListaLigada_1();
        Carta auxCarta;
        
        while(quant > 1) {
            auxCarta = this.removerCarta();
            auxCarta.setCartaVirada(true);
            auxListaCartas.inserirFim(auxCarta);
            quant--;
        }
        auxCarta = this.removerCarta();
        auxCarta.setCartaVirada(false);
        auxListaCartas.inserirFim(auxCarta);
        return auxListaCartas;
    }
    
    public void baralhar(){
        Pilha cartasCoracoes = new Pilha();
        Pilha cartasEspadas  = new Pilha();
        Pilha cartasDiamante = new Pilha();
        Pilha cartasPauspi   = new Pilha();
        int auxSize = this.size;
        Carta cartaRetirada;
        
        for(int i = 0; i < auxSize; i++) {
            System.out.println(this.baralho.contList());
            cartaRetirada = this.baralho.removerFim();
            
            System.out.println(cartaRetirada.toString());
            
            switch(cartaRetirada.getSimbolo()) {
                case 'h':
                    cartasCoracoes.push(cartaRetirada);
                    break;
                case 's':
                    cartasEspadas.push(cartaRetirada);
                    break;
                case 'd':
                    cartasDiamante.push(cartaRetirada);
                    break;
                case 'c':
                    cartasPauspi.push(cartaRetirada);
                    break;
                default:
                    System.err.println("Erro not defined type of cart");
                    break;
            }
            this.size--;
        }
        int escalonador = this.generateRandomNumber(0, 4);
        Pilha pilha1 = new Pilha();
        Pilha pilha2 = new Pilha();
        
        
        while (cartasCoracoes.tamanho() > 0 || cartasDiamante.tamanho() > 0 || cartasEspadas.tamanho() > 0 || cartasPauspi.tamanho() > 0){
            switch (escalonador) {
                case 0:
                    if (cartasCoracoes.tamanho() > 0) {
                        escalonador = this.generateRandomNumber(0, 2);
                        if (escalonador == 0) {
                            pilha1.push(cartasCoracoes.pop());
                        } else {
                            pilha2.push(cartasCoracoes.pop());
                        }   
                        break;  
                    } 
                case 1:
                    if (cartasPauspi.tamanho() > 0) {
                        escalonador = this.generateRandomNumber(0, 2);
                        if (escalonador == 0) {
                            pilha1.push(cartasPauspi.pop());
                        } else {
                            pilha2.push(cartasPauspi.pop());
                        }   
                        break;   
                    }
                case 2:
                    if (cartasDiamante.tamanho() > 0) {
                        escalonador = this.generateRandomNumber(0, 2);
                        if (escalonador == 0) {
                            pilha1.push(cartasDiamante.pop());
                        } else {
                            pilha2.push(cartasDiamante.pop());
                        }   
                        break;  
                    }
                    
                case 4:
                    if (cartasEspadas.tamanho() > 0) {
                        escalonador = this.generateRandomNumber(0, 2);
                        if (escalonador == 0) {
                            pilha1.push(cartasEspadas.pop());
                        } else {
                            pilha2.push(cartasEspadas.pop());
                        }   
                        break;
                    }
                default:
                    break;
            }
            escalonador = this.generateRandomNumber(0, 4);
        }
        
        
        while (pilha1.tamanho() > 0 || pilha2.tamanho() > 0) {
            escalonador = generateRandomNumber(0, 2);
            
            if(escalonador == 0 && pilha1.tamanho() > 0) {
                this.baralho.inserirFim(pilha1.pop());
                this.size++;
            } else if (escalonador == 1 && pilha2.tamanho() > 0) {
                this.baralho.inserirFim(pilha2.pop());
                this.size++;
            }
        }
    }
    
    public int generateRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
    
    public Carta cartaTopo() {
        if (this.tamanho() > 0) {
            return this.baralho.busca(this.tamanho() - 1);
        }
        return null;
    }
    
    public int tamanho() {
        return this.size;
    }
    
    public void mostrarBaralho() {
        List<Carta> auxPilha = new ArrayList<>();
        
        while(this.baralho.contList()> 0) {
            auxPilha.add(this.baralho.removerFim());
            System.out.println(auxPilha.get(auxPilha.size() - 1));
        }
        
        while(auxPilha.size() > 0) {
            this.baralho.inserirFim(auxPilha.remove(auxPilha.size() - 1));
        }
    }
}
