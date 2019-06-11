package View;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controller.InterfaceController;
import Controller.InterfaceController.DepartamentoTipo;
import Model.Departamento;
import Model.Produto;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Font;
import java.awt.Canvas;


public class WindowInterface {
	// CONSTANTES GLOBAIS
	static int decisao = 0, navegador = 0, prmIndex = 0;
	static Scanner leia = new Scanner(System.in);
	static ArrayList<ArrayList<Produto>> Loja = InterfaceController.constroiNovaLoja();
	static ArrayList<DepartamentoTipo> listaDepartamentos = InterfaceController.getDepartamentoTipo();
	
	private JFrame frmLojaDeProdutos;
	private JTable TabelaDeObjetos;
	private JTextField NomeProduto;
	private JTextField MarcaProduto;
	private JTextField CaracteristicaProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowInterface window = new WindowInterface();
					window.frmLojaDeProdutos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmLojaDeProdutos = new JFrame();
		frmLojaDeProdutos.getContentPane().setEnabled(false);
		frmLojaDeProdutos.setTitle("Loja de produtos");
		frmLojaDeProdutos.setBounds(100, 100, 788, 531);
		frmLojaDeProdutos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLojaDeProdutos.setVisible(true);
		frmLojaDeProdutos.getContentPane().setLayout(null);
		
		//
		// JTable
		// Parte que popula informações no Grid
		//
		Object data[][] = {{"", "", "", "", "", "", "", ""}};
		Object columnNames[] = {"Nome", "Peso", "Preço", "Tipo", "Marca", "Quantidade", "Especificação", "Com Desconto"};
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		//
		// Parte do funcionamento do click do botão
		//
		JButton btnConfirmar = new JButton("Cadastrar");
		btnConfirmar.setBounds(616, 218, 107, 23);
		frmLojaDeProdutos.getContentPane().add(btnConfirmar);
		//
		// JTextField
		//
		NomeProduto = new JTextField();
		NomeProduto.setBounds(127, 47, 225, 20);
		frmLojaDeProdutos.getContentPane().add(NomeProduto);
		NomeProduto.setColumns(10);
		//
		// Labels
		//
		JLabel lblDepartamentos = new JLabel("Departamentos:");
		lblDepartamentos.setBounds(30, 97, 107, 14);
		frmLojaDeProdutos.getContentPane().add(lblDepartamentos);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(30, 147, 107, 14);
		frmLojaDeProdutos.getContentPane().add(lblTipo);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setLabelFor(NomeProduto);
		lblNome.setBounds(30, 47, 107, 14);
		frmLojaDeProdutos.getContentPane().add(lblNome);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(362, 197, 107, 14);
		frmLojaDeProdutos.getContentPane().add(lblPeso);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setBounds(362, 147, 107, 14);
		frmLojaDeProdutos.getContentPane().add(lblPreco);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(30, 197, 107, 14);
		frmLojaDeProdutos.getContentPane().add(lblMarca);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(362, 97, 107, 14);
		frmLojaDeProdutos.getContentPane().add(lblQuantidade);
		
		JLabel lblCaracteristica = new JLabel("Caracter\u00EDstica:");
		lblCaracteristica.setBounds(362, 47, 107, 14);
		frmLojaDeProdutos.getContentPane().add(lblCaracteristica);
		
		JComboBox<Object> TipoProduto = new JComboBox<Object>();
		TipoProduto.setBounds(127, 144, 225, 20);
		frmLojaDeProdutos.getContentPane().add(TipoProduto);
		
		MarcaProduto = new JTextField();
		MarcaProduto.setColumns(10);
		MarcaProduto.setBounds(127, 197, 225, 20);
		frmLojaDeProdutos.getContentPane().add(MarcaProduto);
		
		CaracteristicaProduto = new JTextField();
		CaracteristicaProduto.setColumns(10);
		CaracteristicaProduto.setBounds(459, 47, 225, 20);
		frmLojaDeProdutos.getContentPane().add(CaracteristicaProduto);
		
		JSpinner PrecoProduto = new JSpinner();
		PrecoProduto.setBounds(459, 147, 94, 20);
		frmLojaDeProdutos.getContentPane().add(PrecoProduto);
		
		JSpinner QuantidadeProduto = new JSpinner();
		QuantidadeProduto.setBounds(459, 97, 94, 20);
		frmLojaDeProdutos.getContentPane().add(QuantidadeProduto);
		
		JSpinner PesoProduto = new JSpinner();
		PesoProduto.setBounds(459, 197, 94, 20);
		frmLojaDeProdutos.getContentPane().add(PesoProduto);
		
		//
		// Tabela que mostra os produtos
		//
		TabelaDeObjetos = new JTable(model);
		TabelaDeObjetos.setLocation(78, 70);
		TabelaDeObjetos.setEnabled(false);
		TabelaDeObjetos.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		TabelaDeObjetos.setColumnSelectionAllowed(true);
		TabelaDeObjetos.getTableHeader();
		frmLojaDeProdutos.getContentPane().add(TabelaDeObjetos);
		
		//
		// JScrollPane fica atrás da tabela para mostrar o cabeçalho
		//
		JScrollPane ScrollTabelaDeObjetos = new JScrollPane(TabelaDeObjetos);
		ScrollTabelaDeObjetos.setBounds(10, 343, 751, 127);
		frmLojaDeProdutos.getContentPane().add(ScrollTabelaDeObjetos);
		TabelaDeObjetos.setFillsViewportHeight(true);
		
		JComboBox<Object> BuscaTipo = new JComboBox<Object>();
		BuscaTipo.setBounds(321, 312, 229, 20);
		frmLojaDeProdutos.getContentPane().add(BuscaTipo);
		
		JComboBox<Object> BuscaMarca = new JComboBox<Object>();
		BuscaMarca.setBounds(560, 312, 163, 20);
		frmLojaDeProdutos.getContentPane().add(BuscaMarca);
		
		JComboBox<String> BuscaDepartamento = new JComboBox<String>();
		BuscaDepartamento.setBounds(174, 312, 137, 20);
		frmLojaDeProdutos.getContentPane().add(BuscaDepartamento);
		
		JLabel lblBusca = new JLabel("Buscar produto");
		lblBusca.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblBusca.setBounds(10, 290, 154, 54);
		frmLojaDeProdutos.getContentPane().add(lblBusca);
		
		JLabel lblBuscaTipo = new JLabel("Tipo:");
		lblBuscaTipo.setBounds(321, 290, 229, 14);
		frmLojaDeProdutos.getContentPane().add(lblBuscaTipo);
		
		JLabel lblBuscaDepartamento = new JLabel("Departamentos:");
		lblBuscaDepartamento.setBounds(174, 290, 126, 14);
		frmLojaDeProdutos.getContentPane().add(lblBuscaDepartamento);
		
		JLabel lblBuscaMarca = new JLabel("Marca:");
		lblBuscaMarca.setBounds(560, 290, 163, 14);
		frmLojaDeProdutos.getContentPane().add(lblBuscaMarca);
		
		//
		// Linha de divisão central
		//
		Canvas LinhaDivisaoCentral = new Canvas();
		LinhaDivisaoCentral.setBackground(Color.LIGHT_GRAY);
		LinhaDivisaoCentral.setBounds(10, 253, 751, 4);
		frmLojaDeProdutos.getContentPane().add(LinhaDivisaoCentral);
		
		JLabel lblCadastra = new JLabel("Cadastra produto");
		lblCadastra.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		lblCadastra.setBounds(81, 0, 196, 33);
		frmLojaDeProdutos.getContentPane().add(lblCadastra);
		
		//
		// JComboBox que mostra os departamentos
		//
		JComboBox<String> DepartamentoProduto = new JComboBox<String>();
		DepartamentoProduto.setBounds(127, 94, 225, 20);
		frmLojaDeProdutos.getContentPane().add(DepartamentoProduto);
		
		//
		// Alert labels
		//
		JLabel lblAlertCadastraNome = new JLabel("Insira um nome para o produto com n\u00FAmeros e letras!");
		lblAlertCadastraNome.setForeground(Color.RED);
		lblAlertCadastraNome.setBounds(40, 72, 355, 14);
		lblAlertCadastraNome.setVisible(false);
		frmLojaDeProdutos.getContentPane().add(lblAlertCadastraNome);
		
		JLabel lblAlertCadastraDepartamento = new JLabel("Escolha um departamento para o produto!");
		lblAlertCadastraDepartamento.setForeground(Color.RED);
		lblAlertCadastraDepartamento.setBounds(40, 122, 279, 14);
		lblAlertCadastraDepartamento.setVisible(false);
		frmLojaDeProdutos.getContentPane().add(lblAlertCadastraDepartamento);
		
		JLabel lblAlertCadastraTipo = new JLabel("Escolha um tipo para o produto!");
		lblAlertCadastraTipo.setForeground(Color.RED);
		lblAlertCadastraTipo.setBounds(40, 172, 279, 14);
		lblAlertCadastraTipo.setVisible(false);
		frmLojaDeProdutos.getContentPane().add(lblAlertCadastraTipo);
		
		JLabel lblAlertCadastraMarca = new JLabel("Insira uma marca para o produto!");
		lblAlertCadastraMarca.setForeground(Color.RED);
		lblAlertCadastraMarca.setBounds(40, 222, 279, 14);
		lblAlertCadastraMarca.setVisible(false);
		frmLojaDeProdutos.getContentPane().add(lblAlertCadastraMarca);
		
		JLabel lblAlertCadastraQuantidade = new JLabel("Escolha uma quantidade para o produto!");
		lblAlertCadastraQuantidade.setForeground(Color.RED);
		lblAlertCadastraQuantidade.setBounds(372, 122, 279, 14);
		lblAlertCadastraQuantidade.setVisible(false);
		frmLojaDeProdutos.getContentPane().add(lblAlertCadastraQuantidade);
		
		JLabel lblAlertCadastraPreco = new JLabel("Determine um pre\u00E7o para o produto");
		lblAlertCadastraPreco.setForeground(Color.RED);
		lblAlertCadastraPreco.setBounds(372, 172, 279, 14);
		lblAlertCadastraPreco.setVisible(false);
		frmLojaDeProdutos.getContentPane().add(lblAlertCadastraPreco);
		
		JLabel lblAlertCadastraPeso = new JLabel("Determine um peso para o produto!");
		lblAlertCadastraPeso.setForeground(Color.RED);
		lblAlertCadastraPeso.setBounds(372, 222, 279, 14);
		lblAlertCadastraPeso.setVisible(false);
		frmLojaDeProdutos.getContentPane().add(lblAlertCadastraPeso);
		frmLojaDeProdutos.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNome, lblDepartamentos, lblTipo, lblMarca, lblCaracteristica, lblQuantidade, lblPreco, lblPeso, NomeProduto, DepartamentoProduto, TipoProduto, MarcaProduto, CaracteristicaProduto, QuantidadeProduto, PrecoProduto, PesoProduto}));
		
		
		
		
		//
		// Solução que popula o comboBox de departamento e tipo
		//
		listaDepartamentos.add(0, new DepartamentoTipo("Escolha um departamento"));
		for(DepartamentoTipo prmDepartamento : listaDepartamentos) {
			DepartamentoProduto.addItem(prmDepartamento.getDepartamento().toString());
			BuscaDepartamento.addItem(prmDepartamento.getDepartamento().toString());
		}
		//
		// Controle comboBox departamento e tipo em cadastro de produto
		//
		DepartamentoProduto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(TipoProduto.getItemCount() > 0)
					TipoProduto.removeAllItems();
				if(DepartamentoProduto.getItemAt(0).equals("Escolha um departamento"))
					DepartamentoProduto.removeItemAt(0);
				for(DepartamentoTipo prmDepartamento : listaDepartamentos) {
					if(DepartamentoProduto.getSelectedItem().equals(prmDepartamento.getDepartamento())){
						for(String prmTipo : prmDepartamento.getTipo()) {
							TipoProduto.addItem(prmTipo.toString());
						}
					}
				}
			}
		});
		//
		// Controle comboBoxes departamento, tipo, marca em busca de produto
		// JComboBox Departamento
		//
		BuscaDepartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(BuscaTipo.getItemCount() > 0)
					BuscaTipo.removeAllItems();
				if(BuscaDepartamento.getItemAt(0).equals("Escolha um departamento"))
					BuscaDepartamento.removeItemAt(0);
				for(DepartamentoTipo prmDepartamento : listaDepartamentos) {
					if(BuscaDepartamento.getSelectedItem().equals(prmDepartamento.getDepartamento())) {
						for(String prmTipo : prmDepartamento.getTipo()) {
							BuscaTipo.addItem(prmTipo.toString());
						}
					}
				}
				if(BuscaTipo.getItemCount() == 0)
					BuscaTipo.addItem(new DepartamentoTipo("Nenhum tipo encontrado"));
			}
		});
		//
		// JComboBox Tipo
		//
		BuscaTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(BuscaMarca.getItemCount() > 0)
					BuscaMarca.removeAllItems();
				for(ArrayList<Produto> prmDepartamento : Loja){
					for(Produto prmTipos : prmDepartamento) {
						if(prmTipos.getTipo().equals(BuscaTipo.getSelectedItem())) {
							BuscaMarca.addItem(prmTipos.getMarca());
						}
					}
				}
			}
		});
		//
		// Solução que popula o GRID que mostra as informações do produto
		//
		BuscaMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				model.setRowCount(0);
				for(ArrayList<Produto> prmProdutos : Loja) {
					for(Produto prmProduto : prmProdutos) {
						if(prmProduto.getTipo().equals(BuscaTipo.getSelectedItem()) && prmProduto.getMarca().equals(BuscaMarca.getSelectedItem())) {
							//JOptionPane.showMessageDialog(null, "Entrei no IF");
							//Object columnNames[] = {"Nome", "Peso", "Preço", "Tipo", "Marca", "Quantidade", "Especificação", "Com Desconto"};
							Object data[] = {
								prmProduto.getNome(), 
								prmProduto.getPeso(), 
								prmProduto.getPreco(), 
								prmProduto.getTipo(), 
								prmProduto.getMarca(), 
								prmProduto.getQuantidade(), 
								prmProduto.getCaracteristicaEspecifica(), 
								prmProduto.CalculaPreco()};
							//Object data[] = new Object[]{"", "", "", "", "", "", "", ""};
							model.addRow(data);
						}
					}
				}
			}
		});
		
		//
		// EventListener Botão Confirmar
		//
		btnConfirmar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(NomeProduto.getText().equals(null)
					|| NomeProduto.getText().equals("")
					|| !NomeProduto.getText().matches("[a-zA-Z0-9]+")) {
				lblAlertCadastraNome.setVisible(true);
			}else {
				lblAlertCadastraNome.setVisible(false);
				if(DepartamentoProduto.getSelectedItem().equals("Escolha um departamento")) {
					lblAlertCadastraDepartamento.setVisible(true);
				}else {
					lblAlertCadastraDepartamento.setVisible(false);
					if(TipoProduto.getSelectedItem().equals(null)) {
						lblAlertCadastraTipo.setVisible(true);
					}else {
						lblAlertCadastraTipo.setVisible(false);
						if(MarcaProduto.getText().equals(null) 
								|| MarcaProduto.getText().equals("")
								|| !MarcaProduto.getText().matches("[a-zA-Z0-9]+")) {
							lblAlertCadastraMarca.setVisible(true);
						}else {
							lblAlertCadastraMarca.setVisible(false);
							if((int)QuantidadeProduto.getValue() == 0) {
								lblAlertCadastraQuantidade.setVisible(true);
							}else {
								lblAlertCadastraQuantidade.setVisible(false);
								if((int)PrecoProduto.getValue() == 0) {
									lblAlertCadastraPreco.setVisible(true);
								}else {
									lblAlertCadastraPreco.setVisible(false);
									if((int)PesoProduto.getValue() == 0) {
										lblAlertCadastraPeso.setVisible(true);
									}else {
										lblAlertCadastraPeso.setVisible(false);
										InterfaceController.adicionaNovoProduto(
												Loja,
												NomeProduto.getText(),
												DepartamentoProduto.getSelectedItem().toString(),
												TipoProduto.getSelectedItem().toString(),
												MarcaProduto.getText(),
												CaracteristicaProduto.getText(),
												(int)QuantidadeProduto.getValue(),
												Float.parseFloat(PrecoProduto.getValue().toString()),
												Float.parseFloat(PesoProduto.getValue().toString()));
										String printConfirmacao = "Novo produto cadastrado!\n" +
												"Nome: " + NomeProduto.getText() + "\n" +
												"Departamento: " + DepartamentoProduto.getSelectedItem().toString() + "\n" +
												"Tipo: " + TipoProduto.getSelectedItem().toString() + "\n" +
												"Marca: " + MarcaProduto.getText() + "\n" +
												"Característica: " + CaracteristicaProduto.getText() + "\n" +
												"Quantidade" + QuantidadeProduto.getValue().toString() + "\n" +
												"Preço: " + PrecoProduto.getValue().toString() + "\n" +
												"Peso: " + PesoProduto.getValue().toString();
										JOptionPane.showMessageDialog(null, printConfirmacao);
									}
								}
							}
						}
					}
				}
			}	
		}
		});
		
//		//
//		// Tentativa de inserção de fieldset
//		//
//		JPanel panel = new JPanel();
//		panel.add(new JLabel("foo"));
//		panel.setBorder(BorderFactory.createTitledBorder("bar")); 
//		//JOptionPane.showMessageDialog(null,"Funcionou");
	}
}

