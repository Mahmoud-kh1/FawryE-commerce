import java.time.LocalDate;

interface Expairable{
    LocalDate getExpirationDate();
    default boolean isExpired(){
        return  LocalDate.now().isAfter(getExpirationDate());
    }
}