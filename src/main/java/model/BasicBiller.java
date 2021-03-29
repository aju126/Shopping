package model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
import service.BillerInterface;
import service.ProductInterface;

/**
 * A Basic biller class implementing biller interface.
 */
public final class BasicBiller implements BillerInterface {

    private ArrayList<TaxComponent> taxComponents = new ArrayList<>();
    private HashMap<String, Double> taxBreakup = new HashMap<>();

    private double roundOff(double value, RoundingMode roundOffStrategy) {
        DecimalFormat df = new DecimalFormat("##.##");
        if(roundOffStrategy == null) {
            roundOffStrategy = RoundingMode.HALF_EVEN;
        }
        df.setRoundingMode(roundOffStrategy);
        return Double.parseDouble(df.format(value));
    }

    /**
     * Returns the final computed cost for a list of products
     */
    @Override
    public double generateBill(ArrayList<ProductInterface> products) {
        double finalCost = 0.0;
        double basicCost = 0.0;
        for (ProductInterface product : products) {
            basicCost += product.getPrice();
        }
        finalCost = basicCost;
        for (TaxComponent tax : this.taxComponents) {
            double computedTax = roundOff((tax.taxPercent / 100) * basicCost, tax.roundingMode);
            this.taxBreakup.put(tax.taxName, computedTax);
            finalCost += computedTax;
        }
        return roundOff(finalCost, null);
    }

    @Override
    public void setTaxComponent(String taxName, double percent, RoundingMode mode) {
        TaxComponent tax = new TaxComponent();
        tax.taxName = taxName;
        tax.taxPercent = percent;
        tax.roundingMode = mode;
        taxBreakup.put(taxName, 0.0);
        taxComponents.add(tax);
    }

    class TaxComponent {
        @Getter
        @Setter
        private String taxName;

        @Getter
        @Setter
        private double taxPercent;

        @Getter
        @Setter
        private RoundingMode roundingMode;
    }

    @Override
    public void clearTaxComponent() {
        this.taxComponents.clear();

    }

    @Override
    public HashMap<String, Double> getTaxBreakup() {
        return this.taxBreakup;
    }

}
