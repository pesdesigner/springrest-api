package com.graficoeweb.springrest.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graficoeweb.springrest.api.model.Comentario;
import com.graficoeweb.springrest.domain.exception.EntidadeNaoEncontradaException;
import com.graficoeweb.springrest.domain.exception.NegocioException;
import com.graficoeweb.springrest.domain.model.Cliente;
import com.graficoeweb.springrest.domain.model.OrdemServico;
import com.graficoeweb.springrest.domain.model.StatusOrdemServico;
import com.graficoeweb.springrest.domain.repository.ClienteRepository;
import com.graficoeweb.springrest.domain.repository.ComentarioRepository;
import com.graficoeweb.springrest.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
	}

	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
	}
	
	private OrdemServico buscar(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem não encontrada"));
	}
}
