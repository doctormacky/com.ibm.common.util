package com.ibm.common.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public final class TimeUtil {

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE      = "yyyy-MM-dd";
    public static final String TIME      = "HH:mm:ss";

    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT      = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat TIME_FORMAT      = new SimpleDateFormat("HH:mm:ss");

    /**

     * 获取字符串日期

     * @return

     */
    public static String getDate() {
        return DATE_FORMAT.format(new Date());
    }

    /**

     * 格式字符串 日期加时间

     * @return

     */
    public static String getDateTime() {
        return DATE_TIME_FORMAT.format(new Date());
    }

    /**

     * 格式化字符串时间

     * @param date

     * @param format

     * @return

     */
    public static Date parse(String date, String format) {
        try {
            if (DATE.equals(format)) {
                return DATE_FORMAT.parse(date);
            }
            if (DATE_TIME.equals(format)) {
                return DATE_TIME_FORMAT.parse(date);
            }
            if (TIME.equals(format)) {
                return TIME_FORMAT.parse(date);
            }
            SimpleDateFormat dateformat = new SimpleDateFormat(format);
            return dateformat.parse(format);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**

     * 格式化时间

     * @param date

     * @param format

     * @return

     */
    public static String String(Date date, String format) {
        try {
            if (DATE.equals(format)) {
                return DATE_FORMAT.format(date);
            }
            if (DATE_TIME.equals(format)) {
                return DATE_TIME_FORMAT.format(date);
            }
            if (TIME.equals(format)) {
                return TIME_FORMAT.format(date);
            }
            SimpleDateFormat dateformat = new SimpleDateFormat(format);
            return dateformat.format(format);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static TimeUtil newInstance() {
        return new TimeUtil();
    }

    /**

     * The internal calendar used in most of the methods.

     */
    private Calendar calendar = null;

    /**

     * Default constructor. This constructor does not use any

     * parameters. A GregorianCalendar is used as the internal calendar and the

     * default time zone and locale are used.

     */
    public TimeUtil() {
        setCalendar(new GregorianCalendar());
    }

    /**

     * This constructor takes as calendar

     * as input. This constructor can be used when you do not want a

     * GregorianCalendar to created by default.

     * 

     * @param calendar

     *            The calendar to be used internally by many of the methods in

     *            this class.

     */
    public TimeUtil(final Calendar calendar) {
        setCalendar(calendar);
    }

    /**

     * This constructor takes a locale as input and can

     * be used for processing involving other locations other than the default.

     * A GregorianCalendar is used as the internal calendar and is created using

     * the input locale.

     * 

     * @param locale

     *            The input time zone.

     */
    public TimeUtil(final Locale locale) {
        setCalendar(new GregorianCalendar(locale));
    }

    /**

     * This constructor of the constructor takes a time

     * zone as input. A GregorianCalendar is used as the internal calendar and

     * is created using the input time zone.

     * 

     * @param timeZone

     *            The input time zone.

     */
    public TimeUtil(final TimeZone timeZone) {
        setCalendar(new GregorianCalendar(timeZone));
    }

    /**

     * This constructor takes a time zone as input and

     * can be used for advanced processing involving such things as daylight

     * savings time. This constructor also takes a locale as input and can be

     * used for processing involving other locations other than the default. A

     * GregorianCalendar is used as the internal calendar and is created using

     * the input tiem zone and locale.

     * 

     * @param timeZone

     *            The input time zone.

     * @param locale

     *            The input time zone.

     */
    public TimeUtil(final TimeZone timeZone, final Locale locale) {
        setCalendar(new GregorianCalendar(timeZone, locale));
    }

    /**

     * Returns a new date that is a specified number of days from a given date.

     * 

     * @param inputDate

     *            The original date to add days to.

     * @param amount

     *            A positive, zero or negative number of days to add to the

     *            original date.

     * 

     * @return A new date that is a specified number of days from a given date.

     */
    public Date addDay(final Date inputDate, final int amount) {
        if (inputDate == null) {
            return null;
        }
        getCalendar().setTime(inputDate);
        getCalendar().add(Calendar.DAY_OF_MONTH, amount);
        return getCalendar().getTime();
    }

    /**

     * Returns a new date that is a specified number of hours from a given date.

     * 

     * @param inputDate

     *            The original date to add hours to.

     * @param amount

     *            A positive, zero or negative number of hours to add to the

     *            original date.

     * 

     * @return A new date that is a specified number of hours from a given date.

     */
    public Date addHour(final Date inputDate, final int amount) {
        if (inputDate == null) {
            return null;
        }
        getCalendar().setTime(inputDate);
        getCalendar().add(Calendar.HOUR, amount);
        return getCalendar().getTime();
    }

    /**

     * Returns a new date that is a specified number of milliseconds from a

     * given date.

     * 

     * @param inputDate

     *            The original date to add milliseconds to.

     * @param amount

     *            A positive, zero or negative number of milliseconds to add to

     *            the original date.

     * 

     * @return A new date that is a specified number of milliseconds from a

     *         given date.

     */
    public Date addMillisecond(final Date inputDate, final int amount) {
        if (inputDate == null) {
            return null;
        }
        getCalendar().setTime(inputDate);
        getCalendar().add(Calendar.MILLISECOND, amount);
        return getCalendar().getTime();
    }

    /**

     * Returns a new date that is a specified number of minutes from a given

     * date.

     * 

     * @param inputDate

     *            The original date to add minutes to.

     * @param amount

     *            A positive, zero or negative number of minutes to add to the

     *            original date.

     * 

     * @return A new date that is a specified number of minutes from a given

     *         date.

     */
    public Date addMinute(final Date inputDate, final int amount) {
        if (inputDate == null) {
            return null;
        }
        getCalendar().setTime(inputDate);
        getCalendar().add(Calendar.MINUTE, amount);
        return getCalendar().getTime();
    }

    /**

     * Returns a new date that is a specified number of months from a given

     * date.

     * 

     * @param inputDate

     *            The original date to add months to.

     * @param amount

     *            A positive, zero or negative number of months to add to the

     *            original date.

     * 

     * @return A new date that is a specified number of months from a given

     *         date.

     */
    public Date addMonth(final Date inputDate, final int amount) {
        if (inputDate == null) {
            return null;
        }
        getCalendar().setTime(inputDate);
        getCalendar().add(Calendar.MONTH, amount);
        return getCalendar().getTime();
    }

    /**

     * Returns a new date that is a specified number of seconds from a given

     * date.

     * 

     * @param inputDate

     *            The original date to add seconds to.

     * @param amount

     *            A positive, zero or negative number of seconds to add to the

     *            original date.

     * 

     * @return A new date that is a specified number of seconds from a given

     *         date.

     */
    public Date addSecond(final Date inputDate, final int amount) {
        if (inputDate == null) {
            return null;
        }
        getCalendar().setTime(inputDate);
        getCalendar().add(Calendar.SECOND, amount);
        return getCalendar().getTime();
    }

    /**

     * Returns a new date that is a specified number of years from a given date.

     * 

     * @param inputDate

     *            The original date to add years to.

     * @param amount

     *            A positive, zero or negative number of years to add to the

     *            original date.

     * 

     * @return A new date that is a specified number of years from a given date.

     */
    public Date addYear(final Date inputDate, final int amount) {
        if (inputDate == null) {
            return null;
        }
        getCalendar().setTime(inputDate);
        getCalendar().add(Calendar.YEAR, amount);
        return getCalendar().getTime();
    }

    /**

     * Converts a date to a string of a specified format.

     * 

     * @param inputDate

     *            The original date to convert to a string.

     * @param dateFormat

     *            The format to convert the date to. See the official Javadoc

     *            for the Java class SimpleDateFormat for a description of

     *            format syntax.

     * 

     * @return A string representing the original date in the specified format.

     */
    public String convertDateToString(final Date inputDate, final String dateFormat) {
        final SimpleDateFormat realDateFormat = new SimpleDateFormat(dateFormat);
        String outputDate = null;

        if (inputDate != null) {
            outputDate = realDateFormat.format(inputDate);
        }

        return outputDate;
    }

    /**

     * Converts a string to a date.

     * 

     * @param inputDate

     *            The orignal date in string format.

     * @param dateFormat

     *            The current format of the string date. See the official

     *            Javadoc for the Java class SimpleDateFormat for a description

     *            of format syntax.

     * 

     * @return A date created from the input string.

     * 

     * @exception IllegalArgumentException

     *                Input data is invalid.

     */
    public Date convertStringToDate(String inputDate, final String dateFormat) {
        final SimpleDateFormat realDateFormat = new SimpleDateFormat(dateFormat);
        Date outputDate = null;
        String newString = null;

        if (inputDate != null) {
            outputDate = realDateFormat.parse(inputDate, new ParsePosition(0));
            newString = convertDateToString(outputDate, dateFormat);

            if (!newString.equals(inputDate)) {
                IllegalArgumentException exception = new IllegalArgumentException(
                    "Data in string " + "is not a valid date.");

                throw exception;
            }
        }

        return outputDate;
    }

    /**

     * Creates a new date from a year, month and day.

     * 

     * @param inputYear

     *            The input year.

     * @param inputMonth

     *            The input month.

     * @param inputDay

     *            The input day.

     * 

     * @return The new date created from the input year, month and day.

     */
    public Date createDate(final int inputYear, final int inputMonth, final int inputDay) {
        getCalendar().clear();
        getCalendar().set(inputYear, inputMonth, inputDay);
        return getCalendar().getTime();
    }

    /**

     * Creates a new date from a year, month, day, hour, minute and second.

     * 

     * @param inputYear

     *            The input year.

     * @param inputMonth

     *            The input month.

     * @param inputDay

     *            The input day.

     * @param inputHour

     *            The input hour of the day. This should be from 0 to 23.

     * @param inputMinute

     *            The input minute.

     * @param inputSecond

     *            The input second.

     * 

     * @return The new date created from the input year, month, day, hour,

     *         minute and second.

     */
    public Date createDate(final int inputYear, final int inputMonth, final int inputDay,
                           final int inputHour, final int inputMinute, final int inputSecond) {
        getCalendar().clear();
        getCalendar().set(inputYear, inputMonth, inputDay, inputHour, inputMinute, inputSecond);
        return getCalendar().getTime();
    }

    /**

     * Creates a new date from a year, month, day, hour, minute, second and

     * millisecond.

     * 

     * @param inputYear

     *            The input year.

     * @param inputMonth

     *            The input month.

     * @param inputDay

     *            The input day.

     * @param inputHour

     *            The input hour of the day. This should be from 0 to 23.

     * @param inputMinute

     *            The input minute.

     * @param inputSecond

     *            The input second.

     * 

     * @return The new date created from the input year, month, day, hour,

     *         minute, second and millisecond.

     */
    public Date createDate(final int inputYear, final int inputMonth, final int inputDay,
                           final int inputHour, final int inputMinute, final int inputSecond,
                           final int inputMillisecond) {
        getCalendar().clear();
        getCalendar().set(inputYear, inputMonth, inputDay, inputHour, inputMinute, inputSecond);
        getCalendar().set(Calendar.MILLISECOND, inputMillisecond);
        return getCalendar().getTime();
    }

    /**

     * Returns the internal calendar used by this object.

     * 

     * @return The internal calendar used by this object.

     */
    private Calendar getCalendar() {
        return calendar;
    }

    /**

     * Returns the number of calendar days date2 is after date1. If date2 is

     * before date1, then a negative number is returned. This method does not

     * take into account time. Therefore, the difference between 2001-04-31

     * 11:23:54 PM and 2001-05-01 00:01:12 AM is one day.

     * 

     * @param date1

     *            The first date in the range.

     * @param date2

     *            The second date in the range.

     * 

     * @return The number of days date2 is after date1.

     */
    public int getCalendarDaysAfter(final Date date1, final Date date2) {
        Date newDate1 = createDate(getYear(date1), getMonth(date1), getDayOfMonth(date1));
        Date newDate2 = createDate(getYear(date2), getMonth(date2), getDayOfMonth(date2));
        int daysAfter = (new Double(getDaysAfter(newDate1, newDate2))).intValue();

        return daysAfter;
    }

    /**

     * Returns the number of calendar months date2 is after date1. If date2 is

     * before date1, then a negative number is returned. This method does not

     * take into account time or days. Therefore, the difference between 2001-04-31

     * 11:23:54 PM and 2001-05-01 00:01:12 AM is one month.

     * 

     * @param date1

     *            The first date in the range.

     * @param date2

     *            The second date in the range.

     * 

     * @return The number of months date2 is after date1.

     */
    public int getCalendarMonthsAfter(final Date date1, final Date date2) {
        int month1 = getMonth(date1);
        int month2 = getMonth(date2);
        int year1 = getYear(date1);
        int year2 = getYear(date2);
        int difference = 0;

        if (year1 == year2) {
            difference = month2 - month1;
        } else if (year1 < year2) {
            int yearCtr = year2 - 1;

            difference = month2;

            while (yearCtr > year1) {
                difference = difference + 12;

                yearCtr--;
            }

            difference = difference + 12 - month1;
        } else if (year1 > year2) {
            int yearCtr = year1 - 1;

            difference = month1;

            while (yearCtr > year2) {
                difference = difference + 12;

                yearCtr--;
            }

            difference = difference + 12 - month2;

            difference = difference * -1;
        }

        return difference;
    }

    /**

     * Returns the number of calendar years date2 is after date1. If date2 is

     * before date1, then a negative number is returned. This method does not

     * take into account time, days or months. Therefore, the difference between 2000-12-31

     * 11:23:54 PM and 2001-01-01 00:01:12 AM is one year.

     * 

     * @param date1

     *            The first date in the range.

     * @param date2

     *            The second date in the range.

     * 

     * @return The number of years date2 is after date1.

     */
    public int getCalendarYearsAfter(final Date date1, final Date date2) {
        int year1 = getYear(date1);
        int year2 = getYear(date2);
        int difference = year2 - year1;
        return difference;
    }

    /**

     * Returns the current date and time.

     * 

     * @return A date representing the current date and time.

     */
    public Date getCurrentDate() {
        final GregorianCalendar _calendar = new GregorianCalendar();
        return _calendar.getTime();
    }

    /**

     * Returns the abbreviation of the day from the input date. Abbreviations

     * are three letters in length. For example the abbreviation for Monday is

     * "Mon" and Tuesday is "Tue".

     * 

     * @param inputDate

     *            The date for which the day abbreviation is requested.

     * 

     * @return The day abbreviation for the input date.

     */
    public String getDayAbrv(final Date inputDate) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE");
        final String dayAbrv = dateFormat.format(inputDate);
        return dayAbrv;
    }

    /**

     * Returns the day name from the input date, such as "Monday" or "Tuesday".

     * 

     * @param inputDate

     *            The date for which the day name is requested.

     * 

     * @return The day name for the input date.

     */
    public String getDayName(final Date inputDate) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        final String dayName = dateFormat.format(inputDate);
        return dayName;
    }

    /**

     * Returns the day of month from the input date.

     * 

     * @param inputDate

     *            The date for which the day of month is requested.

     * 

     * @return The day of month for the input date.

     */
    public int getDayOfMonth(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    /**

     * Returns the day of week from the input date. The days of the week begin

     * with 1 which represents Sunday. Monday is represented by 2, Tuesday is

     * represented by 3 and so on.

     * 

     * @param inputDate

     *            The date for which the day of week is requested.

     * 

     * @return The day of week for the input date.

     */
    public int getDayOfWeek(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.DAY_OF_WEEK);
    }

    /**

     * Returns the day of year from the input date.

     * 

     * @param inputDate

     *            The date for which the day of year is requested.

     * 

     * @return The day of year for the input date.

     */
    public int getDayOfYear(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.DAY_OF_YEAR);
    }

    /**

     * Returns the number of days date2 is after date1. If date2 is before

     * date1, then a negative number is returned. The number returned is based

     * on the number of milliseconds each date is from the epoch (January 1,

     * 1970, 00:00:00 GMT).

     * 

     * @param date1

     *            The first date in the range.

     * @param date2

     *            The second date in the range.

     * 

     * @return The number of days date2 is after date1.

     */
    public double getDaysAfter(final Date date1, final Date date2) {
        final double amount1 = date1.getTime();
        final double amount2 = date2.getTime();
        final double difference = (amount2 - amount1) / 86400000;
        return difference;
    }

    /**

     * Returns the first day of the week. In the US, the first day of the week

     * is Sunday and in France it is Monday.

     * 

     * @param inputDate

     *            The date for which the first day of the week is requested.

     * 

     * @return The first day of the week.

     */
    public int getFirstDayOfWeek(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().getFirstDayOfWeek();
    }

    /**

     * Returns the hour from the input date. This hour represents the hour of

     * the morning or afternoon.

     * 

     * @param inputDate

     *            The date for which the hour is requested.

     * 

     * @return The hour from the input date.

     */
    public int getHour(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.HOUR);
    }

    /**

     * Returns the hour of day from the input date. This hour is the same as a

     * military time hour.

     * 

     * @param inputDate

     *            The date for which the hour of day is requested.

     * 

     * @return The hour of day from the input date.

     */
    public int getHourOfDay(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.HOUR_OF_DAY);
    }

    /**

     * Returns the number of hours date2 is after date1. If date2 is before

     * date1, then a negative number is returned. The number returned is based

     * on the number of milliseconds each date is from the epoch (January 1,

     * 1970, 00:00:00 GMT).

     * 

     * @param date1

     *            The first date in the range.

     * @param date2

     *            The second date in the range.

     * 

     * @return The number of hours date2 is after date1.

     */
    public double getHoursAfter(final Date date1, final Date date2) {
        final double amount1 = date1.getTime();
        final double amount2 = date2.getTime();
        final double difference = (amount2 - amount1) / 3600000;
        return difference;
    }

    /**

     * This method will return a date which represents the last date of the

     * month in the input date.

     * 

     * @param inputDate

     *            The inputDate the date to obtain the last date of month for.

     * 

     * @return A date which represents the last date of the month

     */
    public Date getLastDateOfMonth(final Date inputDate) {
        // Advance the input date to the first day of the next month.

        final Date nextMonthDate = addMonth(inputDate, 1);
        getCalendar().setTime(nextMonthDate);
        getCalendar().set(Calendar.DAY_OF_MONTH, 1);

        // Subtract one day from the first day of the next month.

        // This will get us the last date of the current month.

        final Date lastDayOfMonth = addDay(getCalendar().getTime(), -1);

        return lastDayOfMonth;
    }

    /**

     * Returns the millisecond from the input date.

     * 

     * @param inputDate

     *            The date for which the millisecond is requested.

     * 

     * @return The millisecond from the input date.

     */
    public int getMillisecond(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.MILLISECOND);
    }

    /**

     * Returns the number of milliseconds date2 is after date1. If date2 is

     * before date1, then a negative number is returned. The number returned is

     * based on the number of milliseconds each date is from the epoch (January

     * 1, 1970, 00:00:00 GMT).

     * 

     * @param date1

     *            The first date in the range.

     * @param date2

     *            The second date in the range.

     * 

     * @return The number of milliseconds date2 is after date1.

     */
    public long getMillisecondsAfter(final Date date1, final Date date2) {
        final long amount1 = date1.getTime();
        final long amount2 = date2.getTime();
        final long difference = amount2 - amount1;
        return difference;
    }

    /**

     * Returns the minute from the input date.

     * 

     * @param inputDate

     *            The date for which the minute is requested.

     * 

     * @return The minute from the input date.

     */
    public int getMinute(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.MINUTE);
    }

    /**

     * Returns the number of minutes date2 is after date1. If date2 is before

     * date1, then a negative number is returned. The number returned is based

     * on the number of milliseconds each date is from the epoch (January 1,

     * 1970, 00:00:00 GMT).

     * 

     * @param date1

     *            The first date in the range.

     * @param date2

     *            The second date in the range.

     * 

     * @return The number of minutes date2 is after date1.

     */
    public double getMinutesAfter(final Date date1, final Date date2) {
        final double amount1 = date1.getTime();
        final double amount2 = date2.getTime();
        final double difference = (amount2 - amount1) / 60000;
        return difference;
    }

    /**

     * Returns the month from the input date.

     * 

     * @param inputDate

     *            The date for which the month is requested.

     * 

     * @return The month from the input date.

     */
    public int getMonth(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.MONTH);
    }

    /**

     * Returns the month abbreviation from the input date. Abbreviations are

     * three letters in length. For example the abbreviation for January is

     * "Jan" and February is "Feb".

     * 

     * @param inputDate

     *            The date for which the month abbreviation is requested.

     * 

     * @return The month abbreviation from the input date.

     */
    public String getMonthAbrv(final Date inputDate) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM");
        final String monthAbrv = dateFormat.format(inputDate);
        return monthAbrv;
    }

    /**

     * Returns the month name from the input date, such as "January" or

     * "February".

     * 

     * @param inputDate

     *            The date for which the month name is requested.

     * 

     * @return The month name from the input date.

     */
    public String getMonthName(final Date inputDate) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
        final String monthName = dateFormat.format(inputDate);
        return monthName;
    }

    /**

     * Returns the second from the input date.

     * 

     * @param inputDate

     *            The date for which the second is requested.

     * 

     * @return The second from the input date.

     */
    public int getSecond(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.SECOND);
    }

    /**

     * Returns the number of seconds date2 is after date1. If date2 is before

     * date1, then a negative number is returned. The number returned is based

     * on the number of milliseconds each date is from the epoch (January 1,

     * 1970, 00:00:00 GMT).

     * 

     * @param date1

     *            The first date in the range.

     * @param date2

     *            The second date in the range.

     * 

     * @return The number of seconds date2 is after date1.

     */
    public double getSecondsAfter(final Date date1, final Date date2) {
        final double amount1 = date1.getTime();
        final double amount2 = date2.getTime();
        final double difference = (amount2 - amount1) / 1000;
        return difference;
    }

    /**

     * Returns the time zone offset from the input date. The offset represents

     * the milliseconds from Greenwich Mean Time (GMT).

     * 

     * @param inputDate

     *            The date for which the time zone offset is requested.

     * 

     * @return The time zone offset from the input date.

     */
    public int getTimeZoneOffset(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.ZONE_OFFSET);
    }

    /**

     * Returns the week of month from the input date.

     * 

     * @param inputDate

     *            The date for which the week of month is requested.

     * 

     * @return The week of month from the input date.

     */
    public int getWeekOfMonth(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.WEEK_OF_MONTH);
    }

    /**

     * Returns the week of year from the input date.

     * 

     * @param inputDate

     *            The date for which the week of year is requested.

     * 

     * @return The week of year from the input date.

     */
    public int getWeekOfYear(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.WEEK_OF_YEAR);
    }

    /**

     * Returns the year from the input date.

     * 

     * @param inputDate

     *            The date for which the year is requested.

     * 

     * @return The year from the input date.

     */
    public int getYear(final Date inputDate) {
        getCalendar().setTime(inputDate);
        return getCalendar().get(Calendar.YEAR);
    }

    /**

     * Indicates if date1 is after date2.

     * 

     * @param date1

     *            The first date to be compared.

     * @param date2

     *            The second date to be compared.

     * 

     * @return True is date1 is after date2, otherwise false.

     */
    public boolean isAfter(final Date date1, final Date date2) {
        boolean rtnValue = false;

        if (date1.getTime() > date2.getTime()) {
            rtnValue = true;
        }

        return rtnValue;
    }

    /**

     * Indicates if the time in the input date is in the AM or not.

     * 

     * @param inputDate

     *            The date to evaluate.

     * 

     * @return True is time of date is in the AM, otherwise false.

     */
    public boolean isAM(final Date inputDate) {
        boolean rtnValue;

        getCalendar().setTime(inputDate);

        switch (getCalendar().get(Calendar.AM_PM)) {
            case Calendar.AM:
                rtnValue = true;
                break;
            default:
                rtnValue = false;
        }

        return rtnValue;
    }

    /**

     * Indicates if date1 is before date2.

     * 

     * @param date1

     *            The first date to be compared.

     * @param date2

     *            The second date to be compared.

     * 

     * @return True is date1 is before date2, otherwise false.

     */
    public boolean isBefore(final Date date1, final Date date2) {
        boolean rtnValue = false;

        if (date1.getTime() < date2.getTime()) {
            rtnValue = true;
        }

        return rtnValue;
    }

    /**

     * Indicates if date1 is equal to date2.

     * 

     * @param date1

     *            The first date to be compared.

     * @param date2

     *            The second date to be compared.

     * 

     * @return True is date1 is equal to date2, otherwise false.

     */
    public boolean isEqualTo(final Date date1, final Date date2) {
        boolean rtnValue = false;

        if (date1.getTime() == date2.getTime()) {
            rtnValue = true;
        }

        return rtnValue;
    }

    /**

     * Indicates if the year is a leap year or not.

     * 

     * @param inputDate

     *            The date to evaluate.

     * 

     * @return True is year is a leap year, otherwise false.

     */
    public boolean isLeapYear(final Date inputDate) {
        boolean rtnValue = false;

        getCalendar().setTime(inputDate);
        rtnValue = ((GregorianCalendar) getCalendar()).isLeapYear(getYear(inputDate));

        return rtnValue;
    }

    /**

     * Indicates if the time in the input date is in the PM or not.

     * 

     * @param inputDate

     *            The date to evaluate.

     * 

     * @return True is time of date is in the PM, otherwise false.

     */
    public boolean isPM(final Date inputDate) {
        boolean rtnValue;

        getCalendar().setTime(inputDate);

        switch (getCalendar().get(Calendar.AM_PM)) {
            case Calendar.PM:
                rtnValue = true;
                break;
            default:
                rtnValue = false;
        }

        return rtnValue;
    }

    /**

     * Sets the internal calendar to be used by this object.

     * 

     * @param calendar

     *            The internal calendar to be used by this object.

     */
    private void setCalendar(final Calendar calendar) {
        this.calendar = calendar;
    }

    /**

     * Sets the day of month on the input date.

     * 

     * @param inputDate

     *            The date for which to set the day of month.

     * @param dayOfMonth

     *            The day of month to set on the input date.

     * 

     * @return The new date with the new day of month value.

     */
    public Date setDayOfMonth(final Date inputDate, final int dayOfMonth) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return getCalendar().getTime();
    }

    /**

     * Sets the day of week on the input date.

     * 

     * @param inputDate

     *            The date for which to set the day of week.

     * @param dayOfWeek

     *            The day of week to set on the input date.

     * 

     * @return The new date with the new day of week value.

     */
    public Date setDayOfWeek(final Date inputDate, final int dayOfWeek) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.DAY_OF_WEEK, dayOfWeek);
        return getCalendar().getTime();
    }

    /**

     * Sets the day of year on the input date.

     * 

     * @param inputDate

     *            The date for which to set the day of year.

     * @param dayOfYear

     *            The day of year to set on the input date.

     * 

     * @return The new date with the new day of year value.

     */
    public Date setDayOfYear(final Date inputDate, final int dayOfYear) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.DAY_OF_YEAR, dayOfYear);
        return getCalendar().getTime();
    }

    /**

     * Sets the hour on the input date.

     * 

     * @param inputDate

     *            The date for which to set the hour.

     * @param hour

     *            The hour to set on the input date.

     * 

     * @return The new date with the new hour value.

     */
    public Date setHour(final Date inputDate, final int hour) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.HOUR, hour);
        return getCalendar().getTime();
    }

    /**

     * Sets the hour of day on the input date.

     * 

     * @param inputDate

     *            The date for which to set the hour of day.

     * @param hourOfDay

     *            The hour to set on the input date.

     * 

     * @return The new date with the new hour of day value.

     */
    public Date setHourOfDay(final Date inputDate, final int hourOfDay) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.HOUR_OF_DAY, hourOfDay);
        return getCalendar().getTime();
    }

    /**

     * Sets the millisecond on the input date.

     * 

     * @param inputDate

     *            The date for which to set the millisecond.

     * @param millisecond

     *            The millisecond to set on the input date.

     * 

     * @return The new date with the new millisecond value.

     */
    public Date setMillisecond(final Date inputDate, final int millisecond) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.MILLISECOND, millisecond);
        return getCalendar().getTime();
    }

    /**

     * Sets the minute on the input date.

     * 

     * @param inputDate

     *            The date for which to set the minute.

     * @param minute

     *            The minute to set on the input date.

     * 

     * @return The new date with the new minute value.

     */
    public Date setMinute(final Date inputDate, final int minute) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.MINUTE, minute);
        return getCalendar().getTime();
    }

    /**

     * Sets the month on the input date.

     * 

     * @param inputDate

     *            The date for which to set the month.

     * @param month

     *            The month to set on the input date.

     * 

     * @return The new date with the new month value.

     */
    public Date setMonth(final Date inputDate, final int month) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.MONTH, month);
        return getCalendar().getTime();
    }

    /**

     * Sets the second on the input date.

     * 

     * @param inputDate

     *            The date for which to set the second.

     * @param second

     *            The second to set on the input date.

     * 

     * @return The new date with the new second value.

     */
    public Date setSecond(final Date inputDate, final int second) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.SECOND, second);
        return getCalendar().getTime();
    }

    /**

     * Sets the week of month on the input date.

     * 

     * @param inputDate

     *            The date for which to set the week of month.

     * @param weekOfMonth

     *            The week of month to set on the input date.

     * 

     * @return The new date with the new week of month value.

     */
    public Date setWeekOfMonth(final Date inputDate, final int weekOfMonth) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.WEEK_OF_MONTH, weekOfMonth);
        return getCalendar().getTime();
    }

    /**

     * Sets the week of year on the input date.

     * 

     * @param inputDate

     *            The date for which to set the week of year.

     * @param weekOfYear

     *            The week of year to set on the input date.

     * 

     * @return The new date with the new week of year value.

     */
    public Date setWeekOfYear(final Date inputDate, final int weekOfYear) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.WEEK_OF_YEAR, weekOfYear);
        return getCalendar().getTime();
    }

    /**

     * Sets the year on the input date.

     * 

     * @param inputDate

     *            The date for which to set the year.

     * @param year

     *            The month to set on the input date.

     * 

     * @return The new date with the new year value.

     */
    public Date setYear(final Date inputDate, final int year) {
        getCalendar().setTime(inputDate);
        getCalendar().set(Calendar.YEAR, year);
        return getCalendar().getTime();
    }
}

