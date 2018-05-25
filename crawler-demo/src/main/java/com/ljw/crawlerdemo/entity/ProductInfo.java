package com.ljw.crawlerdemo.entity;

public class ProductInfo {

    private Integer id;
    private String productId;
    private String productName;
    private String imgUrl;
    private String productPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }


    public ProductInfo( String productId, String productName, String imgUrl, String productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.imgUrl = imgUrl;
        this.productPrice = productPrice;
    }
}
