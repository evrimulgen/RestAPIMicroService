package ie.martin.model;

/**
 * Created by martin on 02/03/16.
 */
public class PurchasedShare {

    private int amount;
    private Share share;

    @Override
    public String toString() {
        return "PurchasedShare{" +
                "amount=" + amount +
                ", share=" + share +
                '}';
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }
}
