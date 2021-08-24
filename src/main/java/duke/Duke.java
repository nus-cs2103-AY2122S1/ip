package duke;

import java.time.DateTimeException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import java.lang.String;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;


public class Duke {
    private static List<Task> tasks = new ArrayList<Task>();
    //private static List<String> lines;
    private static Ui ui = new Ui();
    private static Storage storage = new Storage();
    private static TaskList taskList = new TaskList();

    private static void listFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        TaskList.lines = new ArrayList<String>();
        while (s.hasNext()) {
            TaskList.lines.add(s.nextLine());
        }
    }

    public static void main(String[] args) {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        File file = new File("data/list.txt");
        storage.checkExistence();

        ui.showFileLocation(file.getAbsolutePath());

        try {
            storage.listFileContents(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(true){
            String command = sc.nextLine();
            if(command.equals("bye")) {
                sc.close();
                ui.sayBye();
                break;
            }else if(command.equals("list")) {
                /*
                try {
                    printFileContents(file.getPath());
                } catch (FileNotFoundException e) {
                    System.err.println("WHERES THE FILE OH GOD SOMETHING TERRIBLE HAS HAPPENED");
                }
                */

                ui.showList(TaskList.lines);

            }else if(command.contains("done")) {
                String numbers = command.substring(5);
                try {
                    int taskNo = Integer.parseInt(numbers);
                } catch (NumberFormatException notANumber) {
                    System.err.println(notANumber);
                    ui.showNotANumberMsg();
                    continue;
                }
                int taskNo = Integer.parseInt(numbers);
                if (TaskList.lines.size() < taskNo) {
                    ui.showOutOfBoundsMsg(TaskList.lines.size());
                    continue;
                }
                if (taskNo <= 0) {
                    ui.showLessThanZeroMsg(taskNo);
                    continue;
                }
                taskNo--;
                String toBeDone = TaskList.lines.get(taskNo);
                if(toBeDone.contains("[ ]")){
                    toBeDone=toBeDone.substring(0,4)+"X"+toBeDone.substring(5);
                    TaskList.lines.set(taskNo,toBeDone);
                    try {
                        storage.writeListToFile(file.getPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ui.showMarkedAsDone(toBeDone);
                }else{
                    ui.showAlreadyDone();
                }
            }else if(command.contains("delete") || command.contains("remove")){
                String numbers = command.substring(7);
                try {
                    int taskNo = Integer.parseInt(numbers);
                } catch (NumberFormatException notANumber) {
                    System.err.println(notANumber);
                    ui.showNotANumberMsg();
                    continue;
                }
                int taskNo = Integer.parseInt(numbers);
                if (TaskList.lines.size() < taskNo) {
                    ui.showOutOfBoundsMsg(TaskList.lines.size());
                    continue;
                }
                if (taskNo <= 0) {
                    ui.showLessThanZeroMsg(taskNo);
                    continue;
                }
                taskNo--;
                String toBeDeleted=TaskList.lines.get(taskNo);
                TaskList.lines.remove(taskNo);
                try {
                    storage.writeListToFile(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ui.showDeletionMsg(toBeDeleted,TaskList.lines.size());

            }else if(command.contains("todo")){
                String task=command.substring(5);
                if(task.equals("")){
                    ui.showNoNameError();
                    continue;
                }
                ToDo taskToAdd = new ToDo(task);

                String toBeAdded = taskToAdd.toString();
                if(!TaskList.lines.contains(toBeAdded)){
                    TaskList.lines.add(toBeAdded);
                    ui.showTaskAdded(toBeAdded);
                    ui.showListSize(TaskList.lines.size());
                }else{
                    ui.showAlreadyInList(toBeAdded);
                }
                try {
                    storage.writeListToFile(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if(command.contains("deadline")){
                String taskNDate=command.substring(9);
                if(!(taskNDate.contains("/by"))){
                    ui.showNoDateError();
                    continue;
                }
                int splitIndex=taskNDate.indexOf("/by");
                String task =taskNDate.substring(0,splitIndex-1);
                String date =taskNDate.substring(splitIndex+4);
                if(task.equals("")){
                    ui.showNoNameError();
                    continue;
                }

                try{
                    LocalDate test = LocalDate.parse(date);
                }catch(DateTimeException error){
                    ui.showBadDateError();
                    continue;
                }

                Deadline taskToAdd = new Deadline(task,date);
                String toBeAdded = taskToAdd.toString();
                if (!TaskList.lines.contains(toBeAdded)){
                    TaskList.lines.add(toBeAdded);
                    ui.showTaskAdded(toBeAdded);
                    ui.showListSize(TaskList.lines.size());
                }else{
                    ui.showAlreadyInList(toBeAdded);
                }

                try {
                    storage.writeListToFile(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if(command.contains("event")) {
                String taskNDate = command.substring(6);
                if (!(taskNDate.contains("/at"))) {
                    ui.showNoDateError();
                    continue;
                }
                int splitIndex = taskNDate.indexOf("/at");
                String task = taskNDate.substring(0, splitIndex - 1);
                String date = taskNDate.substring(splitIndex + 4);
                if (task.equals("")) {
                    ui.showNoNameError();
                    continue;
                }

                try{
                    LocalDate test = LocalDate.parse(date);
                }catch(DateTimeException error){
                    ui.showBadDateError();
                    continue;
                }

                Deadline taskToAdd = new Deadline(task,date);
                String toBeAdded = taskToAdd.toString();
                if (!TaskList.lines.contains(toBeAdded)) {
                    TaskList.lines.add(toBeAdded);
                    ui.showTaskAdded(toBeAdded);
                    ui.showListSize(TaskList.lines.size());
                } else {
                    ui.showAlreadyInList(toBeAdded);
                }

                try {
                    storage.writeListToFile(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if(command.equals("WIPE")){
                System.out.println("ARE YOU SURE? SAY Y IF YOU ARE AND LITERALLY ANYTHING ELSE IF YOU AREN'T");
                if(sc.nextLine().equals("Y")) {
                    TaskList.lines.clear();
                    try {
                        storage.writeListToFile(file.getPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("BAYUM! The list has been wiped. How tragic.");
                }else{
                    System.out.println("WOWZA! That was real close.");
                }
            }else{
                System.out.println("(WHAT IS THIS PERSON TRYING TO SAY WHY IS HE TYPING GIBBERISH I'M JUST TRYING TO SURVIVE)");
            }
        }
    }


}
