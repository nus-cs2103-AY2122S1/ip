package duke.constants;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import duke.shared.StringHelpers;

/**
 * Encapsulates global variables possibly used in multiple areas in code.
 */
public class Constants {
    /**
     * Encapsulates constants related to CLI display.
     */
    public static class Display {
        /**
         * Allows choice between tab and spaces for indentation in the CLI
         */
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

        public static enum BootstrapColor {
            PRIMARY("color-primary"),
            SUCCESS("color-success"),
            INFO("color-info"),
            WARNING("color-warning"),
            DANGER("color-danger");
            private String cssClass;

            private BootstrapColor(String cssClass) {
                this.cssClass = cssClass;
            }

            @Override
            public String toString() {
                return cssClass;
            }
        }


        public static final IndentationType INDENTATION_TYPE = IndentationType.TAB;
        /**
         * Number of characters of breakline pattern used in breakline.
         */
        public static final int BREAKLINE_LENGTH = 100;
        /**
         * A unit that makes up the breakline.
         */
        public static final String BREAKLINE_PATTERN = "_";
        /**
         * Breakline consists of BREAKLINE_PATTERN repeated BREAKLINE_LENGTH times, together with a newline char.
         */
        public static final String BREAKLINE;
        static {
            BREAKLINE = StringHelpers.repeat(BREAKLINE_LENGTH, BREAKLINE_PATTERN) + System.lineSeparator();
        }
        public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM);


    }

    /**
     * Encapsulates constants relevant to user input.
     */
    public static class Input {
        public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy[ HHmm]");
        // note that this means ISO-8601 strings will not be used as input
        public static final String DATE_RANGE_SEPARATOR = "-";
    }

    /**
     * Encapsulates constants relevant to data persistence.
     */
    public static class Storage {
        public static final String PERSISTENCE_SEPARATOR = "|";
        public static final String PERSISTENCE_SEPARATOR_REGEX = String.format("\\%s", PERSISTENCE_SEPARATOR);
        public static final String PERSISTENCE_SEPARATOR_PRETTY = String.format(" %s ", PERSISTENCE_SEPARATOR);
        public static final String[] PERSISTENCE_LOCATION = { "data", "local_storage.txt" };
    }
}
