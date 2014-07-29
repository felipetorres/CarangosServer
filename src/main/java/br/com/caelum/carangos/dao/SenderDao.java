package br.com.caelum.carangos.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;

import br.com.caelum.carangos.gcm.model.Device;
import br.com.caelum.carangos.gcm.model.Sender;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class SenderDao {

	private Session session;

	public SenderDao(Session session) {
		this.session = session;
	}

	public Sender findOrCreateSenderForEmail(String email) {
		try {
			return  (Sender) session.createQuery(
							"from " + Sender.class.getName()
									+ " where email = :email")
					.setParameter("email", email).uniqueResult();
		} catch (NoResultException e) {
			Sender sender = new Sender(email, null);
			session.save(sender);
			
			return sender;
			
		}
	}

	@SuppressWarnings("unchecked")
	public List<Device> findDevicesOf(Sender sender) {
		return  session.createQuery(
				"from " + Device.class.getName()
						+ " where sender = :sender")
		.setParameter("sender", sender).list();
	}

	public Sender findByEmail(String email) {
		try {
			return  (Sender) session.createQuery(
							"from " + Sender.class.getName()
									+ " where email = :email")
					.setParameter("email", email).uniqueResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
