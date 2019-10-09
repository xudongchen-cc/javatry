/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 * @author xudong
 */
// TODO done xudong add your name to @author. fix it on other file. by katashin (2019/10/09)
// @author に名前を追加しましょう。他のファイルもね。
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15

    private static final int MAX_QUANTITY_TWO = 10;
    private static final int TWO_DAY_PRICE = 13200;

    private static final int MAX_QUANTITY_FOUR = 10;
    private static final int FOUR_DAY_PRICE = 22400;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity1 = MAX_QUANTITY;
    private int quantity2 = MAX_QUANTITY_TWO;
    private int quantity4 = MAX_QUANTITY_FOUR;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        /*
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        --quantity;
        if (handedMoney < ONE_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + handedMoney;
        } else {
            salesProceeds = handedMoney;
        }
        */

        /*
        if (quantity1 <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        else if (handedMoney < ONE_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        else
        {
            --quantity1;
            if (salesProceeds != null) {
                salesProceeds = salesProceeds + ONE_DAY_PRICE;
            } else {
                salesProceeds = ONE_DAY_PRICE;
            }
        }
        */
        int change = buyPassport(quantity1, handedMoney, ONE_DAY_PRICE, TicketType.Oneday);
        return new TicketBuyResult(ONE_DAY_PRICE, change, "ONE");
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        /*
        if (quantity2 <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        else if (handedMoney < TWO_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        else
        {
            --quantity2;
            if (salesProceeds != null) {
                salesProceeds = salesProceeds + TWO_DAY_PRICE;
            } else {
                salesProceeds = TWO_DAY_PRICE;
            }
            return handedMoney - TWO_DAY_PRICE;
        }
        */
        int change = buyPassport(quantity2, handedMoney, TWO_DAY_PRICE, TicketType.Twoday);
        return new TicketBuyResult(TWO_DAY_PRICE, change, "TWO");
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) {
        int change = buyPassport(quantity4, handedMoney, FOUR_DAY_PRICE, TicketType.Fourday);
        return new TicketBuyResult(FOUR_DAY_PRICE, change, "FOUR");
    }

    // TODO xudong accumulating to buyPassport method is good. define constant value of type as static final string is better.
    // buyPassportにまとまったのはいいね。 typeのための定数を、static final stringとして宣言できるといいね。
    // TODO done xudong method parameter is presented as lowercase as a habit. so price is better than PRICE. by katashin (2019/10/09)
    // メソッドの引数は習慣として小文字が多いです。priceの方がいいね。
    private int buyPassport(int quantity, int handedMoney, int price, TicketType type) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        } else if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        } else {
            if (type.equals(TicketType.Oneday))
                --quantity1;
            if (type.equals(TicketType.Twoday))
                --quantity2;
            if (type.equals(TicketType.Fourday))
                --quantity4;
            if (salesProceeds != null) {
                salesProceeds = salesProceeds + price;
            } else {
                salesProceeds = price;
            }
            return handedMoney - price;
        }
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity1;
    }

    public int getQuantityTwo() {
        return quantity2;
    }

    public int getQuantityFour() {
        return quantity4;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
