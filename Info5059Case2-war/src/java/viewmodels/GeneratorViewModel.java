package viewmodels;

import case2dtos.GeneratorItemDTO;
import case2dtos.ProductEJBDTO;
import case2dtos.PurchaseOrderEJBDTO;
import case2dtos.PurchaseOrderLineitemEJBDTO;
import case2ejbs.POBeanFacade;
import dto.PurchaseOrderDTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;

import javax.inject.Inject;
import javax.inject.Named;
import models.GeneratorModel;

/**
 * @author Jacob
 */
@Named(value="generatorViewModel")
@SessionScoped
public class GeneratorViewModel implements Serializable {
    
    @EJB
    POBeanFacade pofb;
    
    @Inject
    VendorViewModel vendorView;
    @Inject
    ProductViewModel productView;
    @Inject
    GeneratorModel model;
    
    private String msg;
    private boolean pickedVendor = false;
    private String vendorno;
    private String displayName;
    private int poNum;
    private String prodcd;
    private double total;
    private boolean pickedItem = false;
    private String qtySelected;
    private ArrayList<SelectItem> productNamesAndCodes;
    private ArrayList<ProductEJBDTO> items;
    private ArrayList<GeneratorItemDTO> itemGID;
    private ArrayList<PurchaseOrderLineitemEJBDTO> orderedItems;
    private ArrayList<PurchaseOrderLineitemEJBDTO> itemsEJB;
    private java.util.Calendar cal = java.util.Calendar.getInstance();
    private java.util.Date utilDate = cal.getTime();
    private Date dt = new Date(utilDate.getTime());
    
     public ArrayList<GeneratorItemDTO> getItemGID() {
        return itemGID;
    }

    public void setItemGID(ArrayList<GeneratorItemDTO> itemGID) {
        this.itemGID = itemGID;
    }

    public ArrayList<PurchaseOrderLineitemEJBDTO> getItemsEJB() {
        return itemsEJB;
    }

    public void setItemsEJB(ArrayList<PurchaseOrderLineitemEJBDTO> itemsEJB) {
        this.itemsEJB = itemsEJB;
    }
    
    public ArrayList<SelectItem> getProdNandC(){
        return productNamesAndCodes;
    }
    
    public void setProdNandC(ArrayList<SelectItem> value){
        productNamesAndCodes = value;
    }
    
    public ArrayList<PurchaseOrderLineitemEJBDTO> getOrderedItems(){
        return orderedItems;
    }
    
    public void setOrderedItems(ArrayList<PurchaseOrderLineitemEJBDTO> orderedItems){
        this.orderedItems = orderedItems;
    }
    
    public String getQtySelected() {
        return qtySelected;
    }

    public void setQtySelected(String qtySelected) {
        this.qtySelected = qtySelected;
    }
    
    public VendorViewModel getVendorView(){
        return vendorView;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isPickedVendor() {
        return pickedVendor;
    }

    public void setPickedVendor(boolean pickedVendor) {
        this.pickedVendor = pickedVendor;
    }

    public String getVendorno() {
        return vendorno;
    }

    public void setVendorno(String vendorno) {
        this.vendorno = vendorno;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPoNum() {
        return poNum;
    }

    public void setPoNum(int poNum) {
        this.poNum = poNum;
    }

    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isPickedItem() {
        return pickedItem;
    }

    public void setPickedItem(boolean pickedItem) {
        this.pickedItem = pickedItem;
    }

    public ArrayList getItems() {
        return items;
    }

    public void setItems(ArrayList items) {
        this.items = items;
    }
    
    public void changeVendor(){
         msg = "";
        pickedVendor = true;
        vendorView.setVendorN(Integer.parseInt(vendorno));
        vendorView.getVendor();
        productView.setVendorno(Integer.parseInt(vendorno));
        displayName = vendorView.getName();
        
        poNum = -1;
        prodcd = "";
        total = 0;
        
        productView.getAllProductsForVendor(Integer.parseInt(vendorno));
        ArrayList<ProductEJBDTO> prods = productView.getVendorProducts();
        items = productView.getVendorProductCodes();
        orderedItems = new ArrayList<>();
        pickedItem = false;
    }
    
    public ArrayList<SelectItem> getVendorProductCodes(){
        productNamesAndCodes = new ArrayList();
        
    try{
       //model.getVendorProdCodes(Integer.parseInt(vendorno));
        for(ProductEJBDTO detail : productView.getVendorProductCodes())
        {
            SelectItem item = new SelectItem(detail.getProdcd(),detail.getProdname());
           
            productNamesAndCodes.add(item);
                    
        }
    }catch(Exception e){
        msg = "Error: " + e.getMessage();
    }
    return productNamesAndCodes;
    }
    
    public ArrayList<SelectItem> listProductsForSelectedVendor(){
        
        ArrayList<SelectItem> productNames = new ArrayList();
        
        try{
            for(ProductEJBDTO i : items){
                SelectItem item = new SelectItem(i.getProdcd());
                productNames.add(item);
            }
            
        }catch (Exception e){
            System.out.println("Can't create list of items " + e );
        }
        return productNames;
    }
    
    public void poAdd()
    {
       int ponum;
       try{
           itemsEJB = new ArrayList();
           for (GeneratorItemDTO item : itemGID) {
               if(item.getQty() > 0){
                  PurchaseOrderLineitemEJBDTO ejbItem = new PurchaseOrderLineitemEJBDTO();
                  ejbItem.setPoNumber(0);
                  ejbItem.setPrice(item.getPrice());
                  ejbItem.setProdcd(item.getProdcd());
                  ejbItem.setQty(item.getQty());
                  itemsEJB.add(ejbItem);
               }
           }
           PurchaseOrderEJBDTO poDTO = new PurchaseOrderEJBDTO();
           poDTO.setItems(itemsEJB);
           poDTO.setVendorno(Integer.parseInt(vendorno));
           poDTO.setTotal(BigDecimal.valueOf(total));
           ponum = pofb.addPO(poDTO);
           msg = "PO " + ponum + " - Added!";
       }catch (Exception e){
           System.out.println("Didn't add po " + e);
       }
       pickedItem = false;
    }
    
     public void addSelectedItemToPO(){
        
        pickedItem = true;
        for(int i = 0; i < items.size(); i++){
        if (items.get(i).getProdcd().equalsIgnoreCase(prodcd)){
         total -= items.get(i).getExt().doubleValue();
         items.remove(i);
        }
      }
        try{
            for(ProductEJBDTO prod : productView.getVendorProducts()){
           // if (items.get(i).getProdcd().equalsIgnoreCase(prodcd)){
                if(!"0".equals(qtySelected)){
                    
                    for(int i = 0; i < itemGID.size(); i++){
                        
                        if (itemGID.get(i).getProdcd().equalsIgnoreCase(prodcd)){
                            
                        GeneratorItemDTO items = itemGID.get(i);
                        
                        if("EOQ".equals(qtySelected)){
                            
                            items.setQty(items.getEoq());
                        
                        }else{
                            
                            items.setQty(Integer.parseInt(qtySelected));
                        
                        }
                total += items.getPrice().doubleValue() * items.getQty();
                
                items.setExt(BigDecimal.valueOf(items.getPrice().doubleValue() * items.getQty()));
                orderedItems.add(items); 
                        } 
                            }
                    }
            }
        }catch(Exception e){
            e.getMessage();
        }
}
    
    /*public void addSelectedItemToPO(){
        for(int i = 0; i < orderedItems.size(); i++){
        if (orderedItems.get(i).getProdcd().equalsIgnoreCase(prodcd)){
         total -= orderedItems.get(i).getExt();
         orderedItems.remove(i);
        }
       }
        if(!"0".equals(qtySelected)){
            for(int i = 0; i < items.size(); i++){
            if (items.get(i).getProdcd().equalsIgnoreCase(prodcd)){
              ProductEJBDTO item = items.get(i);
              if("EOQ".equals(qtySelected)){
                  item.setQty(item.getEoq());
             }else{
                  item.setQty(Integer.parseInt(qtySelected));
              }
              total += item.getPrice() * item.getQty();
              item.setExt(item.getPrice() * item.getQty());
              orderedItems.add(item);
            }
           }
        }
        
        pickedItem = orderedItems.size()>0;
    }
    
    public void addPurchaseOrder(){
        try{
            msg = "Purchase Order " + model.dbaddOrder(vendorno,total,dt, items) + " has been added.";
            pickedVendor = false;
            pickedItem = false;
        } catch (Exception e){
            msg = e.getMessage();
        }
    }*/
}
