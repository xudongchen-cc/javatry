package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    private int price;
    private int change;

    public TicketBuyResult(int price, int change)
    {
        this.price = price;
        this.change = change;
    }

    public Ticket getTicket()
    {
        return new Ticket(price);
    }

    public int getChange()
    {
        return change;
    }
}
