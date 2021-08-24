import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    private static void SpecificDateEvent(String time){
        int count = 0;//count the number of the events happen on the time.

        System.out.println("Here are all the tasks taking place on the date you give me:");
        for (int i = 0; i < list.size(); i++) {
            String Message = list.get(i).PrintTaskInfo();
            String UnParsedInfo = list.get(i).GetTime();
            if (UnParsedInfo != null && (UnParsedInfo.contains(time) || (ParseTime(time)!=null && UnParsedInfo.contains(
                    ParseTime(time).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH)))))
                    ||Message.contains(time) || (ParseTime(time)!=null && Message.contains(
                    ParseTime(time).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH))))) {
                count++;
                System.out.println(i + 1 + "." + Message);
            }
        }
        if (count == 0) {
            System.out.println("Sorry. There is no event occurred on the time you give me!! :(");
        }
    }

    private static boolean ValidDate(int day, int month, int year, int hour, int minute) {

        if (((year%4 == 0 && year%100 != 0) || (year % 400 == 0)) && month == 2) {
            if (day > 29 || day <= 0) {
                return false;
            }
        } else if (month == 2){
            if (day > 28 || day <= 0) {
                return false;
            }
        }

        if (month <= 0 || month > 12) {
            return false;
        }

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day > 31 || day <= 0){
                return false;
            }
        } else if (month != 2){
            if (day > 30 || day <= 0) {
                return false;
            }
        }

        if (hour > 24 || hour < 0) {
            return false;
        }

        if (minute > 60 || minute < 0) {
            return false;
        }

        return true;
    }

    /*Will only include the Time Format of: DD/MM/YY Time
     */
    private static LocalDateTime ParseTime(String time) {
        LocalDateTime parsedTime = null;
        int day = 0;
        int month = 0;
        int year = 0;
        int hour = 0;
        int minute = 0;

        if (time.contains("/") && time.indexOf("/", 3) != -1 && time.contains(" ") && !time.contains("-")) {
            int endIndex1 = time.indexOf("/");
            int endIndex2 = time.lastIndexOf(" ");
            day = Integer.parseInt(time.substring(0, endIndex1));

            Integer dayInteger = day;
            int endIndex3 = time.indexOf("/", dayInteger.toString().length() + 1);
            month = Integer.parseInt(time.substring(endIndex1 + 1, endIndex3));
            year = Integer.parseInt(time.substring(endIndex3 + 1,  endIndex2));

            hour = Integer.parseInt(time.substring(endIndex2 + 1).substring(0, 2));
            minute = Integer.parseInt(time.substring(endIndex2 + 1).substring(2));
        } else if (time.contains("-")) {
            try {
                parsedTime = LocalDate.parse(time).atTime(0,0);
                return parsedTime;
            } catch (DateTimeParseException e){
                return null;
            }
        } else {
            return null;
        }

        //Some Other cases;
        if (!ValidDate(day,month,year,hour,minute)){
            return null;
        } else {
            parsedTime = LocalDate.of(year,month,day).atTime(hour, minute);
        }


        return parsedTime;
    }


    private static void HandleTask(String Message) throws DukeException{
        String task = "";
        String time = "";

        //If the task type does not belong to the three types, throw an error.
        if (!(Message.startsWith("todo") || Message.startsWith("event") || Message.startsWith("deadline"))){
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        //Get Task description and time if it has it.
        if (Message.contains("/")) {
            task = Message.substring(Message.indexOf(" ") + 1, Message.indexOf("/") - 1);

            //throw exceptions for deadline or events' format.
            if (Message.startsWith("deadline")) {
                if (Message.contains("/by")) {
                    time = Message.substring(Message.indexOf("/by") + 4);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but the format of deadline is wrong :-(");
                }
            } else if (Message.startsWith("event")) {
                if (Message.contains("/at")) {
                    time = Message.substring(Message.indexOf("/at") + 4);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but the format of event is wrong :-(");
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but the format of todo is wrong :-(");
            }
        }
        else {
            if (!Message.contains(" ")) {
                throw new DukeException("☹ OOPS!!! The description of a " + Message +" cannot be empty.");
            }else {
                task = Message.substring(Message.indexOf(" ") + 1);

                time = "";
            }
        }

        //Time for deadlines or event cannot be empty.
        if ((Message.startsWith("event") || Message.startsWith("deadline")) && time.equals("")) {
            throw new DukeException("☹ OOPS!!! The time of a " + Message.substring(0, Message.indexOf(" ")) +" cannot be empty.");
        }

        System.out.println("Got it. I've added this task: ");
        LocalDateTime parsedTime = ParseTime(time);
        String taskType = Message.substring(0, Message.indexOf(" "));
        TaskType[] taskTypes = TaskType.values();
        for (TaskType t : taskTypes) {
            if (t.toString().equals(taskType)){
                Task newTask = t.AssignTaskType(t, task, parsedTime);
                System.out.println(" " + newTask.PrintTaskInfo());
                list.add(newTask);
                break;
            }
        }

        System.out.println("Now you have " + list.size() + "" +
                " tasks in the list.");
    }


    private static void MarkDone(int index) throws DukeException{
        if (index < 0 || index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the index is invalid :-(");
        } else {
            System.out.println("Nice! I've marked this task as done:");
            list.get(index).MarkDone();
            System.out.println(" " + list.get(index).PrintTaskInfo());
        }
    }

    private static void Delete(int index) throws DukeException{
        if (index < 0 || index >= list.size()) {
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

        LocalDateTime parsedTime = ParseTime(time);
        TaskType[] taskTypes = TaskType.values();
        for (TaskType t : taskTypes) {
            if (t.toString().toUpperCase().charAt(0) == taskType){
                Task newTask = t.AssignTaskType(t, task, parsedTime);
                list.add(newTask);
                break;
            }
        }

        return done;
    }

    private static void ReadDataFromFile(String filePath) throws FileNotFoundException {
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

    private static void LoadData(){
        try {
            ReadDataFromFile("data/duke.txt");
        } catch (FileNotFoundException e1){
            System.out.println("No File in the given root. Create a new file.");
            try {
                FileWriter fw = new FileWriter("data/duke.txt");
                fw.close();
            } catch (IOException e2) {
                System.out.println("Something went wrong: " + e2.getMessage());
            }
        }
    }

    private static void OperationForDuke(String t, String Message) {
        int index;
        String date = "";
        switch (t) {
            case "bye": {
                String Goodbye_message = "Bye. Hope to see you again soon!";
                System.out.println(Goodbye_message);
                break;}
            case "list": {
                PrintList();
                break;
            }
            case "done": {
                try {
                    index = (Message.contains(" "))? Integer.parseInt(Message.substring(Message.indexOf(" ") + 1)) - 1
                            :-1;
                    MarkDone(index);
                } catch (DukeException e){
                    e.PrintErrorMessage();
                }
                break;
            }
            case "delete":{
                try {
                    index = (Message.contains(" "))? Integer.parseInt(Message.substring(Message.indexOf(" ") + 1)) - 1
                            :-1;
                    Delete(index);
                } catch (DukeException e){
                    e.PrintErrorMessage();
                }
                break;
            }
            case "tell": {
                date = (Message.contains(" "))? Message.substring(Message.indexOf(" ") + 1)
                        :"nope";
                SpecificDateEvent(date);
                break;
            }
            default:{
                try {
                    HandleTask(Message);
                } catch (DukeException e)
                {
                    e.PrintErrorMessage();
                }
                break;
            }
        }
    }


    private static void PrintMessage() {
        Scanner scanner = new Scanner(System.in);
        String Message = "";
        String OperationType = "";

        //Use loop to determine if a user enters "bye" or not.
        while (!Message.equals("bye")) {
            Message = scanner.nextLine();

            if (Message.contains(" ")) {
                OperationType = Message.substring(0, Message.indexOf(" "));
            } else {
                OperationType = Message;
            }
            System.out.println(line);
            OperationForDuke(OperationType, Message); // Choose an operation for duke to do.
            System.out.println(line + "\n");
            UpdateSaveData(); //Update the SaveData every time a round of operation is done.
        }
    }

    private enum TaskType{
        todo, deadline, event;

        public Task AssignTaskType(TaskType t,String task, LocalDateTime time){
            switch (t) {
                case todo: return new ToDos(false, task);
                case deadline: return new Deadlines(false, task, time);
                case event: return new Events(false, task, time);
                default: return null;
            }
        }
    }



    public static void main(String[] args) {
       //Print Hello.
        HelloMessage();

        //Load Save Data
        LoadData();

        //Print Message();
        PrintMessage();



    }
}
