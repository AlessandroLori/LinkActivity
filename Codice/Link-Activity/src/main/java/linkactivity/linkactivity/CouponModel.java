package linkactivity.linkactivity;

import linkactivity.linkactivity.Pattern.Decorator.Priceable;

public abstract class CouponModel implements Priceable {
    private Double couponDiscount;
    private Priceable appliedPrice ;

    public CouponModel(Double couponDiscount){
        setCouponDiscount(couponDiscount);
    }


    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public Double getCouponDiscount(){
        return couponDiscount;
    }

    public void setAppliedPrice(Priceable appliedPrice) {
        this.appliedPrice = appliedPrice;
    }

    protected Priceable getAppliedPrice() {
        return appliedPrice ;
    }

}