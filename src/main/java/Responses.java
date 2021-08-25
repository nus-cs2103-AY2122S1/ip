import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Responses {

    protected static ArrayList<Task> list = new ArrayList<>();

    private static void addToList(String taskString) {
        String[] taskInfo = taskString.split("\\|");
        Task t;
        switch (taskInfo[0].trim()) {
        case "T":
            t = new Todo(taskInfo[2]);
            break;
        case "D":
            t = new Deadline(taskInfo[2], taskInfo[3]);
            break;
        default:
            t = new Event(taskInfo[2], taskInfo[3]);
            break;            
        }
        if (taskInfo[1] == "true") {
            t.finishTask();
        }
        list.add(t);
    }

    protected static void loadList() {
        list = new ArrayList<>();
        try {
            File inputFile = new File("dukeList.txt");
            Scanner fileReader = new Scanner(inputFile);
            while (fileReader.hasNextLine()) {
                String taskString = fileReader.nextLine();
                addToList(taskString);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {}
    }

    private static void saveList() {
        String response = "";
        for (Task t : Responses.list) {
            response += String.format("%s\n", t.save());
        }
        try (PrintWriter out = new PrintWriter("dukeList.txt")) {
            if (!response.isEmpty()) {
                out.println(response.trim());
            }
        } catch (FileNotFoundException e) {
            displayDukeResponse(e.getMessage());
            Exit.chat();
        }
    }

    protected static void displayDukeResponse(String dResponse) {
        System.out.println(String.format("\t____________________________________________________________\n%s\t____________________________________________________________", dResponse));
    }

    private static String getUserResponse() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private static void next(String uResponse) {
        try {
            switch (uResponse.split(" ")[0]) {
            case "bye":
                Exit.chat();
            case "list":
                List.chat();
            case "done":
                Done.chat(uResponse);
            case "delete":
                Delete.chat(uResponse);
            default:
                Echo.chat(uResponse);
            }
        } catch (DukeException e) {
            displayDukeResponse(String.format("\t%s\n", e.getMessage()));
            next(getUserResponse());
        } 
    } 

    protected static void interact(String dResponse) {
        saveList();
        displayDukeResponse(dResponse);
        next(getUserResponse());
    }

}
