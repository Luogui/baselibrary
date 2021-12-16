package com.android.luogui.baseproject.bean;

import java.io.Serializable;

public class BuyInHouseBean implements Serializable {

    /**
     * 仓库编码
     */
    private String invCode;

    /**
     * 货号
     */
    private String serialNumber;

    /**
     * 库存类型 默认为a
     */
    private String invQtyType = "a";

    /**
     * 商品编号
     */
    private String itemNumber;
    /**
     * 商品名称
     */
    private String itemName2;

    /**
     * 数量
     */
    private int qty;

    /**
     * 供应商编码
     */
    private String venderCode;

    /**
     * 客户编号
     */
    private String customerCode;


    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInvQtyType() {
        return invQtyType;
    }

    public void setInvQtyType(String invQtyType) {
        this.invQtyType = invQtyType;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getVenderCode() {
        return venderCode;
    }

    public void setVenderCode(String venderCode) {
        this.venderCode = venderCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getItemName2() {
        return itemName2;
    }

    public void setItemName2(String itemName2) {
        this.itemName2 = itemName2;
    }
}
