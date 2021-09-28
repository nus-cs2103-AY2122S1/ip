package duke.main;

import java.nio.charset.Charset;

/**
 * Represents exceptions raised in Duke.Duke.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2.
 */
public class DukeException extends Exception {
    private static byte[] sadEmojiByteCode = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x9E};
    private static byte[] angryEmojiByteCode = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0xA0};
    private static String sadEmoji = new String(sadEmojiByteCode, Charset.forName("UTF-8"));
    private static String angryEmoji = new String(angryEmojiByteCode, Charset.forName("UTF-8"));
    private Exceptions exception;
    /**
     * Class constructor.
     *
     * @param exception the exception thrown.
     */
    public DukeException(Exceptions exception) {
        this.exception = exception;
    }

    public static String getSadEmoji() {
        return sadEmoji;
    }
    public static String getAngryEmoji() {
        return angryEmoji;
    }
    /**
     * Enum of exceptions handled by DukeException.
     */
    public enum Exceptions {
        StringIndexOutOfBoundsException,
        ArrayIndexOutOfBoundsException,
        IndexOutOfBoundsException,
        IOException,
        DateTimeParseException,
        EXCEPTIONS;
    }
    /**
     * Returns a message notifying user of an error.
     *
     * @return error message.
     */
    @Override
    public String getMessage() {
        String message;
        // found on stackoverflow

        switch (exception) {
        case StringIndexOutOfBoundsException:
        case ArrayIndexOutOfBoundsException:
            message = String.format("%s The duke.task description or duke.command is incomplete. %s\n", sadEmoji,
                    sadEmoji);
            break;
        case IndexOutOfBoundsException:
            message = String.format("%s OH NO!!! The duke.task does not exist. %s\n", sadEmoji, sadEmoji);
            break;
        case IOException:
            message = String.format("%s OH NO!!! I cannot find the file. %s\n", sadEmoji, sadEmoji);
            break;
        case DateTimeParseException:
            message = String.format("%s Please enter a proper date and time format. %s\n", angryEmoji, angryEmoji);
            break;
        default:
            message = String.format("%s OOPS!!! I'm sorry, but I don't know what that means %s\n", sadEmoji, sadEmoji);
        }
        return message;
    }
}
