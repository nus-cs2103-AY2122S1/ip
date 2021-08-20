package constants;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import shared.StringHelpers;

/**
 * Encapsulates global variables possibly used in multiple areas in code.
 */
public class Constants {

    public static class Display {
        public static enum IndentationType {
            TAB("\t"), SPACE("    ");

            private String val;

            private IndentationType(String val) {
                this.val = val;
            }

            @Override
            public String toString() {
                return val;
            }

        }

        public static final IndentationType INDENTATION_TYPE = IndentationType.TAB;
        public static final int BREAKLINE_LENGTH = 100;
        public static final String BREAKLINE_PATTERN = "_";
        public static final String BREAKLINE;
        static {
            BREAKLINE = StringHelpers.repeat(BREAKLINE_LENGTH, BREAKLINE_PATTERN) + System.lineSeparator();
        }
        public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM);
    }

    public static class Input {
        public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy[ HHmm]");
        // note that this means ISO-8601 strings will not be used as input
        public static final String DATE_RANGE_SEPARATOR = "-";
    }

    public static class Storage {
        public static final String PERSISTENCE_SEPARATOR = "|";
        public static final String PERSISTENCE_SEPARATOR_REGEX = String.format("\\%s", PERSISTENCE_SEPARATOR);
        public static final String PERSISTENCE_SEPARATOR_PRETTY = String.format(" %s ", PERSISTENCE_SEPARATOR);
        public static final String[] PERSISTENCE_LOCATION = { "data", "local_storage.txt" };
    }
}
