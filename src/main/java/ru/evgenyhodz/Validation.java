package ru.evgenyhodz;

import java.util.Calendar;
import java.util.Date;

/**
 * Класс валидации. Проверяем даты.
 * <p>
 * Полный месяц:
 * месяц с первого числа (включительно) до последнего дня месяца (включительно).
 * Например с 1 до 31.
 * <p>
 * Неполный месяц - число месяца (либо оба числа) не равны первому дню, либо
 * последнему дню. Например: с 3 по 31, с 1 числа по 25, со 2-го по 20 число.
 * <p>
 * Validation class. Checking the dates.
 * <p>
 * Full month: a month from the first day (inclusive) to the last day of the month
 * (inclusive). For example, from 1 to 31.
 * <p>
 * Incomplete (not full) month - day of month not equal to first day of month and/or
 * the last day of month. For example, from 3 to 31, from 1 t0 25, from 2 to 20 day of month.
 *
 * @author Evgeny Khodzitskiy (evgeny.hodz@gmail.com)
 * @since 05.02.2017
 */
class Validation {
    /**
     * Ситуация 0 - когда данные еще не введены в программу. Далее переменная может приравнятся
     * к значениям от 1 до 4, в зависимости от результатов анализа дат отчетного периода.
     * <p>
     * Ситуации:
     * 1) первый месяц полный, последний неполный
     * 2) первый месяц неполный, последний полный
     * 3) оба месяца неполные
     * 4) оба месяца полные.
     * <p>
     * Situation 0 - when data is not entered yet. After that variable can get value from 1 to 4,
     * depends on date analysis results.
     * <p>
     * Situations:
     * 1. First month is full, last month is not
     * 2. Last month is full, first month is not
     * 3. Both months are incomplete.
     * 4. Both months are full.
     */
    private int situation = 0;
    /**
     * Ссылка на дату начала отчетного периода.
     * <p>
     * Ref to date of the beginning of billing period.
     */
    private Calendar started;
    /**
     * Ссылка на дату конца отчетного периода.
     * <p>
     * Ref to date of the end of billing period.
     */
    private Calendar ended;

    /**
     * Геттер/Getter.
     *
     * @return - дату начала отчетного периода/beginning of billing period.
     */
    Calendar getStarted() {
        return started;
    }

    /**
     * Геттер/Getter.
     *
     * @return - дату конца отчетного периода/end of billing period.
     */
    Calendar getEnded() {
        return ended;
    }

    /**
     * Геттер/Getter.
     *
     * @return - номер ситуации/number of situation.
     */
    int getSituation() {
        return situation;
    }

    /**
     * Метод проверяет, какая у нас ситуация, исходя из введенных дат.
     * Используются 4 метода для каждой из ситуаций.
     * <p>
     * Checks current situation using four special methods for each situation.
     *
     * @param start - начало расчетного периода/beginning of billing period.
     * @param end   - конец расчетного периода/end of billing period.
     * @return - номер ситуации записывается в поле situation. По умолчанию = 0/
     * number of situation writes to field 'situation'. Default value = 0.
     */
    int check(Date start, Date end) {
        if (start.before(end)) {
            checkOnlyStartFull(start, end);
            if (situation == 0) {
                checkOnlyEndFull(start, end);
            }
            if (situation == 0) {
                checkBothNotFull(start, end);
            }
            if (situation == 0) {
                checkBothFull(start, end);
            }
        }
        return situation;
    }

    /**
     * Метод проверяет, является ли ситуация номером 1, исходя из введенных дат.
     * Ситуации:
     * 1) первый месяц полный, последний неполный
     * 2) первый месяц неполный, последний полный
     * 3) оба месяца неполные
     * 4) оба месяца полные.
     * <p>
     * Checks, if current situation = 1.
     * <p>
     * Situations:
     * 1. First month is full, last month is not
     * 2. Last month is full, first month is not
     * 3. Both months are incomplete.
     * 4. Both months are full.
     *
     * @param start - начало расчетного периода/beginning of billing period.
     * @param end   - конец расчетного периода/end of billing period.
     */
    private void checkOnlyStartFull(Date start, Date end) {
        createCalDates(start, end);
        int lastDay = ended.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (started.get(Calendar.DAY_OF_MONTH) == 1
                && ended.get(Calendar.DAY_OF_MONTH) != lastDay) {
            situation = 1;
        }
    }

    /**
     * Метод проверяет, является ли ситуация номером 2, исходя из введенных дат.
     * Ситуации:
     * 1) первый месяц полный, последний неполный
     * 2) первый месяц неполный, последний полный
     * 3) оба месяца неполные
     * 4) оба месяца полные.
     * <p>
     * Checks, if current situation = 2.
     * <p>
     * Situations:
     * 1. First month is full, last month is not
     * 2. Last month is full, first month is not
     * 3. Both months are incomplete.
     * 4. Both months are full.
     *
     * @param start - начало расчетного периода/beginning of billing period.
     * @param end   - конец расчетного периода/end of billing period.
     */
    private void checkOnlyEndFull(Date start, Date end) {
        createCalDates(start, end);
        int lastDay = ended.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (started.get(Calendar.DAY_OF_MONTH) != 1
                && ended.get(Calendar.DAY_OF_MONTH) == lastDay
                && started.get(Calendar.MONTH) != ended.get(Calendar.MONTH)) {
            situation = 2;
        }
    }

    /**
     * Метод проверяет, является ли ситуация номером 3, исходя из введенных дат.
     * Ситуации:
     * 1) первый месяц полный, последний неполный
     * 2) первый месяц неполный, последний полный
     * 3) оба месяца неполные
     * 4) оба месяца полные.
     * <p>
     * Checks, if current situation = 3.
     * <p>
     * Situations:
     * 1. First month is full, last month is not
     * 2. Last month is full, first month is not
     * 3. Both months are incomplete.
     * 4. Both months are full.
     *
     * @param start - начало расчетного периода/beginning of billing period.
     * @param end   - конец расчетного периода/end of billing period.
     */
    private void checkBothNotFull(Date start, Date end) {
        createCalDates(start, end);
        int lastDay = ended.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (started.get(Calendar.DAY_OF_MONTH) != 1
                && ended.get(Calendar.DAY_OF_MONTH) != lastDay) {
            situation = 3;
        } else if (started.get(Calendar.DAY_OF_MONTH) != 1
                && ended.get(Calendar.DAY_OF_MONTH) == lastDay
                && started.get(Calendar.MONTH) == ended.get(Calendar.MONTH)) {
            situation = 3;
        } else if (started.get(Calendar.DAY_OF_MONTH) == 1
                && ended.get(Calendar.DAY_OF_MONTH) != lastDay
                && started.get(Calendar.MONTH) == ended.get(Calendar.MONTH)) {
            situation = 3;
        }
    }

    /**
     * Метод проверяет, является ли ситуация номером 4, исходя из введенных дат.
     * Ситуации:
     * 1) первый месяц полный, последний неполный
     * 2) первый месяц неполный, последний полный
     * 3) оба месяца неполные
     * 4) оба месяца полные.
     * <p>
     * Checks, if current situation = 4.
     * <p>
     * Situations:
     * 1. First month is full, last month is not
     * 2. Last month is full, first month is not
     * 3. Both months are incomplete.
     * 4. Both months are full.
     *
     * @param start - начало расчетного периода/beginning of billing period.
     * @param end   - конец расчетного периода/end of billing period.
     */
    private void checkBothFull(Date start, Date end) {
        createCalDates(start, end);
        int lastDay = ended.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (started.get(Calendar.DAY_OF_MONTH) == 1
                && ended.get(Calendar.DAY_OF_MONTH) == lastDay) {
            situation = 4;
        }
    }

    /**
     * Метод для подготовки и вынимания дней и месяцев из даты.
     * Creates references to calendar dates.
     *
     * @param start - начало расчетного периода/beginning of billing period.
     * @param end   - конец расчетного периода/end of billing period.
     */
    private void createCalDates(Date start, Date end) {
        started = Calendar.getInstance();
        ended = Calendar.getInstance();
        started.setTime(start);
        ended.setTime(end);
    }

}
