package duke;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Integer.*;

public class Duke {

    enum Category {
        TODO,
        EVENT,
        DEADLINE
    }

    public static void main(String[] args) throws DukeException, IOException {
        Ui ob = new Ui();
        ob.execInput();
    }
}
