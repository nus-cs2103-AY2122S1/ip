package duke.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public enum Messages {
    KNOWN {
        public String toString() {
            return "I'm sorry, but I don't know what that means :-(";
        }
    },
    EXIST {
        public String toString() {
            return "The task selected does not exist";
        }
    },
    EMPTY {
        public String toString() {
            return "The field(s) of %s cannot be empty.";
        }
    },
    TIME {
        public String toString() {
            return "Date & time format entered is invalid. Follow d/M/yyyy [HHmm].";
        }
    };

    /**
     * Returns display datetime format "yyyy MMM d h.mm a".
     *
     * @param time datetime object of task.
     */
    public static String dateFormat(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM d h.mm a");
        return time.format(formatter);
    };
}
