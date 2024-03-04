package app.data_models;

import java.util.ArrayList;
import java.util.List;

public class Operations {
    public static Polynomial add(Polynomial polynomial1, Polynomial polynomial2) {
        if(polynomial1.getMonomials().isEmpty()) {
            if (polynomial2.getMonomials().isEmpty()) {
                return null;
            }
            else {
                return polynomial2;
            }
        }
        if(polynomial2.getMonomials().isEmpty()) {
            return polynomial1;
        }

        Polynomial polynomial = new Polynomial();
        polynomial.setMonomials(polynomial1.getMonomials());

        for (Monomial monomialI : polynomial2.getMonomials().values()) {
            if(polynomial.getMonomials().containsKey(monomialI.getPower())) {
                polynomial.getMonomials().get(monomialI.getPower()).setCoefficient(polynomial.getMonomials().get(monomialI.getPower()).getCoefficient() + monomialI.getCoefficient());
            }
            else {
                polynomial.getMonomials().put(monomialI.getPower(), monomialI);
            }
        }

        polynomial.verifyPolynomial();
        return polynomial;
    }

    public static Polynomial subtract(Polynomial polynomial1, Polynomial polynomial2) {
        if(polynomial1.getMonomials().isEmpty()) {
            if (polynomial2.getMonomials().isEmpty()) {
                return null;
            }
            else {
                for (Monomial monomialI : polynomial2.getMonomials().values()) {
                    monomialI.setCoefficient(-monomialI.getCoefficient());
                }
                return polynomial2;
            }
        }
        if(polynomial2.getMonomials().isEmpty()) {
            return polynomial1;
        }

        for (Monomial monomialI : polynomial2.getMonomials().values()) {
            if(polynomial1.getMonomials().containsKey(monomialI.getPower())) {
                polynomial1.getMonomials().get(monomialI.getPower()).setCoefficient(polynomial1.getMonomials().get(monomialI.getPower()).getCoefficient() - monomialI.getCoefficient());
                polynomial1.verifyPolynomial();
            }
            else {
                monomialI.setCoefficient(-monomialI.getCoefficient());
                polynomial1.getMonomials().put(monomialI.getPower(), monomialI);
            }
        }

        polynomial1.verifyPolynomial();
        return polynomial1;
    }

    public static Polynomial multiply(Polynomial polynomial1, Polynomial polynomial2) {
        if(polynomial1.getMonomials().isEmpty() || polynomial2.getMonomials().isEmpty()) {
            return null;
        }
        Polynomial polynomial = new Polynomial();

        for (Monomial monomialI : polynomial1.getMonomials().values()) {
            for (Monomial monomialJ : polynomial2.getMonomials().values()) {
                Integer power = monomialI.getPower();
                Double coefficient = monomialI.getCoefficient();

                power += monomialJ.getPower();
                coefficient *= monomialJ.getCoefficient();

                Monomial monomial = new Monomial(power, coefficient);
                if (polynomial.getMonomials().containsKey(power)) {
                    polynomial.getMonomials().get(power).setCoefficient(polynomial.getMonomials().get(power).getCoefficient() + coefficient);
                }
                else {
                    polynomial.getMonomials().put(power, monomial);
                }
            }
        }

        polynomial.verifyPolynomial();
        return polynomial;
    }

    public static List<Polynomial> div(Polynomial polynomial1, Polynomial polynomial2) {
        if(polynomial1.getMonomials().isEmpty() || polynomial2.getMonomials().isEmpty()) {
            return null;
        }
        Polynomial quotient = new Polynomial();
        Polynomial remainder = polynomial1;

        while (!remainder.getMonomials().isEmpty() && remainder.getMonomials().firstKey() >= polynomial2.getMonomials().firstKey()) {
            Double coefficient = remainder.getMonomials().firstEntry().getValue().getCoefficient() / polynomial2.getMonomials().firstEntry().getValue().getCoefficient();
            Integer power = remainder.getMonomials().firstKey() - polynomial2.getMonomials().firstKey();
            //System.out.println(power + " " + coefficient);

            Monomial monomial = new Monomial(power, coefficient);
            quotient.getMonomials().put(power, monomial);
            quotient.verifyPolynomial();
            /*System.out.println(quotient.toString());*/

            Polynomial subtractor = new Polynomial();
            for (Monomial monomialI : polynomial2.getMonomials().values()) {
                Monomial monomial1 = new Monomial(monomialI.getPower(), monomialI.getCoefficient());
                subtractor.getMonomials().put(monomial1.getPower(), monomial1);
            }
            subtractor.multiplyCoefficient(coefficient);
            subtractor.multiplyPower(power);
            subtractor.verifyPolynomial();
            //System.out.println(subtractor.toString());

            remainder = subtract(remainder, subtractor);
            remainder.verifyPolynomial();
            //System.out.println(remainder.toString());
        }

        List<Polynomial> polynomialList = new ArrayList<>();

        remainder.verifyPolynomial();
        quotient.verifyPolynomial();

        polynomialList.add(quotient);
        //System.out.println(quotient.toString());
        polynomialList.add(remainder);
        //System.out.println(remainder.toString());

        return polynomialList;
    }

    public static Polynomial derivative(Polynomial polynomial) {
        if(polynomial.getMonomials().isEmpty()) {
            return null;
        }

        for (Monomial monomial : polynomial.getMonomials().values()) {
            if(!monomial.getPower().equals(0)) {
                monomial.setCoefficient(monomial.getCoefficient() * monomial.getPower());
                monomial.setPower(monomial.getPower() - 1);
            }
            else {
                polynomial.getMonomials().remove(monomial.getPower());
            }
        }

        polynomial.verifyPolynomial();
        return polynomial;
    }

    public static Polynomial integral(Polynomial polynomial) {
        if(polynomial.getMonomials().isEmpty()) {
            return null;
        }

        for (Monomial monomial : polynomial.getMonomials().values()) {
            monomial.setPower(monomial.getPower() + 1);
            monomial.setCoefficient(monomial.getCoefficient() / monomial.getPower());
        }

        polynomial.verifyPolynomial();
        return polynomial;
    }
}
