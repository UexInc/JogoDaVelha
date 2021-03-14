package jogoDaVelha;

import java.util.Scanner;

public class MainGame {

	/*
	 * **************** INTEGRANTES ****************
	 * - Geisa Pereira Souza (1903417)
	 * - Gabriel de Melo Marcondes (1903416)
	 * - Raphael Vinícius Oliveira da Silva (1902604)
	 * - Ricardo de Oliveira Trovato (1903223)
	 * - Willian Donha dos Santos Pestana (1902650)
	 * */

	/*
	 * Inicializa a matriz do jogo (com todos os campos vazios).
	 * 
	 * Cada índice dessa matriz receberá o valor 'v' de "vazio",
	 * é o que vai determinar se "tal" posição está vazia ou não.
	 * 
	 * Retorna a matriz criada.
	 */
	public static char[][] initialize() {

		char[][] matriz = new char[3][3];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = 'v';
			}
		}

		return matriz;
	}

	/*
	 * Verifica se a posição é válida e se está livre para ser preenchida.
	 * 
	 * Caso sim, será preenchida e retorna true, caso não, apenas retorna false.
	 */
	public static boolean step(char M[][], int lin, int col, char gamer) {

		if (lin >= 0 && lin <= 2 && col >= 0 && col <= 2) { // posição válida?
			if(M[lin][col] == 'v') { // posição vazia?
				M[lin][col] = gamer;
				return true;
			}
			System.out.println("Posição ocupada. Escolha novamente.\n");
			return false;
		}
		System.out.println("Posição inválida. Escolha novamente.\n");
		return false;
	}

	/*
	 * Verifica o status do jogo:
	 * Se ninguém ganhou ainda....retorna -1.
	 * Se deu empate..............retorna 0.
	 * Se o jogador "O" ganhou....retorna 1.
	 * Se o jogador "X" ganhou....retorna 2.
	 */
	public static int status(char M[][]) {

		// Verifica a diagonal principal para o jogador "O".
		if (M[0][0] == 'O' && M[1][1] == 'O' && M[2][2] == 'O')
			return 1;

		// Verifica a diagonal principal para o jogador "X".
		if (M[0][0] == 'X' && M[1][1] == 'X' && M[2][2] == 'X')
			return 2;

		// Verifica a diagonal secundária para o jogador "O".
		if (M[0][2] == 'O' && M[1][1] == 'O' && M[2][0] == 'O')
			return 1;

		// Verifica a diagonal secundária para o jogador "X".
		if (M[0][2] == 'X' && M[1][1] == 'X' && M[2][0] == 'X')
			return 2;

		/* 
		 * Se nenhuma diagonal tiver uma combinação que vença,
		 * faremos a verificação de cada linha:
		 * */

		/*
		 * cria uma variável do tipo String para concatenar com cada valor da linha,
		 * assim, podemos verificar se o valor da linha é completamente igual a "OOO" ou
		 * a "XXX", o que significa que um dos jogadores venceu.
		 */
		String linha = "";
		/*
		 * "camposVazios" é a variável que será verificada para saber se ainda tem campos vazios,
		 * ela irá receber o valor true, caso tenha ao menos um valor 'v' na linha, o que indica
		 * que o jogo pode continuar, porém a verificação para saber se o jogo continua ou não
		 * será feita no final desta função, já que ainda podemos ter uma combinação de linha
		 * ou coluna que faça um dos jogadores vencerem, pois essas combinações ainda não foram
		 * verificadas.
		 */
		boolean camposVazios = false;

		for (int i = 0; i < M.length; i++) {
			
			linha = ""; // resetar a linha.
			
			for (int j = 0; j < M[0].length; j++) {
				linha += M[i][j]; // Concatena com o valor dos índices *i* e *j*.
			}

			if (linha.equals("OOO")) // Jogador "O" venceu.
				return 1;

			if (linha.equals("XXX")) // Jogador "X" venceu.
				return 2;

			/*
			 * Verifica se a variável camposVazios ainda possui o valor false, se ainda possuir este
			 * valor, significa que nenhum campo vazio foi encontrado, então entra no if para fazer
			 * a verificação na linha atual.
			 * 
			 * Mas se o valor for true, significa que pelo menos um campo vazio foi encontrado,
			 * sendo assim, não é mais necessário fazer outra verificação, então não entra no if.
			 * */
			
			// String t = "texto";
			// t.charAt(2)
			if (!camposVazios) {
				for (int j = 0; j < linha.length(); j++) {
					/*
					 * A primeira condição do if a seguir leva a mesma ideia da verificação do if acima.
					 * 
					 * Se o valor for true, não é mais necessário uma verificação/execução.
					 * */
					if (!camposVazios && linha.charAt(j) == 'v') {
						camposVazios = true; // Um campo vazio foi encontrado
					}
				}
			}
		}

		/* 
		 * Se nenhuma linha tiver uma combinação que vença,
		 * faremos a verificação de cada coluna:
		 * */

		/*
		 * cria uma variável do tipo String para concatenar com cada valor da coluna,
		 * assim, podemos verificar se o valor da coluna é completamente igual a "OOO" ou
		 * a "XXX", o que significa que um dos jogadores venceu.
		 */
		String coluna = "";

		for (int i = 0; i < M.length; i++) {
			
			coluna = ""; // resetar a coluna.
			
			for (int j = 0; j < M[0].length; j++) {
				coluna += M[j][i];
			}

			if (coluna.equals("OOO")) // Jogador "O" venceu.
				return 1;

			if (coluna.equals("XXX")) // Jogador "X" venceu.
				return 2;
		}

		/*
		 * Se chegou até aqui, significa que nenhum jogador venceu nesta rodada, então, verifica
		 * se o valor de camposVazios para saber se o jogo continua ou se deu velha (empate).
		 * 
		 * Caso o jogo continue, retorna -1, mas se camposVazios for true, não entra no if.
		 * */
		if (camposVazios) {
			return -1;
		}

		// Retorno em caso de empate.
		return 0;
	}

	/*
	 * Mostra o jogo com as linhas divisórias.
	 * */
	public static void mostrarJogo(char matriz[][]) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				if (matriz[i][j] == 'v') { // Caso o valor seja 'v', é mostrado um espaço em branco.
					System.out.printf(" ");
				} else { // Senão, mostra o valor real ("O" ou "X").
					System.out.printf(matriz[i][j] + "");
				}
				if (j < 2) // Se não for a última coluna, mostra a linha divisória vertical.
					System.out.printf("│");
			}
			
			if (i < 2) // Se não for a última linha, mostra a linha divisória horizontal.
				System.out.printf("\n─┼─┼─");
			
			System.out.println();
		}
	}

	/*
	 * Função que inicia o jogo.
	 * */
	public static void game() {
		// Variáveis em relação a jogadores.
		/*
		 * A variável jogador terá os valores 0 (jogador "O") e 1 (jogador "X").
		 * 
		 * Esses valores serão úteis para acessar o caracter relacionado a eles,
		 * no vetor "caracterJogador". 
		 * */
		int jogador = 0;
		char[] caracterJogador = { 'O', 'X' };

		// Variáveis em relação ao jogo em si.
		char[][] matriz = initialize(); // matriz do jogo.
//		boolean step = false; // permissão para preencher um campo.
		int status = -1; // no início, automaticamente, o status do jogo é -1 (continua jogando).
		int lin; // valor do índice da linha.
		int col; // valor do índice da coluna.

		Scanner in = new Scanner(System.in); // Variável de leitura.

		while (status == -1) {

			mostrarJogo(matriz); // mostra o jogo.
			System.out.println(); // espaço.

			do { // validação de entrada para campos vazios.
					
				// Pede o índice da linha.
				System.out.printf("Jogador \"" + caracterJogador[jogador] + "\" escolha a linha: ");
				lin = in.nextInt();
				// Pede o índice da coluna.
				System.out.printf("Jogador \"" + caracterJogador[jogador] + "\" escolha a coluna: ");
				col = in.nextInt();

				System.out.println(); // espaço.

			} while (!step(matriz, lin, col, caracterJogador[jogador])); /* Verifica se o campo é válido 
			e se é um campo vazio. Se sim, o campo é preenchido (isso está dentro da função step). */

			status = status(matriz);

			if(status == -1) // verifica o status do jogo para trocar de jogador somente se necessário.
				jogador = (jogador + 1) % 2; // troca de jogador.

		}

		mostrarJogo(matriz); // mostra como o jogo ficou no final.
		System.out.println(); // espaço.

		if(status == 0) // EMPATE.
			System.out.println("Ótimo jogo gente, mas deu empate!");

		else // TEMOS UM VENCEDOR.
			System.out.println("Parabéns jogador \"" + caracterJogador[jogador] + "\", você venceu!");

		System.out.println("*** Fim do jogo ***");

		in.close(); // fecha a variável de leitura.
	}

	public static void main(String[] args) {
		game(); // start.
	}

}
