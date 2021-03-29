package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.ProductInterface;
import support.SampleObjects;

public class BasicBillerTest {

    static BasicBiller biller;
    private double SalesTaxPc = 12.5;

    @BeforeAll
    private static void setUp() {
        biller = BasicBiller.getBillerInstance();
    }

    @Test
    public void generateBillSuccess() {
        ProductInterface prod1 = SampleObjects.sampleProduct();
        ProductInterface prod2 = SampleObjects.sampleProduct();
        biller.setTaxComponent("Sales Tax", 12.5);
        double basic_price = prod1.getPrice() + prod2.getPrice();
        double final_price = ( SalesTaxPc / 100 ) * basic_price + basic_price;
        assertTrue(final_price == biller.generateBill(new ArrayList<ProductInterface>(Arrays.asList(prod1, prod2))) );
    }

    @Test
    public void generateBillSuccessWithoutTax() {
        ProductInterface prod1 = SampleObjects.sampleProduct();
        ProductInterface prod2 = SampleObjects.sampleProduct();
        biller.clearTaxComponent();
        double basic_price = prod1.getPrice() + prod2.getPrice();
        assertTrue(basic_price == biller.generateBill(new ArrayList<ProductInterface>(Arrays.asList(prod1, prod2))) );
    }
}