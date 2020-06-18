package com.bolsaideas.springboot.app.models.domain;

import java.util.Random;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Partida {

	private Integer intentos;

	private String numberToGuess;

	@NotEmpty
	@Size(max = 10, min = 1)
	private String acierto;

	private boolean victory;

	private Integer range;

	private Integer digitos;

	private Integer picas;

	private Integer famas;



	public Partida() {
		picas = 0;
		famas = 0;
		digitos = 1;
		// intentos=5;
	}

	public void prepareNumber() {

		if (getNumberToGuess() == null) {
			String rango = "";
			//System.out.println("PREPAREnumber este es el digito: " + digitos);
			for (int i = 0; i < getDigitos(); i++) {
				rango += "9";
			}

			Integer maximoRango = Integer.parseInt(rango);
			Random r = new Random();

			setNumberToGuess(r.nextInt(maximoRango) + 1 + "");
			setIntentos(5);
		}
	}

	public boolean isFinished() {
		boolean band = false;

		calcule();

		if (getNumberToGuess().equals(getAcierto())) {
			setVictory(true);
			band = true;
		} else if (getIntentos() < 1) {
			band = true;
			setVictory(false);
		}
		return band;
	}

	private void calcule() {
		if (getAcierto() != null) {
			setPicas(0);
			setFamas(0);
			evaluePicas();
			evalueFamas();

		}
	}

	private void evaluePicas() {
		for (int i = 0; i < getNumberToGuess().length(); i++) {
			if (getNumberToGuess().charAt(i) == getAcierto().charAt(i)) {
				setPicas(getPicas() + 1);
			}
		}
	}

	private void evalueFamas() {
		String cadena = getNumberToGuess();

		for (int i = 0; i < getAcierto().length(); i++) {
			if (cadena.contains(getAcierto().subSequence(i, i + 1))) {

				cadena = cadena.replaceFirst((String) getAcierto().subSequence(i, i + 1), "-");
				System.out.println("Entra: " + cadena);
				setFamas(getFamas() + 1);
			}
		}
	}

	public Integer getPicas() {
		return picas;
	}

	public void setPicas(Integer picas) {
		this.picas = picas;
	}

	public Integer getFamas() {
		return famas;
	}

	public void setFamas(Integer famas) {
		this.famas = famas;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	public String getNumberToGuess() {
		return numberToGuess;
	}

	public void setNumberToGuess(String numberToGuess) {
		this.numberToGuess = numberToGuess;
	}

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public Integer getDigitos() {
		return digitos;
	}

	public void setDigitos(Integer digitos) {
		//System.out.println("Entrando en el SetDigitos : " + digitos);
		this.digitos = digitos;
	}

	public String getAcierto() {
		return acierto;
	}

	public void setAcierto(String acierto) {
		this.acierto = acierto;
	}

	public boolean isVictory() {
		return victory;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}

}
