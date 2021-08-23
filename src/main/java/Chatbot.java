// /**
//  * 
//  * This file contains the main logic behind the Duke chatbot.
//  * 
//  * @author: @rish-16
//  * @version: CS2103, AY21/22 Semester 1
//  * 
//  */

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.io.IOException;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

// /**
//  * This represents the chatbot and its business logic.
//  */
// public class Chatbot {
//     private static final String TODO_NO_DESC = "OOPS!!! The description of a todo cannot be empty.";
//     private static final String DEADLINE_NO_INFO = "OOPS!!! A deadline must have a description and datetime limit.";
//     private static final String UNRECOG = "OOPS!!! I'm sorry, this is an unrecognised command.";
//     private static final String EVENT_NO_INFO = "OOPS!!! An event must have a description and datetime duration.";
//     private static final String IDX_OUT_OF_BOUNDS = "OOPS!!! You provided an invalid task index. Try again.";

//     private ArrayList<Task> dataStore; // stores the actual tasks
    
//     // Duke class constructor
//     public Chatbot() {
//         dataStore = new ArrayList<Task>();
//     }

//     /**
//      * Stores tasks from a flat-file-based memory.
//      * 
//      * @param store The pre-defined store from the text file
//      */
//     public void ingestData(ArrayList<String> store) {
//         for (int i = 0; i < store.size(); i++) {
//             String record = store.get(i);
//             String[] splitRecord = record.split(";");
            
//             for (int j = 0; j < splitRecord.length; j++) {
//                 splitRecord[j] = splitRecord[j].strip();
//             }

//             if (splitRecord[0].equals("T")) {
//                 ToDo todoTask = new ToDo(splitRecord[2]);
//                 if (splitRecord[1].equals("1")) {
//                     todoTask.setDone();
//                 }

//                 // add to task data store
//                 dataStore.add(todoTask);
//             } else if (splitRecord[0].equals("D")) {
//                 Deadline deadlineTask = new Deadline(splitRecord[2], splitRecord[3]);
//                 if (splitRecord[1].equals("1")) {
//                     deadlineTask.setDone();
//                 }

//                 // add to task data store
//                 dataStore.add(deadlineTask);
//             } else if (splitRecord[0].equals("E")) {
//                 Event eventTask = new Event(splitRecord[2], splitRecord[3]);
//                 if (splitRecord[1].equals("1")) {
//                     eventTask.setDone();
//                 }

//                 // add to task data store
//                 dataStore.add(eventTask);
//             }
//         }
//     }

//     /**
//      * Throws exceptions when unexpected inputs are passed in to the chatbot.
//      * 
//      * @param input the user query from Scanner
//      * @throws BotException
//      */
//     public void errorHandler(String input) throws BotException {
//         input = input.strip(); // remove whitespace
//         String[] brokenInput = input.split(" ");

//         if (input.equals("todo")) {
//             throw new BotException(TODO_NO_DESC);
//         } else if (input.equals("deadline") || input.contains("deadline") && !input.contains("/by")) {
//             throw new BotException(DEADLINE_NO_INFO);
//         } else if (input.equals("event") || input.contains("event") && !input.contains("/at")) {
//             throw new BotException(EVENT_NO_INFO);
//         }

//         ArrayList<String> prunedInput = new ArrayList<String>();
//         for (String str : brokenInput) {
//             if (str.length() > 0) {
//                 prunedInput.add(str);
//             }
//         }

//         // advanced syntax errors
//         if (prunedInput.get(0).equals("deadline")) {
//             // deadline with no description
//             if (prunedInput.get(1).equals("/by")) { // eg: deadline /by Mon 2PM
//                 throw new BotException(DEADLINE_NO_INFO);
//             }

//             // deadline with no date limit
//             int byIndex = prunedInput.indexOf("/by");
//             if (byIndex == prunedInput.size() - 1) { // eg: deadline write program /by
//                 throw new BotException(DEADLINE_NO_INFO);
//             }
//         } else if (prunedInput.get(0).equals("event")) {
//             // event with no description
//             if (prunedInput.get(1).equals("/at")) { // eg: event /at Mon 2-4PM
//                 throw new BotException(EVENT_NO_INFO);
//             }

//             // event with no date
//             int byIndex = prunedInput.indexOf("/at");
//             if (byIndex == prunedInput.size() - 1) { // eg: event visit mom /at
//                 throw new BotException(EVENT_NO_INFO);
//             }
//         }
//     }

//     /**
//      * 
//      * Returns the parsed LocalDate object using the given string date.
//      * 
//      * @param date The date to be parsed in String format.
//      * @return the LocalDate representation of the string date.
//      */
//     public String processDate(String date) {
//         date = date.strip();
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//         LocalDateTime datetime = LocalDateTime.parse(date, formatter);
//         DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
//         String finalTime = datetime.format(newFormatter);
//         System.out.println(finalTime);
        
//         return finalTime;
//     }

//     /**
//      * Handles the main business logic of the chatbot. 
//      * 
//      * @param input the user query from Scanner
//      * @throws BotException
//      */
//     public void processInput(String input) throws BotException {
//         errorHandler(input);
        
//         if (input.equals("list")) {
//             for (int i = 0; i < dataStore.size(); i++) {
//                 Task task = dataStore.get(i);

//                 if (task.getStatus()) {
//                     System.out.println(i+1 + ". " + task.toString());
//                 } else {
//                     System.out.println(i+1 + ". " + task.toString());
//                 }
//             }
//         } else if (input.contains("todo")) {
//             // create new ToDo task
//             String query = input.split(" ", 2)[1].strip();
//             ToDo todo = new ToDo(query);
//             dataStore.add(todo);

//             System.out.println("Got it. I've added this task: ");
//             System.out.println("\t" + todo.toString());
//             System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
//         } else if (input.contains("deadline")) {
//             // create new Deadline task
//             input = input.split(" ", 2)[1].strip();
//             String query = input.split("/by")[0];
//             String limit = input.split("/by")[1];
//             String procLimit = processDate(limit);

//             Deadline deadlineTask = new Deadline(query, procLimit);
//             dataStore.add(deadlineTask);

//             System.out.println("Got it. I've added this task: ");
//             System.out.println("\t" + deadlineTask.toString());
//             System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
//         } else if (input.contains("event")) {
//             // Create new Event task
//             input = input.split(" ", 2)[1].strip();
//             String query = input.split("/at")[0];
//             String datetime = input.split("/at")[1];
//             String procDate = processDate(datetime);

//             Event eventTask = new Event(query, procDate);
//             dataStore.add(eventTask);

//             System.out.println("Got it. I've added this task: ");
//             System.out.println("\t" + eventTask.toString());
//             System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
//         } else if (input.contains("done")) {
//             // Mark task as completed
//             int idx = Integer.parseInt(input.split(" ")[1]);
            
//             // check if index is within appropriate range
//             if (idx < 0 || idx > dataStore.size()) {
//                 throw new BotException(IDX_OUT_OF_BOUNDS);
//             } else {
//                 Task task = dataStore.get(idx-1);
//                 task.setDone();
//                 System.out.println("Nice! I've marked this task as done: ");
//                 System.out.println("\t" + task.toString());
//             }
//         } else if (input.contains("delete")) {
//             int idx = Integer.parseInt(input.split(" ")[1]);
//             Task task = dataStore.get(idx-1);
            
//             System.out.println("Noted. I've removed this task:");
//             System.out.println("\t" + task.toString());
//             dataStore.remove(idx-1); // actual deletion
//             System.out.println("Now you have " + (dataStore.size()) + " tasks in the list."); 
//         } else {
//             throw new BotException(UNRECOG);
//         }
//     }

//     public void commitToMemory(String filePath) {
//         System.out.println("Changes have been saved to memory.");
//         MemoryBuffer memBuff = new MemoryBuffer(filePath, dataStore);
        
//         try {
//             memBuff.writeFile();
//         } catch (IOException err) {
//             System.out.println(err);
//         }
//     }
// }