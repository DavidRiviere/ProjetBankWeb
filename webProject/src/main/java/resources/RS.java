package resources;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import model.AccountType;
import model.Address;
import model.Advisor;
import model.Agency;
import model.Bank;
import model.Category;
import model.CountryCode;
import model.CpVille;
import model.Frequency;
import model.Identifiable;
import model.Owner;
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.Transaction;
import model.TransactionType;
import mvc.model.AccountDoesNotExistException;

@Path("/rs/{class:accounttype|agency|bank|advisor|category|owner|transaction|transactiontype|targetTransaction|frequency|address|periodicTransaction|countryCode}")
public class RS {
	private static final Map<String, Class> myMap = createMap();
    private static Map<String, Class> createMap()
    {
        Map<String,Class> myMap = new HashMap<String,Class>();
        myMap.put("cpville", CpVille.class);
        myMap.put("accounttype", AccountType.class);
        myMap.put("agency", Agency.class);
        myMap.put("bank", Bank.class);
        myMap.put("advisor", Advisor.class);
        myMap.put("category", Category.class);
        myMap.put("owner", Owner.class);
        myMap.put("transaction", Transaction.class);
        myMap.put("transactiontype", TransactionType.class);
        myMap.put("targetTransaction", TargetTransaction.class);
        myMap.put("frequency", Frequency.class);
        myMap.put("address", Address.class);
        myMap.put("periodicTransaction", PeriodicTransaction.class);
        myMap.put("countryCode", CountryCode.class);
        return myMap;
    }

	@PersistenceContext(unitName = "bankProjectWeb")
	EntityManager entityManager;

	@EJB
	PersistManager persistManager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> get(@PathParam("class") String pathClass) throws AccountDoesNotExistException {
		Class myClass = RS.myMap.get(pathClass);
		System.out.println(this.entityManager);
		System.out.println(this.entityManager.createQuery("SELECT a FROM "+myClass.getName()+" a", myClass));

		try {
			return this.entityManager.createQuery("SELECT a FROM "+myClass.getName()+" a", myClass).getResultList();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object get(@PathParam("class") String pathClass ,@PathParam("id") long id) throws AccountDoesNotExistException {
		Class myClass = RS.myMap.get(pathClass);

		try {
			return entityManager
					.find(myClass, id);
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response post(@PathParam("class") String pathClass, Object entity, @Context UriInfo uriInfo) {
//		Class myClass = RS.myMap.get(pathClass);
//		Identifiable cast = (Identifiable) myClass.cast(entity);
//		
//		persistManager.persist(cast);
//		
//		
//		URI location = uriInfo.getRequestUriBuilder()
//                .path(String.valueOf((cast).getId()))
//                .build();
//		return Response.seeOther(location).build();
//		
//	}
	
	protected Response postLocation(Long id, UriInfo uriInfo) {
		URI location = uriInfo.getRequestUriBuilder()
                .path(String.valueOf(id))
                .build();
		return Response.seeOther(location).build();
	}


	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("class") String pathClass ,@PathParam("id") long id) {
		Class myClass = RS.myMap.get(pathClass);
		persistManager.remove(myClass, id);
		
		return Response.ok().build();

	}

}
