/**
 * 
 */
package com.bankProjectWeb;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CpVille;

/**
 * @author user
 *
 */
@WebServlet("/hello")
public class Test extends HttpServlet {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager em;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		long b = em.createNativeQuery("SELECT 1", Long.class).getFirstResult();
		// EntityManager em =this.entityManagerFactory.createEntityManager();

		List<?> a = em.createNativeQuery("SELECT * FROM `cpville` ").getResultList();

		resp.getWriter().write("count : " + a.size() );
		resp.getWriter().write(" " + a.get(1) );
	}

}
