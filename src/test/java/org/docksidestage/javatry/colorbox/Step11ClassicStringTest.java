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
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author xudong
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // also show name for visual check
            //5 (green), 名前の文字数は5
        } else {
            log("*not found");
        }
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {//the last one
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int longest = 0;
        String longestStr = null;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    String str = boxSpace.toString();
                    if (str.length() > longest) {
                        longest = str.length();
                        longestStr = str;
                    }
                }
            }
            if (!longestStr.isEmpty())
                log(longestStr);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int longest = 0;
        int shotest = 10000;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    String str = boxSpace.toString();
                    if (str.length() > longest) {
                        longest = str.length();
                    }
                    if (str.length() < shotest) {
                        shotest = str.length();
                    }
                }
            }
            log(longest - shotest);
        } else {
            log("*not found");
        }
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (without sort) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {//the last one
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int longest = 0;
        int longest2 = 0;
        String sea = null;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    String str = boxSpace.toString();
                    if (str.length() > longest) {
                        longest = str.length();
                    }
                    if (str.length() > longest2 && !(str.length() == longest)) {
                        longest2 = str.length();
                        sea = str;
                    }
                }
            }
            if (!sea.isEmpty())
                log(sea);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sum = 0;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    String str = boxSpace.toString();
                    sum += str.length();
                }
            }
            log(sum);
        } else {
            log("*not found");
        }
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {//the last one
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int longest = 0;
        String longestColor = null;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                String colorString = colorBox.getColor().getColorName();
                if (colorString.length() > longest) {
                    longest = colorString.length();
                    longestColor = colorString;
                }
            }
            if (!longestColor.isEmpty())
                log(longestColor);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxes.isEmpty()) {

        }
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxes.isEmpty()) {
        }
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
        if (!colorBoxes.isEmpty()) {
        }
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxes.isEmpty()) {
        }
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
        if (!colorBoxes.isEmpty()) {
        }
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxes.isEmpty()) {
        }
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
        if (!colorBoxes.isEmpty()) {
        }
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxes.isEmpty()) {
        }
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
        if (!colorBoxes.isEmpty()) {
        }
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
        if (!colorBoxes.isEmpty()) {
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxes.isEmpty()) {
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxes.isEmpty()) {
        }
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        List<ColorBox> colorBoxes = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxes.isEmpty()) {
        }
    }
}
