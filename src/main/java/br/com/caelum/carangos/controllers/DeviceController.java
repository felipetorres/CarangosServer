package br.com.caelum.carangos.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.caelum.carangos.dao.DeviceDao;
import br.com.caelum.carangos.dao.SenderDao;
import br.com.caelum.carangos.gcm.model.Device;
import br.com.caelum.carangos.gcm.model.Sender;
import br.com.caelum.carangos.util.Constantes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;

@Resource
public class DeviceController {
	private Logger log = Logger.getLogger(DeviceController.class);
	private final SenderDao senders;
	private final DeviceDao devices;
	private final Result result;

	public DeviceController(SenderDao senders, DeviceDao devices, Result result) {
		this.senders = senders;
		this.devices = devices;
		this.result = result;
	}

	@Path("device/register/{email}/{id}")
	// @Consumes("application/json")
	public void register(String email, String id) {
		log.info("método de registrar o device com registrationid " + id
				+ " e email:" + email);

		System.out.println(id);
		System.out.println(email);

		Sender sender = senders.findOrCreateSenderForEmail(email);

		if (!devices.contains(email)) {
			log.info("Device não encontrado, cadastrando o registrationId: "
					+ id);
			Device device = new Device(sender, id);
			devices.save(device);

			result.use(Results.status()).created();
		} else {
			log.info("Device encontrado para o registrationId: " + id);

			result.use(Results.status()).notModified();
		}
	}

	@Get
	public void pingaNiMim() {
		List<String> deviceIds = devices.listAllRegistrationIds();

		try {
			com.google.android.gcm.server.Sender sender = new com.google.android.gcm.server.Sender(
					Constantes.CGM_API_KEY);
			Message message = new Message.Builder().addData("message",
					"maroto!").build();

			// MulticastResult mResult = sender.send(message, deviceIds, 5);
			// List<com.google.android.gcm.server.Result> results =
			// mResult.getResults();

			MulticastResult resultis = sender.send(message, deviceIds, 5);
			for (com.google.android.gcm.server.Result result : resultis
					.getResults()) {
				if (result.getMessageId() != null) {
					String canonicalRegId = result.getCanonicalRegistrationId();
					log.info("CannocialId " +  canonicalRegId);
					deviceIds.add("CannocialId " +  canonicalRegId);
				} else {
					String error = result.getErrorCodeName();
					log.info("ERROR DO GCM " +  error);
					deviceIds.add("ERROR DO GCM " +  error);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Zica no envio para devices: " + e.getMessage());
			deviceIds.add("ZICAAAA!" + e.getMessage());
		}

		deviceIds.add("Se nessa casa tem goteira...");

		result.use(Results.json()).from(deviceIds).recursive().serialize();
	}

	@Post
	// @Consumes("application/json")
	public void update(String oldRegistrationId, String newRegistrationId) {
		log.info("Device não encontrado, mudando registrationId de "
				+ oldRegistrationId + " para " + newRegistrationId);

		devices.update(oldRegistrationId, newRegistrationId);
	}

}
