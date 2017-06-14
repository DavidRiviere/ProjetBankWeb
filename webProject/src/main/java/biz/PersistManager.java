package biz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
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

	public void remove(Identifiable entity){
		entityManager.remove(entity);
	}

	public void remove(Class myClass, long id) {
		Identifiable entity = (Identifiable) entityManager.find(myClass, id, LockModeType.PESSIMISTIC_WRITE);
		remove(entity);
	}

	public void assignRole(String login) {
		String req = "INSERT INTO `user_roles` (`login`, `role_name`) VALUES (?, 'owner')";
		entityManager.createNativeQuery(req ).setParameter(1, login).executeUpdate();
	}

}
