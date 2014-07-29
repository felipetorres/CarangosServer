package br.com.caelum.carangos.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.carangos.gcm.model.Device;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class DeviceDao {

	private Session session;

	public DeviceDao(Session session) {
		this.session = session;
	}

	public void save(Device device) {
		session.save(device);
	}

	public Device find(String hash) {
		return (Device) session.createQuery("from "+Device.class.getName()+ " where registrationId = :id").setParameter("id", hash)
			.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listAllRegistrationIds() {
		return session.createSQLQuery("select d.registrationId from Device as d").list();
//		return em.createQuery("select registrationId from "+ Device.class.getName(), String.class).getResultList();
	}
	
	public boolean update(String oldHash, String newHash) {
		session.beginTransaction();
		Device found = find(oldHash);
		
		if (found != null) {
			found.setRegistrationId(newHash);
			return true;
		}
		session.getTransaction().commit();
		return false;
	}

	public boolean contains(String registrationId) {
		Number quantidade =  (Number) session.createQuery("select count(d.id) from "+Device.class.getName()+ " as d where d.registrationId = :id").setParameter("id", registrationId).uniqueResult();
		
		return quantidade.intValue() >0;
	}
}
