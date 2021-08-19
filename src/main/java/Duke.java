import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ToDoList toDoList = ToDoList.of();

        displayWithStyle(beginScript()); //display welcome msg

        while (sc.hasNext()) {
            int numOfTasks = toDoList.length();
            String userInput = sc.nextLine();
            String msgOutput = "";
            if (userInput.equals("list")) {
                msgOutput = toDoList.toString();
            } else if (userInput.equals("bye")) {
                msgOutput = endScript();
            } else if (userInput.matches("done\s[0-9]{1,2}")) {
                //eg. done 4
                int idxFrom0 = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (!(idxFrom0 < 0 || idxFrom0 >= numOfTasks)) { //valid argument indexes
                    toDoList.toggleDone(idxFrom0);
                    msgOutput = String.format(
                            "Nice! I've marked this task as done:\n    %s",
                            toDoList.get(idxFrom0).toString()
                    );
                }
            } else {//add task to to do list
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

