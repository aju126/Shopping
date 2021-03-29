package support;

import java.util.UUID;

import model.ProductManager;
import service.ProductInterface;

public class SampleObjects {

    public static final String sampleName = "sample name";
    public static final double samplePrice = 10.0;
    public static final UUID sampleId = UUID.randomUUID();
    private static ProductManager manager = ProductManager.getProductMapperInstance();

    public static ProductInterface sampleProduct() {
        manager.createProduct("Sample", 10.0, sampleId);
        return manager.get(sampleId);
    }

}