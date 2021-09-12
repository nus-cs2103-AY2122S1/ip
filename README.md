# User Guide
TauBot is a desktop app for managing your tasks, which can be used through the Command Line Interface (CLI) or the Graphical User Interface (GUI) for a more familiar chatting experience!

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/taubot.Taubot.java` file, right-click it, and choose `Run tauBot.Taubot.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
```
hey, i'm
████████╗ █████╗ ██╗   ██╗██████╗  ██████╗ ████████╗
╚══██╔══╝██╔══██╗██║   ██║██╔══██╗██╔═══██╗╚══██╔══╝
   ██║   ███████║██║   ██║██████╔╝██║   ██║   ██║   
   ██║   ██╔══██║██║   ██║██╔══██╗██║   ██║   ██║   
   ██║   ██║  ██║╚██████╔╝██████╔╝╚██████╔╝   ██║   
   ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═════╝  ╚═════╝    ╚═╝   
                                                    
what do you want?
```

## Quick Start
### CLI
1. To run the CLI app, run `tauBot.Taubot.main()`.
2. Type the command in the CLI and press Enter to execute it. Try adding your first task!

### GUI
1. To run the GUI, run `tauBot.Launcher.main()`. The GUI similar to this should appear in a few seconds:
![Screenshot 2021-09-12 at 12 39 56 PM](https://user-images.githubusercontent.com/61085398/132972393-841cfde1-c4aa-4f4a-b18f-adfa6d86ee3d.png)
2. Type the command in the GUI message field and press Enter to execute it. Try adding your first task! 

### Example of tasks you can try adding:
1. `todo eat apple` Adds eat apple to the list of tasks.
2. `list` Shows the tasks you have.
3. `done 1` Marks the first task as done.
4. `delete 1` Deletes the first task from the list.
5. `bye` Exits the program.

## Features
In Taubot, there are 3 different tasks, `todo`, `event`, `deadline`.

### Adding a `todo` task
Format: `todo TASK_DESCRIPTION`  
* `todo cook lunch`
* `todo mop the floor :-(`

### Adding a `event` task
Format: `event TASK_DESCRIPTION /at DATE TIME`  
* The date must be in yyyy-mm-dd format.
* The time must be in hhmm format.
Examples: 
* `event charity run /at 2021-09-17 0600`
* `event khai's wedding /at 2024-12-12 0900`

### Adding a `deadline` task
Format: `deadline TASK_DESCRIPTION /by DATE TIME`    
* The date must be in yyyy-mm-dd format.
* The time must be in hhmm format.
Examples: 
* `deadline CS2103 project /by 2021-09-17 2359`
* `deadline maths homework /by 2021-09-13 1300`

### `list` all your tasks
Format: `list`
* Taubot will list all the tasks that are stored.

### Marking a task as `done`  
Format: `done INDEX`  
* Marks a task as done at the speficied index from the list.
* The index refers to the index number shown in the displayed tasks list.
* The index **must be a positive integer 1,2,3...**
Example: `done 1`

### `delete` a task 
Format: `delete INDEX`
* Deletes a task at the specified index from the list. 
* The index refers to the index number shown in the displayed tasks list.
* The index **must be a positive integer 1,2,3...**
Example: `delete 1`

### `find` a task by keyword
Finds tasks containing a single keyword.  
Format: `find KEYWORD`  
* The search is case-insensitive e.g. `Homework` will match `homework`
* Only full words will be matched e.g. `home` will not match `homework`
Examples:
* `find homework` returns:
<img width="683" alt="Screenshot 2021-09-12 at 1 07 59 PM" src="https://user-images.githubusercontent.com/61085398/132972966-e4b2437b-e00f-42df-965b-96faa7c64417.png">

### View the `schedule` on a certain date
Finds tasks using an **optional** date.
Format: `schedule` or `schedule DATE`
* If a date is passed in, a list of tasks with that date will be shown.
* If no date is passed in, a list of deadlines and events are shown.
* The date must be in yyyy-mm-dd format.

Examples:
* `schedule 2021-10-12` returns:
<img width="687" alt="Screenshot 2021-09-12 at 1 14 17 PM" src="https://user-images.githubusercontent.com/61085398/132973059-48277ae0-5246-413f-a45d-d514be4760d8.png">

* `schedule` returns:
<img width="682" alt="Screenshot 2021-09-12 at 1 15 45 PM" src="https://user-images.githubusercontent.com/61085398/132973110-22c84254-233b-44e2-8f6a-f414c280b359.png">

### Exit the program `bye`
Format: `bye`
