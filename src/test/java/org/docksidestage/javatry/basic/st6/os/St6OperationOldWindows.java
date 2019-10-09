package org.docksidestage.javatry.basic.st6.os;

/**
 * @author xudong
 */
public class St6OperationOldWindows extends St6OperationSystem {

    public St6OperationOldWindows(String loginId)
    {
        super(loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Documents and Settigs/" + loginId;
    }
}
