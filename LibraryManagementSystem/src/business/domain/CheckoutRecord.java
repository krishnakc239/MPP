package business.domain;

public class CheckoutRecord {
    private CheckoutRecordEntry checkoutRecordEntry;

    public CheckoutRecord(CheckoutRecordEntry checkoutRecordEntry) {
        this.checkoutRecordEntry = checkoutRecordEntry;
    }

    public CheckoutRecordEntry getCheckoutRecord() {
        return checkoutRecordEntry;
    }

    public void setCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry){
        this.checkoutRecordEntry = checkoutRecordEntry;
    }
}
