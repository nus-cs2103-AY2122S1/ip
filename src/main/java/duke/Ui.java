package duke;

public class Ui {
	public void showf(String format, Object... args) {
		System.out.printf(format, args);
	}

	public void show(Object s) {
		System.out.println(s);
	}
}
