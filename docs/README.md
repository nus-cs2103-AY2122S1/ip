# Jarvis User Guide
> “Your mind is for having ideas, not holding them.” – David Allen

* Have you always wanted your own personal assistant?
* Are you tired of manually keeping track of your ever-changing tasks?

Then Jarvis is perfect for you 😉

Jarvis is an **ABSOLUTELY FREE** and easy-to-use chatbot that can manage your tasks for you!

## Quick Start
1. Ensure that **Java 11** is installed.
2. Download the latest **jarvis.jar** file from [here](https://github.com/Preshita01/ip/releases/tag/A-Release)
3. Move **jarvis.jar** to your preferred location in the computer.
4. Open terminal and navigate to the directory in which you have placed `jarvis.jar`
   (e.g. if **jarvis.jar** is in **Desktop**, run the command `cd Desktop`)
5. Once in the right directory, run the command `java -jar jarvis.jar` to start using Jarvis!

## Features
* Adding a todo task: `todo`
* Adding a deadline task: `deadline`
* Adding an event task: `event`
* Deleting a task: `delete`
* Marking a task as completed: `done`
* Listing all tasks: `list`
* Listing all tasks due today: `today`
* Finding a specific task: `find`
* Adding a note: `note`
* Deleting a note: `delete note`
* Listing all notes: `notes`
* Exiting the program: `bye`
<br/><br/>
  
#### Notes about the command format:
* Words in `UPPER_CASE` are the parameters to be supplied by the user. 
  (e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter that can be used as `todo upload assignment`.)
* Parameters must be in the given fixed order.
<br/><br/>

### Adding a todo task: `todo`
Adds a todo task to the task list.

Format: `todo TASK_DESCRIPTION`
* `TASK_DESCRIPTION` can include spaces.

Example(s): `todo upload assignment`
<br/><br/>

### Adding a deadline task: `deadline`
Adds a deadline task to the task list.

Format: `deadline TASK_DESCRIPTION /by DATE TIME`
* `TASK_DESCRIPTION` can include spaces.
* The format for `DATE` is dd/mm/yyyy.
* The format for `TIME` is hh:mm (24h format)

Example(s):
`deadline return book /by 15/09/2021 17:00`
<br/><br/>

### Adding an event task: `event`
Adds an event task to the task list.

Format: `event TASK_DESCRIPTION /at DATE START_TIME-END_TIME`
*  `TASK_DESCRIPTION` can include spaces.
* The format for `DATE` is dd/mm/yyyy.
* The format for `START_TIME` and `END_TIME` is hh:mm (24h format)
* Any clashes with existing events **will not** be highlighted

Example(s):
`event party /at 15/09/2021 17:00-20:00`
<br/><br/>

### Deleting a task: `delete`
Deletes a todo/deadline/event task from the task list.

Format: delete `TASK_INDEX`
* `TASK_INDEX` is the index of the task based on the list of tasks that is generated when entering the list command.
* The index must be a positive integer 1,2,3, …

Example(s):
`delete 2` deletes the 2nd task in the task list
<br/><br/>

### Marking a task as completed: `done`
Marks a todo, deadline or an event task as done.

Format: `done TASK_INDEX`
* `TASK_INDEX` is the index of the task based on the list of tasks that is generated when entering the list command.
* The index must be a positive integer 1,2,3, …

Examples:
`done 2` marks the 2nd task in the task list as done
<br/><br/>

### Listing all tasks: `list`
Displays all the tasks in the task list in the order in which they were added.

Format: `list`
<br/><br/>

### Listing all tasks due today: `today`
Displays all the tasks in the task list that are due today. Within this list, tasks are in the order in which they 
were added.

Format: `today`
<br/><br/>

### Finding a specific task: `find`
Displays a list of tasks that have task descriptions that matches the search keywords/phrases.

Format: `find KEY_WORDS`
* `KEY_WORDS` can include spaces.
* Search is case-sensitive (e.g `book` will not match with `Book`)
* Partial words can be matched (e.g. `book` can match with a task description `return book`)

Examples:
`find web` displays a list of tasks with the word ‘web’ in the task description.
<br/><br/>

### Adding a note: `note`
Adds a note to the notes list.

Format: `note NOTE_TITLE /NOTE_BODY`
* `NOTE_TITLE` can include spaces.

Examples: `note grocery list /tomatoes, milk`
<br/><br/>

### Deleting a note: `delete note`
Deletes a note from the notes list.

Format: `delete note NOTE_INDEX`
* `NOTE_INDEX` is the index of the note based on the list of notes that is generated when entering the notes command.
* The index must be a positive integer 1,2,3, …

Examples: `delete note 2` deletes the 2nd note in the notes list.
<br/><br/>

### Listing all notes: `notes`
Displays all the notes in the notes list in the order in which they were added.

Format: `notes`
<br/><br/>

### Exiting the program: `bye`
Exits the program.

Format: `bye`
<br/><br/>

## Acknowledgements
### Reused from [James_D](https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx) with some modifications
1. `Main::end()`

