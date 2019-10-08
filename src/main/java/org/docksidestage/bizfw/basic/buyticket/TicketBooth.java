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
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15

    private static final int MAX_QUANTITY_TWO = 10;
    private static final int TWO_DAY_PRICE = 13200;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity1 = MAX_QUANTITY;
    private int quantity2 = MAX_QUANTITY_TWO;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    public Ticket buyOneDayPassport(int handedMoney) {
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
        //return buyPassport(quantity1, handedMoney, ONE_DAY_PRICE);
        buyPassport(quantity1, handedMoney, ONE_DAY_PRICE);
        return new Ticket(ONE_DAY_PRICE);
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney)
    {
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
        //return buyPassport(quantity2, handedMoney, TWO_DAY_PRICE);
        int change = buyPassport(quantity2, handedMoney, TWO_DAY_PRICE);
        return new TicketBuyResult(TWO_DAY_PRICE, change);
    }

    private int buyPassport(int quantity, int handedMoney, int PRICE)
    {
        if (quantity1 <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        else if (handedMoney < PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        else
        {
            --quantity1;
            if (salesProceeds != null) {
                salesProceeds = salesProceeds + PRICE;
            } else {
                salesProceeds = PRICE;
            }
            return handedMoney - PRICE;
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

    public int getQuantity_two() {
        return quantity2;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
