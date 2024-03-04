package app.controller;

import app.data_models.Monomial;
import app.data_models.Operations;
import app.data_models.Polynomial;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class CalculatorController {
    CalculatorView calculatorView;

    public void startLogic() {
        calculatorView = new CalculatorView();

        GUIFrameSinglePointAccess.changePanel(calculatorView.getCalculatorPanel(), "Calculator");

        calculatorView.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatorView.getResultLabel().setText("");
                calculatorView.getResultLabel2().setText("");

                if (calculatorView.getFirstPolynom().getText().isBlank() || calculatorView.getSecondPolynom().getText().isBlank()) {
                    return;
                }

                String exp1 = calculatorView.getFirstPolynom().getText();
                String exp2 = calculatorView.getSecondPolynom().getText();


                Polynomial polynomial1 = new Polynomial();
                Polynomial polynomial2 = new Polynomial();

                if (getPolynomial(exp1) != null) {
                    polynomial1 = getPolynomial(exp1);
                }
                if (getPolynomial(exp2) != null) {
                    polynomial2 = getPolynomial(exp2);
                }

                if(!polynomial1.getMonomials().isEmpty() && !polynomial2.getMonomials().isEmpty()) {
                    Polynomial result = Operations.add(polynomial1, polynomial2);

                    calculatorView.getResultLabel().setText(result.toString());
                }
            }
        });

        calculatorView.getSubButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatorView.getResultLabel().setText("");
                calculatorView.getResultLabel2().setText("");

                if (calculatorView.getFirstPolynom().getText().isBlank() || calculatorView.getSecondPolynom().getText().isBlank()) {
                    return;
                }

                String exp1 = calculatorView.getFirstPolynom().getText();
                String exp2 = calculatorView.getSecondPolynom().getText();

                Polynomial polynomial1 = new Polynomial();
                Polynomial polynomial2 = new Polynomial();

                if (getPolynomial(exp1) != null) {
                    polynomial1 = getPolynomial(exp1);
                }
                if (getPolynomial(exp2) != null) {
                    polynomial2 = getPolynomial(exp2);
                }

                if(!polynomial1.getMonomials().isEmpty() && !polynomial2.getMonomials().isEmpty()) {
                    Polynomial result = Operations.subtract(polynomial1, polynomial2);

                    calculatorView.getResultLabel().setText(result.toString());
                }
            }
        });

        calculatorView.getMulButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatorView.getResultLabel().setText("");
                calculatorView.getResultLabel2().setText("");

                if (calculatorView.getFirstPolynom().getText().isBlank() || calculatorView.getSecondPolynom().getText().isBlank()) {
                    return;
                }

                String exp1 = calculatorView.getFirstPolynom().getText();
                String exp2 = calculatorView.getSecondPolynom().getText();

                Polynomial polynomial1 = new Polynomial();
                Polynomial polynomial2 = new Polynomial();

                if (getPolynomial(exp1) != null) {
                    polynomial1 = getPolynomial(exp1);
                }
                if (getPolynomial(exp2) != null) {
                    polynomial2 = getPolynomial(exp2);
                }

                if(!polynomial1.getMonomials().isEmpty() && !polynomial2.getMonomials().isEmpty()) {
                    Polynomial result = Operations.multiply(polynomial1, polynomial2);

                    calculatorView.getResultLabel().setText(result.toString());
                }
            }
        });

        calculatorView.getDivButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatorView.getResultLabel().setText("");
                calculatorView.getResultLabel2().setText("");

                if (calculatorView.getFirstPolynom().getText().isBlank() || calculatorView.getSecondPolynom().getText().isBlank()) {
                    return;
                }

                String exp1 = calculatorView.getFirstPolynom().getText();
                String exp2 = calculatorView.getSecondPolynom().getText();

                Polynomial polynomial1 = new Polynomial();
                Polynomial polynomial2 = new Polynomial();

                if (getPolynomial(exp1) != null) {
                    polynomial1 = getPolynomial(exp1);
                }
                if (getPolynomial(exp2) != null) {
                    polynomial2 = getPolynomial(exp2);
                }

                if(!polynomial1.getMonomials().isEmpty() && !polynomial2.getMonomials().isEmpty()) {
                    List<Polynomial> result = Operations.div(polynomial1, polynomial2);
                    calculatorView.getResultLabel().setText(result.get(0).toString());
                    calculatorView.getResultLabel2().setText(result.get(1).toString());
                }
            }
        });

        calculatorView.getIntegralButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatorView.getResultLabel().setText("");
                calculatorView.getResultLabel2().setText("");

                if (calculatorView.getFirstPolynom().getText().isBlank()) {
                    return;
                }

                String exp = calculatorView.getFirstPolynom().getText();

                Polynomial polynomial = new Polynomial();

                if (getPolynomial(exp) != null) {
                    polynomial = getPolynomial(exp);
                }

                if(!polynomial.getMonomials().isEmpty()) {
                    Polynomial result = Operations.integral(polynomial);

                    calculatorView.getResultLabel().setText(result.toString());
                }
            }
        });

        calculatorView.getDerivativeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatorView.getResultLabel().setText("");
                calculatorView.getResultLabel2().setText("");

                if (calculatorView.getFirstPolynom().getText().isBlank()) {
                    return;
                }

                String exp = calculatorView.getFirstPolynom().getText();

                Polynomial polynomial = new Polynomial();

                if (getPolynomial(exp) != null) {
                    polynomial = getPolynomial(exp);
                }

                if(!polynomial.getMonomials().isEmpty()) {
                    Polynomial result = Operations.derivative(polynomial);

                    calculatorView.getResultLabel().setText(result.toString());
                }
            }
        });
    }

    static public Polynomial getPolynomial(String exp) {
        if(exp.charAt(0) != '-') {
            exp = "+".concat(exp);
        }
        Polynomial polynomial = new Polynomial();

        String generalRegex = "(([+-]?(((([1-9](\\d)+)|(\\d))\\.(\\d)+)|([1-9](\\d)+)|(\\d)))\\*x\\^(([1-9](\\d)+)|(\\d)))";
        String expRegex = "^";

        Pattern pattern = Pattern.compile(generalRegex);
        Matcher matcher = pattern.matcher(exp);

        while (matcher.find()) {
            expRegex = expRegex.concat(generalRegex);
            /*for (int i = 0; i < matcher.groupCount(); i++) {
                   System.out.println(matcher.group(i));
            }*/
            Monomial monomial = new Monomial(parseInt(matcher.group(13)), Double.valueOf(matcher.group(2)));
            polynomial.getMonomials().put(monomial.getPower(), monomial);
        }

        expRegex = expRegex.concat("*$");
        //System.out.println(expRegex);

        pattern = Pattern.compile(expRegex);
        matcher = pattern.matcher(exp);

        if (matcher.find()) {
            //System.out.println("in");
            return polynomial;
        }
        else {
            System.out.println("out regex");
            return null;
        }
    }
}
