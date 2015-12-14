package com.mephalay.model;

import java.math.BigDecimal;

/**
 * Created by GokhanOzgozen on 12/14/2015.
 */
public class ParserVatanProduct {

    private String name;
    private BigDecimal price;

    public ParserVatanProduct() {

    }

    @Override
    public String toString() {
        return "ParserVatanProduct{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParserVatanProduct that = (ParserVatanProduct) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(price != null ? !price.equals(that.price) : that.price != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
