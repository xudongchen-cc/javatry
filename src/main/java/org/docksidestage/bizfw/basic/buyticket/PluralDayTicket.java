package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author xudong
 */
// done xudong create class for ticket of plural days mentioned at test_class_moreFix_useInterface. and aggregate FourDayTicket and TwoDayTicket to the class. by katashin (2019/10/09)
// test_class_moreFix_useInterfaceで書かれているように、複数日用のクラスを作成しよう。そして TwoDayTicketとFourDayTicketをそのクラスにまとめてみよう
// done xudong add a space between Ticket and begin bracket . (e.g. Ticket { ) by katashin (2019/10/09)
// use keyboard shortcut to fix it. (command + option + L)
// Ticket と { の間に スペース を入れよう
// ショートカットがあるので使ってみよう (command + option + L)
public class PluralDayTicket implements Ticket {

    private final int displayPrice;
    private int leftInParkTime;
    private TicketType ticketType;

    public PluralDayTicket(TicketType type) {
        displayPrice = type.getPrice();
        leftInParkTime = type.getInPark();
        ticketType = type;
    }

    public void doInPark() {
        if (leftInParkTime == 0) {
            throw new IllegalStateException("Cannot go into park with this ticket anymore: displayedPrice=" + displayPrice);
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
