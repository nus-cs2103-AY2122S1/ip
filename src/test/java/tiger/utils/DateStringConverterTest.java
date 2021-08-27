package tiger.utils;

import org.junit.jupiter.api.Test;
import tiger.exceptions.TigerException;
import tiger.exceptions.inputs.TigerDateParsingException;
import tiger.constants.Messages;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateStringConverterTest {
    DateStringConverter dateStringConverter = new DateStringConverter();

    @Test
    public void parseDateTime_dateAndTime_success() {
        assertEquals("2020-11-16 15:28",
                dateStringConverter.getDateFromString("2020-11-16 15:28").toString());
    }

    @Test
    public void parseDateTime_dateOnly_success() {
        assertEquals("2020-11-16",
                dateStringConverter.getDateFromString("2020-11-16").toString());
    }

    @Test
    public void parseDateTime_timeOnly_success() {
        assertEquals(String.format("%s 08:24", LocalDate.now()),
                dateStringConverter.getDateFromString("08:24").toString());
    }

    @Test
    public void parseDateTime_dayAccidentallyOutOfRange_success() {
        assertEquals(String.format("2020-11-30", LocalDate.now()),
                dateStringConverter.getDateFromString("2020-11-31").toString());
    }

    @Test
    public void parseDateTime_InFuture_success() {
        assertEquals(String.format("2030-11-30", LocalDate.now()),
                dateStringConverter.getDateFromString("2030-11-31").toString());
    }

    @Test
    public void parseDateTime_dayObviouslyOutOfRange_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("2020-11-32");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_monthObviouslyOutOfRange_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("2020-13-15");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_fiveDigitYear_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("12345-13-15");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_threeDigitYear_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("345-11-16");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_zeroInFrontOfYears_success() {
        assertEquals(String.format("0345-11-16", LocalDate.now()),
                dateStringConverter.getDateFromString("0345-11-16").toString());
        assertEquals(String.format("0045-11-16", LocalDate.now()),
                dateStringConverter.getDateFromString("0045-11-16").toString());
        assertEquals(String.format("0005-11-16", LocalDate.now()),
                dateStringConverter.getDateFromString("0005-11-16").toString());
    }

    @Test
    public void parseDateTime_spaceParsing_success() {
        assertEquals(String.format("0345-11-16", LocalDate.now()),
                dateStringConverter.getDateFromString("0345-11-16    ").toString());
        assertEquals(String.format("0045-11-16", LocalDate.now()),
                dateStringConverter.getDateFromString("    0045-11-16").toString());
        assertEquals(String.format("0005-11-16 08:00", LocalDate.now()),
                dateStringConverter.getDateFromString("     0005-11-16 08:00 " +
                        "   ").toString());
    }

    @Test
    public void parseDateTime_spaceParsing_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("2345-11-16  08:00");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_tooManyArguments_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("2345-11-16 08:00 08:00");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_wrongOrder_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("08:00 2345-11-16");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }


    @Test
    public void parseDateTime_negativeThreeDigitYear_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("-345-11-16");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_negativeFourDigitYear_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("-1345-11-16");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_negativeOneDigitMonth_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("-345--4-16");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_negativeOneTwoDigitMonth_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("-345--12-16");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_negativeOneDigitDay_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("-345-11--6");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_negativeTwoDigitDay_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("-345-11--16");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_negativeTwoDigitHour_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("1345-11-16 -09:00");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_negativeOneDigitHour_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("1345-11-16 -9:00");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_negativeTwoDigitMinute_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("1345-11-16 09:-00");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_negativeOneDigitMinute_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("1345-11-16 09:-0");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_excludeZeroMonth_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("1345-1-16 09:00");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_excludeZeroDay_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("1345-11-6 09:00");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_excludeZeroHour_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("1345-11-06 9:00");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_excludeZeroMinute_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("1345-11-06 09:3");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_zeroYear_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("0000-11-16");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_invalidDelimiter_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("2020:11:16 15:28");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_invalidDelimiterMixed_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("2020/11-16 15:28");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }

    @Test
    public void parseDateTime_invalidDelimiterTime_failure() {
        TigerException thrown = assertThrows(TigerDateParsingException.class, () -> {
            dateStringConverter.getDateFromString("2020/11/16 15-28");
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage()));
    }
}
