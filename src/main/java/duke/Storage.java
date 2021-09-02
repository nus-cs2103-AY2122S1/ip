package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a means to load and save files
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs <code>Storage</code>
     * @param filePath Path to file
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a save file
     * @return List of saved tasks
     */
    public List<Task> loadSave() {
        List<Task> savedTasks = new ArrayList<>();
        String[] filepathArr = this.filePath.split("/");
        File directory = new File(filepathArr[0]);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File saveFile = new File(this.filePath);
        try {
            if (saveFile.createNewFile()) {
                System.out.println("Save file created!");
            } else {
                System.out.println("Save file found!");
            }
        } catch (IOException e) {
            System.out.println("Error loading save file :(");
            e.printStackTrace();
        }
        try {
            Scanner sc2 = new Scanner(saveFile);
            while (sc2.hasNextLine()) {
                String nextLine = sc2.nextLine();
                String[] splitLine = nextLine.split("[|]");
                if (splitLine.length == 3) {
                    if (splitLine[1].equals("0")) {
                        savedTasks.add(new Todo(splitLine[2], false));
                    } else {
                        savedTasks.add(new Todo(splitLine[2], true));
                    }

                } else if (splitLine.length == 4) {
                    String[] dateParameters = splitLine[3].split("-");
                    int day = Integer.parseInt(dateParameters[2]);
                    int month = Integer.parseInt(dateParameters[1]);
                    int year = Integer.parseInt(dateParameters[0]);
                    LocalDate localDate = LocalDate.of(year, month, day);
                    if (splitLine[0].equals("D")) {
                        if (splitLine[1].equals("0")) {
                            savedTasks.add(new Deadline(splitLine[2],
                                    false,
                                    localDate));
                        } else {
                            savedTasks.add(new Deadline(splitLine[2],
                                    true,
                                    localDate));
                        }
                    } else {
                        if (splitLine[1].equals("0")) {
                            savedTasks.add(new Event(splitLine[2],
                                    false,
                                    localDate));
                        } else {
                            savedTasks.add(new Event(splitLine[2],
                                    true,
                                    localDate));
                        }
                    }
                }
            }
            sc2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return savedTasks;
    }

    /**
     * Updates save file
     * @param updatedTasks New list of tasks
     * @throws IOException
     */
    public void updateFile(List<Task> updatedTasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task i : updatedTasks) {
            String taskBody = i.getBody();
            boolean isDone = i.isDone();
            if (i instanceof Todo) {
                if (!isDone) {
                    fw.write("T|0|" + taskBody + System.lineSeparator());
                } else {
                    fw.write("T|1|" + taskBody + System.lineSeparator());
                }
            } else if (i instanceof Deadline) {
                LocalDate taskDeadline = ((Deadline) i).getDeadline();
                if (!isDone) {
                    fw.write("D|0|" + taskBody + "|" + taskDeadline + System.lineSeparator());
                } else {
                    fw.write("D|1|" + taskBody + "|" + taskDeadline + System.lineSeparator());
                }
            } else {
                LocalDate taskDate = ((Event) i).getDate();
                if (!isDone) {
                    fw.write("E|0|" + taskBody + "|" + taskDate + System.lineSeparator());
                } else {
                    fw.write("E|1|" + taskBody + "|" + taskDate + System.lineSeparator());
                }
            }
        }
        fw.close();
    }
}
