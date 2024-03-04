import app.controller.CalculatorController;
import app.data_models.Operations;
import app.data_models.Polynomial;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationsTest {
    @Test
    public void addTest() {
        Polynomial polynomial1 = CalculatorController.getPolynomial("3*x^2+4*x^1");
        Polynomial polynomial2 = CalculatorController.getPolynomial("5*x^1+7*x^0");
        Polynomial result = CalculatorController.getPolynomial("3*x^2+9*x^1+7*x^0");
        
        assertEquals(Operations.add(polynomial1, polynomial2).toString(), result.toString());
    }

    @Test
    public void subtractTest() {
        Polynomial polynomial1 = CalculatorController.getPolynomial("3*x^2+4*x^1");
        Polynomial polynomial2 = CalculatorController.getPolynomial("5*x^1+7*x^0");
        Polynomial result = CalculatorController.getPolynomial("3*x^2-1*x^1-7*x^0");

        assertEquals(Operations.subtract(polynomial1, polynomial2).toString(), result.toString());
    }

    @Test
    public void multiplyTest() {
        Polynomial polynomial1 = CalculatorController.getPolynomial("1*x^2+2*x^1-1*x^0");
        Polynomial polynomial2 = CalculatorController.getPolynomial("2*x^2-3*x^1+6*x^0");
        Polynomial result = CalculatorController.getPolynomial("2*x^4+1*x^3-2*x^2+15*x^1-6*x^0");

        assertEquals(Operations.multiply(polynomial1, polynomial2).toString(), result.toString());
    }

    @Test
    public void divTest() {
        /*Polynomial polynomial1 = CalculatorController.getPolynom("4*x^3-7*x^2-11*x^1+5*x^0");
        Polynomial polynomial2 = CalculatorController.getPolynom("4*x^1+5*x^0");
        Polynomial resultQuotient = CalculatorController.getPolynom("1*x^2-3*x^1+1*x^0");
        Polynomial resultRemainder = new Polynomial();
        List<Polynomial> results = Operations.div(polynomial1, polynomial2);

        assertEquals(results.get(0).toString(), resultQuotient.toString());
        assertEquals(results.get(1).toString(), resultRemainder.toString());*/

        Polynomial polynomial1 = new Polynomial();
        Polynomial polynomial2 = CalculatorController.getPolynomial("4*x^1+5*x^0");
        Polynomial resultQuotient = null;
        Polynomial resultRemainder = null;
        List<Polynomial> results = Operations.div(polynomial1, polynomial2);

        assertEquals(results, null);
    }

    @Test
    public void integralTest() {
        Polynomial polynomial = CalculatorController.getPolynomial("1*x^2+2*x^1+1*x^0");
        Polynomial result = CalculatorController.getPolynomial("0.33*x^3+1*x^2+1*x^1");

        assertEquals(Operations.integral(polynomial).toString(), result.toString());
    }

    @Test
    public void derivativeTest() {
        Polynomial polynomial = CalculatorController.getPolynomial("5*x^3-2*x^1+3*x^0");
        Polynomial result = CalculatorController.getPolynomial("15*x^2-2*x^0");

        assertEquals(Operations.derivative(polynomial).toString(), result.toString());
    }

}
