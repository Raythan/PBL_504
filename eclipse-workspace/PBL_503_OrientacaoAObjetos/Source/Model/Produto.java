package Model;
import java.util.Observable;
import java.util.Observer;

public class Produto extends Observable implements Observer{
	private String nome;
	private float desconto;
	private float peso;
	private float preco;
	private String tipo;
	private String marca;
	private int quantidade;
	private String caracteristicaEspecifica;
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nomeParametro) {
		this.nome = nomeParametro;
	}
	public float getDesconto() {
		return this.desconto;
	}
	public void setDesconto(float descontoParametro) {
		this.desconto = descontoParametro;
	}
	public float getPeso() {
		return this.peso;
	}
	public void setPeso(float pesoParametro) {
		this.peso = pesoParametro;
	}
	public float getPreco() {
		return this.preco;
	}
	public void setPreco(float precoParametro) {
		this.preco = precoParametro;
	}
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipoParametro) {
		this.tipo = tipoParametro;
	}
	public String getMarca() {
		return this.marca;
	}
	public void setMarca(String marcaParametro) {
		this.marca = marcaParametro;
	}
	public int getQuantidade() {
		return this.quantidade;
	}
	public void setQuantidade(int quantidadeParametro) {
		this.quantidade = quantidadeParametro;
	}
	public String getCaracteristicaEspecifica() {
		return this.caracteristicaEspecifica;
	}
	public void setCaracteristicaEspecifica(String caracteristicaParametro) {
		this.caracteristicaEspecifica = caracteristicaParametro;
	}
	public float CalculaPreco() {
		float aux = 0.0f;
		if(this.desconto > 0.0f)
			aux = this.preco - (this.preco * (this.desconto / 100));
		else
			aux = this.preco;
		return aux;
	}
	//
	// M�todo que insere o desconto para todas as marcas
	// e notifica o Observer se receber uma condi��o v�lida
	//
	public void InsereDescontoMarca(float descontoParametro) {
		if(this.desconto < descontoParametro){
			this.desconto = descontoParametro;

			setChanged();
			notifyObservers("marca!");
		}
	}
	//
	// M�todo que insere o desconto para marcas espec�ficas
	// e notifica o Observer se receber uma condi��o v�lida
	//
	public void InsereDescontoMarca(float descontoParametro, String parametroMarca) {
		if(this.desconto < descontoParametro && this.marca.equals(parametroMarca)){
			this.desconto = descontoParametro;

			setChanged();
			notifyObservers("marca!");
		}
	}
	//
	// M�todo que insere o valor do desconto para todos os produtos
	// 
	public void InsereDesconto(float descontoParametro){
		if(this.desconto < descontoParametro) {
			this.desconto = descontoParametro;

			setChanged();
			notifyObservers();
		}
	}
	//
	// M�todo que insere o valor do desconto para os produtos por nomes espec�ficos
	//
	public void InsereDescontoNome(float descontoParametro, String parametroNome){
		if(this.desconto < descontoParametro && this.nome.equals(parametroNome)) {
			this.desconto = descontoParametro;

			setChanged();
			notifyObservers("nome!");
		}
	}
	//
	// M�todo que insere o desconto por todos os tipos
	// e notifica o Observer se receber uma condi��o v�lida
	//
	public void InsereDescontoTipo(float descontoParametro){
		if(this.desconto < descontoParametro) {
			this.desconto = descontoParametro;

			setChanged();
			notifyObservers("tipo!");
		}
	}
	//
	// M�todo que insere o desconto para tipos espec�ficos
	//
	public void InsereDescontoTipo(float descontoParametro, String parametroTipo){
		if(this.desconto < descontoParametro && this.tipo.equals(parametroTipo)) {
			this.desconto = descontoParametro;

			setChanged();
			notifyObservers("tipo!");
		}
	}
	//
	// M�todo que remove o valor do desconto e retorna o c�lculo ao pre�o original
	//
	public void removeDesconto() {
		if(this.desconto > 0.0f) {
			this.desconto = 0.0f;
			System.out.println("Desconto removido de " + this.getNome());
		}
	}
	public Produto(
			String nomeParametro, 
			float pesoParametro, 
			float precoParametro, 
			String tipoParametro, 
			String marcaParametro, 
			int quantidadeParametro,
			String caracteristicaParametro) {
		this.nome = nomeParametro;
		this.peso = pesoParametro;
		this.preco = precoParametro;
		this.tipo = tipoParametro;
		this.marca = marcaParametro;
		this.quantidade = quantidadeParametro;
		this.caracteristicaEspecifica = caracteristicaParametro;
	}
	@Override
	public void update(Observable prmObserver, Object prm) {
		if(prmObserver != null && this.desconto < (float)prm) {
			this.desconto = (float)prm;
			System.out.println("Este produto agora tem desconto por departamento de " + prm + "%");
		}
		
	}
}
