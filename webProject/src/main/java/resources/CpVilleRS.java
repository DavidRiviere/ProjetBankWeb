package resources;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import biz.PersistManager;
import model.CpVille;
import mvc.model.AccountDoesNotExistException;

@Path("/rs/{class:cpville}")
public class CpVilleRS extends RS{

//	@PersistenceContext(unitName = "bankProjectWeb")
//	private EntityManager em;
//	
//	@EJB private PersistManager pm;

//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public CpVille get(@PathParam("id") long id, @Context UriInfo uriInfo) throws AccountDoesNotExistException {
//		try {
//			CpVille singleResult = entityManager.createQuery("SELECT a FROM CpVille a WHERE a.id = :id", CpVille.class)
//					.setParameter("id", id).getSingleResult();
//			ArrayList<Link> links = new ArrayList<Link>();
//			singleResult.setLinks(links);
//			
//			links.add(Link.fromPath("/rs/cpville/"+singleResult.getId()).rel("res:self").build());
//			links.add(Link.fromUri(uriInfo.getAbsolutePath()).rel("res").build());
//			//links.add(Link.);
//			return singleResult;
//		} catch (NoResultException e) {
//			throw new AccountDoesNotExistException();
//		}
//	}

	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<CpVille> get() throws AccountDoesNotExistException {
//		try {
//			return entityManager.createQuery("SELECT a FROM CpVille a", CpVille.class).getResultList();
//		} catch (NoResultException e) {
//			throw new AccountDoesNotExistException();
//		}
//	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(CpVille cpVille, @Context UriInfo uriInfo) {
		
		this.persistManager.persist(cpVille);
		
		return postLocation(cpVille.getId(), uriInfo);

		
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> get(@PathParam("class")String pathClass) throws AccountDoesNotExistException {
		return super.get(pathClass);
	}
	
	
	



//	@DELETE
//	public Response deleteCpVilleFromID(@PathParam("id") long id) throws AccountDoesNotExistException {
//		//persistManager.remove(get(id));
//		return Response.ok().build();
//
//	}


}
