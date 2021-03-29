package model;

import java.util.HashMap;
import java.util.UUID;

import service.ProductInterface;

public class ProductManager extends HashMap<UUID, ProductInterface> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static ProductManager mapper;

    private ProductManager() {
    }

    public UUID createProduct(String name, double cost, UUID prod_key) {
        ProductInterface product = new ProductInterface() {

            @Override
            public double getPrice() {
                return cost;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public UUID getId() {
                return prod_key;
            }
        };
        return this.setProduct(product);
    }

    public static ProductManager getProductMapperInstance() {
        if (mapper == null) {
            mapper = new ProductManager();
        }
        return mapper;
    }

    public UUID setProduct(ProductInterface product) {
        this.put(product.getId(), product);
        return product.getId();
    }

    public ProductInterface getProduct(UUID key) {
        return this.get(key);
    }

}