package service;

import java.util.ArrayList;

public interface BillerInterface {
    public void clearTaxComponent();
    public void setTaxComponent(String taxName, double percent);
    public double generateBill(ArrayList<ProductInterface> products);

}