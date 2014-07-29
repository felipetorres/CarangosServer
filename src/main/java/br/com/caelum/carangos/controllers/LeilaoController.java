package br.com.caelum.carangos.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.com.caelum.carangos.modelo.Lance;
import br.com.caelum.carangos.modelo.Usuario;
import br.com.caelum.carangos.repositories.UsuarioRepository;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class LeilaoController {
	
	private Result result;
	private UsuarioRepository usuarioDao;

	public LeilaoController(Result result, UsuarioRepository usuarioDao) {
		this.result = result;
		this.usuarioDao = usuarioDao;
	}
	
	@Get("/leilao/{leilaoId}")
	public void bla(String leiaoId) {
		List<Lance> lances = new ArrayList<Lance>();
		Calendar horario = Calendar.getInstance();
		horario.add(Calendar.MINUTE, -20);
		
		regraMarotaPraGerarLances(horario, lances);
		
		result.use(Results.json()).from(lances).recursive().serialize();
	}
	
	@Get("/leilao/{leilaoId}/{timestamp}")
	public void bla(String leiaoId, String timestamp) {
		List<Lance> lances = new ArrayList<Lance>();
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy-HHmmss");
			Calendar ultimaBusca = Calendar.getInstance();
			ultimaBusca.setTime(sdf.parse(timestamp));

			regraMarotaPraGerarLances(ultimaBusca, lances);
		} catch (ParseException e) {
		}
		
		
		result.use(Results.json()).from(lances).recursive().serialize();
	}

	/**
	 * @param timestamp
	 * @param lances
	 */
	private void regraMarotaPraGerarLances(Calendar ultimaBusca, List<Lance> lances) {
		Calendar agora = Calendar.getInstance();
		
		if (ultimaBusca.before(agora)) {
			List<Usuario> usuarios = usuarioDao.findAll();
			
			int segundosEntreAgoraEUltimoLance = (int) ((agora.getTimeInMillis() - ultimaBusca.getTimeInMillis())/1000);
			int maximoDeLances = 5;
			
			int numeroDeLances = new Random().nextInt(maximoDeLances);
			
			
			for (int i = 0; i < Math.abs(numeroDeLances); i++) {
				Usuario aleatorio = usuarios.get(new Random().nextInt(usuarios.size()));
				Calendar data = (Calendar) ultimaBusca.clone();
				data.add(Calendar.SECOND, Math.abs(new Random().nextInt(segundosEntreAgoraEUltimoLance)));
				
				lances.add(new Lance(aleatorio, data));
			}
		}
		
		if (!lances.isEmpty()) {
			Collections.sort(lances);
			
			Lance lanceVencedor = lances.get(lances.size()-1);
			lanceVencedor.setValor(this.criaPrecoParaHorario(agora));
			
			for (int i = 0; i < lances.size()-1; i++) {
				Lance lance = lances.get(i);
				int diferencaDeTempoEntreLances = (int) ((lanceVencedor.getHorario().getTimeInMillis() - lance.getHorario().getTimeInMillis())/1000);
				lance.setValor(lanceVencedor.getValor() - 1 - diferencaDeTempoEntreLances);
			}
		}
	}

	private int criaPrecoParaHorario(Calendar agora) {
		return (agora.get(Calendar.HOUR)+agora.get(Calendar.MINUTE)+agora.get(Calendar.SECOND)/10)*200;
	}
}