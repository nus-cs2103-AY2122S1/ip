# Duke User Guide
This is Duke, a task manager chatbot, suitable for people who love the CLI and type fast. No longer do you have to
write down and organize your own tasks, for Duke is here!

## Features
1. Add tasks such as todos, events or deadlines for Duke to manage
2. Mark your finished tasks
3. Search for tasks that contain specific keywords
4. Delete tasks from Duke
5. (For advanced users) Use advanced syntax to run commands on multiple tasks at once

### Usage
**Add a todo task:** `todo <task description>`

Example: `todo read book`

Expected outcome:

![todo usage results](./todo.png?raw=true)


**Add an event task:** `event <task description> /at YYYY-MM-DD`

Example: `event team meeting /at 2021-09-21`

Expected outcome:

![event usage results](./event.png?raw=true)


**Add an deadline task:** `deadline <task description> /by YYYY-MM-DD`

Example: `deadline cs2103t quiz /by 2021-09-21`

Expected outcome:

![deadline usage results](./deadline.png?raw=true)


**List out all tasks:** `list`

Example: `list`

Expected outcome:

![list usage results](./list.png?raw=true)


**Mark task as done:** `done <task number>`

Example: `done 8`

Expected outcome:

![done usage results](./done.png?raw=true)

**Find tasks that contain term:** `find <search term>`

Example: `find meet`

Expected outcome:

![find usage results](./find.png?raw=true)


**Delete a task:** `delete <task number>`

Example: `delete 1`

Expected outcome:

![delete usage results](./delete.png?raw=true)


**Exit:** `bye`

Example: `bye`

Expected outcome:

![bye usage results](./bye.png?raw=true)


### Advanced usage
**Delete or mark multiple tasks**: `delete / done <start number>...<end number>`

NOTE: The start and end numbers are inclusive

Example: `delete 1...4`

Expected outcome:

![advanced usage results](./advanced1.png?raw=true)

Example: `done 1...4`

Expected outcome:

![advanced usage results](./advanced2.png?raw=true)