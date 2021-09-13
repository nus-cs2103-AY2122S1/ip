# User Guide
This README contains details on the chatbot

## Features 
`JarVIS` provides the following functions:

| Command                  | Description                                                                                                 |
|--------------------------|-------------------------------------------------------------------------------------------------------------|
| `JarVIS?`                | Pulls up the user manual with all available commands.                                                       |
| `todo DESC`              | Creates a ToDo task with `DESC` message                                                                     |
| `event DESC /at TIME`    | Creates an Event task with `DESC` message that takes place at `TIME` in `yyyy-MM-dd HH:mm` format           |
| `deadline DESC /by TIME` | Creates a Deadline task with `DESC` message that should be completed by `TIME` in `yyyy-MM-dd HH:mm` format |
| `done IDX`               | Marks the task at index `IDX` (starts from 1) as done                                                       |
| `delete IDX`             | Deletes the task at index `IDX` (starts from 1) from list                                                   |
| `bye`                    | Saves data and quits the chatbot application                                                                |

> Sometimes, `JarVIS` tends to get a little cocky. Ignore him :/

## Usage

1. Clone this repo and `cd` into it:
```bash
git clone https://github.com/rish-16/ip.git
cd ip
```

2. Open up the `ip` codebase in **IntelliJ** and wait for the Gradle build and processes to end.

3. Press the *Play* button at the top-right corner to launch the application.

