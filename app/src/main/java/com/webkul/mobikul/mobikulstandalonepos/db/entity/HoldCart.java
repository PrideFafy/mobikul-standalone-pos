package com.webkul.mobikul.mobikulstandalonepos.db.entity;



import com.webkul.mobikul.mobikulstandalonepos.db.converters.DataConverter;
import com.webkul.mobikul.mobikulstandalonepos.model.CartModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by aman.gupta on 5/2/18. @Webkul Software Private limited
 */
@Entity
public class HoldCart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "nadministrator_id_seq")
    private long holdCartId;

    @Column(name = "time")
    private String time;
    @Column(name = "date")
    private String date;

    //@TypeConverters(DataConverter.class)
    @Column(name = "cart_data")
    private CartModel cartData;

    @Column(name = "qty")
    private String qty;

    @Column(name = "is_synced")
    private String isSynced;

    public String getIsSynced() {
        return isSynced;
    }

    public void setIsSynced(String isSynced) {
        this.isSynced = isSynced;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public CartModel getCartData() {
        return cartData;
    }

    public void setCartData(CartModel cartData) {
        this.cartData = cartData;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getHoldCartId() {
        return holdCartId;
    }

    public void setHoldCartId(long holdCartId) {
        holdCartId = holdCartId + 12000;
        this.holdCartId = holdCartId;
    }
}
