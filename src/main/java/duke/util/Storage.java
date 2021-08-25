package duke.util;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    /**
     * Loads saved tasks from the hard-disk
     *
     * @param duke The current instance of the Duke class
     */
    public void loadCommands(Duke duke) {
        String directory = System.getProperty("user.dir");
        Path path = Paths.get(directory, "data/taskList.txt");
        boolean exists = Files.exists(path);
        if (exists) {
            try {
                List<String> data = Files.readAllLines(path);
                for (String x : data) {
                    String[] input1 = x.split(" ", 2);
                    String command = input1[0].trim();
                    System.out.println(command);
                    String[] input2 = input1[1].split("\\+", 2);
                    String description = input2[0].trim();
                    int isDone = Integer.parseInt(input2[1].trim());
                    try {
                        DukeCommands dukeCommand = DukeCommands.valueOf(command.toUpperCase());
                        duke.execute(dukeCommand, description, isDone);
                    } catch (IllegalArgumentException e) {
                        System.out.println("I'm sorry, I don't know what that means! ☹");
                    }
                }
            } catch (IOException e) {
                System.out.println("There was an error loading your saved tasks!");
            }
        } else {
            System.out.println("There is no such file!");
        }

    }

    /**
     * Saves task list to hard-disk
     *
     * @param commands list of tasks to be saved
     */
    public void saveCommands(ArrayList<Task> commands) {
        String directory = System.getProperty("user.dir");
        StringBuilder text = new StringBuilder();
        for (Task command : commands) {
            if (command instanceof ToDo) {
                text.append("todo ").append(command.description);

            } else if (command instanceof Deadline) {
                String deadline = ((Deadline) command).deadline.format(DateTimeFormatter.ofPattern("d-MM-yyyy"));
                text.append("deadline ").append(command.description).append("/by ").append(deadline);
            } else if (command instanceof Event) {
                String time = ((Event) command).time.format(DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"));
                text.append("event ").append(command.description).append("/at ").append(time);
            }

            if (command.isDone) {
                text.append("+1\n");
            } else {
                text.append("+0\n");
            }
        }
        Path path = Paths.get(directory, "data");
        boolean exists = Files.exists(path);
        if (!exists) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.write(Paths.get(directory, "data", "taskList.txt"), text.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
