package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xudong
 */
public class BarkingProcess {

    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int hitPoint;
    private String barkWord;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(String backWord)
    {
        hitPoint = getInitialHitPoint();
        this.barkWord = backWord;
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    private void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle"); // dummy implementation
        downHitPoint();
    }

    private void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
        downHitPoint();
    }

    private BarkedSound doBark(String barkWord) {
        downHitPoint();
        return new BarkedSound(barkWord);
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    private void downHitPoint() {
        if(barkWord.equals("uooo-"))
            return;

        tempDownHitPoint();
        if(barkWord.equals("nya-") && hitPoint % 2 == 0)
            tempDownHitPoint();
    }

    private void tempDownHitPoint()
    {
        --hitPoint;
        if (hitPoint == 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + barkWord);
        }
    }

    public int getHitPoint()
    {
        if(barkWord.equals("uooo"))
            return -1;
        else
            return hitPoint;
    }
}
