import java.time.LocalDate;

class PerishableShippableProduct extends Product implements Expairable, Shipable {
    private final LocalDate expirationDate;
    private final double weight;

    public PerishableShippableProduct(String name, double price, int quantity,
                                      LocalDate expirationDate, double weight) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    public LocalDate getExpirationDate() { return expirationDate; }
    public Double getWeight() { return weight * 1.0; }

}