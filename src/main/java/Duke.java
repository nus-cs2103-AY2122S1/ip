import duke.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Duke {

    public TaskList generateTaskList() {
        return new TaskList();
    }

    public static void main(String[] args) {
        TaskList taskList = new Duke().generateTaskList();
        String hello = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(hello);

        Scanner scanner = new Scanner(System.in);
        taskList.run(scanner);
    }
}
