import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {

        private final List<CartItem> items = new ArrayList<>();

        public void add(Product product, int qty) {
            if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");
            if (qty > product.getQuantity()) {
                throw new IllegalArgumentException("Cannot add more than available stock for " + product.getName());
            }
            items.add(new CartItem(product, qty));
        }

        public List<CartItem> getItems() { return Collections.unmodifiableList(items); }
        public boolean isEmpty() { return items.isEmpty(); }


}
