package org.docksidestage.javatry.basic.st6.os;

/**
 * @author xudong
 */
public class St6OperationWindows extends St6OperationSystem {

    public St6OperationWindows(String loginId) {
        super(loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + loginId;
    }
}
