import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws IOException {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "My favorite partner is back! How can I help?\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        ArrayList<Task> taskList = new ArrayList<Task>();
        String taskListPath = "data/dukeTaskList.txt";
        File dukeTaskList = new File(taskListPath);
        if(dukeTaskList.exists()) {
            try {
                Scanner fileScanner = new Scanner(dukeTaskList);
                while (fileScanner.hasNext()) {
                    String currLine = fileScanner.nextLine();
                    String currTaskType = currLine.substring(3,4);
                    String currTaskCheckBox = currLine.substring(6,7);
                    String currTask = currLine.substring(9);
                    switch (currTaskType) {
                        case "T":
                            taskList.add(new Todo(currTask));
                            break;
                        case "D":
                            String[] partsD = currTask.split("by: ");
                            Task currDeadlineTask = new Deadline(partsD[0].replace(" (", ""),
                                    partsD[1].substring(0,11),
                                    partsD[1].substring(12).replace(")", ""));
                            taskList.add(currDeadlineTask);
                            break;
                        case "E":
                            String[] partsE = currTask.split("at: ");
                            Task currEventTask = new Event(partsE[0].replace(" (", ""),
                                    partsE[1].substring(0,11),
                                    partsE[1].substring(12).replace(")", ""));
                            taskList.add(currEventTask);
                            break;
                    }
                    if (currTaskCheckBox.equals("X")) {
                        taskList.get(taskList.size() - 1).markAsDone();
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        } else {
            dukeTaskList.getParentFile().mkdir();
            dukeTaskList.createNewFile();
        }
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            System.out.print(str + "\n");
            System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            if (str.equals("bye")) {
                //Prints goodbye message and ends the loop.
                StringBuilder listBuilder = new StringBuilder();
                for (int j = 0; j < Task.noOfTask; j++) {
                    String listItem = (j + 1)
                            + "."
                            + taskList.get(j).getTaskType()
                            + taskList.get(j).checkIsDone()
                            + " " + taskList.get(j).getDescription();
                    listBuilder.append(listItem).append(System.lineSeparator());
                }
                FileWriter dukeTaskListWriter = new FileWriter(dukeTaskList);
                dukeTaskListWriter.write(listBuilder.toString());
                dukeTaskListWriter.close();
                System.out.print("Have a good day, friend!\n"
                        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

                break;
            } else if (str.equals("list")) {
                //Prints the task list.
                for (int j = 0; j < Task.noOfTask; j++) {
                    String listItem = (j + 1)
                            + "."
                            + taskList.get(j).getTaskType()
                            + taskList.get(j).checkIsDone()
                            + " " + taskList.get(j).getDescription() + "\n";
                    System.out.print(listItem);
                }
            } else if(str.contains("done ")) {
                //Sets task as done and prints the message.
                Integer listIndex = Integer.parseInt(str.substring(5)) - 1;
                taskList.get(listIndex).markAsDone();
                System.out.print("Well Done, I'll get it marked:\n"
                        + taskList.get(listIndex).checkIsDone()
                        + " " + taskList.get(listIndex).getDescription() + "\n");
            } else if (str.contains("delete ")) {
                //Deletes task specified and prints message
                int removeTaskIndex = Integer.parseInt(str.substring(7)) - 1;
                Task removedTask = taskList.remove(removeTaskIndex);
                System.out.print("Roger! I will remove this task from the list: \n"
                        + removedTask.getTaskType()
                        + removedTask.checkIsDone()
                        + removedTask.getDescription() + "\n"
                        + "Now you have "
                        + --Task.noOfTask
                        + " tasks left in the list.\n");
            } else {
                try {
                    //Initialise the task if its a valid input.
                    Task newTask = null;
                    if (str.contains("todo")) {
                        newTask = new Todo(str.replace("todo ", ""));
                    } else if (str.contains("deadline")) {
                        if (!str.contains("/by")) {
                            newTask = new Deadline(str.replace("deadline ", ""), "");
                        } else {
                            String[] parts = str.split(" /by ");
                            newTask = new Deadline(parts[0].replace("deadline " , ""), parts[1]);
                        }
                    } else if (str.contains("event")) {
                        if (!str.contains("/at")) {
                            newTask = new Event(str.replace("event ", ""), "");
                        } else {
                            String[] parts = str.split(" /at ");
                            newTask = new Event(parts[0].replace("event ", ""), parts[1]);
                        }
                    }
                    if (newTask != null) {
                        //Add task to the list and print message.
                        taskList.add(Task.noOfTask - 1, newTask);
                        System.out.print("Roger! I will add this task in: \n"
                                + newTask.getTaskType()
                                + newTask.checkIsDone()
                                + " "
                                + newTask.getDescription() + "\n"
                                + "Now you have "
                                + Task.noOfTask
                                + " tasks left in the list.\n");

                    } else {
                        //For invalid input message
                        throw new WrongInputException();
                    }

                } catch (DukeException e) {
                    System.out.print( e.toString()
                            + "\n");
                }
            }
            System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
    }
}



