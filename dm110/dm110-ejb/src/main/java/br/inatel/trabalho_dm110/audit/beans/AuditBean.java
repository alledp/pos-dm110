package br.inatel.trabalho_dm110.audit.beans;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.inatel.trabalho_dm110.api.supplier.AuditTO;
import br.inatel.trabalho_dm110.audit.entities.Audit;
import br.inatel.trabalho_dm110.interfaces.supplier.AuditLocal;


@Stateless
@Local(AuditLocal.class)
public class AuditBean implements AuditLocal{
	
	private static Logger log = Logger.getLogger(AuditBean.class.getName());
	
	@PersistenceContext(unitName = "supplier_pu")
	private EntityManager em;

	private static int ID=0;
	
	@Override
	public void createNewAuditLog(AuditTO audit) {
		
		if(ID==0) {
			ID = getNextId();
		}
		ID++;
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		Audit audit_log = new Audit(ID,audit.getCnpj(), audit.getOperation(), timeStamp);
		log.info("Saving AUDIT LOG  no DB: " + audit_log);
		em.persist(audit_log);
		
	}

	private int getNextId() {
		
		TypedQuery<Audit> query = em.createQuery("from " + Audit.class.getSimpleName(), Audit.class);
		List<Audit> auditList = query.getResultList();
		
		if (auditList == null) {
			return 0;
		}
		
		List<Integer> ids = auditList.stream().map(a -> a.getId()).collect(Collectors.toList());
		
		return Collections.max(ids);
		
	}
	
}
