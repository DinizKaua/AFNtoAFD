import java.util.*;
import Estrutura.*;

public class App {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // 1) ESTADOS DO AUTOMATO
        System.out.println("Informe os estados do AFN (ex: 0, 1, 2, 3):");
        String[] partesEstados = scan.nextLine().split(", ");
        int[] listaEstados = new int[partesEstados.length];
        for(int i = 0; i < partesEstados.length; i++){
            listaEstados[i] = Integer.parseInt(partesEstados[i]);
        }

        // 2) ESTADO INICIAL
        System.out.println("Informe o estado inicial (ex: 0):");
        int estadoInicial = Integer.parseInt(scan.nextLine());

        // 3) ESTADOS FINAIS
        System.out.println("Informe os estados finais (ex: 2, 3):");
        String[] partesFinais = scan.nextLine().split(", ");
        int[] estadosFinais = new int[partesFinais.length];
        for(int i = 0; i < partesFinais.length; i++){
            estadosFinais[i] = Integer.parseInt(partesFinais[i]);
        }

        // 4) TRANSIÇÕES
        System.out.println("Informe as transições (ex: 0a2), uma por linha. Digite 'fim' para terminar:");
        List<String> listaTransicoes = new ArrayList<>();
        while(true){
            String t = scan.nextLine();
            if(t.equalsIgnoreCase("fim")) break;
            if(!t.isEmpty()) listaTransicoes.add(t);
        }
        String[] transicoes = listaTransicoes.toArray(new String[0]);

        // 5) ALFABETO
        System.out.println("Informe o alfabeto (ex: a b):");
        String[] alfabeto = scan.nextLine().split(" ");

        // CONSTRUÇÃO DO AFN
        AFN afn = new AFN();
        afn.addEstados(estadoInicial, listaEstados, estadosFinais);
        afn.preencherTransicoes(transicoes);

        // CONVERSÃO E IMPRESSÃO
        ConvertAFNtoAFD conversor = new ConvertAFNtoAFD();
        conversor.converter(afn, alfabeto);

        scan.close();
    }
}
