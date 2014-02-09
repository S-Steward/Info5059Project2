package case2ejbs;

import case2dtos.ProductEJBDTO;
import case2models.ProductsModel;
import case2models.VendorsModel;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jacob
 */
@Stateless
@LocalBean
public class ProductBeanFacade {
    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean addProduct (ProductEJBDTO prod)
    {
        ProductsModel pm;
        VendorsModel vm;
        boolean addOk = false;
        
        try{
            pm = new ProductsModel(prod.getProdcd(), prod.getVendorsku(),
                    prod.getProdname(),prod.getCostprice(), prod.getMsrp(),
                    prod.getRop(), prod.getEoq(), prod.getQoh(), prod.getQoo());
            vm = em.find(VendorsModel.class, prod.getVendorno());
            pm.setVendorno(vm);
            pm.setQrcode(prod.getQrcode());
            em.persist(pm);
            em.flush();
            addOk = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return addOk;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public ArrayList<ProductEJBDTO> getAllProductsForVendor(int vendorno)
    {
      ArrayList<ProductEJBDTO> products = new ArrayList<>();
      VendorsModel vm;
      try
      {
          vm = em.find(VendorsModel.class, vendorno);
          Query qry = em.createNamedQuery("ProductsModel.findByVendorno");
          qry.setParameter("vendorno", vm);
          List<ProductEJBDTO> prods = qry.getResultList();
          
          for(Object o: prods)
          {
              ProductsModel prod = (ProductsModel) o;
              ProductEJBDTO oneProd = new ProductEJBDTO();
              oneProd.setProdcd(prod.getProdcd());
              oneProd.setVendorno(vendorno);
              oneProd.setProdname(prod.getProdnam());
              products.add(oneProd);
          }
          
      } catch(Exception e)
      {
          System.out.println("Error getting AllProductsForVendor from Facade -" + e.getMessage());
      }
      return products;
    }
    public ArrayList<ProductEJBDTO> getVendorProductCodes(int vendorno)
    {
      ArrayList<ProductEJBDTO> products = new ArrayList<>();
      VendorsModel vm;
      try
      {
          vm = em.find(VendorsModel.class, vendorno);
          Query qry = em.createNamedQuery("ProductsModel.findByVendorno");
          qry.setParameter("vendorno", vm);
          List<ProductEJBDTO> prods = qry.getResultList();
          
          for(Object o: prods)
          {
              ProductsModel prod = (ProductsModel) o;
              ProductEJBDTO oneProd = new ProductEJBDTO();
              oneProd.setProdcd(prod.getProdcd());
              products.add(oneProd);
          }
          
      } catch(Exception e)
      {
          System.out.println("Error getting getVendorProductCodes from Facade -" + e.getMessage());
      }
      return products;
    }
}
