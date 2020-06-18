package com.bolsaideas.springboot.app.controllers;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsaideas.springboot.app.models.domain.Partida;

@Controller
@SessionAttributes("partida")
public class IndexController {

	private Integer numero;
	private Partida partida;
	
	@GetMapping({"/index","/",""})
	public String index(@RequestParam(name="gano", required = false) String gano,Model model) {
		partida = new Partida();
		if(gano!=null) {
			System.out.println("Es diferente: "+gano);
			model.addAttribute("gano", gano);
		}
		else {
			System.out.println("ES NULO EL GANO "+gano);
		}
		model.addAttribute("titulo", "Picas & Famas");
		model.addAttribute("partida", partida);
		System.out.println("Partida getNumberToGuest: "+partida);
	
		return "index";
	}
	
	
	
	@GetMapping("/form")
	public String mostrarJuego(Model model,SessionStatus status) {
		
		partida.prepareNumber();
		
		model.addAttribute("titulo","Partida en juego");
		model.addAttribute("digito", numero);
		model.addAttribute("xx", partida.getIntentos());
		model.addAttribute("rango", partida.getRange());
		model.addAttribute("adivinar", partida.getNumberToGuess());
		
		if(partida.isFinished()) {
			if(partida.isVictory()) {
				model.addAttribute("gano", "Has ganado");
				System.out.println("TERMINO EL JUEGO HAS ACERTADO");
			}
			else
				System.out.println("TERMINO EL JUEGO. PERDISTE");
			status.setComplete();
			return "redirect:index?gano="+partida.isVictory();
		}
		else {
			partida.setIntentos(partida.getIntentos()-1);
		}
		
		return "form";
	}
	

	
	@PostMapping("/form")
	public String leerDigito(@Validated Partida partida, BindingResult result, Model model,SessionStatus status) {//,@RequestParam(name="numero") String numero


		this.numero = partida.getDigitos();
		model.addAttribute("partida", partida);
		return "redirect:form";
	}
	
	
}
