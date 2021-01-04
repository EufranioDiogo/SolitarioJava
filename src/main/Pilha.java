package main;

import javax.swing.JPanel;

public class Pilha extends JPanel {
  public Carta pilha[];
  private int topo;
  private int x, y;

  // Construtor da Pilha
  public Pilha(int x, int y) {
    super.setLocation(x, y);
    this.pilha = new Carta[52];
    this.topo = 0;
  }
  
  public Pilha() {
    this.pilha = new Carta[52];
    this.topo = 0;
  }

  // Verificar se está vazia
  public boolean isEmpty() {
    return this.topo == 0;
  }

  // Inserir elemento na pilha
  public void push(Carta elemento) {
    if (this.topo < this.pilha.length) {
        this.pilha[this.topo] = elemento;
        this.topo++;
    }
  }
  public void push(ListaLigada_1 listaDeCartas) {
    while(!listaDeCartas.isEmpty()){
        this.push(listaDeCartas.removerFim());
    }
  }
  
  
  public int tamanho() {
      return this.topo;
  }

  // remover elemento na pilha
  public Carta pop() {
   if (!this.isEmpty()) {
       Carta aux = this.pilha[this.topo - 1];
       this.topo--;
       return aux;
    }
   return null;
  }

  public Carta top() {
    if (this.isEmpty()){
        return null;
    }
    return this.pilha[this.topo - 1];
  }
}