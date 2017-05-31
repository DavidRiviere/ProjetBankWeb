package resources;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.CpVille;
import mvc.model.AccountDoesNotExistException;

@Path("/rs/cpville")
public class CpVilleRS {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

	@GET
	@Path("/{id}")
	@Produces( MediaType.APPLICATION_JSON)
	public CpVille get(@PathParam("id") long id) throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT a FROM CpVille a WHERE a.id = :id", CpVille.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}

}
