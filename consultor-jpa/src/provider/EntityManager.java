package provider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManager {

	private static EntityManagerFactory factory;
	
	private EntityManager(){
		
	}
	
	public static void createEntityManagerFactory(){
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("BolsaValoresPU");
		}
	}
	
	public static void destroyEntityManagerFactory(){
		if (factory != null) {
			factory.close();
		}
	}

	public static EntityManagerFactory getFactory() {
		return factory;
	}
	
}
