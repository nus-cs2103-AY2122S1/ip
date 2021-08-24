import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {


    static String line = "____________________________________________________________";

    static List<Task> list = new ArrayList<Task>();

    private static void HelloMessage() {
        String Hello_message = "Hello! I'm Duke\n" +
                                "What can I do for you?\n";

        System.out.println(line + "\n" + Hello_message + line + "\n");
    }

    private static void PrintList() {

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i).PrintTaskInfo());
        }
    }

    private static void AddTask() {

    }

    private static void HandleTask(String Message) throws DukeException{
        String task = "";
        String deadline = "";

        //If the task type does not belong to the three types, throw an error.
        if (!(Message.startsWith("todo") || Message.startsWith("event") || Message.startsWith("deadline"))){
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        //Get Task description and time if it has it.
        if (Message.indexOf("/") != -1) {
            task = Message.substring(Message.indexOf(" ") + 1, Message.indexOf("/") - 1);

            //throw exceptions for deadline or events' format.
            if (Message.startsWith("deadline")) {
                if (Message.indexOf("/by") != -1) {
                    deadline = Message.substring(Message.indexOf("/by") + 3);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but the format of deadline is wrong :-(");
                }
            } else if (Message.startsWith("event")) {
                if (Message.indexOf("/at") != -1) {
                    deadline = Message.substring(Message.indexOf("/at") + 3);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but the format of event is wrong :-(");
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but the format of todo is wrong :-(");
            }
        }
        else {
            if (Message.indexOf(" ") == -1) {
                throw new DukeException("☹ OOPS!!! The description of a " + Message +" cannot be empty.");
            }else {
                task = Message.substring(Message.indexOf(" ") + 1);

                deadline = "";
            }
        }

        //Time for deadlines or event cannot be empty.
        if ((Message.startsWith("event") || Message.startsWith("deadline")) && deadline.equals("")) {
            throw new DukeException("☹ OOPS!!! The time of a " + Message.substring(0, Message.indexOf(" ")) +" cannot be empty.");
        }


        System.out.println("Got it. I've added this task: ");


        String taskType = Message.substring(0, Message.indexOf(" "));
        TaskType[] taskTypes = TaskType.values();
        for (TaskType t : taskTypes) {
            if (t.toString().equals(taskType)){
                Task newTask = t.AssignTaskType(t, task, deadline);
                System.out.println(" " + newTask.PrintTaskInfo());
                list.add(newTask);
                break;
            }
        }

        System.out.println("Now you have " + list.size() + "" +
                " tasks in the list.");
    }

    private static void MarkDone(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the index is invalid :-(");
        } else {
            System.out.println("Nice! I've marked this task as done:");
            list.get(index).MarkDone();
            System.out.println(" " + list.get(index).PrintTaskInfo());
        }
    }

    private static void Delete(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the index is invalid :-(");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + list.get(index).PrintTaskInfo());
            list.remove(index);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }

    private static void SaveListDataToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size(); i++) {
            fw.write( list.get(i).GetDataInfo()+ "\n");
        }
        fw.close();
    }

    private static void UpdateSaveData() {
        try {
            SaveListDataToFile("data/duke.txt");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static char HandleTaskText(String Data) {
        char taskType;
        char done;
        String task = "";
        String time = "";

        taskType = Data.charAt(0);
        done = Data.charAt(4);

        if (taskType == 'D' || taskType == 'E') {
            time = Data.substring(Data.lastIndexOf("|") + 2);
            task = Data.substring(8 ,Data.indexOf("|", 8) - 1);
        } else {
            time = "";
            task = Data.substring(8);
        }
        TaskType[] taskTypes = TaskType.values();
        for (TaskType t : taskTypes) {
            if (t.toString().toUpperCase().charAt(0) == taskType){
                Task newTask = t.AssignTaskType(t, task, time);
                list.add(newTask);
                break;
            }
        }

        return done;
    }

    private static void LoadDataFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f);
        int index = 1;
        while (s.hasNext()) {
            String Data = s.nextLine();
            char done = HandleTaskText(Data);
            if (done == '1') {
                list.get(index - 1).MarkDone();
            }
            index++;
        }
    }


    private static void PrintMessage() {
        Scanner scanner = new Scanner(System.in);
        String Message = "";
        String Goodbye_message = "Bye. Hope to see you again soon!";

        //Use loop to determine if a user enters "bye" or not.
        while (true) {
            Message = scanner.nextLine();
            System.out.println(line);
            if (Message.equals("bye")) {
                System.out.println(Goodbye_message);
                System.out.println(line + "\n");
                break;
            }  else if (Message.equals("list")){
                PrintList();
            }
            else if (Message.startsWith("done")) {
                int index = Integer.parseInt(Message.substring(Message.indexOf(" ") + 1)) - 1;

                try {
                    MarkDone(index);
                } catch (DukeException e){
                    e.PrintErrorMessage();
                }
            } else if (Message.startsWith("delete")) {
                int index =  Integer.parseInt(Message.substring(Message.indexOf(" ") + 1)) - 1;

                try {
                    Delete(index);
                } catch (DukeException e){
                    e.PrintErrorMessage();
                }
            } else  {
                try {
                    HandleTask(Message);
                } catch (DukeException e)
                {
                    e.PrintErrorMessage();
                }
            }
            System.out.println(line + "\n");

            //Update the SaveData every time a round of operation is done.
            UpdateSaveData();
        }
    }

    private enum TaskType{
        todo, deadline, event;

        public Task AssignTaskType(TaskType t,String task, String time){
            switch (t) {
                case todo: return new ToDos(false, task);
                case deadline: return new Deadlines(false, task, time);
                case event: return new Events(false, task, time);
                default: return null;
            }
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Load Save Data
        try {
            LoadDataFromFile("data/duke.txt");
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }


       //Print Hello.
        HelloMessage();

        //Print Message();
        PrintMessage();
        
    }
}
