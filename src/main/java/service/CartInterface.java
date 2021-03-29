package service;

import java.util.ArrayList;

public interface CartInterface {
    public void addProduct(ProductInterface product);
    public boolean removeProduct(ProductInterface product);
    public ArrayList<ProductInterface> getAllProducts();
    public void printCart();
    public void clearCart();
}