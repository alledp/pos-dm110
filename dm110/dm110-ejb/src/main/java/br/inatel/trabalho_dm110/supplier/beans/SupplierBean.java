package br.inatel.trabalho_dm110.supplier.beans;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.google.gson.Gson;

import br.inatel.trabalho_dm110.api.supplier.AuditTO;
import br.inatel.trabalho_dm110.api.supplier.SupplierTO;
import br.inatel.trabalho_dm110.interfaces.supplier.SupplierLocal;
import br.inatel.trabalho_dm110.queue.AuditQueueSender;
import br.inatel.trabalho_dm110.supplier.entities.Supplier;

@Stateless
@Local(SupplierLocal.class)
public class SupplierBean implements SupplierLocal {

	private static Logger log = Logger.getLogger(SupplierBean.class.getName());
	
	@PersistenceContext(unitName = "supplier_pu")
	private EntityManager em;
	
	@EJB
	private AuditQueueSender queueSender;
	
	@Override
	public void createNewSupplier(SupplierTO supplier) {
		Supplier company = new Supplier(supplier.getCnpj(), supplier.getName(), supplier.getEmail(), supplier.getCep(), supplier.getDate(), supplier.getRate());
		//State entity = new ModelMapper().map(state, State.class); //não mapeou o initials e o name
		log.info("Saving Supplier on DB: " + company.toString());
		//String msg = "QUEUE SENDER Enviando a Mensagem!!!!";
		
		AuditTO auditTO = new AuditTO(supplier.getCnpj(), "create");
		Gson gson = new Gson();
		final String json = gson.toJson(auditTO);
		
		queueSender.sendTextMessage(json);
		em.persist(company);
	}
	
	@Override
	public List<SupplierTO> getAllSuppliers() {
		TypedQuery<Supplier> query = em.createQuery("from Supplier s", Supplier.class);
		log.info("LISTING ALL SUPPLIERS: ");
		List<Supplier> suppliers = query.getResultList();
		return toCollectionAPIModel(suppliers);
	}

	private List<SupplierTO> toCollectionAPIModel(List<Supplier> supplierList) {
		return supplierList.stream().map(s -> {
			SupplierTO sp = new SupplierTO();
			sp.setCnpj(s.getCnpj());
			sp.setName(s.getName());
			sp.setEmail(s.getEmail());
			sp.setCep(s.getCep());
			sp.setDate(s.getDate());
			sp.setRate(s.getRate());
			return sp;
		}).collect(Collectors.toList());
	}

	@Override
	public SupplierTO getSupplierById(int id) {
		
		Supplier supplier = em.find(Supplier.class, id);
		
		SupplierTO sp = new SupplierTO();
		
		sp.setCnpj(supplier.getCnpj());
		sp.setName(supplier.getName());
		sp.setEmail(supplier.getEmail());
		sp.setCep(supplier.getCep());
		sp.setDate(supplier.getDate());
		sp.setRate(supplier.getRate());

		
		return sp;

	}

	@Override
	public boolean updateById(int id,SupplierTO supplier) {
		
		Supplier supplierToUpdate = em.find(Supplier.class, id);
		
		if(supplierToUpdate == null) {
			return false;
		}
		
		supplierToUpdate.setName(supplier.getName());
		supplierToUpdate.setEmail(supplier.getEmail());
		supplierToUpdate.setCep(supplier.getCep());
		supplierToUpdate.setDate(supplier.getDate());
		supplierToUpdate.setRate(supplier.getRate());
		
		AuditTO auditTO = new AuditTO(supplier.getCnpj(), "update");
		Gson gson = new Gson();
		final String json = gson.toJson(auditTO);
		queueSender.sendTextMessage(json);
		
		em.persist(supplierToUpdate);
		return true;
	}
}