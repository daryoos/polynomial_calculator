package app.data_models;

public class Monomial {
    private Integer power;
    private Double coefficient;

    public Monomial(Integer power, Double coefficient) {
        this.power = power;
        this.coefficient = coefficient;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }
}
