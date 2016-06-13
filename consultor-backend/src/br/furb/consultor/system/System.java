package br.furb.consultor.system;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import provider.EntityManager;

public class System implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		EntityManager.destroyEntityManagerFactory();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		EntityManager.createEntityManagerFactory();
	}
}

