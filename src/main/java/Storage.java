import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    /** File name for the storage. */
    private static final String LOCATION = "./data/duke.txt";

    /**
     * Export the task in list to a txt file in ./data.
     *
     * @param taskList list of task to be stored.
     */
    public static void exportTask(List<Task> taskList) {
        try {
            File file = new File(LOCATION);
            file.getParentFile().mkdirs();
            file.createNewFile();

            FileWriter fileWrite = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWrite);
            taskList.stream()
                    .forEach(task -> {
                        try {
                            bufferedWriter.write(task.saveOutput());
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            Duke.printMessage(String.format("Unable to save %s", task.toString()));
                        }
                    });
            bufferedWriter.flush();
        } catch (IOException e) {
            Duke.printMessage("Unable to save task.");
        }
    }

    /**
     * Loads the txt file containing information on the task.
     *
     * @return A list of all the task stored.
     */
    public static List<Task> importTask() {
        File file = new File(LOCATION);
        List<Task> taskList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.lines()
                    .forEach(task -> {
                        String[] taskData = task.split(" \\| ");
                        if (taskData.length == 3 || taskData.length == 4) {
                            if (taskData[0].equals(Keyword.DEADLINE.getSaveWord())) {
                                try {
                                    taskList.add(new Deadlines(taskData[1], taskData[3],
                                            taskData[2].equals("1") ? true : false));
                                } catch (ParseException e) {
                                    Duke.printMessage(String.format("Cant import %s", taskData[1]));
                                }
                            } else if (taskData[0].equals(Keyword.EVENTS.getSaveWord())) {
                                try {
                                    taskList.add(new Events(taskData[1], taskData[3],
                                            taskData[2].equals("1") ? true : false));
                                } catch (ParseException e) {
                                    Duke.printMessage(String.format("Cant import %s", taskData[1]));
                                }
                            } else if (taskData[0].equals(Keyword.TODOS.getSaveWord())) {
                                taskList.add(new ToDos(taskData[1],
                                        taskData[2].equals("1") ? true : false));
                            }
                        }
                    });
        } catch (FileNotFoundException e) {
            Duke.printMessage("No stored task found.");
        }
        return taskList;
    }
}
