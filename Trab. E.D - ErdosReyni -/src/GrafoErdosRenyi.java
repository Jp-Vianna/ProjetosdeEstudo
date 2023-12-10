import java.io.BufferedWriter; //Import para escrever os resultados no arquivo .txt
import java.io.FileWriter;     //Import para escrever os resultados no arquivo .txt
import java.io.IOException;    //Excecao de entrada e saida
import java.io.PrintWriter;    //Import para escrever os resultados no arquivo .txt
import java.util.Random;       //Gerar valores aleatorios

public class GrafoErdosRenyi {
    //gerarGrafo e Main ok
    public static void gerarGrafo(ordenacaoTopologica grafo, int numNo, double probabilidade) {
        Random random = new Random(); //Gera valores aleatorios

        for (int i = 1; i <= numNo; i++){     //For percorrendo a "chave" dos vertices
            for (int j = 1; j <= numNo; j++){     //For percorrendo a "chave" dos vertices
                if(i != j){     //Impede de um vertice criar aresta para si mesmo
                    if (random.nextDouble() < probabilidade){     //Determina se a aresta sera criada ou nao
                        grafo.adicionarAresta(i, j);     //Aresta entre i e j

                        if(grafo.temCiclo(numNo)){     //Verifica se gerou um ciclo
                            System.out.println("A aresta " + i + " e " + j + " causou um ciclo.");     //Informa o ciclo
                            grafo.removeAresta(i, j);     //Remove aresta criada
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("saida.txt", true)));     //Instancia objeto de escrita
        
        int numNo = 20000;     //Estabelece o tamanho do grafo a ser criado        
        double probabilidade = 0.15;     //Estabelece uma probabilidade de 15% de criar a aresta
        int qtd = 10;     //Quantidade de testes a serem executados
        ordenacaoTopologica grafo = new ordenacaoTopologica();     //Instancia grafo

        long t0 = System.currentTimeMillis();     //Realiza marcacao do tempo de execucao

        for(int i = 0; i < qtd; i++){     //Roda os testes
            grafo.limpaGrafo();     //Limpa o grafo

            gerarGrafo(grafo, numNo, probabilidade);     //Gera um grafo aleatorio

            if (!grafo.executa())     //Realiza ordenacao topologica do grafo gerado
                System.out.println("  //O conjunto nao eh parcialmente ordenado.");
            else
                System.out.println("  //O conjunto eh parcialmente ordenado.");

            boolean possuiCiclos = grafo.temCiclo(numNo);     //Verifica a existencia de ciclos por garantia

            System.out.println("\nPossui ciclos: " + possuiCiclos);     //Mostra resultado
        }

        long t1 = System.currentTimeMillis();     //Finaliza medicao de tempo
        long tempoProcessamentoTotal = t1 - t0;     //Calcula o tempo total

        System.out.println("Tempo total: " + tempoProcessamentoTotal);     //Mostra resultado tempo
        String linha = "Vezes rodadas: " + qtd + " Tamanho: " + numNo + " Tempo: " + tempoProcessamentoTotal;     //Linha com os resultados a ser escrita

        out.println(linha);     //Escreve linha no .txt de saida
        out.println();     //Quebra linha 
        out.close();     //Fecha buffer
    }
}
