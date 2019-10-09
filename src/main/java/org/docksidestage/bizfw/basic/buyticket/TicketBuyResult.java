package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {
    private int price;
    private int change;
    private String type;

    public TicketBuyResult(int price, int change, String type)
    {
        this.price = price;
        this.change = change;
        this.type = type;
    }

    public Ticket getTicket()
    {
        if(type.equals("ONE"))
            return new OneDayTicket(price);
        else if(type.equals("TWO"))
            return new TwoDayTicket(price);
        else if(type.equals("FOUR"))
            return new FourDayTicket(price);
        else
            return null;
    }

    public int getPrice()
    {
        return price;
    }

    public int getChange()
    {
        return change;
    }

    public String getType()
    {
        return type;
    }
}
