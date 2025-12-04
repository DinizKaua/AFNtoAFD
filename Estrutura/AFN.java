package Estrutura;

import java.util.*;

public class AFN {
    private Estado estadoInicial;
    private Set<Estado> estados;
    private Map<Integer, Estado> mapaEstados;

    public AFN(){
        this.estados = new HashSet<>();
        this.mapaEstados = new HashMap<>();
        this.estadoInicial = null;
    }

    public void addEstados(int estadoIncialId, int [] lista_estados, int [] estados_finais){
        for(int id : lista_estados){

            boolean ehFinal = contains(estados_finais, id); // verifca se é final
            Estado e = new Estado(id, ehFinal); // cria
            estados.add(e); // adiciona ao conjunto de estados
            mapaEstados.put(id, e); // adiciona ao mapeamento por id

            // verifica se é inical
            if(id == estadoIncialId){
                this.estadoInicial = e;
            }
        }
    }

    public void preencherTransicoes(String[] transcioes){

        for(String s : transcioes){
            // pega o id de cada transciao
            int idOrigem = Character.getNumericValue(s.charAt(0));
            String simbolo = String.valueOf(s.charAt(1));
            int idDestino = Character.getNumericValue(s.charAt(2));

            // converte para estado
            Estado origem = mapaEstados.get(idOrigem);
            Estado destino = mapaEstados.get(idDestino);

            // add a transicao
            origem.addTransicao(simbolo, destino);
        }
    }

    public Estado getEstadoInicial(){
        return estadoInicial;
    }

    public Set<Estado> getEstados(){
        return estados;
    }
    
    private boolean contains(int[] array, int value){
        for(int v : array){
            if(v == value) return true;
        }
        return false;
    }
}
