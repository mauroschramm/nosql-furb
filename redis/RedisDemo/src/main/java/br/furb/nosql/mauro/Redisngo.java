package br.furb.nosql.mauro;

import java.util.Iterator;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;


/**
 * @author Mauro Schramm
 * 
 * Implementação do Redisngo
 *
 */
public class Redisngo {
	
	
	private static final String URL_REDIS = "redis://127.0.0.1:6379";
	
	private static final int TOTAL_PEDRAS = 99;
	private static final int TOTAL_JOGADORES = 50;
	private static final int TOTAL_NUMEROS_CARTELA = 15;
	private static final int ESCORE_PARA_BINGO = 15;
	private static final String CHAVE_GLOBO = "RedisngoGlobo";
	private static final String CHAVE_VENCEDORES = "RedisngoVencedores";
	private static final String CHAVE_PEDRAS_SORTEADAS = "RedisngoPedrasSorteadas";
	private static final boolean TRACE = false;

	private RedissonClient redisson;

	
	public Redisngo (RedissonClient clienteRedisson) {
		this.redisson = clienteRedisson;
	}


	
	/**
	 * Cria no Redis um SET de Integers representando o globo do bingo, contendo as pedras que serão sorteadas no jogo
	 */
	public void criaGlobo () {

		// cria globo (que conterá as pedras) como um SET no REDIS 
		RSet<Integer> globo = redisson.getSet(CHAVE_GLOBO);
		// adiciona as pedras
		for (int i = 1; i <= TOTAL_PEDRAS; i++) {
			globo.add(i);
		}

		if (TRACE) {
			System.out.println("Pedras no globo:" + globo);
		}
		
	}
	
	/**
	 * Cria no Redis vários MAPs representando os jogadores do bingo.
	 */
	public void criaJogadores() {
		for (int i = 1; i <= TOTAL_JOGADORES; i++) {
			// gera a chave do jogador,conforme especificado
			String chaveJogador = "user:" + String.format("%02d", i);
			
			// cria uma MAP (com chave e valor String) para para cada jogador no Redis.
			RMap<String, String> jogador = redisson.getMap(chaveJogador);
			
			// adiciona os campos no MAP do jogador, conforme especificado
			jogador.put("name", 	"user" 		+ String.format("%02d", i));
			jogador.put("bcartela", "cartela:" 	+ String.format("%02d", i));
			jogador.put("bscore", 	"score:" 	+ String.format("%02d", i));
		}

		if (TRACE) {
			for (int i = 1; i <= TOTAL_JOGADORES; i++) {
				// gera a chave do jogador,conforme especificado
				String chaveJogador = "user:" + String.format("%02d", i);
				
				// cria uma MAP (com chave e valor String) para para cada jogador no Redis.
				RMap<String, String> jogador = redisson.getMap(chaveJogador);
				
				// imprime
				System.out.println(chaveJogador + " " + jogador.get("name") + " " + jogador.get("bcartela") + " " + jogador.get("bscore") );
			}
		}
	}
	
	/**
	 * Cria no Redis vários SETs de Integers representando as cartelas dos jogadores.
	 * Cada cartela é elaborada a partir de um subconjunto aleatório das pedras constantes no globo.
	 */
	public void criaCartelas() {
		for (int i = 1; i <= TOTAL_JOGADORES; i++) {
			// gera cahve da cartela
			String chaveCartela = "cartela:" + String.format("%02d", i);
            
			// cria cartela como um SET no Redis
			RSet<Integer> cartela = redisson.getSet(chaveCartela);
			cartela.clear();
			
			// gera cartela à partir de um subconjunto aleatório das pedras no globo, com TOTAL_NUMEROS_CARTELA números
			RSet<Integer> globo = redisson.getSet(CHAVE_GLOBO);
			cartela.addAll(globo.random(TOTAL_NUMEROS_CARTELA));

		}
		
		if (TRACE) {
			for (int x = 1; x <= TOTAL_JOGADORES; x++) {
				// gera cahve da cartela
				String cc = "cartela:" + String.format("%02d", x);
	            
				// busca a cartela
				RSet<Integer> c = redisson.getSet(cc);
				
				// imprime
				System.out.println(cc + c.toString());
			}
		}
	}
	

	/**
	 * Cria no Redis vários Longs para representar o escore de cada jogador
	 */
	public void criaEscores() {
		for (int i = 1; i <= TOTAL_JOGADORES; i++) {
			String chaveEscore = "score:" + String.format("%02d", i);
			redisson.getAtomicLong(chaveEscore).set(0);	
		}
	}
	
	/**
	 * Executa o jogo de bingo.
	 * 
	 * As pedras do globo são retiradas aleatoriamente e comparadas com os números das cartelas.
	 * Para cada combinação entre a pedra sorteada e o número da cartela, o escore do jogador é incrementado usando o incrmento atômico do Redis.
	 * Quando o jogador atinge o escore determinado, ele é considerado vencedor. 
	 * Pode haver mais de um vencedor, desde que eles atinjam o escore determinado na mesma jogada.
	 * O índice dos vencedores e também as  pedras sorteadas são armazenadas em estruturas do Redis.
	 */
	public void jogar() {

		// cria uma LIST no Redison para armazenar os índices dos vencedores
		RList<Integer> vencedores = redisson.getList(CHAVE_VENCEDORES);
		vencedores.clear();
		
		// cria uma LIST no Redis para guardar as pedras sorteadas no jogo
		RList<Integer> pedrasSorteadas = redisson.getList(CHAVE_PEDRAS_SORTEADAS);
		pedrasSorteadas.clear();
		
		RSet<Integer> globo = redisson.getSet(CHAVE_GLOBO);
		
		// rodadas do bingo até acabarem as pedras ou haver ganhador
		boolean habemusVencedor = false;
		int i = 1;
		while (i <= TOTAL_PEDRAS && (!habemusVencedor) ) {
			// retira uma pedra aleatória do globo
			int pedra = globo.removeRandom();
			pedrasSorteadas.add(pedra);
			
			// ietração sobre todas as cartelas
			for (int j=1; j <= TOTAL_JOGADORES; j++ ) {

				// recupara cada cartela
				String chaveCartela = "cartela:" + String.format("%02d", j);
				RSet<Integer> cartela = redisson.getSet(chaveCartela);
				Iterator<Integer> itCartela = cartela.iterator();
				
				// compara pedra sorteda com cada número da cartela
				while (itCartela.hasNext()) {
					int numCartela = itCartela.next();
					if (pedra == numCartela) {
						
						//usa o incremento atômico do Redis para o escore do jogador
						String chaveEscore = "score:" + String.format("%02d", j);
						long escore = redisson.getAtomicLong(chaveEscore).incrementAndGet();
						

						if (escore >= ESCORE_PARA_BINGO) {
							habemusVencedor = true;
							
							// adiciona o índice jogador/cartela/escora na lista de vencedores
							vencedores.add(j);
							
						}
					}
					
				}
				
			}
			i++;
		}
		
		if (TRACE) {
			for (int x = 1; x <= TOTAL_JOGADORES; x++) {
				String chaveEscore = "score:" + String.format("%02d", x);
				RAtomicLong escore = redisson.getAtomicLong(chaveEscore);	
				System.out.println(chaveEscore + "=" + escore.get());
			}
			System.out.println("Pedras sorteadas " + pedrasSorteadas.size() + pedrasSorteadas);
			System.out.println(CHAVE_VENCEDORES + " " + vencedores);
		}
		
	}
	
	/**
	 * Exibe a apuração do(s) vencedor(es) do jogo a partir das estruturas armazendas anteriormente no Redis
	 */
	public void mostrarVencedores() {
		
		RList<Integer> pedrasSorteadas = redisson.getList(CHAVE_PEDRAS_SORTEADAS);
		System.out.println("\nPedras sorteadas=" + pedrasSorteadas.size() + "-->" + pedrasSorteadas);
		System.out.println("\nVencedor(es):");
		
		RList<Integer> vencedores = redisson.getList(CHAVE_VENCEDORES);
		
		//percorre a lista de vencedores
		Iterator<Integer> itVencedores = vencedores.iterator();
		while(itVencedores.hasNext()) {
			int iv = itVencedores.next();
			
			String chaveJogador = "user:" + String.format("%02d", iv);
			RMap<String, String> jogador =  redisson.getMap(chaveJogador);
			
			String chaveCartela = jogador.get("bcartela");
			RSet<Integer> cartela = redisson.getSet(chaveCartela);
			
			String chaveEscore = jogador.get("bscore");
			long escore = redisson.getAtomicLong(chaveEscore).get();
			
			System.out.println("Jogador=" + jogador.get("name") + " Escore=" + escore + " cartela=" + cartela);
			
		}
	}
	
	public static void main(String[] args) {
	
		System.out.println("Bem-vindo ao Redisngo, o bingo do Redis!");
		System.out.println("Preparando o jogo...");
		
		// conexão ao Redis (single serever)
        System.out.print("Conectando ao Redis...");
		Config config = new Config();
		config.useSingleServer().setAddress(URL_REDIS);
		RedissonClient clienteRedisson = Redisson.create(config);
		System.out.println("ok");
		
		//instancia o jogo
		Redisngo bingo = new Redisngo(clienteRedisson);
		
		// criar "pedras" no "globo"
		System.out.print("Colocando as pedras no globo...");
		bingo.criaGlobo();
		System.out.println("ok");
		
		//cria jogadores
		System.out.print("Configurando os jogadores...");
		bingo.criaJogadores();
		System.out.println("ok");
		
		// gerar cartelas
		System.out.print("Gerando as cartelas...");
		bingo.criaCartelas();
		System.out.println("ok");

		// inicializar os escores
		System.out.print("Inicializando os escores...");
		bingo.criaEscores();
		System.out.println("ok");
		
        // rodar o jogo
		System.out.print("Jogando...");
		bingo.jogar();
		System.out.println("ok");
		
        // mostrar o resultado
		bingo.mostrarVencedores();

		System.out.print("\nObrigado por participar do Redisngo!");
		
	}

}
