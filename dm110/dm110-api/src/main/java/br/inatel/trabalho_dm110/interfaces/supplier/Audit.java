package br.inatel.trabalho_dm110.interfaces.supplier;

import br.inatel.trabalho_dm110.api.supplier.AuditTO;

public interface Audit {
	
	void createNewAuditLog(AuditTO audit);

}
