package br.com.caelum.carangos.controllers;

import br.com.caelum.carangos.infra.PopulaBanco;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class Maroto {
	
	private Result result;

	public Maroto(Result result) {
		this.result = result;
	}
	
	@Get("populaJesus")
	public void init() {
		PopulaBanco.main(new String[0]);
		
		result.nothing();
	}

}
