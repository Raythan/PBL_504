package View;

import java.util.ArrayList;
import java.util.Scanner;
import Controller.InterfaceController;
import Model.Departamento;
import Model.Produto;

public class ComandosInterface {
	// CONSTANTES GLOBAIS
	static int decisao = 0, navegador = 0, prmIndex = 0;
	static Scanner leia = new Scanner(System.in);
	static ArrayList<ArrayList<Produto>> Loja = InterfaceController.constroiNovaLoja();
	static String[] nomesDepto = InterfaceController.getNomesDepto();
	static ArrayList<Departamento> listaDepartamentos = InterfaceController.getDepartamentos();
	
	public static void main(String[] args) {
		while(decisao != 1) {
			InterfaceController.ImprimeCabecalho();
			navegador = leia.nextInt();
			switch(navegador) {
			case 1:
				System.out.println("\nQual departamento?\n");
				for(int i = 0; i < nomesDepto.length; i++) {
					System.out.println("(" + (i + 1) + ") - " + nomesDepto[i]);
				}
				prmIndex = leia.nextInt();
				InterfaceController.ImprimeProdutosPorDepartamento(Loja, (prmIndex - 1));
				break;
			case 2:
				InterfaceController.ImprimeProdutos(Loja);
				break;
			case 3:
				boolean condicao = true;
				float prmDesconto = 0.0f;
				String prmNome = "";
				while(condicao) {
					System.out.println("\nDesconto por:\n" +
							"(1) - Tipo específico\n" + 
							"(2) - Marca específica\n" + 
							"(3) - Nome específico\n" + 
							"(4) - Todos os produtos da loja\n" + 
							"(5) - Departamento específico\n");
					prmIndex = leia.nextInt();
					System.out.println("Qual o valor do desconto? Ex.: 12,0 = 12%, 0,3 = 3%\n");
					prmDesconto = leia.nextFloat();
					leia.nextLine();

					if(prmIndex == 1 || prmIndex == 2 || prmIndex == 3) {
						System.out.println("Qual em específico?\n");
						prmNome = leia.nextLine();
					}
					if(prmIndex == 5) {
						System.out.println("Qual em específico?\n");
						prmNome = leia.nextLine();
						
					}
					if(prmDesconto != 0.0) {
						if(prmIndex == 5)
							InterfaceController.InsereDescontoDepartamento(listaDepartamentos, prmIndex, prmDesconto, prmNome);
						else
							InterfaceController.InsereDesconto(Loja, prmIndex, prmDesconto, prmNome);
						condicao = false;
					}
					else {
						System.out.println("Você digitou algum valor inválido");
						leia.nextLine();
					}	
				}
				break;
			case 4:
				InterfaceController.RemoveDesconto(Loja);
				System.out.println("Todos os descontos foram removidos com sucesso! :D");
				break;
			case 5:
				// ELABORAR INSERÇÃO DE PRODUTOS NOVOS
				break;
			case 6:
				decisao = 1;
				break;
			case 7:
				InterfaceController.getDepartamentoTipo();
				break;
			}
		}	
	}
	
}
