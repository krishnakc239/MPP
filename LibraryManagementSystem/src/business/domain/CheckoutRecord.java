package business.domain;

import java.io.Serializable;

public class CheckoutRecord implements Serializable {
    private CheckoutRecordEntry checkoutRecordEntry;
    CheckoutRecord(){}
    public CheckoutRecord(CheckoutRecordEntry checkoutRecordEntry) {
        this.checkoutRecordEntry = checkoutRecordEntry;
    }

    public CheckoutRecordEntry getCheckoutRecordEntry() {
        return checkoutRecordEntry;
    }

    public void setCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry){
        this.checkoutRecordEntry = checkoutRecordEntry;
    }
}
