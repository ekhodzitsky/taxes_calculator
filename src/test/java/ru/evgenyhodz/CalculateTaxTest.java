package ru.evgenyhodz;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Evgeny Khodzitskiy (evgeny.hodz@gmail.com)
 * @since 24.07.2017
 */
public class CalculateTaxTest {
    /**
     * Some date.
     */
    private static final Date FIRST = new GregorianCalendar(2017, Calendar.FEBRUARY, 1).getTime();
    /**
     * Some date.
     */
    private static final Date SECOND = new GregorianCalendar(2017, Calendar.MARCH, 4).getTime();
    /**
     * Some date.
     */
    private static final Date THIRD = new GregorianCalendar(2017, Calendar.AUGUST, 31).getTime();
    /**
     * Some date.
     */
    private static final Date FOURTH = new GregorianCalendar(2017, Calendar.DECEMBER, 25).getTime();
    /**
     * CalculateTax object (2017 year parameters).
     */
    private static final CalculateTax CALC = new CalculateTax(new Validation(), 7500, 0.26D, 0.051D);

    /**
     * Imitation of entering dates to program.
     */
    @Test
    public void whenGetSomeDatesFirstTest() {
        CALC.countAll(FIRST, SECOND);
        assertThat(CALC.getTotal(), is(2633.47D));
    }

    /**
     * Imitation of entering dates to program.
     */
    @Test
    public void whenGetSomeDatesSecondTest() {
        CALC.countAll(SECOND, THIRD);
        assertThat(CALC.getTotal(), is(13995.0D));
    }

    /**
     * Imitation of entering dates to program.
     */
    @Test
    public void whenGetSomeDatesThirdTest() {
        CALC.countAll(THIRD, FOURTH);
        assertThat(CALC.getTotal(), is(8953.79D));
    }

    /**
     * Imitation of entering dates to program.
     */
    @Test
    public void whenGetSomeDatesFourthTest() {
        CALC.countAll(SECOND, FOURTH);
        assertThat(CALC.getTotal(), is(22647.82D));
    }
}