package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author xudong
 */
public class OneDayTicket implements Ticket {

    private final int displayPrice;
    private int leftInParkTime;
    private TicketType ticketType;

    public OneDayTicket(TicketType type) {
        displayPrice = type.getPrice();
        leftInParkTime = type.getInPark();
        ticketType = type;
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

    public TicketType getTicketType() {
        return ticketType;
    }

    public boolean isAlreadyIn() {
        return leftInParkTime == 0;
    }
}
