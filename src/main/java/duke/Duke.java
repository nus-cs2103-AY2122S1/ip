package duke;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import java.time.LocalDate;
import java.time.LocalTime;


public class Duke {

    public static class Task{

        protected String description;
        protected boolean isDone;

        public Task(String description, boolean isDone) {
            this.description = description;
            this.isDone = isDone;
        }

        public String getStatusIcon() {
            return (this.isDone ? "X" : " "); // mark done task with X
        }

        public void setDone(){
            this.isDone = true;
            System.out.println(this.toString());
        }

        @Override
        public String toString(){
            return "[" + this.getStatusIcon() + "] " + this.description;
        }

    }

    public static class ToDo extends Task {
        public ToDo(String description, boolean isDone){
            super(description, isDone);
        }

        @Override
        public String toString(){
            return "[T] " + "[" + this.getStatusIcon() + "] " + this.description;
        }

    }

    public static class Deadline extends Task {
        protected LocalDate date;
        protected LocalTime time;

        public Deadline(String description, LocalDate date, LocalTime time, boolean isDone){
            //input: dd/mm/yyyy, time
            super(description, isDone);
            this.date = date;
            this.time = time;
        }

        @Override
        public String toString(){
            //output: yyyy-mm-dd, time
            return "[D] " + "[" + this.getStatusIcon() + "] " + this.description
                    + " (by: " + this.date + " " + this.time + ")";
        }
    }

    public static class Event extends Task {
        protected LocalDate date;
        protected LocalTime startTime;
        protected LocalTime endTime;

        public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isDone){
            //fixed date
            super(description, isDone);
            this.date = date;
            this.startTime = startTime;
            this.endTime =  endTime;

        }

        @Override
        public String toString(){
            return "[E] " + "[" + this.getStatusIcon() + "] " + this.description
                    + " (at: " + this.date + " " + this.startTime + " to " + this.endTime + ")";
        }
    }


    public static class Storage{
        //handles file writing and data extraction
        protected String path; //path of task file

        public Storage(String path){
            this.path = path;
        }

        public void save(ArrayList<Task> tasks){
            //create a new file if not available
            try {
                File myObj = new File(this.path);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            //write each task to the task as a new line
            try {
                FileWriter myWriter = new FileWriter(this.path);
                for (int i = 1; i < tasks.size()+1; i++) {
                    myWriter.write(i + ". " + tasks.get(i-1).toString());
                    myWriter.write("\n");
                }
                myWriter.close();

            } catch (IOException e){
                System.out.println("Oops! An error occurred.");
                e.printStackTrace();
            }
        }

        public ArrayList<Task> load(){
            //reads the file line by line and adds tasks saved by the file.
            ArrayList<Task> loadedList = new ArrayList<>();

            BufferedReader reader;
            int lineNumber = 1;
            try {

                reader = new BufferedReader(new FileReader(this.path));
                String line = reader.readLine();

                while (line != null) {
                    String description;
                    boolean done;
                    int startIndex = String.valueOf(lineNumber).length() + 3;

                    if (line.charAt(startIndex) == 'T'){
                        //to-do task
                        done = (line.charAt(startIndex+4) == 'X');
                        description = line.substring(startIndex+7);

                        loadedList.add(new ToDo(description, done));

                    } else {
                        int endIndex = line.length()-1;
                        int day, month, year;
                        LocalDate date;

                        if(line.charAt(startIndex) == 'D'){
                            int hours, minutes;
                            LocalTime time;
                            //deadline task
                            done = (line.charAt(startIndex+4) == 'X');

                            minutes = Integer.parseInt(line.substring(endIndex-2,endIndex));
                            hours = Integer.parseInt(line.substring(endIndex-5, endIndex-3));

                            day = Integer.parseInt(line.substring(endIndex-8,endIndex-6));
                            month = Integer.parseInt(line.substring(endIndex-11,endIndex-9));
                            year = Integer.parseInt(line.substring(endIndex-16,endIndex-12));

                            description = line.substring(startIndex+7, endIndex-22);

                            date = java.time.LocalDate.of(year, month, day);
                            time = java.time.LocalTime.of(hours, minutes);

                            loadedList.add(new Deadline(description,date,time,done));

                        } else{

                            int startHours, endHours;
                            LocalTime startTime;
                            int startMinutes, endMinutes;
                            LocalTime endTime;
                            //event task

                            done = (line.charAt(startIndex + 4) == 'X');

                            endMinutes = Integer.parseInt(line.substring(endIndex - 2, endIndex));
                            endHours = Integer.parseInt(line.substring(endIndex - 5, endIndex - 3));

                            startMinutes = Integer.parseInt(line.substring(endIndex - 11, endIndex - 9));
                            startHours = Integer.parseInt(line.substring(endIndex - 14, endIndex - 12));

                            day = Integer.parseInt(line.substring(endIndex - 17, endIndex - 15));
                            month = Integer.parseInt(line.substring(endIndex - 20, endIndex - 18));
                            year = Integer.parseInt(line.substring(endIndex - 25, endIndex - 21));

                            description = line.substring(startIndex + 7, endIndex - 31);

                            date = java.time.LocalDate.of(year, month, day);
                            startTime = java.time.LocalTime.of(startHours, startMinutes);
                            endTime = java.time.LocalTime.of(endHours, endMinutes);

                            loadedList.add(new Event(description, date, startTime, endTime, done));
                        }
                        //parse each line and create a task to put into the arraylist

                    }
                    //read next line
                    line = reader.readLine();
                    lineNumber++;
                    System.out.println();
                }
                System.out.println("Successfully loaded task list from file!");
                reader.close();
            } catch (IOException e) {
                System.out.println("Oops! I couldn't load the task list file.");
                System.out.println("Starting over with a brand new list.");
            }

            return loadedList;
        }

    }

    public static class TaskList{
        //contains the list of tasks and operations to the list.
        protected ArrayList<Task> list;

        //if list cannot be loaded, create an empty list to start
        public TaskList(){
            this.list = new ArrayList<>();
        }

        public TaskList(ArrayList<Task> loadedList){
            this.list = loadedList;
        }

        public ArrayList<Task> getList(){
            return this.list;
        }

        public int getSize(){
            return this.list.size();
        }

        public Task getTask(int index){
            return this.list.get(index);
        }

        //add a task at the end of the list
        public void add(Task task){
            this.list.add(task);
        }

        //delete a task of a specific index
        public void delete(int index){
            this.list.remove(index);
        }

        //mark task at specific index as done
        public void markDone(int index){
            this.list.get(index).setDone();
        }

        //print all tasks, used for "list" command.
        public void printAllTasks(){
            for (int i = 0; i<this.list.size(); i++){
                System.out.println(this.list.get(i));
            }
        }

    }

    public static class Ui{
        //Shows messages and takes in user input.
        Scanner reader;
        public Ui(){
            this.reader = new Scanner(System.in);
        }
        public String readInput(String prompt){
            System.out.println(prompt);
            return this.reader.nextLine();
        }
        public void showHelp(){
            String logo = "                                        _       \n"
                    + "  _ __  _ _   _ __  ___ ___ ___ ___ ___| |__ ___\n"
                    + " | '  \\| '_| | '  \\/ -_) -_|_-</ -_) -_) / /(_-<\n"
                    + " |_|_|_|_|   |_|_|_\\___\\___/__/\\___\\___|_\\_\\/__/\n"
                    + "\n";

            System.out.println(logo);
            System.out.println("I'm Mr Meeseeks look at me!");
            System.out.println("Type in \"todo\", \"deadline\" or \"event\" and I will keep track of a task!");
            System.out.println("Type \"list\" to show all tasks so far");
            System.out.println("Type \"done\" to mark a task as done");
            System.out.println("Type \"bye\" to exit");
        }

        public void showError(String errorType){
            switch (errorType){
                case "loadFail":
                    System.out.println("Oops! I couldn't load the task file.");
                    System.out.println("Starting over with an empty task list.");
                case "invalidCommand":
                    System.out.println("Oops! Sorry I don't recognise that command!");
                    System.out.println("Type \"help\" for list of commands, check for illegal characters/trailing spaces");
                    break;
                case "emptyInput":
                    System.out.println("Oops! Please input something.");
                    break;
                case "invalidDate":
                    System.out.println("Oops! Please follow the format: dd/mm/yyyy hhmm or I can't process the date!");
                    break;
                case "noTasks":
                    System.out.println("Oops! There are no tasks yet!");
                    break;
                case "maxTasks":
                    System.out.println("Oops! My task list is full, no new tasks can be added.");
                    break;
                case "invalidIndex":
                    System.out.println("Oops! Please enter a valid task number.");
                    break;
                default:
                    System.out.println("Oops! An unknown error occurred.");
            }

        }

        public void showMessage(String s) {
            System.out.println(s);
        }

        public void taskCreationSuccess(Task t){
            System.out.println("Ok! I've created and added the task below");
            System.out.println(t);
        }

    }
    public static class Parser{

        Ui ui;
        public Parser(Ui ui){
            this.ui = ui;
        }

        //Check user commands and handle them.
        public void parseCommand(String input, TaskList list){
            switch (input){
                case "bye":
                    ui.showMessage("Bye! *Poof!*");
                    System.exit(0);
                case "help":
                    ui.showHelp();
                    break;
                case "list":
                    ui.showMessage("Tasks so far:");
                    list.printAllTasks();
                    break;
                case "done":
                    if (list.getSize() == 0){
                        ui.showError("noTasks");
                    } else{
                        while (true){
                            try {
                                int index = Integer.parseInt(ui.readInput("Can do! Which task should I mark as done?\n"
                                        + "From task number 1 to " + list.getSize()));
                                if (index > list.getSize() || index <= 0) {
                                    ui.showError("invalidIndex");
                                    continue;
                                }
                                ui.showMessage("Ok I've marked the following task as done");
                                list.markDone(index);
                                break;
                            } catch (Exception e){
                                ui.showError("invalidIndex");
                            }
                        }

                    }
                    break;
                case "delete":
                    if (list.getSize() == 0) {
                        ui.showError("noTasks");
                    } else {
                        while (true) {
                            try {
                                int index = Integer.parseInt(ui.readInput(
                                        "Can Do! Which task should I delete?\n" +
                                                "From task number 1 to " + list.getSize()));
                                if (index > list.getSize() || index <= 0) {
                                    ui.showError("invalidIndex");
                                    continue;
                                }
                                ui.showMessage("Ok, boom this task is gone!");
                                System.out.println(list.getTask(index));
                                list.delete(index);
                                ui.showMessage("There are now " + list.getSize() + " tasks");
                                break;
                            } catch (Exception e){
                                ui.showError("invalidIndex");
                            }
                        }

                    }
                    break;
                case "todo":
                    ui.showMessage("Ok! A to-do task");

                    while (true){
                        String description;
                        description = ui.readInput("What is the description of the task?");
                        if (description.equals("")){
                            ui.showError("emptyInput");
                        } else{
                            Task t = new ToDo(description, false);
                            ui.taskCreationSuccess(t);
                            list.add(t);
                            ui.showMessage("There are now " + list.getSize() + " tasks");
                            break;
                        }
                    }
                    break;
                case "deadline":

                    ui.showMessage("Ok! A Deadline task");
                    String description;
                    int day, month, year;
                    int hours, minutes;
                    LocalDate date;
                    LocalTime time;

                    while (true){
                        description = ui.readInput("What is the description of the task?");
                        if (description.equals("")){
                            ui.showError("emptyInput");
                        } else{
                            break;
                        }
                    }
                    while (true) {

                        input = ui.readInput("Enter the deadline for the task\n" +
                                "Enter date/time in this exact format: dd/mm/yyyy hhmm");

                        try {
                            day = Integer.parseInt(input.substring(0, 2));
                            month = Integer.parseInt(input.substring(3, 5));
                            year = Integer.parseInt(input.substring(6, 10));

                            hours = Integer.parseInt(input.substring(11, 13));
                            minutes = Integer.parseInt(input.substring(13));

                            date = java.time.LocalDate.of(year, month, day);
                            time = java.time.LocalTime.of(hours, minutes);

                            break;
                        } catch (Exception e) {
                            ui.showError("invalidDate");
                        }


                    }

                    Task t = new Deadline(description, date, time, false);
                    ui.taskCreationSuccess(t);
                    list.add(t);
                    ui.showMessage("There are now " + list.getSize() + " tasks");
                    break;

                case "event":
                    ui.showMessage("Ok! A Deadline task");
                    int startHours, endHours, startMinutes, endMinutes;
                    LocalTime startTime, endTime;
                    while (true){
                        description = ui.readInput("What is the description of the task?");
                        if (description.equals("")){
                            ui.showError("emptyInput");
                        } else{
                            break;
                        }
                    }

                    while (true) {

                        input = ui.readInput("Enter the event time range for the task\n" +
                                "Enter date/time in this exact format: dd/mm/yyyy hhmm hhmm");

                        try {
                            day = Integer.parseInt(input.substring(0, 2));
                            month = Integer.parseInt(input.substring(3, 5));
                            year = Integer.parseInt(input.substring(6, 10));

                            startHours = Integer.parseInt(input.substring(11, 13));
                            startMinutes = Integer.parseInt(input.substring(13,15));

                            endHours = Integer.parseInt(input.substring(16,18));
                            endMinutes = Integer.parseInt(input.substring(18));

                            date = java.time.LocalDate.of(year, month, day);
                            startTime = java.time.LocalTime.of(startHours, startMinutes);
                            endTime = java.time.LocalTime.of(endHours, endMinutes);

                            break;
                        } catch (Exception e) {
                            ui.showError("invalidDate");
                        }


                    }
                    t = new Event(description, date, startTime, endTime, false);
                    ui.taskCreationSuccess(t);
                    list.add(t);
                    ui.showMessage("There are now " + list.getSize() + " tasks");
                    break;

                default:
                    //invalid command
                    ui.showError("invalidCommand");
                    break;
            }

        }

    }


    public static void main(String[] args) {
        //initialise ui, parser and task list
        Ui ui = new Ui();
        Parser parser = new Parser(ui);
        TaskList tasks;

        //Attempt to load file
        String FILEPATH = "./duke.txt";
        Storage storage = new Storage(FILEPATH);

        tasks = new TaskList(storage.load());

        //run program
        ui.showHelp();

        while (true){
            //read input, parse command, update list if needed, save to file.
            String input = ui.readInput("");
            parser.parseCommand(input,tasks);
            storage.save(tasks.getList());
        }

    }
}

