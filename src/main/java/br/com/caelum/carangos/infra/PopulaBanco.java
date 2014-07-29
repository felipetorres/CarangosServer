package br.com.caelum.carangos.infra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.carangos.modelo.BlogPost;
import br.com.caelum.carangos.modelo.Humor;
import br.com.caelum.carangos.modelo.Usuario;

public class PopulaBanco {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("default");

		EntityManager em = factory.createEntityManager();

		System.out.println("POPULANDO!!");

		popula(em);
		
		em.close();
	}

	private static void popula(EntityManager em) {
		em.getTransaction().begin();
		
		List<String> images = new ArrayList<String>();
		images.add("http://www.classic-carstore.com/sitebuildercontent/sitebuilderpictures/CHEVY55TM92905001.JPG");
		images.add("http://t3.gstatic.com/images?q=tbn:ANd9GcRxi88u-adXpHNSwPLnP6w-fK4QBT8RdWU1tfKRE3VeVbOHQLpBtw");
		images.add("http://autospeedcars.com/wp-content/uploads/2013/03/Cars-classic-wallpapers-old-cars-wallpapers-desktop-backgrounds.jpg");
		images.add("http://t1.gstatic.com/images?q=tbn:ANd9GcRPfVeoRlb3oNrq-K8F69uLsd_HeAHAKcmLmWbemt3VLYgnrg3Q-A");
		images.add("http://t3.gstatic.com/images?q=tbn:ANd9GcTuokXzdd-_I08wD_Gb0tkFO-b26kJhNHuHE0zgkrgRwCaEmZwv");
		images.add("http://3.bp.blogspot.com/-4cHZguqdiJk/T76ZROV_HjI/AAAAAAAAA-0/jJJGRxblezk/s1600/classic+cars+wallpapers-3.jpg");
		images.add("http://www.wallpapersshop.net/wp-content/uploads/2012/11/Gto-Old-Car.jpg");
		images.add("http://autospeedcars.com/wp-content/uploads/2013/03/Cars-muscle-cars-we-have-always-loved-them-classic-cars-classic-cars.jpg");
		images.add("http://t2.gstatic.com/images?q=tbn:ANd9GcQNLBPfAScBYPKDNOliWCSqNL8O5AfF48HeVuGXk3GtE2rCg891wg");
		images.add("http://www.classicscarinsurance.co.uk/blog/wp-content/uploads/2011/09/classic-cars.jpg");
		images.add("http://t1.gstatic.com/images?q=tbn:ANd9GcQJhi-RzrBGOHLntZe4B3Go0pFW0I4WYO-plbiEAb8505vio9cSEg");
		images.add("http://pastorbaumann.files.wordpress.com/2011/09/pontiac-gto1.jpg");
		
		em.createQuery("delete from "+BlogPost.class.getCanonicalName()).executeUpdate();
		em.createQuery("delete from "+Usuario.class.getCanonicalName()).executeUpdate();

		List<Usuario> usuarios = new ArrayList<Usuario>();

		usuarios.add(new Usuario("Erich"));
		usuarios.add(new Usuario("Andre"));
		usuarios.add(new Usuario("Felipe"));

		Map<Usuario, Integer> kilometragens = new HashMap<Usuario, Integer>();
		kilometragens.put(usuarios.get(0), 100);
		kilometragens.put(usuarios.get(1), 200);
		kilometragens.put(usuarios.get(2), 300);

		for (Usuario usuario : usuarios) {
			em.persist(usuario);
		}

		for (int i = 0; i < 2000; i++) {
			Usuario usuarioAleatorio = usuarios.get(new Random().nextInt(3));

			Integer kilometragem = kilometragens.get(usuarioAleatorio);
			Integer novaKilometragem = kilometragem + 1000;
			kilometragens.put(usuarioAleatorio, novaKilometragem);
			Humor humor = Humor.values()[new Random().nextInt(3)];
			
			String urlImagem = images.get(new Random().nextInt(images.size()));
			
			em.persist(new BlogPost("Meu carro fez " + novaKilometragem + "Km",
					usuarioAleatorio, humor,urlImagem));
		}

		em.getTransaction().commit();
	}
}
