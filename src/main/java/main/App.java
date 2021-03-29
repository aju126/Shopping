package main;

import java.util.UUID;

import model.BasicBiller;
import model.Cart;
import model.ProductManager;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ProductManager mapper = ProductManager.getProductMapperInstance();
        BasicBiller biller = BasicBiller.getBillerInstance();
        biller.setTaxComponent("Sales Tax", 12.5);
        UUID dove_prod_key = mapper.createProduct("Dove Soap", 39.99, UUID.randomUUID());
        UUID axe_prod_key = mapper.createProduct("Axe Deo", 99.99, UUID.randomUUID());

        Cart c = Cart.getInstance();
        
        c.addProduct(mapper.getProduct(dove_prod_key));
        c.addProduct(mapper.getProduct(dove_prod_key));
        c.addProduct(mapper.getProduct(dove_prod_key));
        c.addProduct(mapper.getProduct(dove_prod_key));
        c.addProduct(mapper.getProduct(axe_prod_key));
        c.printCart();

        System.out.println("The total cost : " + biller.generateBill(c.getAllProducts()));
    }
}
