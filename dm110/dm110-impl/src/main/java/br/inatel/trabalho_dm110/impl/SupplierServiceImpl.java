package br.inatel.trabalho_dm110.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.trabalho_dm110.api.supplier.SupplierService;
import br.inatel.trabalho_dm110.api.supplier.SupplierTO;
import br.inatel.trabalho_dm110.interfaces.supplier.SupplierLocal;

@RequestScoped
public class SupplierServiceImpl implements SupplierService {

	@EJB
	private SupplierLocal supplierBean;

	@Override
	public void createNewSupplier(SupplierTO supplier) {
		supplierBean.createNewSupplier(supplier);
	}

	@Override
	public List<SupplierTO> getAllSuppliers() {
		return supplierBean.getAllSuppliers();
	}
	

	@Override
	public SupplierTO getSupplierById(Integer id) {
		return supplierBean.getSupplierById(id);
	}
	
	@Override
	public boolean updateById(int id, SupplierTO supplier) {
		return supplierBean.updateById(id, supplier);
	}
	
}
