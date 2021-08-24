package bloom.command;

import bloom.constant.Drawing;
import bloom.constant.Message;

public class GreetCommand extends Command {

	@Override
	public void run() {
		System.out.println(Drawing.LOGO.getDrawing());
		System.out.println(Message.COMMAND_GREET.getMessage());
	}
}
