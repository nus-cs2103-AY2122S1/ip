# Brobot :punch:
> Your very own brother from another computer to help you manage your tasks!

## Features
* User-Friendly: Brobot communicates purely through the CLI with very simple commands. Brobot will also always be your bro!
* Fast: Brobot knows how important time is to you, so he does things __FAST__
* Local save: Brobot will still remember your tasks even after you quit the application. _What a bro move_ :punch:

## Installation
1. Download the latest release from [here](https://github.com/markuslim24/ip/releases).
2. Double click the jar file to run it.(Alternatively, run java -jar [application].jar from CLI)
3. Start typing away!

## Implementation list:
- [x] Basic task creation
   * Todo
   * Events
   * Deadline
- [x] Mark done & delete task function
- [x] Task list local save
- [ ] More commands
- [ ] Better UI(A GUI)

***

If you Java programmer, you can use it to practice Java too. Here's the `main` method:
```Java
public class Brobot {
    public static void main(String[] args) {

            Storage brobot.storage = new Storage("./data/list1.txt");
            TaskList list = brobot.storage.readList();
            BroParser parser = new BroParser(list, brobot.storage);

            UI.printGreeting();
            String input = UI.getUserInput();

            while (!input.equals("bye")) {
                try {
                    parser.parse(input);
                } catch (BroException e) {
                    UI.printError(e);
                } finally {
                    input = UI.getUserInput();
                }
            }
            UI.printBye();
    }
}
```