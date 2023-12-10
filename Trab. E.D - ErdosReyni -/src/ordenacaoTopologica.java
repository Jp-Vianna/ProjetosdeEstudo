public class ordenacaoTopologica {
	private class Elo {
		public int chave;
		public int contador;
		public Elo prox;
		public EloSuc listaSuc;

		/*public Elo() {
			prox = null;
			contador = 0;
			listaSuc = null;
		}*/

		public Elo(int chave, int contador, Elo prox, EloSuc listaSuc) {
			this.chave = chave;
			this.contador = contador;
			this.prox = prox;
			this.listaSuc = listaSuc;
		}
	}

	private class EloSuc {
		public Elo id;
		public EloSuc prox;

		public EloSuc(Elo id, EloSuc prox) {
			this.id = id;
			this.prox = prox;
		}
	}

	private Elo prim;
	private int n;

	public ordenacaoTopologica() {
		prim = null;
		n = 0;
	}
	//limpaGrafo ok
	public void limpaGrafo(){
		prim = null;
		n = 0;
	}
	//Debug ok
	private void debug() {
		Elo atual = prim;

		while (atual != null) {
			System.out.print(atual.chave + " predecessores: " + atual.contador + ", sucessores: ");
			EloSuc atualSuc = atual.listaSuc;
			while (atualSuc != null) {
				System.out.print(atualSuc.id.chave + " -> ");
				atualSuc = atualSuc.prox;
			}

			System.out.println("NULL");
			atual = atual.prox;
		}
	}
	//executa dando erro, não passa por todos os vértices
	public boolean executa() {
		debug();

		while (true) {
			if(prim == null)
				return true;

			Elo elementoSemPred = encontrarElementoSemPred();
			if (elementoSemPred == null)
				break;

			System.out.print(elementoSemPred.chave + " ");
			n--;

			EloSuc atualSuc = elementoSemPred.listaSuc;

			while (atualSuc != null) {
				atualSuc.id.contador--;
				atualSuc = atualSuc.prox;
			}

			removerElemento(elementoSemPred);
		}

        return n == 0;
    }

	private Elo encontrarElemento(int chave) {
		if(prim == null)
			return null;

		Elo atual = prim;

		while (atual != null) {
			if (atual.chave == chave)
				return atual;

			atual = atual.prox;
		}

		return null;
	}
	//encontrarElementosSemPred ok
	private Elo encontrarElementoSemPred() {
		Elo atual = prim;

		while (atual != null) {
			if (atual.contador == 0) {
				return atual;
			}
			atual = atual.prox;
		}

		return null;
	}

	private void adicionaElemento(Elo novoElo) {
		novoElo.prox = prim;
		prim = novoElo;
		n++;
	}
	//adicionaAresta ok
	public void adicionarAresta(int noA, int noB) {
		Elo elox = encontrarElemento(noA);
		if (elox == null) {
			elox = new Elo(noA, 0, null, null);
			adicionaElemento(elox);
		}

		Elo eloy = encontrarElemento(noB);
		if (eloy == null) {
			eloy = new Elo(noB, 0, null, null);
			adicionaElemento(eloy);
		}

		boolean yJaPresente = false;
		EloSuc sucAtual = elox.listaSuc;
		while (sucAtual != null) {
			if (sucAtual.id == eloy) {
				yJaPresente = true;
				break;
			}
			sucAtual = sucAtual.prox;
		}

		if (!yJaPresente) {
			elox.listaSuc = new EloSuc(eloy, elox.listaSuc);
			eloy.contador++;
		}
	}
	//removeAresta ok
	public void removeAresta(int pai, int filho){
		Elo eloPai = encontrarElemento(pai);
		Elo eloFilho = encontrarElemento(filho);

		if(eloPai == null)
			return;

		removerSucessor(eloPai, eloFilho);
	}
	//removeSucessor ok
	private void removerSucessor(Elo elemento, Elo sucessor) {
		EloSuc anterior = null;
		EloSuc atual = elemento.listaSuc;

		while (atual != null) {
			if (atual.id == sucessor) {
				if (anterior == null) {
					elemento.listaSuc = atual.prox;
				} else {
					anterior.prox = atual.prox;
				}
				return;
			}
			anterior = atual;
			atual = atual.prox;
		}
	}
	//removerElemento ok
	private void removerElemento(Elo elemento) {
		if (prim == elemento) {
			prim = elemento.prox;
			return;
		}

		Elo atual = prim;
		while (atual != null && atual.prox != elemento) {
			atual = atual.prox;
		}

		if (atual != null)
			atual.prox = elemento.prox;

	}
	//temCiclo ok
	public boolean temCiclo(int numNo) {
		boolean[] visitado = new boolean[numNo];
   		boolean[] pilhaRecursao = new boolean[numNo];

        for (Elo atual = prim; atual != null; atual = atual.prox) {
            if (temCicloRecursivo(atual, visitado, pilhaRecursao)) {
                return true;
            }
        }

        return false;
    }
	//temCicloRecursivo ok
    private boolean temCicloRecursivo(Elo vertice, boolean[] visitado, boolean[] pilhaRecursao) {
		if(vertice == null)
			return false;

		if(pilhaRecursao[vertice.chave - 1])
			return true;

		if(visitado[vertice.chave - 1])
			return false;

		visitado[vertice.chave - 1] = true;

		pilhaRecursao[vertice.chave - 1] = true;

        EloSuc vizinhos = vertice.listaSuc;

        if(vizinhos != null){
			while(vizinhos != null) {
            	if (temCicloRecursivo(vizinhos.id, visitado, pilhaRecursao))
					return true;

				vizinhos = vizinhos.prox;
        	}
		}

        pilhaRecursao[vertice.chave - 1] = false;

        return false;
    }

	/*import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;*/
	/*public void realizaLeitura(String nomeEntrada) {
		try (BufferedReader br = new BufferedReader(new FileReader(nomeEntrada))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] elementos = linha.split(" < ");
				int x = Integer.parseInt(elementos[0]);
				int y = Integer.parseInt(elementos[1]);

				Elo elox = encontrarElemento(x);
				if (elox == null) {
					elox = new Elo(x, 0, null, null);
					adicionaElemento(elox);
				}

				Elo eloy = encontrarElemento(y);
				if (eloy == null) {
					eloy = new Elo(y, 0, null, null);
					adicionaElemento(eloy);
				}

				boolean yJaPresente = false;
				EloSuc sucAtual = elox.listaSuc;
				while (sucAtual != null) {
					if (sucAtual.id == eloy) {
						yJaPresente = true;
						break;
					}
					sucAtual = sucAtual.prox;
				}

				if (!yJaPresente) {
					EloSuc novoSuc = new EloSuc(eloy, elox.listaSuc);
					elox.listaSuc = novoSuc;
					eloy.contador++;
				}
			}
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo.");
			e.printStackTrace();
		}
	}*/
}