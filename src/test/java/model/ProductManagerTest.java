package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import service.ProductInterface;
import support.SampleObjects;

public class ProductManagerTest {
    static ProductManager prodManager;

    @BeforeAll
    private static void setUp() {
        prodManager = ProductManager.getProductMapperInstance();
    }

    @Test
    public void testCreateProductSuccess() {
        ProductInterface product = new ProductInterface() {

            @Override
            public double getPrice() {
                return SampleObjects.samplePrice;
            }

            @Override
            public String getName() {
                return SampleObjects.sampleName;
            }

            @Override
            public UUID getId() {
                return SampleObjects.sampleId;
            }
        };
        prodManager.setProduct(product);
        assertEquals(product, prodManager.getProduct(product.getId()));
    }
}