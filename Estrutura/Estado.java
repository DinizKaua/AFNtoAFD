package Estrutura;

import java.util.*;

public class Estado {
    private int nome;
    private boolean ehFinal;
    private Map<String, Set<Estado>> transicoes;

    public Estado(int nome, boolean ehFinal){
        this.nome = nome;
        this.ehFinal = ehFinal;
        this.transicoes = new HashMap<>(); 
    }

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

    public int getNome(){return this.nome;}
    public boolean getEhFinal(){return this.ehFinal;}
    public Map<String, Set<Estado>> getTransicoes (){return this.transicoes;}
}