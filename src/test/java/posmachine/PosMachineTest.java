package posmachine;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PosMachineTest {


    @Test
    public void should_get_receipt_using_real_price_calculator() {
        //given
        PriceCalculator calculator = new PriceCalculator();
        PosMachine posMachine = new PosMachine(calculator);
        //then
        Assertions.assertThrows(UnsupportedOperationException.class, () ->{
            posMachine.getReceipt("Coke");
        });
    }

    @Test
    public void should_get_receipt_using_sub_price_calculator() {
        //given
        PriceCalculator calculator = new StubPriceCalculator();
        PosMachine posMachine = new PosMachine(calculator);
        //when
        String receipt = posMachine.getReceipt("Coke");
        //then
        Assertions.assertEquals("Name: Coke, Price: 10.0", receipt);
    }

    @Test
    public void should_get_receipt_using_real_price_calculator_with_mockito() {
        //given
        PriceCalculator calculator = mock(PriceCalculator.class);
        PosMachine posMachine = new PosMachine(calculator);
        String productName = "Coke";

        //when
        when(calculator.calculate(productName)).thenReturn(10.0);
        String receipt = posMachine.getReceipt(productName);

        //then
        Assertions.assertEquals("Name: Coke, Price: 10.0", receipt);
    }


    private class StubPriceCalculator extends PriceCalculator {
        @Override
        public double calculate(String productName) {
            return 10;
        }
    }
}
