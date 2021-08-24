package duke;

import java.time.LocalDate;

/**
 * The ui for Storage that is in charge of displaying relevant messages to the user.
 * */
public class StorageUi extends Ui {
    private static final String[] MONTHS  = new String[] {
            "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY",
            "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"
    };

    /**
     * Convert a input string time to a LocalDate object.
     * @param time the input time in String format.
     * @return a LocalDate object whose time field is from the input time string.
     * */
    public LocalDate convertToLocalTime(String time) {
        String copy = time;
        String month = copy.substring(0,time.indexOf(" "));
        int monthValue = -1;
        for (int i = 0; i < MONTHS.length; i++) {
            if(month.equals(MONTHS[i])) {
                monthValue = i + 1;
                break;
            }
        }
        copy = copy.replace(month + " ", "");
        String day = copy.substring(0,copy.indexOf(" ")).trim();
        int dayValue = Integer.parseInt(day);
        copy = copy.replace(day + " ", "");
        int yearValue = Integer.parseInt(copy);
        return LocalDate.of(yearValue,monthValue,dayValue);
    }

    /**
     * Remind the user that a directory already exists.
     * */
    public void directoryAlreadyExistMessage() {
        System.out.println(formatMessage("This directory already exists!\n"));
    }

    /**
     * Remind the user that a file already exists.
     * */
    public void fileAlreadyExistMessage() {
        System.out.println(formatMessage("This file already exists!\n"));
    }

    /**
     * Warn the user that the input file path is invalid.
     * */
    public void invalidFilePathMessage() {
        System.out.println(formatMessage("Invalid file path detected, please try again.\n"));
    }

    /**
     * Tell the user that the task list is loaded from the specified file successfully.
     * */
    public void loadSuccessfulMessage() {
        System.out.println(formatMessage("Load successfully.\n"));
    }

    /**
     * Warn the user that the autosave record is missing and a new data file is created.
     * */
    public void saveNotFoundMessage() {
        System.out.println(formatMessage("Saved data not found, a new data file created.\n"));
    }

    /**
     * Tell the user that the task list is saved into the specified file successfully.
     * */
    public void saveSuccessfulMessage() {
        System.out.println(formatMessage("Save successfully.\n"));
    }

    /**
     * Remind the user that an unexpected error has occurred.
     * */
    public void unexpectedErrorMessage() {
        System.out.println(formatMessage("An unknown error has occurred.\n"));
    }
}
