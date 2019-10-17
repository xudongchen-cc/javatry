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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.impl.DoorColorBox;
import org.docksidestage.bizfw.colorbox.space.DoorBoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Date with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author xudong
 */
public class Step14DateTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is date in color-boxes formatted as slash-separated (e.g. 2019/04/24)? <br>
     * (カラーボックスに入っている日付をスラッシュ区切り (e.g. 2019/04/24) のフォーマットしたら？)
     */
    public void test_formatDate() {//DateTimeFormatter.ofPattern("uuuu/MM/dd");
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof LocalDate)
                .map(colorSpace -> (LocalDate) colorSpace.getContent())
                .forEach(insideDate -> {
                    String year = String.valueOf(insideDate.getYear());
                    String month = String.valueOf(insideDate.getMonthValue());
                    if (month.length() < 2)
                        month = "0" + month;
                    String day = String.valueOf(insideDate.getDayOfMonth());
                    if (day.length() < 2)
                        day = "0" + day;
                    log(year + "/" + month + "/" + day);
                });

        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof LocalDateTime)
                .map(colorSpace -> (LocalDateTime) colorSpace.getContent())
                .forEach(insideDate -> {
                    String year = String.valueOf(insideDate.getYear());
                    String month = String.valueOf(insideDate.getMonthValue());
                    if (month.length() < 2)
                        month = "0" + month;
                    String day = String.valueOf(insideDate.getDayOfMonth());
                    if (day.length() < 2)
                        day = "0" + day;
                    log(year + "/" + month + "/" + day);
                });
    }

    /**
     * How is it going to be if the slash-separated date string in yellow color-box is converted to LocaDate and toString() is used? <br>
     * (yellowのカラーボックスに入っているSetの中のスラッシュ区切り (e.g. 2019/04/24) の日付文字列をLocalDateに変換してtoString()したら？)
     */
    public void test_parseDate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof Set)
                .map(content -> (Set) content)
                .forEach(insideSet -> {
                    for (Object str : insideSet) {
                        try {
                            String[] parts = str.toString().split("/");
                            int year = Integer.parseInt(parts[0]);
                            int month = Integer.parseInt(parts[1]);
                            int day = Integer.parseInt(parts[2]);
                            LocalDate newDate = LocalDate.of(year, month, day);
                            log(newDate.toString());
                        } catch (RuntimeException ignored) {
                        }
                    }
                });
    }

    /**
     * What is total of month numbers of date in color-boxes? <br>
     * (カラーボックスに入っている日付の月を全て足したら？)
     */
    public void test_sumMonth() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sumDate = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof LocalDate)
                .map(colorSpace -> (LocalDate) colorSpace.getContent())
                .mapToInt(insideDate -> insideDate.getMonthValue())
                .sum();
        int sumDateTime = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof LocalDateTime)
                .map(colorSpace -> (LocalDateTime) colorSpace.getContent())
                .mapToInt(insideDate -> insideDate.getMonthValue())
                .sum();
        log(sumDate + sumDateTime);
    }

    /**
     * Add 3 days to second-found date in color-boxes, what day of week is it? <br>
     * (カラーボックスに入っている二番目に見つかる日付に3日進めると何曜日？)
     */
    public void test_plusDays_weekOfDay() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List dateResult = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof LocalDate || colorSpace.getContent() instanceof LocalDateTime)
                .map(colorSpace -> colorSpace.getContent())
                .collect(Collectors.toList());
        assert (dateResult.size() >= 2);
        int year = dateResult.get(1) instanceof LocalDate ?
                ((LocalDate) dateResult.get(1)).getYear() :
                ((LocalDateTime) dateResult.get(1)).getYear();
        int month = dateResult.get(1) instanceof LocalDate ?
                ((LocalDate) dateResult.get(1)).getMonthValue() :
                ((LocalDateTime) dateResult.get(1)).getMonthValue();
        int day = dateResult.get(1) instanceof LocalDate ?
                ((LocalDate) dateResult.get(1)).getDayOfMonth() :
                ((LocalDateTime) dateResult.get(1)).getDayOfMonth();
        LocalDate result = LocalDate.of(year, month, day);
        log(result.plusDays(3).getDayOfWeek());
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * How many days (number of day) are between two dates in yellow color-boxes? <br>
     * (yellowのカラーボックスに入っている二つの日付は何日離れている？)
     */
    public void test_diffDay() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List dateResult = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof LocalDate || colorSpace.getContent() instanceof LocalDateTime)
                .map(colorSpace -> colorSpace.getContent())
                .collect(Collectors.toList());
        assert (dateResult.size() == 2);
        List<LocalDate> resultList = new ArrayList<>();
        for (int i = 0; i < dateResult.size(); i++) {
            int year = dateResult.get(i) instanceof LocalDate ?
                    ((LocalDate) dateResult.get(i)).getYear() :
                    ((LocalDateTime) dateResult.get(i)).getYear();
            int month = dateResult.get(i) instanceof LocalDate ?
                    ((LocalDate) dateResult.get(i)).getMonthValue() :
                    ((LocalDateTime) dateResult.get(i)).getMonthValue();
            int day = dateResult.get(i) instanceof LocalDate ?
                    ((LocalDate) dateResult.get(i)).getDayOfMonth() :
                    ((LocalDateTime) dateResult.get(i)).getDayOfMonth();
            resultList.add(LocalDate.of(year, month, day));
        }
        log(resultList);
        log(ChronoUnit.DAYS.between(resultList.get(0), resultList.get(1)));
    }

    /**
     * Find LocalDate in yellow color-box,
     * and add same color-box's LocalDateTime's seconds as number of months to it,
     * and add red color-box's Long number as days to it,
     * and subtract the first decimal place of BigDecimal that has three(3) as integer in List in color-boxes from it,
     * What date is it? <br>
     * (yellowのカラーボックスに入っているLocalDateに、同じカラーボックスに入っているLocalDateTimeの秒数を月数として足して、
     * redのカラーボックスに入っているLong型を日数として足して、カラーボックスに入っているリストの中のBigDecimalの整数値が3の小数点第一位の数を日数として引いた日付は？)
     */
    public void test_birthdate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<LocalDate> resultDate = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof LocalDate)
                .map(colorSpace -> (LocalDate) colorSpace.getContent())
                .collect(Collectors.toList());
        assert(resultDate.size()==1);
        //log(resultDate);//2001 09 04

        int seconds2month = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof LocalDateTime)
                .map(colorSpace -> (LocalDateTime) colorSpace.getContent())
                .mapToInt(colorSpace -> colorSpace.getSecond())
                .findFirst()
                .orElse(-1);
        assert (seconds2month != -1);
        //log(seconds2month);//59

        int long2date = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("red"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorSpace -> colorSpace.getContent() instanceof Long)
                .map(colorSpace -> (Long) colorSpace.getContent())
                .mapToInt(colorSpace -> colorSpace.intValue())
                .findFirst()
                .orElse(-1);
        assert (long2date != -1);
        //log(long2date);//54

        int decimals2date = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof List)
                .flatMap(boxSpace -> ((List<?>) boxSpace.getContent()).stream())
                .filter(content -> content instanceof BigDecimal)
                .map(content -> ((BigDecimal) content).doubleValue())
                .filter(content -> content > 3 && content < 4)
                .mapToInt(content -> (int) ((content - 3) * 10))
                .findFirst()
                .orElse(-1);
        assert (decimals2date != -1);
        //log(decimals2date);//1
        log(resultDate.get(0).plusMonths(seconds2month).plusDays(long2date).minusDays(decimals2date));
    }

    /**
     * What second is LocalTime in color-boxes? <br>
     * (カラーボックスに入っているLocalTimeの秒は？)
     */
    public void test_beReader() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<DoorBoxSpace> doorBoxSpace = colorBoxList.stream()
                .filter(colorBox -> colorBox instanceof DoorColorBox)
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(colorBoxes -> colorBoxes instanceof DoorBoxSpace)
                .map(colorBoxes -> (DoorBoxSpace) colorBoxes)
                .collect(Collectors.toList());
        for (DoorBoxSpace boxSpace : doorBoxSpace) {
            if (boxSpace != null) {
                boxSpace.openTheDoor();
            }
        }
        doorBoxSpace.stream()
                .filter(colorSpace -> colorSpace.getContent() instanceof LocalTime)
                .map(colorSpace -> (LocalTime) colorSpace.getContent())
                .forEach(localTime -> log(localTime.getSecond()));
    }
}
