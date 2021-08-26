import data.StoreRoom;
import display.Display;
import exception.DukeException;
import operation.Command;

import java.util.Scanner;

/**
 * Duke is the smart assistance to help you track personal tasks.
 * This is the Driver class for Duke application.
 */
public class Duke {
	public static void main(String[] args) {
		Display.showWelcomeMessage();

		// ask for user input
		Scanner in = new Scanner(System.in);
		String nextLine = in.nextLine();
		Command nextCommand = Command.getCommandWordFromString(nextLine);
		StoreRoom storeRoom = new StoreRoom();
		storeRoom.createFile();

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
			storeRoom.updateFile();
		}
		in.close();
	}
}
