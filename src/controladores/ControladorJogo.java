package controladores;

import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import armas.Arma;
import armas.Couracado;
import armas.Cruzador;
import armas.Destroyer;
import armas.Hidroaviao;
import armas.Submarino;
import dominio.Jogador;
import gui.FrameAtaque;
import gui.FrameJogadores;
import gui.FramePosicionamento;
import gui.PainelArma;
import gui.PainelArmas;
import outros.Coordenada;

public class ControladorJogo {

	private static ControladorJogo gamePresenter = null;
	private FramePosicionamento framePosicionamento;
	private FrameJogadores frameJogadores;
	private FrameAtaque frameAtaque;
	private Jogador jogador1 = new Jogador();
	private Jogador jogador2 = new Jogador();
	
	private ControladorJogo() {
	}
	
	public static ControladorJogo getMainGamePresenter() {
		if(gamePresenter == null) {
			gamePresenter = new ControladorJogo();
		}
		return gamePresenter;
	}
	
	public void iniciarJogo() { 
		frameJogadores = new FrameJogadores();
		frameJogadores.setTitle("Batalha Naval"); 
		frameJogadores.setVisible(true);
	}
	
	public void fecharFrameJogadores(String nomeJogador1, String nomeJogador2) {
		setNomeJogador(nomeJogador1, 1);
		setNomeJogador(nomeJogador2, 2);
		frameJogadores.setVisible(false);
		framePosicionamento = new FramePosicionamento();
		framePosicionamento.setTitle("Batalha Naval");
		framePosicionamento.setVisible(true);
	}
	
	public void fecharFramePosicionamento() {
		framePosicionamento.setVisible(false);
		abrirFrameAtaque();
	}
	
	private void abrirFrameAtaque() {
		frameAtaque = new FrameAtaque();
		frameAtaque.setTitle("Batalha Naval");
		frameAtaque.setVisible(true);
	}
	
	public void fecharFrameAtaque() {
		frameAtaque.dispatchEvent(new WindowEvent(frameAtaque, WindowEvent.WINDOW_CLOSING));
	}
	
	public FramePosicionamento getFramePosicionamento() {
		return framePosicionamento;
	}
	
	public void setNomeJogador(String nome, int numJogador) {
		if(numJogador == 1) {
			jogador1.setNome(nome);
		} else if(numJogador == 2) {
			jogador2.setNome(nome);
		}
	}
	
	public void setArmasJogador(ArrayList<PainelArma> armas, int numJogador) {
		if(numJogador == 1) {
			jogador1.setArmas(armas);
		} else if(numJogador == 2) {
			jogador2.setArmas(armas);
		}
	}
	
	public void setCoordenadaArmasJogador(ArrayList<Coordenada[]> coordenadaArmas, int numJogador) {
		if(numJogador == 1) {
			jogador1.setCoordenadaArmas(coordenadaArmas);
		} else if(numJogador == 2) {
			jogador2.setCoordenadaArmas(coordenadaArmas);
		}
	}
	
	public Jogador getJogador(int numJogador) {
		if(numJogador == 1) {
			return jogador1;
		} else if(numJogador == 2) {
			return jogador2;
		}
		return null;
	}
	
	public void recarregarJogo() {
		JFileChooser fileChooser = new JFileChooser();
		Scanner scanner = null;
        
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try {
        		scanner = new Scanner(new BufferedReader(new FileReader(fileChooser.getSelectedFile())));
        		leDadosSalvos(scanner);
        	} catch (Exception ex) {
        		ex.printStackTrace();
        	} finally {
        		if (scanner != null) { 
        			scanner.close();
        		}
        	}
        }
        abrirFrameAtaque();
	}
	
	private void leDadosSalvos(Scanner scanner) {
		leDadosSalvosJogador(scanner, jogador1);
		leDadosSalvosJogador(scanner, jogador2);		
	}
	
	private void leDadosSalvosJogador(Scanner scanner, Jogador jogador) {
		String nomeJogador = scanner.nextLine();
		jogador.setNome(nomeJogador);
		
		scanner.nextLine();
		String linha = scanner.nextLine();
		
		ArrayList<Coordenada[]> arrayCoordenadas = new ArrayList<>();
		
		while(!linha.isEmpty()) {
			String[] stringCoordenadas = linha.split(", ");
			Coordenada[] coordenadas = new Coordenada[stringCoordenadas.length];
			for(int i = 0; i < stringCoordenadas.length; i++) {
				String[] stringCoordenada = stringCoordenadas[i].split(" ");
				coordenadas[i] = new Coordenada(Integer.valueOf(stringCoordenada[0]), Integer.valueOf(stringCoordenada[1]));
			}
			arrayCoordenadas.add(coordenadas);
			if(scanner.hasNextLine()) {
				linha = scanner.nextLine();				
			} else {
				linha = "";
			}
		}
		
		linha = scanner.nextLine();
		ArrayList<PainelArma> armas = new ArrayList<>();

		while(!linha.isEmpty()) {
			if("couracado".equals(linha)) {
				armas.add(new PainelArma(new Couracado(), PainelArmas.COURACADO_COR));
			} else if("cruzador".equals(linha)) {
				armas.add(new PainelArma(new Cruzador(), PainelArmas.CRUZADOR_COR));
			} else if("destroyer".equals(linha)) {
				armas.add(new PainelArma(new Destroyer(), PainelArmas.DESTROYER_COR));
			} else if("hidroaviao".equals(linha)) {
				armas.add(new PainelArma(new Hidroaviao(), PainelArmas.HIDROAVIAO_COR));
			} else if("submarino".equals(linha)) {
				armas.add(new PainelArma(new Submarino(), PainelArmas.SUBMARINO_COR));
			} 

			if(scanner.hasNextLine()) {
				linha = scanner.nextLine();				
			} else {
				linha = "";
			}
		}
		
		jogador.setCoordenadaArmas(arrayCoordenadas);
		jogador.setArmas(armas);
	}
	
	public void salvarJogo() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		FileWriter fileWriter = null;
		
	    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	        try {
	            fileWriter = new FileWriter(fileChooser.getSelectedFile() + ".txt");
	            fileWriter.write(formataSalvamento());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	        	if(fileWriter != null) {
	        		fileWriter.close();
	        	}
	        }
	    }
	}
	
	private String formataSalvamento() {
		String salvamento = "";
		salvamento += jogador1.toString();
		salvamento += "\n";
		salvamento += jogador2.toString();
		
		return salvamento;
	}
	
}