package Output;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import Estrutura.AFD;
import Estrutura.Estado;

public class GraphvizzExporter {
    // Gera um arquivo .dot para um AFN
    public static void exportAFN(Set<Estado> estados, String nomeArquivo) {
        try (PrintWriter out = new PrintWriter(nomeArquivo)) {

            out.println("digraph AFN {");
            out.println("rankdir=LR;");

            for (Estado e : estados) {

                if (e.getEhFinal()) {
                    out.println(e.getNome() + " [shape=doublecircle];");
                } else {
                    out.println(e.getNome() + " [shape=circle];");
                }

                // transições
                e.getTransicoes().forEach((simbolo, destinos) -> {
                    for (Estado destino : destinos) {
                        out.println(e.getNome() + " -> " + destino.getNome()
                                + " [label=\"" + simbolo + "\"];");
                    }
                });
            }

            out.println("}");
            System.out.println("Arquivo DOT gerado: " + nomeArquivo);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Chama o Graphviz para gerar PNG
    public static void gerarPNG(String dotArquivo, String pngArquivo) {
        try {
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "-Gdpi=200", dotArquivo, "-o", pngArquivo);

            pb.inheritIO();
            pb.start();

            System.out.println("Imagem gerada: " + pngArquivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportAFD(Set<AFD> estadosAFD, Map<AFD, Map<String, AFD>> tabela, String nomeArquivo) {
        try (PrintWriter out = new PrintWriter(nomeArquivo)) {

            out.println("digraph AFD {");
            out.println("rankdir=LR;");

            // nós
            for (AFD afd : estadosAFD) {

                String nome = afd.toString(); // q0_q1 etc

                if (afd.possuiFinal()) {
                    out.println(nome + " [shape=doublecircle];");
                } else {
                    out.println(nome + " [shape=circle];");
                }
            }

            // arestas
            for (Map.Entry<AFD, Map<String, AFD>> linha : tabela.entrySet()) {

                AFD origem = linha.getKey();

                for (Map.Entry<String, AFD> trans : linha.getValue().entrySet()) {

                    String simbolo = trans.getKey();
                    AFD destino = trans.getValue();

                    out.println(origem.toString() + " -> " + destino.toString()
                            + " [label=\"" + simbolo + "\"];");
                }
            }

            out.println("}");
            System.out.println("Arquivo DOT gerado: " + nomeArquivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
