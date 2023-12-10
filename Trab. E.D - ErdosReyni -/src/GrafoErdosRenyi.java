import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GrafoErdosRenyi {
    //gerarGrafo e Main ok
    public static void gerarGrafo(ordenacaoTopologica grafo, int numNo, double probabilidade) {
        Random random = new Random();

        for (int i = 1; i <= numNo; i++){
            for (int j = 1; j <= numNo; j++){
                if(i != j){
                    if (random.nextDouble() < probabilidade){
                        grafo.adicionarAresta(i, j);

                        if(grafo.temCiclo(numNo)){
                            System.out.println("A aresta " + i + " e " + j + " causou um ciclo.");
                            grafo.removeAresta(i, j);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("saida.txt", true)));
        int numNo = 20;//V: 10, 20, 30, 40, 50, 100, 200, 500, 1.000, 5.000, 10.000, 20.000, 30.000, 50.000 e 100.000
        double probabilidade = 0.15;
        int qtd = 10;
        ordenacaoTopologica grafo = new ordenacaoTopologica();

        long t0 = System.currentTimeMillis();

        for(int i = 0; i < qtd; i++){
            grafo.limpaGrafo();

            gerarGrafo(grafo, numNo, probabilidade);

            if (!grafo.executa())
                System.out.println("  //O conjunto nao eh parcialmente ordenado.");
            else
                System.out.println("  //O conjunto eh parcialmente ordenado.");

            boolean possuiCiclos = grafo.temCiclo(numNo);

            System.out.println("\nPossui ciclos: " + possuiCiclos);
        }

        long t1 = System.currentTimeMillis();
        long tempoProcessamentoTotal = t1 - t0;

        System.out.println("Tempo total: " + tempoProcessamentoTotal);
        String linha = "Vezes rodadas: " + qtd + " Tamanho: " + numNo + " Tempo: " + tempoProcessamentoTotal;

        out.println(linha);
        out.println();
        out.close();
    }
}