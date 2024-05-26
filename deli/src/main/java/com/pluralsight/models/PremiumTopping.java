package com.pluralsight.models;

public class PremiumTopping extends Toppings{
    int sandWishSize;
    boolean isExtraCheese;
    boolean isExtraMeat;
    boolean isMeat;
    boolean isCheese;

    public PremiumTopping(String type, int sandWishSize, boolean isMeat, boolean isCheese, boolean isExtraCheese, boolean isExtraMeat) {
        super(type);
        this.isExtraCheese = isExtraCheese;
        this.isExtraMeat = isExtraMeat;
        this.isMeat = isMeat;
        this.isCheese = isCheese;
    }

    public int getSandWishSize() {
        return sandWishSize;
    }

    public void setSandWishSize(int sandWishSize) {
        this.sandWishSize = sandWishSize;
    }

    public boolean isExtraCheese() {
        return isExtraCheese;
    }

    public void setExtraCheese(boolean extraCheese) {
        isExtraCheese = extraCheese;
    }

    public boolean isExtraMeat() {
        return isExtraMeat;
    }

    public void setExtraMeat(boolean extraMeat) {
        isExtraMeat = extraMeat;
    }

    public boolean isMeat() {
        return isMeat;
    }

    public void setMeat(boolean meat) {
        isMeat = meat;
    }

    public boolean isCheese() {
        return isCheese;
    }

    public void setCheese(boolean cheese) {
        isCheese = cheese;
    }

    @Override
    public double getPrice()
    {
        double price = 0;

        if (isMeat())
        {
            switch (getSandWishSize())
            {
                case 4:
                    price = 1.00;
                    break;
                case 8:
                    price = 2.00;
                    break;
                case 12:
                    price = 3.00;
                    break;
            }
        }
        else if (isCheese())
        {
            switch (getSandWishSize())
            {
                case 4:
                    price = .75;
                    break;
                case 8:
                    price = 1.50;
                    break;
                case 12:
                    price = 2.25;
                    break;
            }
        }

        if (isExtraCheese())
        {
            switch (getSandWishSize())
            {
                case 4:
                    price = .30;
                    break;
                case 8:
                    price = .60;
                    break;
                case 12:
                    price = .90;
                    break;
            }
        }
        else if(isExtraMeat())
        {
            switch (getSandWishSize())
            {
                case 4:
                    price = .50;
                    break;
                case 8:
                    price = 1.00;
                    break;
                case 12:
                    price = 1.50;
                    break;
            }
        }

        return price;
    }

}
