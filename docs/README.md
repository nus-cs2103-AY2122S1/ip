# duke.
> 🌺

duke frees mind of is having to remember things. is;

- text based
- easy to learn
- ~~*super fast*~~ fast to use

all is need to do is,

1.  download from [here.](https://github.com/BananaTechs/ip/releases/tag/A-Release)
2.  double-click.
3.  add tasks.
4.  let is manage your tasks for you. 

is can:
- [X]  manage tasks
- [X]  manage deadlines
- [X]  manage events
- [X]  is all is same code really.


![Duke UI](https://github.com/BananaTechs/ip/blob/master/docs/Ui.png?raw=true)

# how is to use?

is show you.

here is things is can do:
- add tasks
- list tasks
- complete tasks
- edit tasks
- search tasks
- exit program.

## add task.
is can add tasks really easy.

is have 3 types of task:
- todo
- deadline
- event

is use the format
`[TASK_TYPE] [TASK_DESCRIPTION]`.

is example:
```
todo start iP week 1

deadline iP week 6 /by 2021-09-17 2359

event duke release /at 2021-10-01 0800
```

deadlines and events is need to specify a date and time. is use the format `yyyy-mm-dd hhmm` as above.

`deadline [TASK_DESCRIPTION] /by [DATE] [TIME]`

`event [TASK_DESCRIPTION] /at [DATE] [TIME]`

is cannot use the '\|' character anywhere in the descriptions, is afraid. is forbidden by duke's master.

if is want to leave description blank, duke is allow but is must use space characters. `event   /at ...`. is 3 space characters there.


## list tasks.
is list current tasks by typing:

`list`

unfortunately is no display tasks unless is use the `list` command. is would like to display all the time but javafx is be difficult for duke to work with.


## complete a task.
is mark a task as done.

`done [TASK_NUMBER]`

is find out a tasks number using the `list` command.


## edit a task.
is can edit the description, date or time of any task.

`edit [TASK_NUMBER] [MODIFIER] [EDITED_VERSION]`
```
edit 1 /desc blahblah

edit 7 /date 2022-10-31

edit 5 /time 1200
```
is can only edit date and time of deadlines and events, obviously. is stick to the format used when adding tasks.


## delete a task.
is can ~~murder~~ poof your old tasks away.

`delete [TASK_NUMBER]`

is sleep with fishes.


## search for tasks.
wow you is a busy person. dukes brain is no expect to use so much room for tasks.

is can search for tasks using:

`find [SEARCHABLE_STRING]`

partial phrases is allowed. is case insensitive.

is remember the `list` command? is essentially search the displayed list for your string. if is not displayed in the list, is not searchable.

here is examples.
```
find duke    // searches for tasks with "duke" in description. searching for "duk" would also work

find [D]     // searches for deadline type tasks

find [ ]     // searches for undone tasks

find sep     // searches for deadlines/events in september, or just tasks with the phrase "sep" in them

find 1800    // searches for deadlines/events at 1800
```
## exit duke.
is... goodbye? 🥺

is remember to say bye okay?

`bye`

is don't just click the x button! is can. but is will be sad.
