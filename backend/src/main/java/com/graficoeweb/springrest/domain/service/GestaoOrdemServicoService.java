package com.graficoeweb.springrest.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graficoeweb.springrest.domain.exception.NegocioException;
import com.graficoeweb.springrest.domain.model.Cliente;
import com.graficoeweb.springrest.domain.model.OrdemServico;
import com.graficoeweb.springrest.domain.model.StatusOrdemServico;
import com.graficoeweb.springrest.domain.repository.ClienteRepository;
import com.graficoeweb.springrest.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
}
