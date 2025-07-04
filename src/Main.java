import java.time.LocalDate;
import java.util.*;
public class Main {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty");

        double subtotal = 0;
        List<Shipable> toShip = new ArrayList<>();
        int shipItemCount = 0;

        for (CartItem ci : cart.getItems()) {
            Product p = ci.product;
            int qty = ci.quantity;


            if (p instanceof Expairable) {
                if (((Expairable) p).isExpired()) {
                    throw new IllegalStateException(p.getName() + " is expired");
                }
            }

            if (qty > p.getQuantity()) {
                throw new IllegalStateException(p.getName() + " is out of stock");
            }

            p.decreaseQuantity(qty);

            subtotal += p.getPrice() * qty;


            if (p instanceof Shipable) {
                for (int i = 0; i < qty; i++) {
                    toShip.add((Shipable) p);
                    shipItemCount++;
                }
            }
        }
        // asummition ship price 10 per unit
        double shippingFee = shipItemCount * 10;
        double total = subtotal + shippingFee;
        if (total > customer.getBalance()) {
            throw new IllegalStateException("Insufficient customer balance");
        }


        customer.deduct(total);

        if (!toShip.isEmpty()) {
            ShippingService.ship(toShip);
        }


        System.out.println("** Checkout receipt **");
        for (CartItem ci : cart.getItems()) {
            System.out.printf("%dx %s %.0f\n", ci.quantity, ci.product.getName(), ci.product.getPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shippingFee);
        System.out.printf("Amount %.0f\n", total);
        System.out.printf("Customer balance after payment: %.0f\n", customer.getBalance());
    }

    public static void main(String[] args) {

        PerishableShippableProduct cheese = new PerishableShippableProduct("Cheese", 100, 5,
                LocalDate.now().plusDays(5), 0.2);
        PerishableShippableProduct biscuits = new PerishableShippableProduct("Biscuits", 150, 2,
                LocalDate.now().plusDays(10), 0.7);
        ShipableProduct tv = new ShipableProduct("TV", 2000, 3, 15.0);
        SimpleProduct scratchCard = new SimpleProduct("ScratchCard", 50, 10);

        Customer customer = new Customer("Alice", 1000);
        Cart cart = new Cart();

        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);

        checkout(customer, cart);
    }
}
