package biz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.CpVille;

@Stateless
public class PersistManager {
	
	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

	public void persist(CpVille cpville)  {
		entityManager.persist(cpville);
	}
	
	//TODO restrict param type
	public void persist(Object ob)  {
		entityManager.persist(ob);
	}
}
