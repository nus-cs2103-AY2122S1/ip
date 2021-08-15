import java.util.*;

public class OutputHandler {
	private final String tab = " ".repeat(4);
	private final String horizontalLine = "_".repeat(60);
	private List<String> list = new ArrayList<>();

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
