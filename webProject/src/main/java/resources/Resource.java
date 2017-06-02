package resources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mvc.model.AccountDoesNotExistException;

public abstract class Resource<T> {
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> get() throws AccountDoesNotExistException {
		try {
			return (List<T>) getEntityManager().createQuery("SELECT a FROM "+this.getClass().getName()+" a WHERE a.id = :id", this.getClass()).getResultList();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}

	//public abstract String getClassName();

	public abstract EntityManager getEntityManager();
	
	

}
