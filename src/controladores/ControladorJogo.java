package controladores;

import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import armas.Couracado;
import armas.Cruzador;
import armas.Destroyer;
import armas.Hidroaviao;
import armas.Submarino;
import dominio.Coordenada;
import dominio.Jogador;
import dominio.Tiro;
import gui.FrameAtaque;
import gui.FrameJogadores;
import gui.FramePosicionamento;
import gui.Menu;
import gui.PainelArma;
import gui.PainelArmas;

class ControladorJogo {

	private static ControladorJogo controladorJogo = null;
	private FramePosicionamento framePosicionamento;
	private FrameJogadores frameJogadores;
	private FrameAtaque frameAtaque;
	private Menu menu;
	private Jogador jogador1;
	private Jogador jogador2;
	
	private ControladorJogo() {
		jogador1 = new Jogador();
		jogador2 = new Jogador();
		menu = new Menu();
	}
	
	static ControladorJogo getControladorJogo() {
		if(controladorJogo == null) {
			controladorJogo = new ControladorJogo();
		}
		return controladorJogo;
	}
	
	void iniciarJogo() { 
		frameJogadores = new FrameJogadores();
		menu.ativarRecarregamento();
		menu.desativarSalvamento();
		frameJogadores.setTitle("Batalha Naval"); 
		frameJogadores.setVisible(true);
	}
	
	void reiniciarJogo() { 
		controladorJogo = null;
		ControladorPosicionamento.getControladorPosicionamento().reiniciarControlador();
		ControladorAtaque.getControladorAtaque().reiniciarControlador();
		if(frameAtaque != null) {
			frameAtaque.setVisible(false);
		}
	}
	
	void fecharFrameJogadores(String nomeJogador1, String nomeJogador2) {
		if("".equals(nomeJogador1)) {
			nomeJogador1 = "Jogador 1";
		}
		if("".equals(nomeJogador2)) {
			nomeJogador2 = "Jogador 2";
		}
		setNomeJogador(nomeJogador1, 1);
		setNomeJogador(nomeJogador2, 2);
		frameJogadores.setVisible(false);
		framePosicionamento = new FramePosicionamento();
		framePosicionamento.setTitle("Batalha Naval");
		framePosicionamento.setVisible(true);
	}
	
	void fecharFramePosicionamento() {
		framePosicionamento.setVisible(false);
		abrirFrameAtaque();
	}
	
	private void abrirFrameAtaque() {
		frameAtaque = new FrameAtaque();
		frameAtaque.setTitle("Batalha Naval");
		frameAtaque.setVisible(true);
	}
	
	void fecharFrameAtaque() {
		frameAtaque.dispatchEvent(new WindowEvent(frameAtaque, WindowEvent.WINDOW_CLOSING));
	}
	
	FramePosicionamento getFramePosicionamento() {
		return framePosicionamento;
	}
	
	void setNomeJogador(String nome, int numJogador) {
		if(numJogador == 1) {
			jogador1.setNome(nome);
		} else if(numJogador == 2) {
			jogador2.setNome(nome);
		}
	}
	
	void setArmasJogador(ArrayList<PainelArma> armas, int numJogador) {
		if(numJogador == 1) {
			jogador1.setArmas(armas);
		} else if(numJogador == 2) {
			jogador2.setArmas(armas);
		}
	}
	
	void setCoordenadaArmasJogador(ArrayList<Coordenada[]> coordenadaArmas, int numJogador) {
		if(numJogador == 1) {
			jogador1.setCoordenadaArmas(coordenadaArmas);
		} else if(numJogador == 2) {
			jogador2.setCoordenadaArmas(coordenadaArmas);
		}
	}
	
	Jogador getJogador(int numJogador) {
		if(numJogador == 1) {
			return jogador1;
		} else if(numJogador == 2) {
			return jogador2;
		}
		return null;
	}
	
	Menu getMenu() {
		return menu;
	}
	
	void recarregarJogo() {
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
        	abrirFrameAtaque();
        	if(frameJogadores != null) {
        		frameJogadores.setVisible(false);
        	} 
        	if(framePosicionamento != null) {
        		framePosicionamento.setVisible(false);
        	}
        }
	}
	
	private void leDadosSalvos(Scanner scanner) {
		String vez = scanner.nextLine();
		scanner.nextLine();
		leDadosSalvosJogador(scanner, jogador1);
		leTirosSalvos(scanner, 1);
		leDadosSalvosJogador(scanner, jogador2);		
		leTirosSalvos(scanner, 2);
		ControladorAtaque.getControladorAtaque().setVez(Integer.valueOf(vez));
	}
	
	private void leTirosSalvos(Scanner scanner, int i) {
		String linha = "";
		if(scanner.hasNextLine()) {
			linha = scanner.nextLine();
		} else {
			return;
		}
		
		ArrayList<Tiro> tiros = new ArrayList<>();
		
		while(!linha.isEmpty()) {
			String[] stringTiros = linha.split(" ");
			Tiro tiro = new Tiro(stringTiros[0], new Coordenada(Integer.valueOf(stringTiros[1]), Integer.valueOf(stringTiros[2])));
			tiros.add(tiro);
			if(scanner.hasNextLine()) {
				linha = scanner.nextLine();				
			} else {
				linha = "";
			}
		}
		
		ControladorAtaque.getControladorAtaque().setTiros(tiros, i);
	}

	private void leDadosSalvosJogador(Scanner scanner, Jogador jogador) {
		String nomeJogador = scanner.nextLine();
		jogador.setNome(nomeJogador);
		
		scanner.nextLine();
		String linha = scanner.nextLine();
		
		ArrayList<Coordenada[]> arrayCoordenadas = new ArrayList<>();
		
		//le coordenadas da arma
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

		//le dados da arma
		while(!linha.isEmpty()) {
			PainelArma arma = null;
			String[] dadosArma = linha.split(" ");
			if("couracado".equals(dadosArma[0])) {
				arma = new PainelArma(new Couracado(), PainelArmas.COURACADO_COR);
			} else if("cruzador".equals(dadosArma[0])) {
				arma = new PainelArma(new Cruzador(), PainelArmas.CRUZADOR_COR);
			} else if("destroyer".equals(dadosArma[0])) {
				arma = new PainelArma(new Destroyer(), PainelArmas.DESTROYER_COR);
			} else if("hidroaviao".equals(dadosArma[0])) {
				arma = new PainelArma(new Hidroaviao(), PainelArmas.HIDROAVIAO_COR);
			} else if("submarino".equals(dadosArma[0])) {
				arma = new PainelArma(new Submarino(), PainelArmas.SUBMARINO_COR);
			}
			arma.getArma().setQuadradosIntactos(Integer.valueOf(dadosArma[1]));
			armas.add(arma);

			if(scanner.hasNextLine()) {
				linha = scanner.nextLine();				
			} else {
				linha = "";
			}
		}
		
		jogador.setCoordenadaArmas(arrayCoordenadas);
		jogador.setArmas(armas);
	}
	
	void salvarJogo() throws IOException {
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
	
	@SuppressWarnings("unchecked")
	private String formataSalvamento() {
		String salvamento = ControladorAtaque.getControladorAtaque().get(12) + "\n\n";
		salvamento += jogador1.toString();
		salvamento += "\n";
		salvamento += formataTiros((ArrayList<Tiro>) ControladorAtaque.getControladorAtaque().get(6));
		salvamento += "\n";
		salvamento += jogador2.toString();
		salvamento += "\n";
		salvamento += formataTiros((ArrayList<Tiro>) ControladorAtaque.getControladorAtaque().get(7));

		return salvamento;
	}
	
	private String formataTiros(ArrayList<Tiro> tiros) {
		String retorno = "";
		for(int i = 0; i < tiros.size(); i++) {
			Tiro tiro = tiros.get(i);
			retorno += tiro.getTipo() + " ";
			retorno += tiro.getCoordenada().getX() + " ";
			retorno += tiro.getCoordenada().getY() + "\n";
		}
		return retorno;
	}
	
}