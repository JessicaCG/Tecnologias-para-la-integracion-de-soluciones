package com.example.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class RestApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@RequestMapping("/")
	String home(){
		return "<h1>Bienvenido</h1>";
	}

	@GetMapping("/hola")
	String hola(){
		return "<h2>Hola mundo get!</h2>";
	}

	@PostMapping("/hola")
	String hola2(){
		return "<h2>Hola mundo post!</h2>";
	}

	@RequestMapping("/adios")
	String adios(){
		return "<h2>Adios mundo!</h2>";
	}

	@RequestMapping("/json")
	String json(){
		return "{'contenido':'valor'}";
	}

	@RequestMapping("/saludar")
	public String saludar(@RequestParam(defaultValue = "Primavera", required = false, value = "apodo") String nombre){
		return "hola " + nombre;
	}

	@RequestMapping("/saludar/{nombre}")
	public String saludarte(@PathVariable String nombre){
		return "hola " + nombre;
	}

	@RequestMapping("/saludarles")
	public List<String> saludarles(@RequestParam(value="nombre") List<String> nombres){
		List <String> respuesta = new ArrayList<String>();
		int i=0;
		for(String nombre: nombres){
			respuesta.add(nombre);
			i++;
		}
		return respuesta;
	}

	@GetMapping("saludar/json")
	public String json(@RequestParam Map<String, String> parametros){
		return "parametros: " +parametros.entrySet();
	}

}
