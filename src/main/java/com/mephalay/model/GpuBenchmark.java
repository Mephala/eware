package com.mephalay.model;

/**
 * Created by GokhanOzgozen on 12/14/2015.
 */
public class GpuBenchmark {


    private String name;
    private String score;
    private String price;

    public GpuBenchmark() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GpuBenchmark that = (GpuBenchmark) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        return !(price != null ? !price.equals(that.price) : that.price != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GpuBenchmark{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
