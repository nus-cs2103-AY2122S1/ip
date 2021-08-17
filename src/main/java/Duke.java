public class Duke {
    static final String divider = "\t____________________________________________________________\n";

    Duke() {
    }

    public void greet() {
        System.out.println(divider +
                "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                divider);
    }

    public boolean echo(String echo) {
        if (echo.equals("bye")) {
            System.out.println(divider + "\tBye. Hope to see you again soon!\n" + divider);
            return false;
        } else {
            System.out.println(divider +"\t"+ echo+"\n" + divider);
            return true;
        }

    }
}
