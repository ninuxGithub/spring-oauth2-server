package com.hundsun.oauth.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {
	private static final String DEFAULT_DATE_FORMATER = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static LocalDateTime now() {
		return LocalDateTime.now();
	}

	public static String toDateTime(LocalDateTime dateTime, String pattern) {
		return dateTime.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
	}

	/**
	 * 采用默认的 pattern
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String toDateTime(LocalDateTime dateTime) {
		return toDateTime(dateTime, DEFAULT_DATE_FORMATER);
	}

	public static String toDateText(LocalDate date, String pattern) {
		if (date == null || pattern == null) {
			return null;
		}
		return date.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
	}

}
