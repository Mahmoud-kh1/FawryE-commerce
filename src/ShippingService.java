import java.util.List;

class ShippingService implements Shipable{
    public static void ship(List<Shipable> list) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (Shipable s : list) {
            System.out.printf("%dx %s %.0fg\n", 1, s.getName(), s.getWeight() * 1000);
            totalWeight += s.getWeight();
        }
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }

    @Override
    public Double getWeight() {
        return 0.0;
    }

    @Override
    public String getName() {
        return "";
    }
}