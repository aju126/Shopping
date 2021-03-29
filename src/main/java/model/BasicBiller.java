package model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import service.BillerInterface;
import service.ProductInterface;

/**
 * A Basic biller class implementing biller interface.
 */
public final class BasicBiller implements BillerInterface {

    /**
     * Static Basic Biller instance.
     */
    private static BasicBiller biller;

    // TODO : Implement a Decorator Pattern for supporting multiple tax components
    private ArrayList<TaxComponent> taxComponents = new ArrayList<>();

    private BasicBiller() {

    }

    public static BasicBiller getBillerInstance() {
        if (biller == null) {
            biller = new BasicBiller();
        }
        return biller;
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
            finalCost += (tax.taxPercent / 100) * basicCost;
        }
        return finalCost;
    }

    @Override
    public void setTaxComponent(String taxName, double percent) {
        TaxComponent tax = new TaxComponent();
        tax.taxName = taxName;
        tax.taxPercent = percent;
        taxComponents.add(tax);
    }

    class TaxComponent {
        @Getter
        @Setter
        private String taxName;

        @Getter
        @Setter
        private double taxPercent;
    }

    @Override
    public void clearTaxComponent() {
        this.taxComponents.clear();

    }

}
