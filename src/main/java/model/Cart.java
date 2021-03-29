package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import service.CartInterface;
import service.ProductInterface;

public final class Cart implements CartInterface {

    private static Cart cart;
    private ArrayList<UUID> productKeys;
    private ProductManager manager = ProductManager.getProductMapperInstance();

    private Cart() {
        productKeys = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    @Override
    public void clearCart() {
        this.productKeys.clear();
    }

    @Override
    public void addProduct(ProductInterface product) {
        if (product == null) {
            return;
        }
        this.productKeys.add(product.getId());
    }

    @Override
    public boolean removeProduct(ProductInterface product) {
        return this.productKeys.remove(product.getId());
    }

    @Override
    public ArrayList<ProductInterface> getAllProducts() {
        return (ArrayList<ProductInterface>) this.productKeys.stream().map(productKey -> manager.getProduct(productKey))
                .collect(Collectors.toList());
    }

    private HashMap<UUID, Integer> getProductCounter() {
        HashMap<UUID, Integer> productCounter = new HashMap<>();
        for (UUID uuid : productKeys) {
            Integer counter = 1;
            if(productCounter.containsKey(uuid)) {
                counter = productCounter.get(uuid);
                counter += 1;
            }
            productCounter.put(uuid, counter);
        }
        return productCounter;
    }

    @Override
    public void printCart() {
        HashMap<UUID, Integer> productCounter = getProductCounter();
        for (Map.Entry<UUID, Integer> prodKey : productCounter.entrySet()) {
            int counter = (Integer)prodKey.getValue();
            StringBuilder prodText = new StringBuilder();
            prodText.append(manager.get(prodKey.getKey()).getName());
            if(counter > 1) {
                prodText.append(" X ");
                prodText.append(counter);
            }
            System.out.println(prodText.toString());
        }
    }
}
