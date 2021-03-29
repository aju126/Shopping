package service;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public interface BillerInterface {
    public void clearTaxComponent();
    public void setTaxComponent(String taxName, double percent, RoundingMode roundingMode);
    public HashMap<String, Double> getTaxBreakup();
    public double generateBill(ArrayList<ProductInterface> products);

}