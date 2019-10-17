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

import java.util.List;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Devil with color-box, (try if you woke up Devil in StringTest) <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author xudong
 */
public class Step19DevilTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        Devil Parade
    //                                                                        ============
    /**
     * What is the content in low space of color-box
     * of which lengths of the color is same as first place number of BigDecimal value first found in List in box spaces,
     * that the second decimal place is same as tens place of depth of the color-box
     * of which color name ends with third character of color-box that contains null as content? <br>
     * (nullを含んでいるカラーボックスの色の名前の3文字目の文字で色の名前が終わっているカラーボックスの深さの十の位の数字が小数点第二桁目になっている
     * スペースの中のリストの中で最初に見つかるBigDecimalの一の位の数字と同じ色の長さのカラーボックスの一番下のスペースに入っているものは？)
     */
    public void test_too_long() {//小数点のところまで
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> firstBoxName = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList().stream().anyMatch(boxSpace -> boxSpace.getContent() == null))
                .map(colorBox -> colorBox.getColor().getColorName().substring(2, 3))
                .distinct()
                .collect(Collectors.toList());
        assert(!firstBoxName.isEmpty());
        log(firstBoxName);

        List<Integer> secondBoxNumber = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().endsWith(firstBoxName.get(0)))
                .map(colorBox -> colorBox.getSize().getDepth()/10)
                .distinct()
                .collect(Collectors.toList());
        assert(!secondBoxNumber.isEmpty());
        log(secondBoxNumber);

        List<Number> collectList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof List)
                .flatMap(boxSpace -> ((List<?>) boxSpace.getContent()).stream())
                .filter(content -> content instanceof Number)
                .map(content -> (Number) content)
                .collect(Collectors.toList());
        assert(!collectList.isEmpty());
        log(collectList);

        for(Number num:collectList)
        {
            log(num.getClass().getName());
        }

    }

    // ===================================================================================
    //                                                                      Java Destroyer
    //                                                                      ==============
    /**
     * What string of toString() is BoxSize of red color-box after changing height to 160 (forcedly in this method)? <br>
     * ((このテストメソッドの中だけで無理やり)赤いカラーボックスの高さを160に変更して、BoxSizeをtoString()すると？)
     */
    public void test_looks_like_easy() {//実現したみたいけど
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<ColorBox> redBoxL =
                colorBoxList.stream().filter(colorBox -> colorBox.getColor().getColorName().equals("red")).collect(Collectors.toList());
        assert(redBoxL.size()==1);
        ColorBox redBox = redBoxL.get(0);
        log(redBox.getSize().getHeight());//height not changed

        redBox.getSize().setHeight(160);
        log(redBox.getSize().toString());
    }

    // ===================================================================================
    //                                                                        Meta Journey
    //                                                                        ============
    /**
     * What value is returned from no-parameter functional method of interface that has FunctionalInterface annotation in color-boxes? <br> 
     * (カラーボックスに入っているFunctionalInterfaceアノテーションが付与されているインターフェースの引数なしのFunctionalメソッドの戻り値は？)
     */
    public void test_be_frameworker() {//まだ
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BoxSpace> collect = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof FunctionalInterface)
                .collect(Collectors.toList());
        log(collect);

    }
}
