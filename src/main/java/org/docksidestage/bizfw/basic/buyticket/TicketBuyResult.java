package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author xudong
 */
public class TicketBuyResult {
    private int change;
    private TicketType type;

    public TicketBuyResult(int change, TicketType type) {
        this.change = change;
        this.type = type;
    }

    public Ticket getTicket() {
        if (type.equals(TicketType.Oneday))
            return new OneDayTicket(type);
        else if (type.equals(TicketType.Twoday))
            return new PluralDayTicket(type);
        else if (type.equals(TicketType.Fourday))
            return new PluralDayTicket(type);
        else
            return null;
    }

    public int getChange() {
        return change;
    }

    public TicketType getType() {
        return type;
    }
}
