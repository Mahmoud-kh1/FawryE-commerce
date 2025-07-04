abstract class Product{
    private String name;
    private Double price;
    private int quantity;
    Product(String name, Double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public void decreaseQuantity(int amount) {
        if (amount > quantity) throw new IllegalArgumentException("There is no enough Quantity " + name);
        quantity -= amount;
    }
}