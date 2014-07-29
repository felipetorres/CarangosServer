package br.com.caelum.carangos.controllers;

import br.com.caelum.carangos.dao.SenderDao;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;

@Resource
public class SenderController {
	
	private final SenderDao senders;

	public SenderController(SenderDao senders) {
		this.senders = senders;
	}

	@Post
	public void save(String email, String hash) {
		senders.findOrCreateSenderForEmail(email);
	}
}
