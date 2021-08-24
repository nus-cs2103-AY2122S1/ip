package bloom.command;

import bloom.app.Ui;
import bloom.constant.Message;

public class ExitCommand extends Command {
	
	public void run() {
		new Ui().stop();
		System.out.println(Message.COMMAND_EXIT.getMessage());
	}
}
