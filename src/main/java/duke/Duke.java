package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    Scanner myScanner = new Scanner(System.in);


    private Parser parser;

//    public Duke(Parser p) {
//        this.parser = p;
//    }
    public Duke() {
        parser = new Parser(myScanner);
    }

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Cycling_pic.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/apollo11.png"));



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String output = "";
        try {
            output += parser.parse(input);
//                if (parser.getBreak()) {
//                    break;
//                }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }

    static List<Task> toDo = new ArrayList<>();

    static int countTasks() {
        int count = 0;
        for (Task task : toDo) {
            if (!task.isDone) {
                count++;
            }
        }
        return count;
    }

//    static void saveTasks(List<Task> tasks) {//Called on "bye"
//        try {
//            File data_file = new File("Data/DukeData.txt");
//            FileWriter writer = new FileWriter("Data/DukeData.txt");//Overwriting entire file
//            for (Task task : tasks) {
//                writer.write(task.toString());
//                writer.write("\n");
//            }
//            writer.close();
//            if (data_file.createNewFile()) {
//                System.out.println("File created: " + data_file.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    static void readFile(String filePath) throws FileNotFoundException {
//        File f = new File(filePath); // create a File for the given file path
//        Scanner s = new Scanner(f); // create a Scanner using the File as the source
//        while (s.hasNext()) {
//            String currLine = s.nextLine();
//            char taskType = currLine.charAt(1);
//            switch (taskType) {
//            case 'T':
//                toDo.add(new ToDo(currLine.substring(7)));
//                System.out.println(new ToDo(currLine.substring(7)));
//                break;
//            case 'D':
//                int l = currLine.indexOf("(");
//                int n = currLine.indexOf(")");
//                toDo.add(new Deadline(currLine.substring(7, l), currLine.substring(l + 1, n)));
//                System.out.println(new Deadline(currLine.substring(7, l), currLine.substring(l + 1, n)));
//                break;
//            case 'E':
//                int i = currLine.indexOf("(");
//                int k = currLine.indexOf(")");
//                toDo.add(new Event(currLine.substring(7, i), currLine.substring(i + 1, k)));
//                System.out.println(new Event(currLine.substring(7, i), currLine.substring(i + 1, k)));
//                break;
//            }
//        }
//    }

}
