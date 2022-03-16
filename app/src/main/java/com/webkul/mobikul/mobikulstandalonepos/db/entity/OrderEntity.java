package com.webkul.mobikul.mobikulstandalonepos.db.entity;



import android.arch.persistence.room.TypeConverters;

import com.j256.ormlite.table.DatabaseTable;
import com.webkul.mobikul.mobikulstandalonepos.db.converters.DataConverter;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.OrderDaoImpl;
import com.webkul.mobikul.mobikulstandalonepos.model.CartModel;
import com.webkul.mobikul.mobikulstandalonepos.model.CashModel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by aman.gupta on 25/1/18. @Webkul Software Private limited
 */
@Entity(name = "OrderEntity")
@DatabaseTable(daoClass = OrderDaoImpl.class)
public class OrderEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "nadministrator_id_seq")
    private long orderId;

    @Column(name = "time")
    private String time;
    @Column(name = "date")
    private String date;

    @TypeConverters(DataConverter.class)
    @Column(name = "cart_data")
    private CartModel cartData;

    @Column(name = "qty")
    private String qty;

    @TypeConverters(DataConverter.class)
    @Column(name = "cash_data")
    private CashModel cashData;

    @Column(name = "is_synced")
    private String isSynced;

    @Column(name = "is_return")
    private boolean isReturn;

    @Column(name = "refunded_order_id")
    private String refundedOrderId;

    public String getIsSynced() {
        return isSynced;
    }

    public void setIsSynced(String isSynced) {
        this.isSynced = isSynced;
    }

    public CashModel getCashData() {
        return cashData;
    }

    public void setCashData(CashModel cashData) {
        this.cashData = cashData;
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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        orderId = orderId + 10000;
        this.orderId = orderId;
    }

    public boolean getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(boolean isReturn) {
        this.isReturn = isReturn;
    }

    public String getRefundedOrderId() {
        if (refundedOrderId == null)
            return "";
        return refundedOrderId;
    }

    public void setRefundedOrderId(String refundedOrderId) {
        this.refundedOrderId = refundedOrderId;
    }
}
