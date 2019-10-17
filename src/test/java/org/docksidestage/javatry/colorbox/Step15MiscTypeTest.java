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
package org.docksidestage.javatry.colorbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of various type with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author xudong
 */
public class Step15MiscTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                           Exception
    //                                                                           =========
    /**
     * What class name is throw-able object in color-boxes? <br>
     * (カラーボックスに入っているthrowできるオブジェクトのクラス名は？)
     */
    public void test_throwable() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof Throwable)
                .map(boxSpace -> boxSpace.getContent())
                .forEach(o -> log(o.getClass().getName()));
    }

    /**
     * What message is for exception that is nested by exception in color-boxes? <br>
     * (カラーボックスに入っている例外オブジェクトのネストした例外インスタンスのメッセージは？)
     */
    public void test_nestedException() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof Throwable)
                .map(boxSpace -> (Throwable) boxSpace.getContent())
                .forEach(throwable -> log(throwable.getMessage()));
    }

    // ===================================================================================
    //                                                                           Interface
    //                                                                           =========
    /**
     * What value is returned by justHere() of FavoriteProvider in yellow color-box? <br>
     * (カラーボックスに入っているFavoriteProviderインターフェースのjustHere()メソッドの戻り値は？)
     */
    public void test_interfaceCall() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof YourPrivateRoom.FavoriteProvider)
                .map(boxSpace -> (YourPrivateRoom.FavoriteProvider) boxSpace.getContent())
                .forEach(favoriteProvider -> log(favoriteProvider.justHere()));
    }

    // ===================================================================================
    //                                                                            Optional
    //                                                                            ========
    /**
     * What keyword is in BoxedStage of BoxedResort in List in beige color-box? (show "none" if no value) <br>
     * (beigeのカラーボックスに入っているListの中のBoxedResortのBoxedStageのkeywordは？(値がなければ固定の"none"という値を))
     */
    public void test_optionalMapping() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Optional<YourPrivateRoom.BoxedPark>> parkItems = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("beige"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof List)
                .flatMap(boxSpace -> ((List<?>) boxSpace.getContent()).stream())
                .filter(content -> content instanceof YourPrivateRoom.BoxedResort)
                .map(content -> (YourPrivateRoom.BoxedResort) content)
                .map(boxedResort -> boxedResort.getPark())
                .collect(Collectors.toList());

        List<Optional<YourPrivateRoom.BoxedStage>> itemStages = new ArrayList<>();
        for (Optional<YourPrivateRoom.BoxedPark> parkItem : parkItems) {
            if (parkItem.isPresent()) {
                itemStages.add(parkItem.get().getStage());
            } else {
                log("none");
            }
        }
        for (Optional<YourPrivateRoom.BoxedStage> itemStage : itemStages) {
            if (itemStage.isPresent()) {
                String str = itemStage.get().getKeyword();
                if (str != null)
                    log(str);
                else
                    log("none");
            } else {
                log("none");
            }
        }

        //        colorBoxList.stream()
        //                .filter(colorBox -> colorBox.getColor().getColorName().equals("beige"))
        //                .flatMap(colorBox -> colorBox.getSpaceList().stream())
        //                .filter(boxSpace -> boxSpace.getContent() instanceof List)
        //                .flatMap(boxSpace -> ((List<Object>) boxSpace.getContent()).stream())
        //                .filter(content -> content instanceof YourPrivateRoom.BoxedResort)
        //                .map(content -> (YourPrivateRoom.BoxedResort) content)
        //                .map(boxedResort -> boxedResort.getPark())
        //                .filter(boxedPark -> boxedPark.isPresent())
        //                .map(boxedPark -> boxedPark.get().getStage())
        //                .filter(boxedStage -> boxedStage.isPresent())
        //                .map(boxedStage -> boxedStage.get().getKeyword())
        //                .forEach(s -> log(s == null ? "none" : s));
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What line number is makeEighthColorBox() call in getColorBoxList()? <br>
     * (getColorBoxList()メソッドの中のmakeEighthColorBox()メソッドを呼び出している箇所の行数は？)
     */
    public void test_lineNumber() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof Throwable)
                .map(boxSpace -> (Throwable) boxSpace.getContent())
                .filter(throwable -> Arrays.stream(throwable.getCause().getStackTrace()).anyMatch(stackTraceElement->stackTraceElement.getMethodName().equals("makeEighthColorBox")))
                .flatMap(throwable -> Arrays.stream(throwable.getCause().getStackTrace()))
                //.filter(stackTraceElement -> stackTraceElement.getMethodName().equals("makeEighthColorBox"))
                .filter(stackTraceElement -> stackTraceElement.getMethodName().equals("getColorBoxList"))
                .forEach(stackTraceElement -> log(stackTraceElement.getLineNumber()));
    }
}
