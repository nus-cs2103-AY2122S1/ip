import java.util.*;

public class UI {
	private final String tab;
	private final String horizontalLine;
	private List<String> list;
	private final Scanner sc;

	public UI() {
		this.tab = " ".repeat(4);
		this.horizontalLine = "_".repeat(60);
		this.list = new ArrayList<>();
		this.sc = new Scanner(System.in);
	}

	public void printStartUpMessage() {
		add("Hello! I'm Duke");
		add("What can I do for you?");
		print();
	}

	public void printErrorMessage(DukeException ex) {
		add(ex.getMessage());
		for (String line : ex.getHelpMessages()) {
			add(line);
		}
		print();
	}

	public String readLine() {
		return sc.nextLine();
	}

	public void add(String line) {
		this.list.add(line);
	}

	public void clear() {
		this.list.clear();
	}

	public void print() {
		System.out.println(tab + horizontalLine);
		for (String line : list) {
			System.out.println(tab + " " + line);
		}
		System.out.println(tab + horizontalLine);
		System.out.println();
		clear();
	}
}
