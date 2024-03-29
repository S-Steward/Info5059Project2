/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jacob
 */
@Entity
@Table(name = "PURCHASEORDERLINEITEMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseOrderLineitemsModel.findAll", query = "SELECT p FROM PurchaseOrderLineitemsModel p"),
    @NamedQuery(name = "PurchaseOrderLineitemsModel.findByLineid", query = "SELECT p FROM PurchaseOrderLineitemsModel p WHERE p.lineid = :lineid"),
    @NamedQuery(name = "PurchaseOrderLineitemsModel.findByProdcd", query = "SELECT p FROM PurchaseOrderLineitemsModel p WHERE p.prodcd = :prodcd"),
    @NamedQuery(name = "PurchaseOrderLineitemsModel.findByQty", query = "SELECT p FROM PurchaseOrderLineitemsModel p WHERE p.qty = :qty"),
    @NamedQuery(name = "PurchaseOrderLineitemsModel.findByPrice", query = "SELECT p FROM PurchaseOrderLineitemsModel p WHERE p.price = :price")})
public class PurchaseOrderLineitemsModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LINEID")
    private Integer lineid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRODCD")
    private String prodcd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QTY")
    private int qty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;
    @JoinColumn(name = "PONUMBER", referencedColumnName = "PONUMBER")
    @ManyToOne(optional = false)
    private PurchaseOrdersModel ponumber;

    public PurchaseOrderLineitemsModel() {
    }

    public PurchaseOrderLineitemsModel(Integer lineid) {
        this.lineid = lineid;
    }

    public PurchaseOrderLineitemsModel(Integer lineid, String prodcd, int qty, BigDecimal price) {
        this.lineid = lineid;
        this.prodcd = prodcd;
        this.qty = qty;
        this.price = price;
    }

    public Integer getLineid() {
        return lineid;
    }

    public void setLineid(Integer lineid) {
        this.lineid = lineid;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PurchaseOrdersModel getPonumber() {
        return ponumber;
    }

    public void setPonumber(PurchaseOrdersModel ponumber) {
        this.ponumber = ponumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineid != null ? lineid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrderLineitemsModel)) {
            return false;
        }
        PurchaseOrderLineitemsModel other = (PurchaseOrderLineitemsModel) object;
        if ((this.lineid == null && other.lineid != null) || (this.lineid != null && !this.lineid.equals(other.lineid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "case2models.PurchaseOrderLineitemsModel[ lineid=" + lineid + " ]";
    }
    
}
