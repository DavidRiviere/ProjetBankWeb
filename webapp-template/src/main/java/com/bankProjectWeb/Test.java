/**
 * 
 */
package com.bankProjectWeb;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
	
	 @PersistenceUnit(unitName="bankProjectWeb")
	 private EntityManagerFactory entityManagerFactory;
	 
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em =this.entityManagerFactory.createEntityManager();
		
		long a = em.createNativeQuery("Select COUNT(*) FROM cpville").getFirstResult();
		List<CpVille> l = em.createNamedQuery("CpVille.findAll", CpVille.class).getResultList();
		em.close();
		
		resp.getWriter().write("count : "+ a+" count2 = "+ l.size());
	}
}
