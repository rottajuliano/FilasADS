package mochila;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUI {

	private JFrame frmEventbasedSimulator;
	private JTextField tfChegadaMax;
	private JTextField tfSaidaMax;
	private JTextField tfCapacidade;
	private JTextField tfNumServidores;
	private JTextField tfTempoDeSimulacao;
	private JTextField tfChegadaMin;
	private JTextField tfSaidaMin;
	private JLabel labelSemente;
	private JTextField tfSemente;
	
	private Simulador simulador;
	
	public void writeLogToFile(String fileName)
	{
		try {
			PrintWriter out = new PrintWriter(fileName);
			out.println(simulador.getLog());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void writeResultsToFile(String fileName)
	{
		String results = "";
		results += "Clientes perdidos: " + simulador.getNumClientesPerdidos() + "\n";
		results += "Probabilidades marginais: " + simulador.getProbabilidadesMarginais() + "\n";
		results += "Populacao media: " + simulador.getPopulacaoMedia() + "\n";
		results += "Utilizacao media: " + simulador.getUtilizacaoMedia() + "\n";
		results += "Vazao media: " + simulador.getVazaoMedia() + "\n";
		results += "Tempo de resposta medio: " + simulador.getTempoRespostaMedio() + "\n";
		try {
			PrintWriter out = new PrintWriter(fileName);
			out.println(results);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmEventbasedSimulator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEventbasedSimulator = new JFrame();
		frmEventbasedSimulator.setTitle("Simulador");
		frmEventbasedSimulator.setBounds(100, 100, 300, 300);
		frmEventbasedSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEventbasedSimulator.getContentPane().setLayout(null);
		frmEventbasedSimulator.setLocationRelativeTo(null);
		
		JLabel labelCapacidadeMaxima = new JLabel("Capacidade m\u00E1xima:");
		labelCapacidadeMaxima.setBounds(10, 73, 142, 14);
		frmEventbasedSimulator.getContentPane().add(labelCapacidadeMaxima);
		
		JLabel labelTemposDeChegada = new JLabel("Tempos de chegada:");
		labelTemposDeChegada.setBounds(10, 11, 142, 14);
		frmEventbasedSimulator.getContentPane().add(labelTemposDeChegada);
		
		JLabel labelAChegada = new JLabel("a");
		labelAChegada.setBounds(214, 8, 14, 14);
		frmEventbasedSimulator.getContentPane().add(labelAChegada);
		
		JLabel labelTemposDeSaida = new JLabel("Tempos de sa\u00EDda:");
		labelTemposDeSaida.setBounds(10, 42, 142, 14);
		frmEventbasedSimulator.getContentPane().add(labelTemposDeSaida);
		
		JLabel labelASaida = new JLabel("a");
		labelASaida.setBounds(214, 39, 14, 14);
		frmEventbasedSimulator.getContentPane().add(labelASaida);
		
		JLabel labelNumeroDeServidores = new JLabel("N\u00FAmero de servidores:");
		labelNumeroDeServidores.setBounds(10, 104, 142, 14);
		frmEventbasedSimulator.getContentPane().add(labelNumeroDeServidores);
		
		JLabel labelTempoDeSimulacao = new JLabel("Tempo de simula\u00E7\u00E3o:");
		labelTempoDeSimulacao.setBounds(10, 135, 142, 14);
		frmEventbasedSimulator.getContentPane().add(labelTempoDeSimulacao);
		
		labelSemente = new JLabel("Semente:");
		labelSemente.setBounds(10, 166, 142, 14);
		frmEventbasedSimulator.getContentPane().add(labelSemente);
		
		tfChegadaMin = new JTextField();
		tfChegadaMin.setColumns(10);
		tfChegadaMin.setBounds(162, 5, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfChegadaMin);
		
		tfChegadaMax = new JTextField();
		tfChegadaMax.setBounds(228, 5, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfChegadaMax);
		tfChegadaMax.setColumns(10);
		
		tfSaidaMin = new JTextField();
		tfSaidaMin.setColumns(10);
		tfSaidaMin.setBounds(162, 36, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfSaidaMin);
		
		tfSaidaMax = new JTextField();
		tfSaidaMax.setColumns(10);
		tfSaidaMax.setBounds(228, 36, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfSaidaMax);
		
		tfCapacidade = new JTextField();
		tfCapacidade.setColumns(10);
		tfCapacidade.setBounds(162, 67, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfCapacidade);
		
		tfNumServidores = new JTextField();
		tfNumServidores.setColumns(10);
		tfNumServidores.setBounds(162, 98, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfNumServidores);
		
		tfTempoDeSimulacao = new JTextField();
		tfTempoDeSimulacao.setColumns(10);
		tfTempoDeSimulacao.setBounds(162, 129, 112, 20);
		frmEventbasedSimulator.getContentPane().add(tfTempoDeSimulacao);
		
		tfSemente = new JTextField();
		tfSemente.setColumns(10);
		tfSemente.setBounds(162, 160, 112, 20);
		frmEventbasedSimulator.getContentPane().add(tfSemente);
		
		final JCheckBox checkBoxGerarLogs = new JCheckBox("Gerar logs");
		checkBoxGerarLogs.setBounds(6, 187, 97, 23);
		frmEventbasedSimulator.getContentPane().add(checkBoxGerarLogs);
		
		JButton btnSimular = new JButton("Simular");
		btnSimular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int cap = Integer.parseInt(tfCapacidade.getText());
				int nServ = Integer.parseInt(tfNumServidores.getText());
				float chegadaMin = Float.parseFloat(tfChegadaMin.getText());
				float chegadaMax = Float.parseFloat(tfChegadaMax.getText());
				float saidaMin = Float.parseFloat(tfSaidaMin.getText());
				float saidaMax = Float.parseFloat(tfSaidaMax.getText());
				
				float tempoSimulacao = Float.parseFloat(tfTempoDeSimulacao.getText());
				long seed = Long.parseLong(tfSemente.getText());
				Fila f = new Fila(cap, nServ, chegadaMin, chegadaMax, saidaMin, saidaMax);
				simulador = new Simulador(f, tempoSimulacao, seed);
				simulador.simular();
				if(checkBoxGerarLogs.isSelected()) writeLogToFile("log.txt");
				writeResultsToFile("results.txt");
				infoBox("Os resultados e logs foram salvos em arquivos de texto\n no diretório de execução do programa.", "Simulacão bem sucedida!");
				
			}
		});
		btnSimular.setBounds(10, 228, 264, 23);
		frmEventbasedSimulator.getContentPane().add(btnSimular);
	}
}
