import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;


public class Duke {
    static List<Task> listOfText2 = new ArrayList<>();
    static String filepath = "src/main/data/duke.txt";

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Adds task to arraylist
     * @param task
     */
    private static void list(Task task) {
        listOfText2.add(task);
        int counter = listOfText2.size();
        save();
        System.out.println("Got it. I've added this task:\n" + "  "+ task.toString() +"\nNow you have "+counter+" tasks in the list.");
    }

    /**
     * Prints the task in the arraylist
     */
    private static void printList() {
        if (listOfText2.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < listOfText2.size(); i++) {
                System.out.println(i+1 + "." +  listOfText2.get(i).logo() + listOfText2.get(i).getStatusIcon() + " " + listOfText2.get(i).description);
            }
        } else {
            System.out.println("There are no tasks in your list");
        }

    }

    /**
     * Mark as done at index number-1
     * @param number
     */
    private static void changeStatus(int number) {
        if(listOfText2.size() >= number) {
            listOfText2.get(number-1).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + listOfText2.get(number-1) );
            return;
        }
    }

    /**
     * Check if description is missing
     * @param input
     * @return validity
     */
    private static boolean checkInvalidDescription(String input) {
        if(input.trim().equals("todo") || input.trim().equals("deadline") || input.trim().equals("event")) {
            return true;
        }
        return false;
    }

    /**
     * deletes task in arraylist at index-1
     * @param index
     */
    private static void deleteTask(int index) {
        Task item = listOfText2.get(index-1);
        listOfText2.remove(index-1);
        save();
        System.out.println("Noted. I've removed this task:\n  " + item + "\nNow you have " + listOfText2.size() + " tasks left in the list");
    }

    private static void createFile() throws IOException{
        Files.createDirectories(Paths.get("src/main/data"));
        String directory = "src/main/data";
        File file = new File("src/main/data/duke.txt");
        if(!(file.exists())) {
            file.createNewFile();

        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void save() {
        String temp = "";
        for(int i = 0; i < listOfText2.size(); i++) {
            temp = temp + listOfText2.get(i) +"\n";
        }

        try {
            writeToFile(filepath, temp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            createFile();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input;
        while(!((input = sc.nextLine()).equals("bye"))) {
            try {
                if(input.equals("list")) {
                    printList();
                }else if (input.length() > 5 && input.substring(0, 5).equals("done ")) {
                    String[] split = input.split("\\s+");
                    int number = Integer.parseInt(split[1]);
                    changeStatus(number);
                }else if (input.length() > 9 && input.substring(0, 9).equals("deadline ")) {
                    String[] split = input.substring(9).split(" /by ");
                    list(new Deadline(split[0], split[1]));
                } else if (input.length() > 5 && input.substring(0, 5).equals("todo ")) {
                    list(new ToDo(input.substring(5)));
                } else if (input.length() > 6 && input.substring(0, 6).equals("event ")) {
                    String[] split = input.substring(6).split(" /at ");
                    list(new Event(split[0], split[1]));
                } else if (input.length() > 7 && input.substring(0, 7).equals("delete ")) {
                    String[] split = input.substring(6).split("\\s+");
                    deleteTask(Integer.parseInt(split[1]));
                } else if (checkInvalidDescription(input)) {
                    throw new InvalidDescription(input);
                } else if (input.length() > 0) {
                    throw new InvalidArguement();
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

        if(input.equals("bye")) {
            bye();
        }
    }
}

