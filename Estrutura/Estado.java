package Estrutura;

import java.util.*;

public class Estado {
    private int nome;
    private boolean ehFinal;
    private Map<String, Set<Estado>> transicoes; // conjunto de transicoes para cada estado

    public Estado(int nome, boolean ehFinal){
        this.nome = nome;
        this.ehFinal = ehFinal;
        this.transicoes = new HashMap<>(); 
    }

    // adiciona uma transicao
    public void addTransicao(String simbolo, Estado destino){
        if(transicoes.containsKey(simbolo)){
            transicoes.get(simbolo).add(destino);
        }
        else{
            Set<Estado> novoConjunto = new HashSet<>();
            novoConjunto.add(destino);
            transicoes.put(simbolo, novoConjunto);
        }
    }

    // funcoes auxiliares
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Estado)) return false;
        Estado outro = (Estado) o;
        return this.nome == outro.nome;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(nome);
    }

    public int getNome(){return this.nome;}
    public boolean getEhFinal(){return this.ehFinal;}
    public Map<String, Set<Estado>> getTransicoes (){return Collections.unmodifiableMap(transicoes);}
}