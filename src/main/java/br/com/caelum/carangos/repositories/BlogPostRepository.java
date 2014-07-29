package br.com.caelum.carangos.repositories;

import java.util.List;

import br.com.caelum.carangos.modelo.BlogPost;
import br.com.caelum.carangos.view.Pagina;

public interface BlogPostRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	BlogPost create(BlogPost entity);
	
	BlogPost update(BlogPost entity);
	
	void destroy(BlogPost entity);
	
	BlogPost find(Long id);
	
	List<BlogPost> findAll();
	
	List<BlogPost> find(Pagina pagina);

}
