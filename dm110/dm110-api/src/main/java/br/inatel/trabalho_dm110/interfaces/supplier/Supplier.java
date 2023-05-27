package br.inatel.trabalho_dm110.interfaces.supplier;

import java.util.List;

import br.inatel.trabalho_dm110.api.supplier.SupplierTO;

public interface Supplier {

	void createNewSupplier(SupplierTO supplier);
	
	List<SupplierTO> getAllSuppliers();
	
	SupplierTO getSupplierById(int id);
	
	boolean updateById(int id, SupplierTO supplier);
}
