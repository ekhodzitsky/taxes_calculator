package ru.evgenyhodz;

import java.util.Calendar;
import java.util.Date;

/**
 * Класс содержит основные методы расчетов страховых платежей.
 * С примерами расчетов можно ознакомиться на сайте: ipipip.ru
 * <p>
 * Class contains basic methods to calculate insurance payments to:
 * 1. Federal Compulsory Medical Insurance Fund of Russia,
 * 2. Pension Fund of Russian Federation.
 * <p>
 * Examples of calculations can be found on the website: ipipip.ru
 *
 * @author Evgeny Khodzitskiy (evgeny.hodz@gmail.com)
 * @since 04.02.2017
 */
class CalculateTax {
    /**
     * Минимальная зарплата в РФ на 2017 год.
     * Minimum salary in Russia in 2017.
     */
    private int minSalary;
    /**
     * Тариф ПФР.
     * Coefficent of Pension Fund of Russia.
     */
    private double pRate = 0.26;
    /**
     * Тариф ФФОМС.
     * Coefficient of Federal Compulsory Medical Insurance Fund of Russia.
     */
    private double fRate = 0.051;
    /**
     * Ссылка на класс валидации дат.
     * Validation class.
     */
    private Validation val;
    /**
     * Поле результата расчета платежа в ПФР.
     * Payment to Pension Fund of Russia.
     */
    private double pfr;
    /**
     * Поле результата расчета платежа в ФФОМС.
     * Payment to Federal Compulsory Medical Insurance Fund of Russia.
     */
    private double ffoms;
    /**
     * Поле с результатом подсчета числа полных месяцев.
     * Amount of full months.
     */
    private int fullMonth;

    /**
     * Конструктор/Constructor.
     *
     * @param salary     - минимальная зарплата/minimum salary.
     * @param firstRate  - коэффициент ПФР/PFR coefficient.
     * @param secondRate - коэффициент ФФОМС/FFOMS coefficient.
     * @param validation - класс валидации/validation class.
     */
    CalculateTax(Validation validation, int salary, double firstRate, double secondRate) {
        this.val = validation;
        this.minSalary = salary;
        this.pRate = firstRate;
        this.fRate = secondRate;
    }

    /**
     * Геттер/Getter.
     *
     * @return - Округленный результат платежа в ПФР/rounded result of PFR payment.
     */
    double getPfr() {
        return Math.round(pfr * 100D) / 100D;
    }

    /**
     * Геттер/Getter.
     *
     * @return - Округленный результат платежа в ФФОМС/rounded result of FFOMS payment.
     */
    double getFfoms() {
        return Math.round(ffoms * 100D) / 100D;
    }

    /**
     * Геттер/Getter.
     *
     * @return - Округленная сумма ПФР + ФФОМС/rounded amount PFR + FFOMS.
     */
    double getTotal() {
        return Math.round((pfr + ffoms) * 100D) / 100D;
    }

    /**
     * Считаем ситуацию, когда только последний месяц периода неполный.
     * * Ситуаций всего 4:
     * 1) первый месяц полный, последний неполный
     * 2) первый месяц неполный, последний полный
     * 3) оба месяца неполные
     * 4) оба месяца полные.
     * <p>
     * Calculate situation, when only last month of the billing period is not full.
     * There are only four situations:
     * <p>
     * 1. First month is full, last month is not
     * 2. Last month is full, first month is not
     * 3. Both months are incomplete.
     * 4. Both months are full.
     *
     * @param start - начало расчетного периода/beginning of billing period.
     * @param end   - конец расчетного периода/end of billing period.
     */
    private void calcFirstSituation(Date start, Date end) {
        val.check(start, end);
        if (val.getSituation() == 1) {
            int numDays = val.getEnded().getActualMaximum(Calendar.DAY_OF_MONTH);
            int daysWorked = val.getEnded().get(Calendar.DAY_OF_MONTH);
            countFullMonths();
            if (fullMonth != 0) {
                pfr = minSalary * pRate * fullMonth + minSalary * pRate * daysWorked / numDays;
                ffoms = minSalary * fRate * fullMonth + minSalary * fRate * daysWorked / numDays;
            }
        }
    }

    /**
     * Считаем ситуацию, когда только первый месяц периода неполный.
     * * Ситуаций всего 4:
     * 1) первый месяц полный, последний неполный
     * 2) первый месяц неполный, последний полный
     * 3) оба месяца неполные
     * 4) оба месяца полные.
     * <p>
     * Calculate situation, when only first month the of billing period is not full.
     * There are only four situations:
     * <p>
     * 1. First month is full, last month is not
     * 2. Last month is full, first month is not
     * 3. Both months are incomplete.
     * 4. Both months are full.
     *
     * @param start - начало расчетного периода/beginning of billing period.
     * @param end   - конец расчетного периода/end of billing period.
     */
    private void calcSecondSituation(Date start, Date end) {
        val.check(start, end);
        if (val.getSituation() == 2) {
            int numDays = val.getStarted().getActualMaximum(Calendar.DAY_OF_MONTH);
            int daysWorked = numDays - val.getStarted().get(Calendar.DAY_OF_MONTH) + 1;
            countFullMonths();
            if (fullMonth != 0) {
                pfr = minSalary * pRate * fullMonth + minSalary * pRate * daysWorked / numDays;
                ffoms = minSalary * fRate * fullMonth + minSalary * fRate * daysWorked / numDays;
            }
        }
    }

    /**
     * Считаем ситуацию, когда оба месяца (первый и последний периода) неполные.
     * * Ситуаций всего 4:
     * 1) первый месяц полный, последний неполный
     * 2) первый месяц неполный, последний полный
     * 3) оба месяца неполные
     * 4) оба месяца полные.
     * <p>
     * Calculate situation, when both months(the first one and the last one of billing period)
     * are incomplete.
     * There are only four situations:
     * <p>
     * 1. First month is full, last month is not
     * 2. Last month is full, first month is not
     * 3. Both months are incomplete.
     * 4. Both months are full.
     *
     * @param start - начало расчетного периода/beginning of billing period.
     * @param end   - конец расчетного периода/end of billing period.
     */
    private void calcThirdSituation(Date start, Date end) {
        val.check(start, end);
        if (val.getSituation() == 3) {
            int numDays = val.getStarted().getActualMaximum(Calendar.DAY_OF_MONTH);
            int daysWorked = numDays - val.getStarted().get(Calendar.DAY_OF_MONTH) + 1;
            int endDays = val.getEnded().getActualMaximum(Calendar.DAY_OF_MONTH);
            int endWorked = val.getEnded().get(Calendar.DAY_OF_MONTH);
            countFullMonths();
            if (fullMonth != 0) {
                pfr = minSalary * pRate * endWorked / endDays + minSalary
                        * pRate * daysWorked / numDays + minSalary * pRate * fullMonth;
                ffoms = minSalary * fRate * endWorked / endDays + minSalary
                        * fRate * daysWorked / numDays + minSalary * fRate * fullMonth;
            } else {
                pfr = minSalary * pRate * endWorked / endDays + minSalary
                        * pRate * daysWorked / numDays + minSalary * pRate;
                ffoms = minSalary * fRate * endWorked / endDays + minSalary
                        * fRate * daysWorked / numDays + minSalary * fRate;
            }
        }
    }

    /**
     * Считаем ситуацию, когда оба месяца (первый и последний периода) полные.
     * * Ситуаций всего 4:
     * 1) первый месяц полный, последний неполный
     * 2) первый месяц неполный, последний полный
     * 3) оба месяца неполные
     * 4) оба месяца полные.
     * <p>
     * Calculate situation, when both months (the first one and the last one of billing period)
     * are full.
     * There are only four situations:
     * <p>
     * 1. First month is full, last month is not
     * 2. Last month is full, first month is not
     * 3. Both months are incomplete.
     * 4. Both months are full.
     *
     * @param start - начало расчетного периода/beginning of billing period.
     * @param end   - конец расчетного периода/end of billing period.
     */
    private void calcFourthSituation(Date start, Date end) {
        val.check(start, end);
        if (val.getSituation() == 4) {
            countFullMonths();
            pfr = minSalary * pRate * fullMonth;
            ffoms = minSalary * fRate * fullMonth;
        }
    }

    /**
     * Считаем число полных месяцев в расчетном периоде в зависимости от ситуации.
     * Ситуаций всего 4:
     * 1) первый месяц полный, последний неполный
     * 2) первый месяц неполный, последний полный
     * Важно: в ситуаях 1 или 2 как минимум один месяц всегда полный!
     * 3) оба месяца неполные
     * 4) оба месяца полные.
     * Важно: в ситуации 4 как минимум два месяца будут полными!
     * <p>
     * Count the number of full months in the billing period. The calculation method depends on
     * the type of situation. There are only four situations:
     * <p>
     * 1. First month is full, last month is not
     * 2. Last month is full, first month is not
     * What is important: in situations 1 and 2 one month is always full.
     * 3. Both months are incomplete.
     * 4. Both months are full.
     * What is important: in situation 4 at least two months are always full.
     */
    private void countFullMonths() {
        if (val.getSituation() == 2 || val.getSituation() == 1) {
            fullMonth = val.getEnded().get(Calendar.MONTH) - val.getStarted().get(Calendar.MONTH);
            if (fullMonth <= 0) {
                fullMonth = 1;
            }
        } else if (val.getSituation() == 3) {
            fullMonth = val.getEnded().get(Calendar.MONTH) - val.getStarted().get(Calendar.MONTH) - 1;
        } else if (val.getSituation() == 4) {
            fullMonth = val.getEnded().get(Calendar.MONTH) - val.getStarted().get(Calendar.MONTH) + 1;
            if (fullMonth <= 1) {
                fullMonth = 2;
            }
        }
    }

    /**
     * Метод выполняет все 4 расчета (точнее только тот, который нужно).
     * <p>
     * Making calculations of all situations.
     *
     * @param start - начало расчетного периода/beginning of billing period.
     * @param end   - конец расчетного периода/end of billing period.
     */
    void countAll(Date start, Date end) {
        calcFirstSituation(start, end);
        calcSecondSituation(start, end);
        calcThirdSituation(start, end);
        calcFourthSituation(start, end);
    }
}
