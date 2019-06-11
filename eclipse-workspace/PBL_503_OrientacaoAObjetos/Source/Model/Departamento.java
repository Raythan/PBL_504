package Model;

import java.util.Observable;
import java.util.Observer;


public class Departamento extends Observable implements Observer{
	private String nome;
	private float desconto;
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nomeParametro) {
		this.nome = nomeParametro;
	}
	public float getDesconto() {
		return this.desconto;
	}
	public void setDesconto(float prmDesconto) {
		if(this.desconto < prmDesconto) {
			this.desconto = prmDesconto;
			
			setChanged();
			notifyObservers(prmDesconto);
		}
	}
	public Departamento(String nomeParametro) {
		this.nome = nomeParametro;
	}
	@Override
	public void update(Observable objetoParametro, Object arg) {
		//
		// Esse condicional verifica se o objeto que est� notificando n�o � nulo
		// e apresenta uma mensagem de que foi notificado sobre alguma altera��o nas classes
		// que ele est� observando
		//
		if(objetoParametro instanceof Produto && arg != null && arg != "Todos") {
			System.out.println("Agora " + ((Produto) objetoParametro).getNome() + " est� com desconto por " + arg);
		}
	}
}
