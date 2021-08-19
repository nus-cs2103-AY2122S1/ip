import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ToDoList toDoList = ToDoList.of();

        displayWithStyle(beginScript()); //display welcome msg

        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            String msgOutput;
            switch (userInput) {
                case "list":
                    msgOutput = toDoList.toString();
                    break;
                case "bye":
                    msgOutput = endScript();
                    break;
                default: //add task to to do list
                    msgOutput = String.format("added: %s", userInput);
                    toDoList = toDoList.addTask(Task.of(userInput));
            }
            displayWithStyle(msgOutput); //output msg to user
        }

    }

    public static String beginScript() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return ("Hello from\n" + logo);
    }

    public static String endScript() {
        String exitStatment = "Bye, hope to see you again! :)";
        return exitStatment;
//        System.out.println(exitStatment);
    }
    public static void displayWithStyle(String text) {
        String indent = "    ";
        String topBorder    = "____________________________________\n";
        String bottomBorder = "------------------------------------\n";
        String textWithBorders = topBorder +  text + "\n" + bottomBorder;
        String[] lines = textWithBorders.split("\n");
//        System.out.println(Arrays.toString(lines));
        for (String line : lines) {
            System.out.println(indent + line);
        }
    }

//    public static String toDoListAppend(ToDoList toDoList, String taskSummary) {
//        String msg = String.format("added: %s", taskSummary);
//        Task newTask = Task.of(taskSummary);
//        toDoList.addTask(newTask);
//        return msg;
//    }
}

