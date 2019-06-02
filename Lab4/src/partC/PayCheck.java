package partC;

public class PayCheck {
    private double grossPay;
    private double fica = 0.23;
    private double state =0.05;
    private double local =0.01;
    private  double medicare =0.03;
    private double socialSecurity =0.075;

    PayCheck(double grossPay, double fica, double state, double local, double medicare, double socialSecurity){
        this.grossPay = grossPay;
        this.fica = fica;
        this.state = state;
        this.local = local;
        this.medicare = medicare;
        this.socialSecurity = socialSecurity;
    }

    double getNetPay() {
        return grossPay*(1-fica-state-local-medicare-socialSecurity);
    }


}
