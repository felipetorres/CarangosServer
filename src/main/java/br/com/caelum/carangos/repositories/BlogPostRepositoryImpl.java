package br.com.caelum.carangos.repositories;

import org.hibernate.Session;

import br.com.caelum.carangos.modelo.BlogPost;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class BlogPostRepositoryImpl extends Repository<BlogPost, Long>
		implements BlogPostRepository {

	protected BlogPostRepositoryImpl(Session session) {
		super(session);
	}
}
