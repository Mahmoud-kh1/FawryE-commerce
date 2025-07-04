class ShipableProduct extends Product implements Shipable {
    private final double weight;
    public ShipableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }
    public Double getWeight() { return weight; }
}