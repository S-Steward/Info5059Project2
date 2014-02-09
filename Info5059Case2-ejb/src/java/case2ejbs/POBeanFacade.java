/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2ejbs;

import case2dtos.PurchaseOrderEJBDTO;
import case2dtos.PurchaseOrderLineitemEJBDTO;
import case2models.PurchaseOrderLineitemsModel;
import case2models.PurchaseOrdersModel;
import case2models.VendorsModel;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jacob
 */
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Stateless
@LocalBean
public class POBeanFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @PersistenceContext
    private EntityManager em;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public int addPO (PurchaseOrderEJBDTO poDTO)
    {
        
        PurchaseOrdersModel pm;
        VendorsModel vm;
        int poRowID = -1;
        Date poDate = new java.util.Date();
        
        try{
            vm = em.find(VendorsModel.class, poDTO.getVendorno());
            pm = new PurchaseOrdersModel(0, poDTO.getTotal(), poDate);
            pm.setVendorno(vm);
            em.persist(pm);
            
            for(PurchaseOrderLineitemEJBDTO line : poDTO.getItems()){
                addPOLine(line, pm);
        }
            poRowID = pm.getPonumber().intValue();
            
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
        return poRowID;
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addPOLine(PurchaseOrderLineitemEJBDTO line, PurchaseOrdersModel pm)
    {
        PurchaseOrderLineitemsModel polm;
               
        try{
            polm = new PurchaseOrderLineitemsModel(pm.getPonumber(), line.getProdcd(), line.getQty(), line.getPrice());
            polm.setPonumber(pm);
            em.flush();
            em.persist(polm);
        }catch( Exception e){
            System.out.println(e.getMessage());
        }
    }
} 
