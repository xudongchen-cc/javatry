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

import java.util.*;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, using Stream API you can. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author xudong
 */
public class Step12StreamStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * What is color name length of first color-box? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = colorBoxList.stream()
                .findFirst()
                .map(colorBox -> colorBox.getColor().getColorName())
                .map(colorName -> colorName.length() + " (" + colorName + ")")
                .orElse("*not found");
        log(answer);//5 (green)
        //色の名前の文字数は5
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> allStrings = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .filter(colorSpace -> colorSpace.getContent() != null)
                .filter(colorSpace -> colorSpace.getContent().getClass().getSimpleName().equals("String"))
                .map(colorSpace -> colorSpace.toString())
                .collect(Collectors.toList());
        int longest = allStrings.stream().mapToInt(str -> str.length()).max().orElse(-1);
        String sea = allStrings.stream().filter(str -> str.length() == longest).findFirst().orElse(null);
        log(sea);//the first one
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> allStrings = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .filter(colorSpace -> colorSpace.getContent() != null)
                .filter(colorSpace -> colorSpace.getContent().getClass().getSimpleName().equals("String"))
                .map(colorSpace -> colorSpace.toString())
                .collect(Collectors.toList());
        int longest = allStrings.stream().mapToInt(str -> str.length()).max().orElse(-1);
        int shortest = allStrings.stream().mapToInt(str -> str.length()).min().orElse(-1);
        log(longest - shortest);
    }

    // has small #adjustmemts from ClassicStringTest
    //  o sort allowed in Stream
    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (sort allowed in Stream)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (Streamでのソートありで))
     */
    public void test_length_findSecondMax() {//TODO　あとで確かめる
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> allStrings = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .map(colorSpace -> colorSpace.toString())
                .collect(Collectors.toList());
        List<Integer> sizes = allStrings.stream().map(str -> str.length()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        //.sorted(Comparator.reverseOrder())//降順
        //.sorted(Comparator.reverseOrder())// 昇順
        String sea = allStrings.stream().filter(str -> str.length() == sizes.get(1)).findFirst().orElse(null);
        log(sea);//the first one
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            List<String> allStrings = colorBoxList.stream()
                    .map(colorBox -> colorBox.getSpaceList())
                    .flatMap(colorSpace -> colorSpace.stream())
                    .filter(colorSpace -> colorSpace.getContent() != null)
                    .filter(colorSpace -> colorSpace.getContent().getClass().getSimpleName().equals("String"))
                    .map(colorSpace -> colorSpace.toString())
                    .collect(Collectors.toList());
            int sumLength = allStrings.stream().mapToInt(str -> str.length()).sum();
            log(sumLength);
        }
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {//TODO　修正
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        List<String> AllColors = colorBoxes.stream()
                .map(colorBox -> colorBox.getColor())
                .map(boxColor -> boxColor.getColorName())
                .collect(Collectors.toList());
        int longest = AllColors.stream().mapToInt(colorStr -> colorStr.length()).distinct().max().orElse(-1);
        String sea = AllColors.stream().filter(str -> str.length() == longest).findFirst().orElse(null);
        log(sea);//the first one
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {//TODO　wait
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        colorBoxes.stream()
                .filter(colorBox -> colorBox.getSpaceList().stream().anyMatch(boxSpace -> boxSpace.toString().startsWith("Water")))
                .forEach(colorBox -> log(colorBox.getColor().getColorName()));
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {//TODO　wait
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        colorBoxes.stream()
                .filter(colorBox -> colorBox.getSpaceList().stream().anyMatch(boxSpace -> boxSpace.toString().endsWith("front")))
                .forEach(colorBox -> log(colorBox.getColor().getColorName()));
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        colorBoxes.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .filter(colorSpace -> colorSpace.getContent() != null)
                .filter(colorSpace -> colorSpace.getContent().getClass().getSimpleName().equals("String"))
                .filter(colorSpace -> colorSpace.toString().endsWith("front"))
                .forEach(colorSpace -> log(colorSpace.toString().indexOf("front") + 1));
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        colorBoxes.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .filter(colorSpace -> colorSpace.getContent() != null)
                .filter(colorSpace -> colorSpace.getContent().getClass().getSimpleName().equals("String"))
                .filter(colorSpace -> colorSpace.toString().indexOf("ど") != colorSpace.toString().lastIndexOf("ど"))
                .forEach(colorSpace -> log(colorSpace.toString().lastIndexOf("ど") + 1));
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        colorBoxes.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .filter(colorSpace -> colorSpace.getContent() != null)
                .filter(colorSpace -> colorSpace.getContent().getClass().getSimpleName().equals("String"))
                .map(colorSpace -> colorSpace.toString())
                .filter(spaceStr -> spaceStr.endsWith("front"))
                .forEach(spaceStr -> log(spaceStr.substring(0, 1)));
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        colorBoxes.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .filter(colorSpace -> colorSpace.getContent() != null)
                .filter(colorSpace -> colorSpace.getContent().getClass().getSimpleName().equals("String"))
                .map(colorSpace -> colorSpace.toString())
                .filter(spaceStr -> spaceStr.startsWith("Water"))
                .forEach(spaceStr -> log(spaceStr.substring(spaceStr.length() - 1)));
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        colorBoxes.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .filter(colorSpace -> colorSpace.getContent() != null)
                .filter(colorSpace -> colorSpace.getContent().getClass().getSimpleName().equals("String"))
                .map(colorSpace -> colorSpace.toString())
                .filter(spaceStr -> spaceStr.contains("o"))
                .forEach(spaceStr -> {
                    //log(spaceStr);
                    spaceStr = spaceStr.replace("o", "");
                    //log(spaceStr);
                    log(spaceStr.length());
                });
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        colorBoxes.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .filter(colorSpace -> colorSpace.getContent() != null)
                .filter(colorSpace -> colorSpace.getContent().getClass().getName().equals("java.io.File"))
                .map(colorSpace -> colorSpace.toString())
                .forEach(spaceStr -> {
                    spaceStr = spaceStr.replace("/", "\\");//¥¥?
                    log(spaceStr);
                });
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        List<YourPrivateRoom.DevilBox> devilBoxes = colorBoxes.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof YourPrivateRoom.DevilBox)
                .map(colorSpace -> (YourPrivateRoom.DevilBox) colorSpace.getContent())
                .collect(Collectors.toList());
        devilBoxes.stream().forEach(devilBox -> {
            devilBox.wakeUp();
            devilBox.allowMe();
            devilBox.open();
        });
        int sumLength = devilBoxes.stream()
                .map(devilBox -> {
            try {
                return devilBox.getText();
            } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
                log("no txt here", e);
                return "";
            }
        }).mapToInt(str -> str.length()).sum();
        log(sumLength);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        List<Map> mapSpaces = colorBoxes.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof java.util.Map)
                .map(colorSpace -> (Map)colorSpace.getContent())
                .collect(Collectors.toList());
        List<String> results = new ArrayList<>();
        for(Map mapspace :mapSpaces)
        {
            String result = "map:{ ";
            Set<String> keys = mapspace.keySet();
            for (String key : keys) {
                result = result + key + " = " + mapspace.get(key) + " ; ";
                //最後;ある
            }
            result = result.substring(0, result.lastIndexOf(";"));
            result = result + "}";
            results.add(result);
        }
        if (!results.isEmpty())
            for (String result : results)
                log(result);
        else {
            log("*not found");
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        List<Map> mapSpaces = colorBoxes.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(colorSpace -> colorSpace.stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof java.util.Map)
                .map(colorSpace -> (Map)colorSpace.getContent())
                .collect(Collectors.toList());
        List<String> results = new ArrayList<>();
        for(Map mapspace :mapSpaces)
        {
            results.add(getMapResult(mapspace));
        }
        if (!results.isEmpty())
            for (String result : results)
                log(result);
        else {
            log("*not found");
        }
    }

    private String getMapResult(Map content) {
        String result = "map:{ ";
        Set<String> keys = content.keySet();
        for (String key : keys) {
            if (content.get(key) instanceof java.util.Map)
                result = result + key + " = " + getMapResult((Map) content.get(key)) + " ; ";
            else
                result = result + key + " = " + content.get(key) + " ; ";
            //最後;ある
        }
        result = result.substring(0, result.lastIndexOf(";"));
        result = result + "}";
        return result;
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // has small #adjustmemts from ClassicStringTest
    //  o comment out because of too difficult to be stream?
    ///**
    // * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_flat() {
    //}
    //
    ///**
    // * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_nested() {
    //}

    //only for test
    public void test_forTest() {
        /*
        String secretMap = " dockside = over ; hangar = mystic ; broadway = map:{ encore! = musical ; bbb = review } ";
        //String secretMap = " dockside = over ; broadway = map:{ encore! = musical ; bbb = review } ; hangar = mystic ";
        int mapStart = secretMap.lastIndexOf(";", secretMap.indexOf("map:{"));
        int mapEnd = secretMap.indexOf(";",secretMap.lastIndexOf("}"))+1;
        //log(mapEnd);
        //log(secretMap);
        //log(secretMap.substring(0,mapStart));
        //log(secretMap.substring(mapEnd));
        //log(secretMap.substring(mapStart+1,mapEnd-1));
        //String a = secretMap.substring(mapStart+1,mapEnd-1);
        String a = secretMap.substring(mapStart+1);
        log(a);// broadway = map:{ encore! = musical ; bbb = review }
        log(a.substring(1,a.indexOf("=")-1));
        log(a.substring(a.indexOf("=")+2));
        log(mapStart);
        */

        log("aabaa".indexOf("a",2));
        log("aabaa".lastIndexOf("a",2));

        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        ColorBox box = colorBoxes.get(6);
        List<BoxSpace> space = box.getSpaceList();
        space.stream().forEach(sp -> {
            log(sp.toString());
            //log(((YourPrivateRoom.SecretBox)sp.getContent()).getText());
        });
        space.stream().forEach(sp -> {
            log(sp.getContent().getClass().getName());
        });
    }
}
