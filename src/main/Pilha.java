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
  public void push(ListaLigada listaDeCartas) {
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
  
  public Carta busca(int index) {
      Pilha pilhaAux = new Pilha();
      
      int quantElementosARemover = this.tamanho() - index - 1;
      
      while(quantElementosARemover > 0) {
          pilhaAux.push(this.pop());
          quantElementosARemover--;
      }
      Carta cartaSelecionada = this.top();
      
      while(!pilhaAux.isEmpty()) {
          this.push(pilhaAux.pop());
      }
      return cartaSelecionada;
  }
}