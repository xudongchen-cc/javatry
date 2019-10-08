package org.docksidestage.bizfw.basic.buyticket;

public class OneDayTicket implements Ticket {

    private final int displayPrice;
    private int leftInParkTime;

    OneDayTicket(int price)
    {
        displayPrice = price;
        leftInParkTime = 0;
    }

    public void doInPark()
    {

    }

    public int getDisplayPrice()
    {
        return 0;
    }
}
