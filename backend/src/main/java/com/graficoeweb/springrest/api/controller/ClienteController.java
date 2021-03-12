package com.graficoeweb.springrest.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graficoeweb.springrest.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var c1 = new Cliente();
		c1.setId(1L);
		c1.setNome("Edu");
		c1.setTelefone("321245");
		c1.setEmail("email@teste");
		
		var c2 = new Cliente();
		c2.setId(1L);
		c2.setNome("Maria");
		c2.setTelefone("321245");
		c2.setEmail("email@teste");
		
		return Arrays.asList(c1, c2);
	}
}
