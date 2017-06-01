package biz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.CpVille;
import model.Identifiable;

@Stateless
public class PersistManager {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

	public void persist(Identifiable entity) {
		if (entity.getId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}

	}
}
