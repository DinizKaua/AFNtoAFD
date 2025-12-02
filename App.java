import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner;

import Estrutura.AFN;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("=================================================");
        // estados
        System.out.println("Informe os estados do automato:");
        String entrada_estados = scan.nextLine();
        String [] estados_split = entrada_estados.split(", ");
        int [] estadosInt = new int[estados_split.length];
        for(int i = 0; i < estadosInt.length; i++){
            estadosInt[i] = Integer.parseInt(estados_split[i]);
        }

        // estado incial
        System.out.println("Informe o estado incial:");
        int estadoIncial = scan.nextInt();

        scan.nextLine();

        // transicoes
        System.out.println("Informe a funcao programa:");
        List<String> lista_transicoes = new ArrayList<>();
        String transicao = "";
        while(true){
            transicao = scan.nextLine();
            if(transicao.equals("fim")){
                break;
            }
            lista_transicoes.add(transicao);
        }
        String [] arrayTransicoes = lista_transicoes.toArray(new String[0]);

        // estados finais
        System.out.println("Informe os estados finais:");
        String entrada_estadosFinais = scan.nextLine();
        String [] estadosFinais_split = entrada_estadosFinais.split(", ");
        int [] estadosFinaisInt = new int[estadosFinais_split.length];
        for(int i = 0; i < estadosFinaisInt.length; i++){
            estadosFinaisInt[i] = Integer.parseInt(estadosFinais_split[i]);
        }

        AFN afn = new AFN();
        afn.addEstados(estadoIncial, estadosInt, estadosFinaisInt);
        afn.preencherTransicoes(arrayTransicoes);

        scan.close();
    } 
}
