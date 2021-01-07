package main;

public class ListaLigada {
    Carta primeiro;
    Carta ultimo;
    private int id;
    int quant;
    
    public ListaLigada(){
        this.primeiro = null;
        this.ultimo = null;
    }
    
    public boolean isEmpty(){
        return this.quant == 0;
    }
    
    public void inserirInicio(Carta carta){
        if(isEmpty()){
            this.ultimo = carta;
        }
        else{
            carta.proximo = this.primeiro;
            this.primeiro.anterior = carta;
        }
        this.primeiro = carta;
        this.quant += 1;
    }
    
    public void inserirFim(Carta carta){
        if(isEmpty()){
            this.inserirInicio(carta);
        }
        else{
            this.ultimo.proximo = carta;
            carta.anterior = this.ultimo;
            this.ultimo = carta;
            
            this.quant += 1;
        }
    }
    
    public void inserirFim(ListaLigada carta){
        for (int i = 0; i < carta.contList(); i++) {
            carta.busca(i).setListID(this.getId());
            this.inserirFim(carta.busca(i));
        }
    }
    
    public void inserirTudo(ListaLigada listaDeCartas) {
        int quantCartas = listaDeCartas.contList();
        
        Carta auxCarta;
        
        for (int i = quantCartas; 0 < quantCartas; quantCartas--) {
            auxCarta = listaDeCartas.removerFim();
            this.inserirFim(auxCarta);
        }
    }
    
    public int contList() {
        return this.quant;
    }
    
    public void inserirMeio(Carta carta, int pos){
        if(pos == 0){
            this.inserirInicio(carta);
        }
        else if(pos == contList()){
           this.inserirFim(carta);
        }
        else{
            int i = 0;
            Carta atual = this.primeiro;
            
            while(i < pos){
                atual = atual.proximo;
                i++;
            }
            
            carta.proximo = atual;
            carta.anterior = atual.anterior;
            atual.anterior.proximo = carta;
            atual.proximo.anterior = carta;
            this.quant += 1;
        }
    }
    
    public Carta remoberInicio(){
       Carta aux = this.primeiro;
        if (!isEmpty()) {
           this.primeiro = this.primeiro.proximo;
           this.primeiro.anterior = null;
           this.quant -= 1;
       }
       return aux;
    }
    
    public Carta removerFim(){
        Carta aux = ultimo;
        
        if (!this.isEmpty()) {
            this.ultimo = this.ultimo.anterior;
        }
        this.quant -= 1;
        return aux;
    }
    
    public Carta removerMeio(int pos){
        int i = 0;
        Carta aux = primeiro;
        Carta resposta = null;
        
        if(pos == 0){
          resposta = this.remoberInicio();
        }
        else if(pos >= contList() - 1){
          resposta = this.removerFim();
        }
        else{
            
            while(i < pos){
                aux = aux.proximo;
                i++;
            }
            resposta = aux;
            
            aux.anterior.proximo = aux.proximo;
            aux.proximo.anterior = aux.anterior;
            this.quant -= 1;
        }
        return resposta;
    }
    
    public Carta busca(int pos) {
        if (!this.isEmpty()) {
            Carta cartaAux = this.primeiro;
            
            while(pos > 0) {
                cartaAux = cartaAux.proximo;
                pos--;
            }
            return cartaAux;
        }
        return null;
    }
    
    public Carta search(int pos)
    {
        int i = 0;
        if(!this.isEmpty())
        {
            if( pos>=0 && pos<this.contList())
            {
                Carta cartaAux = this.primeiro;
                
                while(i<pos)
                {
                    cartaAux = cartaAux.proximo;
                    i++;
                }
                return cartaAux; 
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     
}
