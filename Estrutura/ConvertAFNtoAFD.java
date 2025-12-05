package Estrutura;

import java.util.*;

import Output.ConversionResult;

public class ConvertAFNtoAFD {

    public ConversionResult converter(AFN afn, String[] alfabeto){
        // estado inicial do AFD é um conjunto com o inicial do AFN
        Set<Estado> inicialConjunto = new HashSet<>();
        inicialConjunto.add(afn.getEstadoInicial());

        AFD inicialAFD = new AFD(inicialConjunto);

        // tabela onde ficara armazenado o afd resultante
        Map<AFD, Map<String, AFD>> tabela = new LinkedHashMap<>();

        // fila para processar estados novos
        Queue<AFD> fila = new LinkedList<>();
        fila.add(inicialAFD);

        // conjunto dos estados já criados no AFD
        Set<AFD> visitados = new HashSet<>();
        visitados.add(inicialAFD);

        // ALGORITMO DE CONVERSÃO
        while(!fila.isEmpty()){

            AFD S = fila.poll();

            tabela.putIfAbsent(S, new HashMap<>());

            for(String simbolo : alfabeto){

                Set<Estado> destinoConjunto = move(S.getConjunto(), simbolo);

                if(destinoConjunto.isEmpty()) continue;

                AFD destinoAFD = new AFD(destinoConjunto);

                tabela.get(S).put(simbolo, destinoAFD);

                if(!visitados.contains(destinoAFD)){
                    visitados.add(destinoAFD);
                    fila.add(destinoAFD);
                }
            }
        }
        // imprime a tabela no terminal
        imprimirTabela(tabela, alfabeto);

        //retorna o resultado para exportar o grafo
        return new ConversionResult(visitados, tabela, inicialAFD);
    }

    // retorna o conjunto de estados que sao lidos pelo mesmo simbolo (para agrupar os estados em um só)
    private Set<Estado> move(Set<Estado> S, String simbolo){
        Set<Estado> resultado = new HashSet<>();

        for(Estado e : S){
            Map<String, Set<Estado>> trans = e.getTransicoes();

            if(trans.containsKey(simbolo)){
                resultado.addAll(trans.get(simbolo));
            }
        }
        return resultado;
    }

    // imprime tabela legível
    private void imprimirTabela(Map<AFD, Map<String, AFD>> tabela, String[] alfabeto) {
        System.out.println("\nTabela do AFD:");

        // Cabeçalho
        System.out.print("Init/Final\tEstado\t");
        for (String s : alfabeto) {
            System.out.print(s + "\t");
        }
        System.out.println();

        // Encontrar o inicial (primeiro inserido)
        AFD inicial = tabela.keySet().iterator().next();

        for (Map.Entry<AFD, Map<String, AFD>> linha : tabela.entrySet()) {

            AFD origem = linha.getKey();

            // coluna Init/Final
            String marcador = "";

            if (origem.equals(inicial)) {
                marcador += "->"; // inicial
            }
            if (origem.possuiFinal()) {
                marcador += "*"; // final
            }

            // se nenhum marcador, deixa vazio
            System.out.print(marcador + "\t\t");

            // Estado
            System.out.print(origem + "\t");

            // Transições
            for (String simbolo : alfabeto) {
                AFD dest = linha.getValue().get(simbolo);
                if (dest == null) System.out.print("-\t");
                else System.out.print(dest + "\t");
            }
            System.out.println();
        }
    }
}
