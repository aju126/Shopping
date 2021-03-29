package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.BasicBiller;
import model.Cart;
import model.ProductManager;
import service.BillerInterface;
import service.ProductInterface;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */

    private static ProductManager mapper = ProductManager.getProductMapperInstance();
    private static ProductInterface doveSoap;
    private static ProductInterface axeDeo;
    private static Cart cart;
    
    @BeforeAll
    private static void setUp() {
        doveSoap = (ProductInterface) mapper.getProduct(mapper.createProduct("Dove Soap", 39.99, UUID.randomUUID()));
        axeDeo = (ProductInterface) mapper.getProduct(mapper.createProduct("Axe Deo", 99.99, UUID.randomUUID()));
        cart = Cart.getInstance();
    }

    @Test
    public void testStepOne() {
        cart.setBiller(new BasicBiller());
        // Adding 5 Dove Soaps to the shopping cart
        cart.addProduct(doveSoap);
        cart.addProduct(doveSoap);
        cart.addProduct(doveSoap);
        cart.addProduct(doveSoap);
        cart.addProduct(doveSoap);
        double expectedCost = 199.95;
        double checkoutCost = cart.checkout();
        assertTrue(checkoutCost == expectedCost);
    }

    @Test
    public void testStepTwo() {
        cart.setBiller(new BasicBiller());
        // Adding 5 Dove Soaps to the shopping cart
        cart.addProduct(doveSoap);
        cart.addProduct(doveSoap);
        cart.addProduct(doveSoap);
        cart.addProduct(doveSoap);
        cart.addProduct(doveSoap);

        // Adding 3 Dove Soaps to the shopping cart
        cart.addProduct(doveSoap);
        cart.addProduct(doveSoap);
        cart.addProduct(doveSoap);
        double expectedCost = 319.92;
        assertTrue(cart.checkout() == expectedCost);
    }

    @Test
    public void testStepThree() {
        // Adding 2 Dove Soaps to the shopping cart
        cart.addProduct(doveSoap);
        cart.addProduct(doveSoap);

        // Adding 2 Axe Deos to the shopping cart
        cart.addProduct(axeDeo);
        cart.addProduct(axeDeo);

        // Verify if cart has 2 dove and 2 axe

        assertTrue(cart.getProductCounter().get(axeDeo.getId()) == 2);
        assertTrue(cart.getProductCounter().get(doveSoap.getId()) == 2);
       
        BillerInterface basicBiller = new BasicBiller();
        basicBiller.setTaxComponent("Sales Tax", 12.5, RoundingMode.CEILING);
        cart.setBiller(basicBiller);
        double checkoutValue = cart.checkout();
        HashMap<String, Double> taxValue = basicBiller.getTaxBreakup();

        // Total sales tax amount for the shopping cart should equal 35.00
        assertTrue(taxValue.get("Sales Tax") == 35.0);

        // Shopping cart’s total price should equal 314.96
        assertTrue(checkoutValue == 314.96);
    }

    @AfterEach
    private void clearCart() {
        cart.clearCart();
    }
}
