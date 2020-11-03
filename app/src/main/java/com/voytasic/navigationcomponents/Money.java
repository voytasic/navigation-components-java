package com.voytasic.navigationcomponents;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;


public class Money implements Parcelable {

    protected Money(Parcel in) {
        this.amount = (BigDecimal) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.amount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Money> CREATOR = new Creator<Money>() {
        @Override
        public Money createFromParcel(Parcel in) {
            return new Money(in);
        }

        @Override
        public Money[] newArray(int size) {
            return new Money[size];
        }
    };

    private BigDecimal amount = null;

    public Money(BigDecimal amount){
        this.amount = amount;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                '}';
    }
}