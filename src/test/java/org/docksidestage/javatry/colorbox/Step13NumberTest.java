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

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Number with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author xudong
 */
public class Step13NumberTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * How many integer-type values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っているInteger型で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_IntegerOnly() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        long result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof Integer)
                .filter(colorSpace -> ((Integer) colorSpace.getContent()).compareTo(0) > 0)
                .filter(colorSpace -> ((Integer) colorSpace.getContent()).compareTo(54) < 0)
                .count();
        log(result);
    }

    /**
     * How many number values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っている数値で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_Number() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        long result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof Number)
                .filter(colorSpace -> ((Number) colorSpace.getContent()).doubleValue() > 0)
                .filter(colorSpace -> ((Number) colorSpace.getContent()).doubleValue() <= 54)
                //second box のなかに 54(long) があります
                .count();
        log(result);
    }

    /**
     * What color name is used by color-box that has integer-type content and the biggest width in them? <br>
     * (カラーボックスの中で、Integer型の Content を持っていてBoxSizeの幅が一番大きいカラーボックスの色は？)
     */
    public void test_findColorBigWidthHasInteger() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<ColorBox> withInteger = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList().stream().anyMatch(boxSpace -> boxSpace.getContent() instanceof Integer))
                .collect(Collectors.toList());
        int maxWidth = withInteger.stream().mapToInt(colorBox -> colorBox.getSize().getWidth()).max().orElse(-1);
        List<String> results = withInteger.stream()
                .filter(colorBox -> colorBox.getSize().getWidth() == maxWidth)
                .map(colorBox -> colorBox.getColor().getColorName())
                .distinct()
                .collect(Collectors.toList());
        if (!results.isEmpty())
            for (String result : results)
                log(result);
        else
            log("*not found");
    }

    /**
     * What is total of BigDecimal values in List in color-boxes? <br>
     * (カラーボックスの中に入ってる List の中の BigDecimal を全て足し合わせると？)
     */
    public void test_sumBigDecimalInList() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<BigDecimal> resultCollect = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof List)
                .flatMap(boxSpace -> ((List<Object>) boxSpace.getContent()).stream())
                .filter(content -> content instanceof BigDecimal)
                .map(content -> (BigDecimal) content)
                .collect(Collectors.toList());
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : resultCollect)
            result = result.add(bigDecimal);
        log(result);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What key is related to value that is max number in Map that has only number in color-boxes? <br>
     * (カラーボックスに入ってる、valueが数値のみの Map の中で一番大きいvalueのkeyは？)
     */
    public void test_findMaxMapNumberValue() {//問題あるような気がする
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Map> withMap = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof Map)
                .map(boxSpace -> (Map) boxSpace.getContent())
                .collect(Collectors.toList());

        List<Map> insideMap = new ArrayList<>();
        for (Map<String, Object> elements : withMap) {
            Set<String> keys = elements.keySet();
            boolean flag = true;
            for (String key : keys) {
                if (!(elements.get(key) instanceof Number))
                    flag = false;
            }
            if (flag)
                insideMap.add(elements);
        }

        String keyMax = null;
        int valueMax = 0;

        Map<String, Number> resultMap = insideMap.get(0);
        Set<String> keys = resultMap.keySet();
        for (String key : keys) {
            if ((int) resultMap.get(key) > valueMax) {
                valueMax = (int) resultMap.get(key);
                keyMax = key;
            }
        }
        log(keyMax);
    }

    /**
     * What is total of number or number-character values in Map in purple color-box? <br> 
     * (purpleのカラーボックスに入ってる Map の中のvalueの数値・数字の合計は？)
     */
    public void test_sumMapNumberValue() {//number with .(3.3) may be problem
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Map> withMap = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("purple"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof Map)
                .map(boxSpace -> (Map) boxSpace.getContent())
                .collect(Collectors.toList());

        int sum = 0;
        for (Map<String, Object> elements : withMap) {
            Set<String> keys = elements.keySet();
            for (String key : keys) {
                Object value = elements.get(key);
                if (value instanceof Number)
                    sum += ((Number) value).intValue();
                if (value instanceof String) {
                    try {
                        Integer tmp = Integer.valueOf((String) value);
                        sum += tmp;
                    } catch (RuntimeException ignored) {
                    }
                }
            }
        }
        log(sum);
    }

    public void test_forNumTest(){
        /*
        Integer a = Integer.valueOf(3);
        log(a.compareTo(3));//0
        log(a.compareTo(1));//1
        log(a.compareTo(5));//-1
        Double b = Double.valueOf(54.3);
        log(b.doubleValue());
        */
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Double> result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof Number)
                .map(colorSpace -> ((Number) colorSpace.getContent()).doubleValue())
                //.filter(colorSpace -> ((Number) colorSpace.getContent()).doubleValue() > 0)
                //.filter(colorSpace -> ((Number) colorSpace.getContent()).doubleValue() <= 54)
                .collect(Collectors.toList());
        log(result);
    }
}
