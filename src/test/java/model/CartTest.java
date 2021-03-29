package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import service.ProductInterface;
import support.SampleObjects;

public class CartTest {

    static ProductManager prodManager;
    static Cart cart;

    @BeforeAll
    private static void setUp() {
        prodManager = ProductManager.getProductMapperInstance();
        cart = Cart.getInstance();
    }

    @Test
    public void testAddProduct() {
        ProductInterface sampleProduct = SampleObjects.sampleProduct();
        cart.addProduct(sampleProduct);
        assertTrue(cart.getAllProducts().size() == 1);
    }

    @Test
    public void testAddProductNull() {
        cart.addProduct(null);
        assertTrue(cart.getAllProducts().size() == 0);
    }

    @Test
    public void testRemoveProductSuccess() {
        ProductInterface sampleProduct = SampleObjects.sampleProduct();
        cart.addProduct(sampleProduct);
        assertTrue(cart.removeProduct(sampleProduct));
    }

    @Test
    public void testRemoveProductNotFound() {
        ProductInterface sampleProduct = SampleObjects.sampleProduct();
        assertFalse(cart.removeProduct(sampleProduct));
    }

    @Test
    public void testGetAllProductsValidation() {
        ProductInterface sampleProduct = SampleObjects.sampleProduct();
        cart.addProduct(sampleProduct);
        ArrayList<ProductInterface> products = cart.getAllProducts();
        ProductInterface firstProduct = products.get(0);
        assertEquals(firstProduct.getId(), SampleObjects.sampleId);
    }

    @Test
    public void testGetAllProductsEmpty() {
        ArrayList<ProductInterface> products = cart.getAllProducts();
        assertTrue(products.size() == 0);
    }

    @AfterEach
    private void clearCart() {
        cart.clearCart();
    }
}