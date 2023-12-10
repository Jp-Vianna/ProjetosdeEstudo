public class ordenacaoTopologica {
	private class Elo {
		public int chave;	//Chave do Elo para comparacoes
		public int contador;	//Contador para quantidade de predessores do vertice
		public Elo prox;	//Ponteiro para o próximo elo da lista encadeada
		public EloSuc listaSuc;	//Lista encadeada com os sucessores do vertice

		public Elo() {
			prox = null;	//Inicializa vazio
			contador = 0;	//Contador zerado
			listaSuc = null;//Lista vazia
		}
		
		//Inicializa objeto com os parametros
		public Elo(int chave, int contador, Elo prox, EloSuc listaSuc) {
			this.chave = chave;		
			this.contador = contador;
			this.prox = prox;
			this.listaSuc = listaSuc;
		}
	}

	//Classe da lista de sucessores
	private class EloSuc {
		public Elo id;		//Elo da lista
		public EloSuc prox;	//Ponteiro para o próximo da lista

		//Construtor inicializa com os parametros 
		public EloSuc(Elo id, EloSuc prox) {
			this.id = id;
			this.prox = prox;
		}
	}

	//Atributos da classe
	private Elo prim;	//Primeiro da lista de vertices
	private int n;		//Quantidade de vertices

	//Inicializa vazio
	public ordenacaoTopologica() {
		prim = null;
		n = 0;
	}
	
	//limpaGrafo ok
	public void limpaGrafo(){
		prim = null; 	//Zera lista do objeto
		n = 0;	       	//Zera contador
	}
	
	//Debug ok
	private void debug() {
		Elo atual = prim; //Pega ponteiro do primeiro para andar pela lista

		while (atual != null) { //Percorre até o ultimo
			System.out.print(atual.chave + " predecessores: " + atual.contador + ", sucessores: ");//Mostra informacoes do elo atual
			
			EloSuc atualSuc = atual.listaSuc; //Pega lista de sucessores
			while (atualSuc != null) { //Percorre sucessores ate o fim
				System.out.print(atualSuc.id.chave + " -> "); //Mostra os sucessores
				atualSuc = atualSuc.prox; // Caminha para o próximo elemento
			}

			System.out.println("NULL");	//Lista acabou, mostra NULL
			atual = atual.prox;	//Caminha para o proximo vertice
		}
	}
	
	//executa dando erro, não passa por todos os vértices//Rever
	public boolean executa() {
		debug(); //Chama debug

		while (true) { //Loop infinito
			if(prim == null) //Se a lista estiver vazia acaba
				return true;

			Elo elementoSemPred = encontrarElementoSemPred(); //Encontra elemento sem vertices anteriores
			
			if (elementoSemPred == null) //Se nao tiver predessor acaba
				break;

			System.out.print(elementoSemPred.chave + " "); //Mostra elemento encontrado
			n--;	//Como elemento foi removido, decrementa a quantidaded de elementos da lista

			EloSuc atualSuc = elementoSemPred.listaSuc; //Pega os elementos sucessores

			while (atualSuc != null) { //Ate o fim da lista
				atualSuc.id.contador--; //Contador de predessores diminui
				atualSuc = atualSuc.prox;	//Ponteiro para o proximo
			}

			removerElemento(elementoSemPred); //Remove o vertice atual
		}

        	return n == 0; //Retorna se a lista esta vazia e ordenada
    	}

	//encontrarElemento ok
	private Elo encontrarElemento(int chave) {
		if(prim == null) //Se a lista estiver vazia acaba
			return null;

		Elo atual = prim; //Pega o primeiro

		while (atual != null) { //Ate o fim
			if (atual.chave == chave) //Se encontrou
				return atual; //Retorna

			atual = atual.prox; //Se nao, proximo
		}

		return null; //Caso elemento nao exista
	}
	
	//encontrarElementosSemPred ok
	private Elo encontrarElementoSemPred() {
		Elo atual = prim; //Pega o priemeiro

		while (atual != null) { //Percorre ate o final
			if (atual.contador == 0) { //Caso ele nao tenha antecessores
				return atual; //Retorna e acaba
			}
			atual = atual.prox; //Se nao, proximo
		}

		return null; //Nao achou
	}

	//adicionaElemento ok
	private void adicionaElemento(Elo novoElo) {
		novoElo.prox = prim; //Adiciona novo elemento no inicio
		prim = novoElo; //Atualiza o primeiro da lista
		n++; //Aumenta contador de elementos
	}
	
	//adicionaAresta ok
	public void adicionarAresta(int noA, int noB) {
		Elo elox = encontrarElemento(noA); //Encontra o elemento de partida da aresta
		if (elox == null) { //Se nao existir
			elox = new Elo(noA, 0, null, null); //Cria vertice
			adicionaElemento(elox); //Adiciona ele na lista
		}

		Elo eloy = encontrarElemento(noB); //Encontra elemento de chegada
		if (eloy == null) { //Se nao existir
			eloy = new Elo(noB, 0, null, null); //Cria elemento
			adicionaElemento(eloy); //Adiciona aa lista
		}

		boolean yJaPresente = false; //Verifica se o no de chagada ja possui aresta com o no de partida
		EloSuc sucAtual = elox.listaSuc; //Pega a lista de sucessores
		while (sucAtual != null) { //Percorre ate o fim
			if (sucAtual.id == eloy) { //Se encontrar
				yJaPresente = true; //Ja esta presente
				break; //Termina
			}
			sucAtual = sucAtual.prox; //Se nao, proximo
		}

		if (!yJaPresente) {//Se nao estiver presente
			elox.listaSuc = new EloSuc(eloy, elox.listaSuc); //Agora noB e um destino do noA
			eloy.contador++; //Aumenta contador de sucessores
		}
	}
	
	//removeAresta ok
	public void removeAresta(int pai, int filho){
		Elo eloPai = encontrarElemento(pai); //Encontra no de partida
		Elo eloFilho = encontrarElemento(filho); //Encontra no de chegada

		if(eloPai == null) //Se partida e null acaba
			return;

		removerSucessor(eloPai, eloFilho); //Remove o no de chegada dos sucessores
	}
	
	//removeSucessor ok
	private void removerSucessor(Elo elemento, Elo sucessor) {
		EloSuc anterior = null; //Guarda o vertice anterior para evitar percorrer a lista mais vezes
		EloSuc atual = elemento.listaSuc; //Pega lista de sucessores

		while (atual != null) { //Percorre ate o fim
			if (atual.id == sucessor) { //Caso achou o vertice
				if (anterior == null) { //Se o anterior esta vazio
					elemento.listaSuc = atual.prox; //Atualiza
				} else {
					anterior.prox = atual.prox; //Guarda o anterior
				}
				return; //finaliza
			}
			anterior = atual; //Atualiza anterior
			atual = atual.prox; //Proximo da lista
		}
	}
	
	//removerElemento ok
	private void removerElemento(Elo elemento) {
		if (prim == elemento) { //Se o primeiro ja e o elemento, acaba
			prim = elemento.prox; //Remove primeiro
			return;
		}

		Elo atual = prim; //Se nao, pega a lista
		while (atual != null && atual.prox != elemento) { //Percorre ate o fim
			atual = atual.prox; //Proximo da lista
		}

		if (atual != null) //Se nao acabou a lista o proximo so pode ser o elemento procurado
			atual.prox = elemento.prox; //Remove da lista

	}
	
	//temCiclo ok
	public boolean temCiclo(int numNo) {
		boolean[] visitado = new boolean[numNo]; //Vetor para marcar todos os vertices visitados
   		boolean[] pilhaRecursao = new boolean[numNo]; //Vetor para guardar o caminho atual realizado no grafo 

        	for (Elo atual = prim; atual != null; atual = atual.prox) { //Perdorre lista ate o fim
            		if (temCicloRecursivo(atual, visitado, pilhaRecursao)) //Chama recursivamente a verificacao
                		return true; //Se achou ciclo, retorna true
        	}

        	return false; //Se nao, false
    	}
	
	//temCicloRecursivo ok
	private boolean temCicloRecursivo(Elo vertice, boolean[] visitado, boolean[] pilhaRecursao) {
		if(vertice == null) //Se vertice e nulo, acaba
			return false;

		if(pilhaRecursao[vertice.chave - 1]) //Se o no ja foi alcancado no caminho atual tem ciclo
			return true;

		if(visitado[vertice.chave - 1]) // Se esse no ja foi visitado, volta
			return false;

		visitado[vertice.chave - 1] = true; //Atualiza lista de visitados

		pilhaRecursao[vertice.chave - 1] = true; //Atualiza o caminho atual

        	EloSuc vizinhos = vertice.listaSuc; //Pega a lista de sucessores

        	if(vizinhos != null){ //Se possui sucessores continua
			while(vizinhos != null) { //Percorre todos os sucessores
            			if (temCicloRecursivo(vizinhos.id, visitado, pilhaRecursao)) //Chama recursivamente para o proximo vertice
					return true;

				vizinhos = vizinhos.prox; //Vai para o proximo
        		}
		}

        	pilhaRecursao[vertice.chave - 1] = false; //Desmarca o vertice atual para explorar outro caminho em busca de ciclos

        	return false; //Nao encontrou ciclos nesse caminho
    	}
}
