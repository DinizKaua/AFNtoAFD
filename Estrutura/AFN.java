package Estrutura;

import java.util.*;

public class AFN {
    private Estado estadoIncial;
    private Set<Estado> estados;

    public AFN(){
        this.estados = new HashSet<>();
        this.estadoIncial = null;
    }

    public void addEstados(int estadoIncial, int [] lista_estados, int [] estados_finais){
        for(int i : lista_estados){
            Estado estado = new Estado(i, ehFinal(i, estados_finais));
            if(i == estadoIncial){ 
                this.estadoIncial = estado;
            }
            estados.add(estado);
        }
    }

    public void preencherTransicoes(String[] transcioes){
        int idOrigem;
        String simbolo;
        int idDestino;
        Estado estado_destino;
        Estado estado_origem;
        for(String s : transcioes){
            idOrigem = Character.getNumericValue(s.charAt(0));
            simbolo = String.valueOf(s.charAt(1));
            idDestino = Character.getNumericValue(s.charAt(2));

            estado_origem = buscarEstados(idOrigem);
            estado_destino = buscarEstados(idDestino);

            if(estado_origem != null && estado_destino != null){
                estado_origem.addTransicao(simbolo, estado_destino);
            }
        }
    }

    private Estado buscarEstados(int id){
        for(Estado e : this.estados){
            if(e.getNome() == id){
                return e;
            }
        }
        return null;
    }
    
    private boolean ehFinal(int i, int[] estados_finais){
        for(int j : estados_finais){
            if(i == j) return true;
        }
        return false;
    }
}
