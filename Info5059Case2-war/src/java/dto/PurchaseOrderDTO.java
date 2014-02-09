/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 * @author Jacob
 */
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value="purchaseOrderDTO")
@SessionScoped
public class PurchaseOrderDTO implements Serializable{
    
    private String prodcd;
    private String prodname;
    private int qty;
    private double price;
    private double ext;
    private int eoq;

    public int getEoq() {
        return eoq;
    }

    public void setEoq(int eoq) {
        this.eoq = eoq;
    }
    
    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getExt() {
        return ext;
    }

    public void setExt(double ext) {
        this.ext = ext;
    }
}
