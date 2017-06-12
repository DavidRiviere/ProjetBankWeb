package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import model.Transaction;

@Path("/rs/{class:transaction}")
public class TransactionRS extends Resource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postTransaction(Transaction transaction, @Context UriInfo uriInfo) {
		this.persistManager.persist(transaction);
		return super.postLocation(transaction.getId(), uriInfo);
	}
}
