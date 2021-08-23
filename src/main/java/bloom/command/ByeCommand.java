package bloom.command;

import bloom.app.Ui;
import bloom.constant.Message;

public class ByeCommand extends Command {
	
	public void run() {
		Ui.isRunning = false;
		System.out.println(Message.COMMAND_BYE.getMessage());
	}
}
