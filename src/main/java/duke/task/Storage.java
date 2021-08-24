package duke.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.InvalidParamException;
import duke.main.TaskList;

public class Storage {

    private final String FILEPATH = "tasklist.txt";
    private final File SAVEFILE = new File(FILEPATH);
    private final String SEPARATOR = "~SEPARATION_STRING~";

    private TaskList taskList;

    public Storage(TaskList taskList) {
        try {
            this.taskList = taskList;
            SAVEFILE.createNewFile();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile() {
        try {
            StringBuilder toBeWritten = new StringBuilder();
            FileWriter fileWriter = new FileWriter(FILEPATH);

            for (Task task : taskList) {
                char initialOfTask = task.toString().charAt(1);
                String description = task.getDescription();

                // Add a marker to tell if its done or not
                toBeWritten.append(task.getIsDone() ? "1" : "0").append(SEPARATOR);

                //Add the task type and description
                toBeWritten.append(initialOfTask).append(SEPARATOR).append(description);

                switch (initialOfTask) {
                case 'T':
                    break;
                case 'D':
                    Deadline deadline = (Deadline) task;
                    toBeWritten.append(SEPARATOR).append(deadline.getBy());
                    break;
                case 'E':
                    Event event = (Event) task;
                    toBeWritten.append(SEPARATOR).append(event.getAt());
                    break;
                }

                toBeWritten.append("\n");
            }

            fileWriter.write(toBeWritten.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void copyFromFileToList() { //STOPPED HERE
        try {
            Scanner sc = new Scanner(SAVEFILE);

            while (sc.hasNext()) {
                Task newTask = null;
                String[] inputArray = sc.nextLine().split(SEPARATOR);
                boolean isDone = inputArray[0].equals("1");

                switch (inputArray[1].charAt(0)) {
                case 'T':
                    newTask = Todo.setTodo(inputArray[2]);
                    break;
                case 'D':
                    //newTask = new Deadline(inputArray[2], inputArray[3]);
                    newTask = Deadline.setDeadline(inputArray[2] + Deadline.getSeparator() + inputArray[3]);
                    break;
                case 'E':
                    //newTask = new Event(inputArray[2], inputArray[3]);
                    newTask = Event.setEvent(inputArray[2] + Event.getSeparator() + inputArray[3]);
                    break;
                }

                if (isDone) {
                    newTask.markAsDone();
                }

                taskList.addTask(newTask);
            }
        } catch (IOException e1) {
            System.out.println(e1.getMessage());
        } catch (InvalidParamException e2) {
            // SHOULD NEVER HAPPEN
            System.out.println("THIS SHOULD NEVER HAPPEN, DEADLINE FORMAT WRONG");
        }
    }

}
