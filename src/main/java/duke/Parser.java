package duke;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * Parses the inputs
 */
public class Parser {

    /** Input string by the user. */
    private String inp;

    /**
     * Constructor to initialise the input.
     * @param inp input
     */
    public Parser(String inp) {
        this.inp = inp;
    }


    /**
     * Parses the input task to extract the task content.
     * @return The actual task content.
     */
    public String parseTask() {
        String desc = ((inp.split("\\s",2)[1]).split("/"))[0];
        return desc;
    }

    /**
     * Parses the input task to extract the date.
     * @return The date of the task.
     */
    public String parseTime() {
        String atByTime = ((inp.split("\\s", 2)[1]).split("/"))[1];
        String time = atByTime.split("\\s", 2)[1];
        LocalDate d1 = LocalDate.parse(time);
        return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}

