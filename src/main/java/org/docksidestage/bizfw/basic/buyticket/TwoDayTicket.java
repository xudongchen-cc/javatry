package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author xudong
 */
public class TwoDayTicket implements Ticket{

    private final int displayPrice;
    private int leftInParkTime;
    private String ticketType;

    public TwoDayTicket(int price)
    {
        displayPrice = price;
        leftInParkTime = 2;
        ticketType = "OneDayTicket";
    }

    public void doInPark()
    {
        if (leftInParkTime==0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        leftInParkTime--;
    }

    public int getDisplayPrice()
    {
        return displayPrice;
    }

    public String getTicketType()
    {
        return ticketType;
    }
}
