package com.kodilla.ecommercee.dto;

public class OrderDto {

    private Long id;
    private int cartId;
    private String oderStatus;

    public OrderDto(Long id, int cartId, String oderStatus) {
        this.id = id;
        this.cartId = cartId;
        this.oderStatus = oderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(String oderStatus) {
        this.oderStatus = oderStatus;
    }
}
