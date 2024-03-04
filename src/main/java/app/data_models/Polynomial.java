package app.data_models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

public class Polynomial {
    private TreeMap<Integer, Monomial> monomials = new TreeMap<>(Collections.reverseOrder());

    void verifyPolynomial () {
        ArrayList<Integer> powers = new ArrayList<>();
        for (Monomial monomial : monomials.values()) {
            int result = Double.compare(monomial.getCoefficient(), 0.0);
            if (result == 0) {
                powers.add(monomial.getPower());
            }
        }
        for (Integer p : powers) {
            monomials.remove(p);
        }
    }
    void multiplyCoefficient (Double value) {
        for (Monomial monomial : monomials.values()) {
            monomial.setCoefficient(monomial.getCoefficient() * value);
        }
    }

    void multiplyPower (Integer power) {
        for (Monomial monomial : monomials.values()) {
            monomial.setPower(monomial.getPower() + power);
        }
    }

    public TreeMap<Integer, Monomial> getMonomials() {
        return monomials;
    }

    public void setMonomials(TreeMap<Integer, Monomial> monomials) {
        this.monomials = monomials;
    }

    @Override
    public String toString() {
        DecimalFormat decFor = new DecimalFormat("#.##");
        if(monomials.isEmpty()) {
            return null;
        }
        String string = "";
        for (Monomial monomial : monomials.values()) {
            if(monomial.getCoefficient() > 0.0) {
                string = string.concat("+" + decFor.format(monomial.getCoefficient()) + "*x^" + monomial.getPower().toString());
            }
            else if(monomial.getCoefficient() < 0.0) {
                string = string.concat(decFor.format(monomial.getCoefficient()) + "*x^" + monomial.getPower().toString());
            }
        }
        return string;
    }
}
