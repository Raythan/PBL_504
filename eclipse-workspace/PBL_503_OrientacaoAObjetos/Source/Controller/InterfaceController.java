package Controller;

import java.util.ArrayList;
import java.util.Random;
import Model.Departamento;
import Model.Produto;


public class InterfaceController {
	static final int QTDPRODUTOS = 10, MINPESO = 10, MAXPESO = 15, MINPRECO = 20, MAXPRECO = 100;
	static Random random = new Random();
	static String[] nomesDepto = new String[] {"Brinquedos", "Telefonia", "TVs/Áudio", "Informática", "Eletrodomésticos", "Moda"};
	//
	// Criação de listas para armazenar os produtos em memória
	//
	static ArrayList<Produto> ListaBrinquedos = new ArrayList<Produto>();
	static ArrayList<Produto> ListaTelefonia = new ArrayList<Produto>();
	static ArrayList<Produto> ListaTVsAudio = new ArrayList<Produto>();
	static ArrayList<Produto> ListaInformatica = new ArrayList<Produto>();
	static ArrayList<Produto> ListaEletrodomestico = new ArrayList<Produto>();
	static ArrayList<Produto> ListaModa = new ArrayList<Produto>();
	//
	// Criação dos departamentos
	//
	static Departamento DeptoBrinquedo = new Departamento(nomesDepto[0]);
	static Departamento DeptoTelefonia = new Departamento(nomesDepto[1]);
	static Departamento DeptoTVsAudio = new Departamento(nomesDepto[2]);
	static Departamento DeptoInformatica = new Departamento(nomesDepto[3]);
	static Departamento DeptoEletrodomestico = new Departamento(nomesDepto[4]);
	static Departamento DeptoModa = new Departamento(nomesDepto[5]);
	static ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	//
	// Arrays com os tipos de produtos da loja
	//
	static String[] TipoBrinquedo = new String[] {"Bonecas", "Pelúcia", "Brinquedos para Bebê", "Lego", "Carrinhos"};
	static String[] TipoTelefonia = new String[] {"Celular Básico", "Smartphone", "Acessórios para Celular", "Chip", "Telefonia Fixa"};
	static String[] TipoTVsAudio = new String[] {"Smart TV", "TV 4K", "Acessórios para áudio e vídeo", "Equipamento de Som"};
	static String[] TipoInformatica = new String[] {"Computador", "Notebook", "Impressora", "Componentes", "Acessórios para Computador"};
	static String[] TipoEletrodomestico = new String[] {"Geladeira e refrigerador", "Fogão", "Lava roupas", "Micro-ondas"};
	static String[] TipoModa = new String[] {"Roupa Masculina", "Calçados Masculino", "Roupa Feminina", "Calçados Femininos", "Bolsas e Acessórios"};
	//
	// Adição de departamentos a lista
	//
	public static ArrayList<Departamento> getDepartamentos(){
		listaDepartamentos.add(DeptoBrinquedo);
		listaDepartamentos.add(DeptoTelefonia);
		listaDepartamentos.add(DeptoTVsAudio);
		listaDepartamentos.add(DeptoInformatica);
		listaDepartamentos.add(DeptoEletrodomestico);
		listaDepartamentos.add(DeptoModa);
		
		return listaDepartamentos;
	}
	public static String[] getNomesDepto() {
		return nomesDepto;
	}
	//
	// Adiciona os departamentos a loja
	// Retorna a loja para a View poder usar as informações
	//
	public static ArrayList<ArrayList<Produto>> constroiNovaLoja(){
		//
		// Lista para impressão de todos os produtos ou por departamento...
		//
		ArrayList<ArrayList<Produto>> Loja = new ArrayList<ArrayList<Produto>>();
		Loja.add(ListaBrinquedos);
		Loja.add(ListaTelefonia);
		Loja.add(ListaTVsAudio);
		Loja.add(ListaInformatica);
		Loja.add(ListaEletrodomestico);
		Loja.add(ListaModa);
		//
		// Nessa parte o método constroi a lista, deriva alguns produtos com quantidades aleatórias para as listas
		// e adiciona para cada produto o observer correspondente a seu departamento
		//
		ListaBrinquedos = ConstroiListaDeProdutos("Brinquedo", DeptoBrinquedo, ListaBrinquedos, TipoBrinquedo);
		ListaTelefonia = ConstroiListaDeProdutos("Telefonia", DeptoTelefonia, ListaTelefonia, TipoTelefonia);
		ListaTVsAudio = ConstroiListaDeProdutos("TVs/Áudio", DeptoTVsAudio, ListaTVsAudio, TipoTVsAudio);
		ListaInformatica = ConstroiListaDeProdutos("Informática", DeptoInformatica, ListaInformatica, TipoInformatica);
		ListaEletrodomestico = ConstroiListaDeProdutos("Eletrodoméstico", DeptoEletrodomestico, ListaEletrodomestico, TipoEletrodomestico);
		ListaModa = ConstroiListaDeProdutos("Moda", DeptoModa, ListaModa, TipoModa);
		return Loja;
	}
	//
	// Método que remove todos os descontos
	//
	public static void RemoveDesconto(ArrayList<ArrayList<Produto>> prmLoja) {
		for(ArrayList<Produto> departamentos : prmLoja)
			for(Produto prm : departamentos)
				prm.removeDesconto();
	}
	//
	// Método que insereDesconto
	//
	public static void InsereDescontoDepartamento(ArrayList<Departamento> prmDepartamento, int prmIndex, float prmDesconto, String prmNome) {
		for(Departamento prmDepto : prmDepartamento)
			if(prmNome.equals(prmDepto.getNome()))
				prmDepto.setDesconto(prmDesconto);
	}
	public static void InsereDesconto(ArrayList<ArrayList<Produto>> prmLoja, int prmIndex, float prmDesconto, String prmNome) {
		for(ArrayList<Produto> prmDepartamentos : prmLoja)
			for(Produto prmProdutos : prmDepartamentos)
				switch(prmIndex) {
				case 1:
					prmProdutos.InsereDescontoTipo(prmDesconto, prmNome);
					break;
				case 2:
					prmProdutos.InsereDescontoMarca(prmDesconto, prmNome);
					break;
				case 3:
					prmProdutos.InsereDescontoNome(prmDesconto, prmNome);
					break;
				case 4:
					prmProdutos.InsereDesconto(prmDesconto);
					break;
				}
	}
	//
	// Método que constroi a lista de produtos
	//
	public static ArrayList<Produto> ConstroiListaDeProdutos(String prmGrupoProduto, Departamento prmDepartamento, ArrayList<Produto> prmLista, String[] prmVetorTipo){
		for(int j = 0; j < prmVetorTipo.length; j++) {
			for(int i = 0, contador = random.nextInt(QTDPRODUTOS); i < contador; i++) {
				Produto produto = new Produto(
						"Nome" + prmGrupoProduto + random.nextInt(QTDPRODUTOS), 			// Nome 
						MINPESO + random.nextFloat() * (MAXPESO - MINPESO),  				// Peso
						MINPRECO + random.nextFloat() * (MAXPRECO - MINPRECO), 				// Preço
						prmVetorTipo[j], 													// Tipo
						"Marca" + prmGrupoProduto  + random.nextInt(QTDPRODUTOS), 			// Marca
						random.nextInt(QTDPRODUTOS), 										// Quantidade
						"Característica" + prmGrupoProduto + random.nextInt(QTDPRODUTOS));	// Característica
				produto.addObserver(prmDepartamento); 										// adicionando observer departamento ao produto
				prmDepartamento.addObserver(produto);										// adicionando observer produto ao departamento
				prmLista.add(produto);														// adicionando objeto na lista
			}
		}
		return prmLista;
	}
	//
	// Método que imprime todos os produtos
	//
	public static void ImprimeProdutos(ArrayList<ArrayList<Produto>> prmLoja){
		for(ArrayList<Produto> prmProdutos : prmLoja ) {
			for(Produto prmProdutos2 : prmProdutos) {
				System.out.println(
						"\n" + prmProdutos2.getNome() + 
						"\n\tTipo: " + prmProdutos2.getTipo() + 
						"\n\tPreço: " + prmProdutos2.getPreco() +
						"\n\tMarca: " + prmProdutos2.getMarca());
				if(prmProdutos2.getDesconto() > 0.0f) {
					System.out.println(
					"\t\tCom desconto: " + prmProdutos2.CalculaPreco());
				}
			}
		}
	}
	//
	// Métodos que imprimem os produtos pelo departamento
	//
	public static void ImprimeProdutosPorDepartamento(ArrayList<ArrayList<Produto>> prmLoja, int prmIndex){
		for(int i = 0; i < prmLoja.get(prmIndex).size(); i++) {
			System.out.println(
					"\n" + prmLoja.get(prmIndex).get(i).getNome() + 
					"\n\tTipo: " + prmLoja.get(prmIndex).get(i).getTipo() + 
					"\n\tPreço: " + prmLoja.get(prmIndex).get(i).getPreco() +
					"\n\tMarca: " + prmLoja.get(prmIndex).get(i).getMarca());
			if(prmLoja.get(prmIndex).get(i).getDesconto() > 0.0f) {
				System.out.println(
						"\t\tCom desconto: " + prmLoja.get(prmIndex).get(i).CalculaPreco());
			}
		}
	}
	//
	// Método para interatividade do usuário
	//
	public static void ImprimeCabecalho() {
		System.out.println(
				"\n\n<<< Menu de navegação >>>\n" +
				"(1) - Imprimir produtos por departamento.\n" +
				"(2) - Imprimir todos os produtos da loja.\n" +
				"(3) - Acrescentar desconto.\n" + 
				"(4) - Remover desconto.\n" +
				"(5) - Inserir produtos.\n" +
				"(6) - Sair do programa.\n" +
				"(7) - TESTEEEEE.\n" +
				"<<<------------------->>>\n\n");
	}
	public static class DepartamentoTipo{
		private String Departamento;
		private String[] Tipo;
		
		public String getDepartamento() {
			return this.Departamento;
		}
		public String[] getTipo() {
			return this.Tipo;
		}
		public DepartamentoTipo(String prmDepartamento, String[] prmTipo) {
			this.Departamento = prmDepartamento;
			this.Tipo = prmTipo;
		}
		public DepartamentoTipo(String prmDepartamento) {
			this.Departamento = prmDepartamento;
		}
	}
	//
	// Retorna as informações de departamento e tipo de produto
	// para popular os comboBox na interface gráfica
	//
	public static ArrayList<DepartamentoTipo> getDepartamentoTipo() {
		ArrayList<DepartamentoTipo> prmDepartamentoTipo = new ArrayList<DepartamentoTipo>();
		prmDepartamentoTipo.add(new DepartamentoTipo(nomesDepto[0], TipoBrinquedo));
		prmDepartamentoTipo.add(new DepartamentoTipo(nomesDepto[1], TipoTelefonia));
		prmDepartamentoTipo.add(new DepartamentoTipo(nomesDepto[2], TipoTVsAudio));
		prmDepartamentoTipo.add(new DepartamentoTipo(nomesDepto[3], TipoInformatica));
		prmDepartamentoTipo.add(new DepartamentoTipo(nomesDepto[4], TipoEletrodomestico));
		prmDepartamentoTipo.add(new DepartamentoTipo(nomesDepto[5], TipoModa));
		
//		for(DepartamentoTipo prmPrint : prmDepartamentoTipo) {
//			System.out.println(prmPrint.getDepartamento());
//			for(String prmPrint2 : prmPrint.getTipo()) {
//				System.out.println("\t" + prmPrint2.toString());
//			}
//		}
		return prmDepartamentoTipo;
	}
	public static String adicionaNovoProduto(
			ArrayList<ArrayList<Produto>> prmLoja, 
			String prmNomeProduto, 
			String prmDepartamentoProduto, 
			String prmTipoProduto,
			String prmMarcaProduto,
			String prmCaracteristicaProduto,
			int prmQuantidadeProduto,
			float prmPrecoProduto,
			float prmPesoProduto) {
		try {
			Produto novoProduto = new Produto(
					prmNomeProduto,
					prmPesoProduto,
					prmPrecoProduto,
					prmTipoProduto,
					prmMarcaProduto,
					prmQuantidadeProduto,
					prmCaracteristicaProduto);
			if(prmDepartamentoProduto.equals(nomesDepto[0])) {
				novoProduto.addObserver(DeptoBrinquedo);
				DeptoBrinquedo.addObserver(novoProduto);
				ListaBrinquedos.add(novoProduto);
			}
			if(prmDepartamentoProduto.equals(nomesDepto[1])) {
				novoProduto.addObserver(DeptoTelefonia);
				DeptoTelefonia.addObserver(novoProduto);
				ListaTelefonia.add(novoProduto);
			}
			if(prmDepartamentoProduto.equals(nomesDepto[2])) {
				novoProduto.addObserver(DeptoTVsAudio);
				DeptoTVsAudio.addObserver(novoProduto);
				ListaTVsAudio.add(novoProduto);
			}
			if(prmDepartamentoProduto.equals(nomesDepto[3])) {
				novoProduto.addObserver(DeptoInformatica);
				DeptoInformatica.addObserver(novoProduto);
				ListaInformatica.add(novoProduto);
			}
			if(prmDepartamentoProduto.equals(nomesDepto[4])) {
				novoProduto.addObserver(DeptoEletrodomestico);
				DeptoEletrodomestico.addObserver(novoProduto);
				ListaEletrodomestico.add(novoProduto);
			}
			if(prmDepartamentoProduto.equals(nomesDepto[5])) {
				novoProduto.addObserver(DeptoModa);
				DeptoModa.addObserver(novoProduto);
				ListaModa.add(novoProduto);
			}
			return "";
		}catch(Exception ex) {
			return ex.getMessage();
		}
	}
}
