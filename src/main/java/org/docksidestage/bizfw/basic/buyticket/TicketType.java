package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author xudong
 */
public enum TicketType {
    Oneday(1, 7400),
    Twoday(2, 13200),
    Fourday(4, 22400);

    final private int inPark;
    final private int price;

    TicketType(int park, int price) {
        this.inPark = park;
        this.price = price;
    }

    public int getInPark() {
        return inPark;
    }

    public int getPrice() {
        return price;
    }
}
