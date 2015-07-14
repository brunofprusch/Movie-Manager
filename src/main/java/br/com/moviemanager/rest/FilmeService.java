package br.com.moviemanager.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.moviemanager.dto.FilmeDTO;

@Path("/filme")
public class FilmeService {

	
	@GET
	@Path("/filmes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FilmeDTO> listAll() {
		
		List<FilmeDTO> list = new ArrayList<FilmeDTO>();
		
		FilmeDTO DTOUm =  new FilmeDTO();
		DTOUm.setId(1L);
		DTOUm.setNome("A Culpa é das Estrelas");
		DTOUm.setGenero("Drama/Romance");
		DTOUm.setNota(10);
		
		list.add(DTOUm);
		
		FilmeDTO DTODois =  new FilmeDTO();
		DTODois.setId(2L);
		DTODois.setNome("Cidades de Papel");
		DTODois.setGenero("Aventura/Comédia/Romance");
		DTODois.setNota(10);
		
		list.add(DTODois);
		
		FilmeDTO DTOTres =  new FilmeDTO();
		DTOTres.setId(3L);
		DTOTres.setNome("O Maravilhoso Agora");
		DTOTres.setGenero("Drama/Comédia/Romance");
		DTOTres.setNota(10);
		
		list.add(DTOTres);

		return list;
	}
}
