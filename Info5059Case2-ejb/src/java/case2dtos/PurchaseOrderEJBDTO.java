/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2dtos;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public class PurchaseOrderEJBDTO {
    
    private int vendorno;
    private BigDecimal total;
    private String productcode;
    private ArrayList<PurchaseOrderLineitemEJBDTO> items;

    public ArrayList<PurchaseOrderLineitemEJBDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<PurchaseOrderLineitemEJBDTO> items) {
        this.items = items;
    }

    public int getVendorno() {
        return vendorno;
    }

    public void setVendorno(int vendorno) {
        this.vendorno = vendorno;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }
    
}

