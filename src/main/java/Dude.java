import dao.TaskDao;
import dao.TaskDaoImpl;
import logic.CommandLogicUnitImpl;
import logic.ICommandLogicUnit;
import parser.IParser;
import parser.ParserImpl;
import ui.IUi;
import ui.UiImpl;

/**
 * main driver program
 */
public class Dude {
	
	private final TaskDao taskDao;
	private final ICommandLogicUnit commandLogicUnit;
	private final IUi ui;
	
	public Dude(TaskDao taskDao, ICommandLogicUnit commandLogicUnit, IUi ui) {
		this.taskDao = taskDao;
		this.commandLogicUnit = commandLogicUnit;
		this.ui = ui;
	}
	
	public static void main(String[] args) {
		TaskDao taskDao = new TaskDaoImpl();
		IUi ui = new UiImpl();
		ICommandLogicUnit commandLogicUnit = new CommandLogicUnitImpl(taskDao, ui);
		
		new Dude(taskDao, commandLogicUnit, ui).run();
	}
	
	/**
	 * Runs Dude chatterbot.
	 */
	@SuppressWarnings("InfiniteLoopStatement")
	public void run() {
		String logo = " ____        ____      \n"
				+ "|  _ \\ _   _|  _ \\____\n"
				+ "| | | | | | | | |/ __ \\\n"
				+ "| |_| | |_| | |_|   __/\n"
				+ "|____/ \\__,_|____\\____|\n";
		System.out.println("Hello from\n" + logo + "by Simon - CS2103T/2122S1");
		
		starting();
		
		try (IParser commandProcessor = new ParserImpl(commandLogicUnit, ui)) {
			while (true) {
				commandProcessor.processInput();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void starting() {
		ui.printSentence(" Hello! I'm Dude\n" +
				" What can I do for you?");
	}
}
