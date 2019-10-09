package org.docksidestage.bizfw.basic.objanimal;

/**
 * The object for bird.
 * @author xudong
 */
public class Bird extends Animal implements Fly{
    public Bird(){};

    @Override
    protected String getBarkWord() {
        return "chu-";
    }

    @Override
    public boolean doFly()
    {
        return true;
    }
}
