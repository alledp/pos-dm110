package br.inatel.trabalho_dm110.api.supplier;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/suppliers")
public interface SupplierService {

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createNewSupplier(SupplierTO supplier);

	@GET
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SupplierTO> getAllSuppliers();
	
	@GET
	@Path("/find/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SupplierTO getSupplierById(@PathParam("id") Integer id);
	
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateById(int id, SupplierTO supplier);


}
