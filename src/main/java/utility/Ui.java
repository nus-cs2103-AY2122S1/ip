package utility;

import java.io.PrintStream;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Scanner;

public class Ui {
    private PrintStream out;
    private Scanner in;
    private static final String line = "\n\t_______________________________________________________________";

    public Ui() {
        this.out = System.out;
        this.in = new Scanner(System.in);
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void greet() {
        Date localDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String localTime = timeFormat.format(localDate);
        String msg = "\t I'm IntelliBot. What can I do for you today?";

        if ("05:00:00".compareTo(localTime) <= 0 && localTime.compareTo("12:00:00") < 0) {
            System.out.println(line + "\n\t Good morning!\n" + msg + line);
        } else if ("12:00:00".compareTo(localTime) <= 0 && localTime.compareTo("18:00:00") < 0) {
            System.out.println(line + "\n\t Good afternoon!\n" + msg + line);
        } else {
            System.out.println(line + "\n\t Good evening!\n" + msg + line);
        }
    }

    public void showToUser(String message) {
        out.println(line + message + line);
    }
}
