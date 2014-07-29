package br.com.caelum.carangos.repositories;

import org.hibernate.Session;

import br.com.caelum.carangos.modelo.Usuario;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsuarioRepositoryImpl extends Repository<Usuario, Long>
		implements UsuarioRepository {

	protected UsuarioRepositoryImpl(Session session) {
		super(session);
	}
}
