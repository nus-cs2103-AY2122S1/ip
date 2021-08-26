import data.StoreRoom;
import display.Display;
import exception.DukeException;
import operation.Command;

import java.util.Scanner;

//import java.util.ArrayList;
//import java.util.Scanner;
//
//import exception.DukeException;
//import operation.Deadline;
//import operation.Task;
//import operation.Event;
//import operation.ToDo;
//
//public class Duke {
//	public static void main(String[] args) {
//		String welcomeString = "____________________________________________________________\n"
//				+ "Yo! Duke here...on behalf of Yang Yuzhao.\n"
//				+ "What do ya want from me?\n"
//				+ "____________________________________________________________\n";
//		String byeString = "____________________________________________________________\n"
//				+ "Duke out! Wake me up when ya need me again:)\n"
//				+ "____________________________________________________________\n";
//		String message;
//		Task task;
//		System.out.println(welcomeString);
//
//		// ask for user input
//		Scanner in = new Scanner(System.in);
//		String nextLine = in.nextLine();
//		ArrayList<Task> storeRoom = new ArrayList<>();
//
//		// not bye
//		while (!nextLine.equals("bye")) {
//			// check list
//			if (nextLine.equals("list")) {
//				Task.printList(storeRoom);
//				nextLine = in.nextLine();
//				continue;
//			}
//
//			// finish task by index
//			if (nextLine.startsWith("done")) {
//				int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
//				Task doneTask = storeRoom.get(intValue - 1);
//				doneTask.doneTask();
//				storeRoom.set(intValue - 1, doneTask);
//				doneTask.printDoneTask();
//				nextLine = in.nextLine();
//				continue;
//			}
//
//			// delete task
//			if (nextLine.startsWith("delete")) {
//				int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
//				Task taskToDelete = storeRoom.get(intValue - 1);
//				storeRoom.remove(intValue - 1);
//				taskToDelete.printDeleteTask(storeRoom.size());
//				nextLine = in.nextLine();
//				continue;
//			}
//
//			//add task
//			try {
//				if (nextLine.startsWith("todo")) {
//					Task.isFirstWordValid(nextLine, "todo");
//					if (Task.isDescriptionEmpty(nextLine)) {
//						message = "____________________________________________________________\n"
//								+ "OOPS!!! The description of a "
//								+ "todo"
//								+ " cannot be empty.\n"
//								+ "____________________________________________________________\n";
//						throw new DukeException(message);
//					}
//					task = new ToDo(nextLine.substring(5));
//				} else if (nextLine.startsWith("deadline")) {
//					Task.isFirstWordValid(nextLine, "deadline");
//					if (Task.isDescriptionEmpty(nextLine)) {
//						message = "____________________________________________________________\n"
//								+ "OOPS!!! The description of a "
//								+ "deadline"
//								+ " cannot be empty.\n"
//								+ "____________________________________________________________\n";
//						throw new DukeException(message);
//					}
//					task = Deadline.splitDeadline(nextLine);
//				} else {
//					Task.isFirstWordValid(nextLine, "event");
//					if (Task.isDescriptionEmpty(nextLine)) {
//						message = "____________________________________________________________\n"
//								+ "OOPS!!! The description of a "
//								+ "event"
//								+ " cannot be empty.\n"
//								+ "____________________________________________________________\n";
//						throw new DukeException(message);
//					}
//					task = Event.splitEvent(nextLine);
//					storeRoom.add(task);
//					task.addTask(storeRoom.size());
//					nextLine = in.nextLine();
//				}
//			} catch (DukeException dukeException) {
//				System.out.println(dukeException);
//				nextLine = in.nextLine();
//				continue;
//			}
//		}
//
//		// bye
//		System.out.println(byeString);
//		in.close();
//	}
//}
//
public class Duke {
	public static void main(String[] args) {
		Display.showWelcomeMessage();

		// ask for user input
		Scanner in = new Scanner(System.in);
		String nextLine = in.nextLine();
		Command nextCommand = Command.getCommandWordFromString(nextLine);
		StoreRoom storeRoom = new StoreRoom();

		while (true) {
			try {
				if (nextCommand == Command.BYE) {
					Display.showByeMessage();
					break;
				} else if (nextCommand == Command.TODO) {
					storeRoom.addTask(nextCommand, nextLine);
				} else if (nextCommand == Command.DEADLINE) {
					storeRoom.addTask(nextCommand, nextLine);
				} else if (nextCommand == Command.EVENT) {
					storeRoom.addTask(nextCommand, nextLine);
				} else if (nextCommand == Command.LIST) {
					storeRoom.printList();
				} else if (nextCommand == Command.DONE) {
					storeRoom.finishTask(nextLine);
				} else if (nextCommand == Command.DELETE) {
					storeRoom.deleteTask(nextLine);
				} else {
					storeRoom.invalidTask();
				}
			} catch (DukeException e){
				System.out.println(e);
			}
			nextLine = in.nextLine();
			nextCommand = Command.getCommandWordFromString(nextLine);
		}
		in.close();
	}
}
