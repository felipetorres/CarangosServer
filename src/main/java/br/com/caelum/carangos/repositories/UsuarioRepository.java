package br.com.caelum.carangos.repositories;

import java.util.List;

import br.com.caelum.carangos.modelo.Usuario;
import br.com.caelum.carangos.view.Pagina;

public interface UsuarioRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	Usuario create(Usuario entity);
	
	Usuario update(Usuario entity);
	
	void destroy(Usuario entity);
	
	Usuario find(Long id);
	
	List<Usuario> findAll();
	
	List<Usuario> find(Pagina pagina);

}
