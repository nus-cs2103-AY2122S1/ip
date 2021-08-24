package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import java.lang.String;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    private static List<Task> tasks = new ArrayList<Task>();
    private static List<String> lines;

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void listFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        lines = new ArrayList<String>();
        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
    }

    private static void writeListToFile(String filePath) throws IOException{
        FileWriter clearer = new FileWriter(filePath);
        clearer.write(""); //clear the file
        clearer.close();
        FileWriter fw = new FileWriter(filePath, true);
        for(String line : lines){
            fw.write(line + System.lineSeparator());
        }
        fw.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome. I am your virtual assistant Duke. Sparkle up your day (TM).");
        Scanner sc = new Scanner(System.in);
        File file = new File("data/list.txt");
        if(!file.exists()){
            try {
                new File("data").mkdir();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Your task list is located in: " + file.getAbsolutePath() + ". SPARKLEOUS.");

        try {
            listFileContents(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(true){
            String command = sc.nextLine();
            if(command.equals("bye")) {
                sc.close();
                System.out.println("Have a SPARKULAR day.");
                break;
            }else if(command.equals("list")) {
                /*
                try {
                    printFileContents(file.getPath());
                } catch (FileNotFoundException e) {
                    System.err.println("WHERES THE FILE OH GOD SOMETHING TERRIBLE HAS HAPPENED");
                }
                */
                int c = 1;
                for (String line : lines) {
                    System.out.println(c + ". " + line);
                    c++;
                }
            }else if(command.contains("done")) {
                String numbers = command.substring(5);
                try {
                    int taskNo = Integer.parseInt(numbers);
                } catch (NumberFormatException notANumber) {
                    System.err.println(notANumber);
                    System.err.println("JUST GIVE ME A NUMBER, WHY ARE YOU DOING THIS");
                    continue;
                }
                int taskNo = Integer.parseInt(numbers);
                if (lines.size() < taskNo) {
                    System.err.println("hello sir there are only " + lines.size() + " tasks in the list sir");
                    continue;
                }
                if (taskNo <= 0) {
                    System.err.println("HOW CAN I COMPLETE THE TASK AT INDEX " + taskNo + "? IT DOESNT MAKE ANY SENSE");
                    continue;
                }
                taskNo--;
                String toBeDone = lines.get(taskNo);
                if(toBeDone.contains("[ ]")){
                    toBeDone=toBeDone.substring(0,4)+"X"+toBeDone.substring(5);
                    lines.set(taskNo,toBeDone);
                    try {
                        writeListToFile(file.getPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(toBeDone + " has been marked as done");
                }else{
                    System.out.println("It is already done. How SPARKTACULAR.");
                }
            }else if(command.contains("delete")){
                String numbers = command.substring(7);
                try {
                    int taskNo = Integer.parseInt(numbers);
                } catch (NumberFormatException notANumber) {
                    System.err.println(notANumber);
                    System.err.println("JUST GIVE ME A NUMBER, WHY ARE YOU DOING THIS");
                    continue;
                }
                int taskNo = Integer.parseInt(numbers);
                if (lines.size() < taskNo) {
                    System.err.println("hello sir there are only " + lines.size() + " tasks in the list sir");
                    continue;
                }
                if (taskNo <= 0) {
                    System.err.println("HOW CAN I COMPLETE THE TASK AT INDEX " + taskNo + "? IT DOESNT MAKE ANY SENSE");
                    continue;
                }
                taskNo--;
                String toBeDeleted=lines.get(taskNo);
                lines.remove(taskNo);
                try {
                    writeListToFile(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("task " +toBeDeleted+ " has been deleted.\nThere are "+lines.size()+" tasks left in the list.");

            }else if(command.contains("todo")){
                String task=command.substring(5);
                if(task.equals("")){
                    System.err.println("I NEED A NAME SIR");
                    continue;
                }
                String toBeAdded = "(T)[ ] " + task;
                if(!lines.contains(toBeAdded)){
                    lines.add("(T)[ ] " + task);
                    System.out.println("todo " + task + " added.");
                    System.out.println("the list has "+lines.size()+" tasks now.");
                }else{
                    System.out.println(task + " is already in the list sir");
                }
                try {
                    writeListToFile(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if(command.contains("deadline")){
                String taskNDate=command.substring(9);
                if(!(taskNDate.contains("/by"))){
                    System.err.println("BY WHEN? HOW CAN YOU HAVE A DEADLINE WITHOUT A DEADLINE???");
                    continue;
                }
                int splitIndex=taskNDate.indexOf("/by");
                String task =taskNDate.substring(0,splitIndex-1);
                String date =taskNDate.substring(splitIndex+4);
                if(task.equals("")){
                    System.err.println("I NEED A NAME SIR");
                    continue;
                }

                String toBeAdded = "(D)[ ] " + task + " (by: " + date + ")";
                if (!lines.contains(toBeAdded)){
                    lines.add("(D)[ ] " + task + " (by: " + date + ")");
                    System.out.println("deadline " + task + " added.");
                    System.out.println("the list has "+lines.size()+" tasks now.");
                }else{
                    System.out.println(task + " is already in the list sir");
                }

                try {
                    writeListToFile(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if(command.contains("event")) {
                String taskNDate = command.substring(6);
                if (!(taskNDate.contains("/at"))) {
                    System.err.println("AT WHEN? YOU HAVE AN EVENT BUT YOU DONT KNOW WHERE IT IS???");
                    continue;
                }
                int splitIndex = taskNDate.indexOf("/at");
                String task = taskNDate.substring(0, splitIndex - 1);
                String date = taskNDate.substring(splitIndex + 4);
                if (task.equals("")) {
                    System.err.println("I NEED A NAME SIR");
                    continue;
                }

                String toBeAdded = "(E)[ ] " + task + " (at: " + date + ")";
                if (!lines.contains(toBeAdded)) {
                    lines.add("(E)[ ] " + task + " (at: " + date + ")");
                    System.out.println("event " + task + " added.");
                    System.out.println("the list has " + lines.size() + " tasks now.");
                } else {
                    System.out.println(task + " is already in the list sir");
                }

                try {
                    writeListToFile(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if(command.equals("WIPE")){
                System.out.println("ARE YOU SURE? SAY Y IF YOU ARE AND LITERALLY ANYTHING ELSE IF YOU AREN'T");
                if(sc.nextLine().equals("Y")) {
                    lines.clear();
                    try {
                        writeListToFile(file.getPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("The list has been wiped. How tragic.");
                }else{
                    System.out.println("WOWZA! That was real close.");
                }
            }else{
                System.out.println("(WHAT IS THIS PERSON TRYING TO SAY WHY IS HE TYPING GIBBERISH I'M JUST TRYING TO SURVIVE)");
            }
        }
    }


}
