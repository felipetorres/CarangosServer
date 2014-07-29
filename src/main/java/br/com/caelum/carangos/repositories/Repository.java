package br.com.caelum.carangos.repositories;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.carangos.view.Pagina;

public abstract class Repository<T, I extends Serializable> {
	
	protected final Class<T> clazz;
	private Session session;

	protected Repository(Session session) {
		
		this.session = session;
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		this.clazz = clazz;
	}
	
	public T create(T entity) {
		session.save(entity);
		
		return entity;
	}
	
	public T update(T entity) {
		session.update(entity);
		return entity;
	}
	
	public void destroy(T entity) {
		session.delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public T find(I id) {
		return (T) session.get(clazz, id);
	}
	
	public List<T> findAll() {
		Query query = session.createQuery("from " + clazz.getName());

		@SuppressWarnings("unchecked")
		List<T> resultList = query.list();

		return resultList;
	}
	public List<T> find(Pagina pagina) {
		Query query = session.createQuery("from " + clazz.getName());
		
		@SuppressWarnings("unchecked")
		List<T> resultList = query.setFirstResult(pagina.getInicio()).setMaxResults(pagina.getTamanho()).list();
		
		return resultList;
	}
}