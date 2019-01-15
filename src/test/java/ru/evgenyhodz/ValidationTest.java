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
public class ValidationTest {
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
     * Checks first situation.
     */
    @Test
    public void whenCheckDatesThenGetFirstSituation() {
        Validation validation = new Validation();
        assertThat(validation.check(FIRST, SECOND), is(1));
    }

    /**
     * Checks second situation.
     */
    @Test
    public void whenCheckDatesThenGetSecondSituation() {
        Validation validation = new Validation();
        assertThat(validation.check(SECOND, THIRD), is(2));
    }

    /**
     * Checks third situation.
     */
    @Test
    public void whenCheckDatesThenGetThirdSituation() {
        Validation validation = new Validation();
        assertThat(validation.check(SECOND, FOURTH), is(3));
    }

    /**
     * Checks fourth situation.
     */
    @Test
    public void whenCheckDatesThenGetFourthSituation() {
        Validation validation = new Validation();
        assertThat(validation.check(FIRST, THIRD), is(4));
    }

    /**
     * Checks default situation.
     */
    @Test
    public void whenCheckDatesThenGetDefaultSituation() {
        Validation validation = new Validation();
        assertThat(validation.check(FOURTH, FIRST), is(0));
    }

}