package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import duke.task.ToDoTask;
import duke.task.Task;
import duke.task.EventTask;
import duke.task.DeadlineTask;

public class Storage {
    public enum Commands {
        DEADLINE,
        EVENT,
        TODO,
        UNKNOWN
    }

    File storageFile;

    public Storage(String filePath) {
        if (filePath != null) {
            storageFile = new File(filePath);
        }
    }

    public void saveTaskList(TaskList tasks) {
        UI ui = new UI();
        try {
            File dir = new File("data");
            boolean isDeleted = false, isCreated = false, isDirectory = dir.mkdirs();
            File data = new File(dir, "duke.txt");
            if (isDirectory && data.exists()) {
                isDeleted = data.delete();
            }
            if(isDeleted) {
                isCreated = data.createNewFile();
            }
            if (isCreated) {
                FileWriter storeData = new FileWriter(data);
                Task task;
                for (int i = 0; i < tasks.getTaskListLength(); i++) {
                    task = tasks.getTask(i);
                    if (task instanceof DeadlineTask) {
                        DeadlineTask deadline = (DeadlineTask) task;
                        storeData.write(deadline.writeToFile());
                    } else if (task instanceof EventTask) {
                        EventTask event = (EventTask) task;
                        storeData.write(event.writeToFile());
                    } else if (task instanceof ToDoTask) {
                        ToDoTask todo = (ToDoTask) task;
                        storeData.write(todo.writeToFile());
                    } else {
                        ui.showLine();
                        ui.showError("Error Occurred While Storing Data");
                        ui.showLine();
                    }
                }
                storeData.close();
            }
        } catch (IOException e) {
            File deletedFile = new File("data/duke.txt");
            boolean isDeleted = deletedFile.delete(), shouldShow = false;
            if (isDeleted) {
                shouldShow = deletedFile.exists();
            }

            if (shouldShow) {
                ui.showLine();
                ui.showError("Error Occurred While Storing Data");
                ui.showLine();
            }
        }
    }

    public Commands getCommand(String command) {
        try {
            if (command != null) {
                return Commands.valueOf(command.toUpperCase());
            } else {
                return Commands.UNKNOWN;
            }
        } catch (IllegalArgumentException e) {
            return Commands.UNKNOWN;
        }
    }

    public List<Task> load() {
        List<Task> taskList = new ArrayList<>();
        UI ui = new UI();

        try {
            Scanner dataInput = new Scanner(storageFile);
            String[] data;
            boolean isDataCorrupted = false;
            while (dataInput.hasNextLine()) {
                try {
                    data = dataInput.nextLine().split(" [|] ");
                    switch (getCommand(data[0])) {
                    case DEADLINE:
                        if (data.length != 4) {
                            isDataCorrupted = true;
                            continue;
                        } else {
                            DeadlineTask deadline = new DeadlineTask(data[1], data[2], data[3]);
                            taskList.add(deadline);
                        }
                        break;
                    case EVENT:
                        if (data.length != 4) {
                            isDataCorrupted = true;
                            continue;
                        } else {
                            EventTask event = new EventTask(data[1], data[2], data[3]);
                            taskList.add(event);
                        }
                        break;
                    case TODO:
                        if (data.length != 3) {
                            isDataCorrupted = true;
                            continue;
                        } else {
                            ToDoTask todo = new ToDoTask(data[1], data[2]);
                            taskList.add(todo);
                        }
                        break;
                    case UNKNOWN:
                        isDataCorrupted = true;
                    }
                } catch (DateTimeParseException ignored) {

                }
            }


            if (isDataCorrupted) {
                ui.showLine();
                ui.showError("Data was partially Corrupted");
                ui.showLine();
            }
        } catch (FileNotFoundException ignored) {

        }
        return taskList;
    }


}