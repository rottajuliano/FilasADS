package mochila;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI {

	private JFrame frmEventbasedSimulator;
	private JTextField tfLambdaMax;
	private JTextField tfMuMax;
	private JTextField tfCapacidade;
	private JTextField tfNumServidores;
	private JTextField tfTempoDeSimulacao;
	private JTextField tfLambdaMin;
	private JTextField tfMuMin;
	private JLabel labelSemente;
	private JTextField tfSemente;
	
	private Simulador simulador;
	private NascimentoEMorte nm;
	
	
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
		frmEventbasedSimulator.setBounds(100, 100, 350, 300);
		frmEventbasedSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEventbasedSimulator.getContentPane().setLayout(null);
		frmEventbasedSimulator.setLocationRelativeTo(null);
		
		JLabel labelCapacidadeMaxima = new JLabel("Capacidade:");
		labelCapacidadeMaxima.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCapacidadeMaxima.setBounds(10, 73, 192, 14);
		frmEventbasedSimulator.getContentPane().add(labelCapacidadeMaxima);
		
		JLabel labelTemposDeChegada = new JLabel("Tempo de chegada:");
		labelTemposDeChegada.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTemposDeChegada.setBounds(10, 11, 192, 14);
		frmEventbasedSimulator.getContentPane().add(labelTemposDeChegada);
		
		JLabel labelAChegada = new JLabel("a");
		labelAChegada.setBounds(264, 14, 14, 14);
		frmEventbasedSimulator.getContentPane().add(labelAChegada);
		
		JLabel labelTemposDeAtendimento = new JLabel("Tempo de atendimento:");
		labelTemposDeAtendimento.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTemposDeAtendimento.setBounds(10, 42, 192, 14);
		frmEventbasedSimulator.getContentPane().add(labelTemposDeAtendimento);
		
		JLabel labelASaida = new JLabel("a");
		labelASaida.setBounds(264, 45, 14, 14);
		frmEventbasedSimulator.getContentPane().add(labelASaida);
		
		JLabel labelNumeroDeServidores = new JLabel("N\u00FAmero de servidores:");
		labelNumeroDeServidores.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNumeroDeServidores.setBounds(10, 104, 192, 14);
		frmEventbasedSimulator.getContentPane().add(labelNumeroDeServidores);
		
		JLabel labelTempoDeSimulacao = new JLabel("Tempo de simula\u00E7\u00E3o:");
		labelTempoDeSimulacao.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTempoDeSimulacao.setBounds(10, 135, 192, 14);
		frmEventbasedSimulator.getContentPane().add(labelTempoDeSimulacao);
		
		labelSemente = new JLabel("Semente:");
		labelSemente.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSemente.setBounds(10, 166, 192, 14);
		frmEventbasedSimulator.getContentPane().add(labelSemente);
		
		tfLambdaMin = new JTextField();
		tfLambdaMin.setColumns(10);
		tfLambdaMin.setBounds(212, 11, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfLambdaMin);
		
		tfLambdaMax = new JTextField();
		tfLambdaMax.setBounds(278, 11, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfLambdaMax);
		tfLambdaMax.setColumns(10);
		
		tfMuMin = new JTextField();
		tfMuMin.setColumns(10);
		tfMuMin.setBounds(212, 42, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfMuMin);
		
		tfMuMax = new JTextField();
		tfMuMax.setColumns(10);
		tfMuMax.setBounds(278, 42, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfMuMax);
		
		tfCapacidade = new JTextField();
		tfCapacidade.setColumns(10);
		tfCapacidade.setBounds(212, 73, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfCapacidade);
		
		tfNumServidores = new JTextField();
		tfNumServidores.setColumns(10);
		tfNumServidores.setBounds(212, 104, 46, 20);
		frmEventbasedSimulator.getContentPane().add(tfNumServidores);
		
		tfTempoDeSimulacao = new JTextField();
		tfTempoDeSimulacao.setColumns(10);
		tfTempoDeSimulacao.setBounds(212, 135, 112, 20);
		frmEventbasedSimulator.getContentPane().add(tfTempoDeSimulacao);
		
		tfSemente = new JTextField();
		tfSemente.setColumns(10);
		tfSemente.setBounds(212, 166, 112, 20);
		frmEventbasedSimulator.getContentPane().add(tfSemente);
		
		final JCheckBox checkBoxGerarLogs = new JCheckBox("Gerar logs");
		checkBoxGerarLogs.setBounds(6, 187, 97, 23);
		frmEventbasedSimulator.getContentPane().add(checkBoxGerarLogs);
		
		JButton btnSimular = new JButton("Simular");
		btnSimular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				float chegadaMin = Float.parseFloat(tfLambdaMin.getText());
				float chegadaMax = Float.parseFloat(tfLambdaMax.getText());
				float atendimentoMin = Float.parseFloat(tfMuMin.getText());
				float atendimentoMax = Float.parseFloat(tfMuMax.getText());
				
				int cap = Integer.parseInt(tfCapacidade.getText());
				int nServ = Integer.parseInt(tfNumServidores.getText());
				
				float tempoSimulacao = Float.parseFloat(tfTempoDeSimulacao.getText());
				long seed = Long.parseLong(tfSemente.getText());
				
				Fila f = new Fila(1/chegadaMin, 1/chegadaMax, 1/atendimentoMin, 1/atendimentoMax, cap, nServ);
				
				simulador = new Simulador(f, tempoSimulacao, seed);
				simulador.simular();
				if(checkBoxGerarLogs.isSelected()) Util.writeStringToFile(simulador.log, "log.txt");
				
				nm = new NascimentoEMorte(f);
				nm.calcular();
				
				Util.writeResultsToFile(f, simulador, nm, "results.txt");
				infoBox("Os resultados e log foram salvos em arquivos de texto\n no diret�rio de execu��o do programa.", "Simulac�o bem sucedida!");
				
			}
		});
		btnSimular.setBounds(10, 228, 294, 23);
		frmEventbasedSimulator.getContentPane().add(btnSimular);
	}
}
