package duke.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exceptions.NoSuchCommandException;

public class Task {

    private final String name;
    private boolean isDone;

    Task(String name, boolean done) {
        assert name != "";
        this.name = name;
        this.isDone = done;
    }

    public void markDone() {
        this.isDone = true;
    }

    public boolean isMatch(String input) {
        for (String word : name.split(" ")) {
            if (word.matches(input)) {
                return true;
            }
        }
        return false;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (this.isDone) ? "X" : " ", this.name);
    }

    public String toStringOutput() {
        return String.format("[%s] %s", (this.isDone) ? "X" : " ", this.name);
    }

    String getName() {
        return this.name;
    }

    Boolean getDone() {
        return this.isDone;
    }

    public static void isLegitFindInput(String input) throws NoSuchCommandException {

        if (input.split(" ").length > 2) {
            throw new NoSuchCommandException("There should be something you're searching for");
        }
    }

    public static void saveTaskList(ArrayList<Task> aList, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + filename));

        try {
            File file = new File(System.getProperty("user.dir") + filename);

            if (!file.exists()) {
                file.createNewFile();
            }

            for (int i = 1; i <= aList.size(); i++) {
                writer.write(aList.get(i - 1).toStringOutput());
                writer.newLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            writer.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static String parseDateTime(String input) {
        if (input.contains(" ")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));

        } else {
            LocalDate dateTime = LocalDate.parse(input);
            return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

}
