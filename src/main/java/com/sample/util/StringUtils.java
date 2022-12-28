package com.sample.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

	private static final DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 M월 d일");
	private static final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 텍스트를 전달받아서, 그 값이 null이면 defaultValue로 지정된 값을 반환한다.
	 * @param str 문자열
	 * @param defaultValue 기본값
	 * @return str이 null이면 defaultValue를 반환한다.
	 */
	public static String nullToValue(String str, String defaultValue) {
		if (str == null) {
			return defaultValue;
		}
		return str.trim();
	}
	
	/**
	 * 텍스트를 전달받아서, 그 값이 null이면 빈 문자열을 반환한다.
	 * @param str 문자열
	 * @return str이 null이면 빈 문자열을 반환한다.
	 */
	public static String nullToBlank(String str) {
		return nullToValue(str, "");
	}
	
	
	/**
	 * 텍스트를 전달받아서 정수로 변환한 다음 반환한다. 정수로 변환이 실패하는 경우 0을 반환한다.
	 * @param text 숫자로만 구성된 텍스트
	 * @return 정수, 변환에 실패하는 0이 반환된다.
	 */
	public static int stringToInt(String text) {
		return StringUtils.stringToInt(text, 0);
	}
	
	/**
	 * 텍스트를 전달받아서 정수로 변환한 다음 반환한다. 정수로 변환이 실패하는 경우 전달받은 기본값을 반환한다.
	 * @param text 숫자로만 구성된 텍스트
	 * @param defaultValue 기본값
	 * @return 정수, 변환에 실패하는 기본값이 반환된다.
	 */
	public static int stringToInt(String text, int defaultValue) {
		if (text == null) {
			return defaultValue;
		}
		
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException ex) {
			return defaultValue;
		}
	}
	
	/**
	 * Date를 "2022년 11월 10일" 형식의 문자열로 변환해서 반환한다.
	 * @param date 날짜
	 * @return 년월일 형식의 문자열, 날짜가 null이면 빈 문자열을 반환한다.
	 */
	public static String dateToText(Date date) {
		if (date == null) {
			return "";
		}
		return simpleDateFormat.format(date);
	}	
	
	/**
	 * yyyy-MM-dd 형식의 문자열을 Date로 변환해서 반환한다.
	 * @param text yyyy-mm-dd 형식의 문자열
	 * @return 날짜
	 */
	public static Date textToDate(String text) {
		if (text == null) {
			throw new RuntimeException("null은 날짜로 변환할 수 없습니다.");
		}
		if (text.isBlank()) {
			throw new RuntimeException("빈 문자열은 날짜로 변환할 수 없습니다.");
		}
		
		try {
			return simpleDateFormat2.parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("유효한 날짜 형식이 아닙니다.");
		}
	}
	
	/**
	 * 정수를 3자리마다 ,가 포함된 문자열로 변환해서 반환한다.
	 * @param number 정수
	 * @return ,가 포함된 문자열
	 */
	public static String numberToText(long number) {
		return decimalFormat.format(number);
	}
	
	/**
	 * 실수를 소숫점 3번째 자리에서 반올림하고, 정수부는 3자리마다 ,가 포함된 문자열로 변환해서 반환한다.
	 * @param number 실수
	 * @return ,가 포함된 문자열(소숫점 3번째 자리에서 반올림한다.)
	 */
	public static String numberToText(double number) {
		return decimalFormat.format(number);
	}
}
