package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author xudong
 */
public class FourDayTicket implements Ticket {

    private final int displayPrice;
    private int leftInParkTime;
    private String ticketType;

    public FourDayTicket(int price) {
        displayPrice = price;
        leftInParkTime = 4;
        ticketType = "FourDayTicket";
    }

    public void doInPark() {
        if (leftInParkTime == 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        leftInParkTime--;
    }

    public int getDisplayPrice() {
        return displayPrice;
    }

    public int getLeftInParkTime() {
        return leftInParkTime;
    }

    public String getTicketType() {
        return ticketType;
    }

    public boolean isAlreadyIn() {
        return leftInParkTime == 0;
    }
}
