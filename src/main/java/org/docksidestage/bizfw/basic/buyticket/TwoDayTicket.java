package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author xudong
 */
// TODO xudong create class for ticket of plural days mentioned at test_class_moreFix_useInterface. and aggregate FourDayTicket and TwoDayTicket to the class. by katashin (2019/10/09)
// test_class_moreFix_useInterfaceで書かれているように、複数日用のクラスを作成しよう。そして TwoDayTicketとFourDayTicketをそのクラスにまとめてみよう
// TODO done xudong add a space between Ticket and begin bracket . (e.g. Ticket { ) by katashin (2019/10/09)
// use keyboard shortcut to fix it. (command + option + L)
// Ticket と { の間に スペース を入れよう
// ショートカットがあるので使ってみよう (command + option + L)
public class TwoDayTicket implements Ticket {

    private final int displayPrice;
    private int leftInParkTime;
    private String ticketType;

    public TwoDayTicket(int price) {
        displayPrice = price;
        leftInParkTime = 2;
        ticketType = "TwoDayTicket";
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
