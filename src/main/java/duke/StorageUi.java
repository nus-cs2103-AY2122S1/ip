package duke;

import java.time.LocalDate;

/**
 * The ui for Storage that is in charge of displaying relevant messages to the user.
 */
public class StorageUi extends Ui {
    private static final String[] MONTHS = new String[]{
            "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY",
            "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"
    };

    /**
     * Converts a input string time to a LocalDate object.
     *
     * @param time the input time in String format.
     * @return a LocalDate object whose time field is from the input time string.
     */
    public LocalDate convertToLocalTime(String time) {
        String copy = time;
        String month = copy.substring(0, time.indexOf(" "));
        int monthValue = -1;
        for (int i = 0; i < MONTHS.length; i++) {
            if (month.equals(MONTHS[i])) {
                monthValue = i + 1;
                break;
            }
        }
        copy = copy.replace(month + " ", "");
        String day = copy.substring(0, copy.indexOf(" ")).trim();
        int dayValue = Integer.parseInt(day);
        copy = copy.replace(day + " ", "");
        int yearValue = Integer.parseInt(copy);
        return LocalDate.of(yearValue, monthValue, dayValue);
    }

    /**
     * Reminds the user that a directory already exists.
     */
    public void printDirectoryAlreadyExistMessage() {
        System.out.println(formatMessage("This directory already exists!\n"));
    }

    /**
     * Reminds the user that a file already exists.
     */
    public void printFileAlreadyExistMessage() {
        System.out.println(formatMessage("This file already exists!\n"));
    }

    /**
     * Warns the user that the input file path is invalid.
     */
    public void printInvalidFilePathMessage() {
        System.out.println(formatMessage("Invalid file path detected, please try again.\n"));
    }

    /**
     * Tells the user that the task list is loaded from the specified file successfully.
     */
    public void printLoadSuccessfulMessage() {
        System.out.println(formatMessage("Load successfully.\n"));
    }

    /**
     * Warns the user that the autosave record is missing and a new data file is created.
     */
    public void printSaveNotFoundMessage() {
        System.out.println(formatMessage("Saved data not found, a new data file created.\n"));
    }

    /**
     * Tells the user that the task list is saved into the specified file successfully.
     */
    public void printSaveSuccessfulMessage() {
        System.out.println(formatMessage("Save successfully.\n"));
    }

    /**
     * Reminds the user that an unexpected error has occurred.
     */
    public void printUnexpectedErrorMessage() {
        System.out.println(formatMessage("An unknown error has occurred.\n"));
    }
}
