/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2dtos;

import java.math.BigDecimal;

/**
 *
 * @author Jacob
 */
public class PurchaseOrderLineitemEJBDTO {
    
    private int poNumber;
    private BigDecimal price;
    private String prodcd;
    private int qty;
    private BigDecimal ext;

    public BigDecimal getExt() {
        return ext;
    }

    public void setExt(BigDecimal ext) {
        this.ext = ext;
    }

    public int getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(int poNumber) {
        this.poNumber = poNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
