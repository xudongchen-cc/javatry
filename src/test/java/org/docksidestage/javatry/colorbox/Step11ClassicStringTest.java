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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.impl.StandardColorBox;
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
        // TODO 陳 if文がNestしていて読みづらくなっている...guard節で書き直してみよう！ by もってぃ
        //         ref: https://refactoring.com/catalog/replaceNestedConditionalWithGuardClauses.html
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    //String str = boxSpace.toString();
                    if (boxSpace.getContent() == null)
                        continue;
                    // TODO 陳 instanceofを使ってStringであることを判定してみよう by もってぃ
                    if (!boxSpace.getContent().getClass().getSimpleName().equals("String"))
                        continue;
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
        // TODO 陳 ColorBoxは空じゃないけど、Stringが一つも含まれていないときに"一番長いものと短いものの差"は10000になってしまわないかな？ by もってぃ
        int shotest = 10000;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    //String str = boxSpace.toString();
                    if (boxSpace.getContent() == null)
                        continue;
                    if (!boxSpace.getContent().getClass().getSimpleName().equals("String"))
                        continue;
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
    public void test_length_findSecondMax() {//the last one//monaiari　TODO　修正
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int longest = 0;
        int longest2 = 0;
        String sea = null;
        // TODO 陳 unusedな変数は削除しよう！ by もってぃ
        String land2 = null;
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
                    //String str = boxSpace.toString();
                    if (boxSpace.getContent() == null)
                        continue;
                    if (!boxSpace.getContent().getClass().getSimpleName().equals("String"))
                        continue;
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
    public void test_length_findMaxColorSize() {//the last one(set hash set)TODO　修正
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> colors = new ArrayList<>();
        String colorTmp;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                colorTmp = colorBox.getColor().getColorName();
                for (BoxSpace boxSpace : boxSpaceList) {
                    //String str = boxSpace.toString();
                    if (boxSpace.getContent() == null)
                        continue;
                    if (!boxSpace.getContent().getClass().getSimpleName().equals("String"))
                        continue;
                    String str = boxSpace.toString();
                    if (str.startsWith("Water"))
                        colors.add(colorTmp);
                }
            }
            if (!colors.isEmpty())
                for (String color : colors)
                    log(color);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> colors = new ArrayList<>();
        String colorTmp;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                colorTmp = colorBox.getColor().getColorName();
                for (BoxSpace boxSpace : boxSpaceList) {
                    //String str = boxSpace.toString();
                    if (boxSpace.getContent() == null)
                        continue;
                    if (!boxSpace.getContent().getClass().getSimpleName().equals("String"))
                        continue;
                    String str = boxSpace.toString();
                    if (str.endsWith("front"))
                        colors.add(colorTmp);
                }
            }
            if (!colors.isEmpty())
                for (String color : colors)
                    log(color);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {//答えだけではなく、提示の文字も一緒に出ればいい
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Integer> positions = new ArrayList<>();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    //String str = boxSpace.toString();
                    if (boxSpace.getContent() == null)
                        continue;
                    if (!boxSpace.getContent().getClass().getSimpleName().equals("String"))
                        continue;
                    String str = boxSpace.toString();
                    if (str.endsWith("front"))
                        positions.add(str.indexOf("front") + 1);
                }
            }
            if (!positions.isEmpty())
                for (int position : positions)
                    log(position);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Integer> positions = new ArrayList<>();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    //String str = boxSpace.toString();
                    if (boxSpace.getContent() == null)
                        continue;
                    if (!boxSpace.getContent().getClass().getSimpleName().equals("String"))
                        continue;
                    String str = boxSpace.toString();
                    if (str.indexOf("ど") != str.lastIndexOf("ど"))
                        positions.add(str.lastIndexOf("ど") + 1);
                }
            }
            if (!positions.isEmpty())
                for (int position : positions)
                    log(position);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> firsts = new ArrayList<>();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    //String str = boxSpace.toString();
                    if (boxSpace.getContent() == null)
                        continue;
                    if (!boxSpace.getContent().getClass().getSimpleName().equals("String"))
                        continue;
                    String str = boxSpace.toString();
                    if (str.endsWith("front"))
                        firsts.add(str.substring(0, 1));
                }
            }
            if (!firsts.isEmpty())
                for (String first : firsts)
                    log(first);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> lasts = new ArrayList<>();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    //String str = boxSpace.toString();
                    if (boxSpace.getContent() == null)
                        continue;
                    if (!boxSpace.getContent().getClass().getSimpleName().equals("String"))
                        continue;
                    String str = boxSpace.toString();
                    if (str.endsWith("front"))
                        lasts.add(str.substring(str.length() - 1));
                }
            }
            if (!lasts.isEmpty())
                for (String last : lasts)
                    log(last);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Integer> counts = new ArrayList<>();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    //String str = boxSpace.toString();
                    if (boxSpace.getContent() == null)
                        continue;
                    if (!boxSpace.getContent().getClass().getSimpleName().equals("String"))
                        continue;
                    String str = boxSpace.toString();
                    if (str.contains("o")) {
                        str = str.replace("o", "");
                        counts.add(str.length());
                    }
                }
            }
            if (!counts.isEmpty())
                for (int count : counts)
                    log(count);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> paths = new ArrayList<>();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    //String str = boxSpace.toString();
                    if (boxSpace.getContent() == null)
                        continue;
                    if (!boxSpace.getContent().getClass().getName().equals("java.io.File"))
                        continue;
                    String str = boxSpace.toString();
                    str = str.replace("/", "\\");//¥¥?
                    paths.add(str);
                }
            }
            if (!paths.isEmpty())
                for (String path : paths)
                    log(path);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int lengths = 0;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    if (boxSpace.getContent() instanceof YourPrivateRoom.DevilBox) {
                        YourPrivateRoom.DevilBox content = (YourPrivateRoom.DevilBox) boxSpace.getContent();
                        content.wakeUp();
                        content.allowMe();
                        content.open();
                        String str;
                        try {
                            str = content.getText();
                            lengths += str.length();
                        } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
                            log("no txt here", e);
                        }
                    }
                }
            }
            if (lengths != 0)
                log(lengths);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> results = new ArrayList<>();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    if (boxSpace.getContent() instanceof java.util.Map) {
                        String result = "map:{ ";
                        Map content = (Map) boxSpace.getContent();
                        Set<String> keys = content.keySet();
                        for (String key : keys) {
                            result = result + key + " = " + content.get(key) + "; ";
                            //最後;ある
                        }
                        result = result + " }";
                        results.add(result);
                    }
                }
            }
            if (!results.isEmpty())
                for (String result : results)
                    log(result);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> results = new ArrayList<>();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    if (boxSpace.getContent() instanceof java.util.Map) {
                        results.add(getMapResult((Map) boxSpace.getContent()));
                    }
                }
            }
            if (!results.isEmpty())
                for (String result : results)
                    log(result);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }

    private String getMapResult(Map content) {
        String result = "map:{ ";
        Set<String> keys = content.keySet();
        for (String key : keys) {
            if (content.get(key) instanceof java.util.Map)
                result = result + key + " = " + getMapResult((Map) content.get(key)) + "; ";
            else
                result = result + key + " = " + content.get(key) + "; ";
            //最後;ある
        }
        result = result + " }";
        return result;
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> results = new ArrayList<>();
        if (!colorBoxList.isEmpty()) {
            /*
            ColorBox whiteBox;
            for (ColorBox colorBox : colorBoxList) {
                if(colorBox.getColor().getColorName().equals("white"))
                    whiteBox = colorBox;
            }
            */
            BoxSpace boxSpace = ((StandardColorBox) colorBoxList.get(4)).getUpperSpace();
            YourPrivateRoom.SecretBox secretBox = (YourPrivateRoom.SecretBox) boxSpace.getContent();
            String secretString = secretBox.getText();

            if (!results.isEmpty())
                for (String result : results)
                    log(result);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> results = new ArrayList<>();
        if (!colorBoxList.isEmpty()) {
            /*
            ColorBox whiteBox;
            for (ColorBox colorBox : colorBoxList) {
                if(colorBox.getColor().getColorName().equals("white"))
                    whiteBox = colorBox;
            }
            */
            BoxSpace boxSpaceMiddle = ((StandardColorBox) colorBoxList.get(4)).getMiddleSpace();
            BoxSpace boxSpaceLower = ((StandardColorBox) colorBoxList.get(4)).getLowerSpace();
            YourPrivateRoom.SecretBox secretBoxMiddle = (YourPrivateRoom.SecretBox) boxSpaceMiddle.getContent();
            YourPrivateRoom.SecretBox secretBoxLower = (YourPrivateRoom.SecretBox) boxSpaceLower.getContent();
            String secretStringMiddle = secretBoxMiddle.getText();
            String secretStringLower = secretBoxLower.getText();

            if (!results.isEmpty())
                for (String result : results)
                    log(result);
            else {
                log("*not found");
            }
        } else {
            log("*not found");
        }
    }
}
