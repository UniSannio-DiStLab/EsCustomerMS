package it.unisannio.controller;

import it.unisannio.model.Customer;
import it.unisannio.service.BranchLocal;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import org.jboss.logging.Logger;

@Consumes("text/plain")
@Produces("text/plain")
@Path("/customers")
public class CustomerController {
	private static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	@EJB
	private BranchLocal branch;

	@Resource UserTransaction utx; // To handle user transactions from a Web component


	public CustomerController() {
		super();

	}

	@POST
	@Path("/")
	@Consumes("application/json")
	public Response createCustomer(Customer c) {
		LOGGER.info("CustomerController.createCustomer c = " + c);
		try {
			branch.createCustomer(c.getCF(), c.getFirstName(), c.getLastName());
			return Response.created(new URI("/customers/"+c.getCF())).build();
		} catch (Exception e) {
			LOGGER.error(e);
			return Response.status(500).build();
		}
	}

	@GET
	@Path("/{custCF}")
	public Response getCustomer(@PathParam("custCF") String cf) {
		LOGGER.info("CustomerController.getCustomer cf = " + cf);
		try {
			Customer c = branch.getCustomer(cf);
			if (c == null) Response.status(404).build();
			return Response.ok(c).build();
		} catch (Exception e) {
			LOGGER.error(e);
			return Response.status(500).build();
		}
	}
}
