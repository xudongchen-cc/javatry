package org.docksidestage.javatry.basic.st6.os;

/**
 * @author xudong
 */
public class St6OperationMac extends St6OperationSystem {

    public St6OperationMac(String loginId) {
        super(loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "/";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + loginId;
    }
}
