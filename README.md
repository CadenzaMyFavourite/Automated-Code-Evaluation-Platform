# Automated-Code-Evaluation-Platform

A Java NetBeans project for a simple DMOJ-style coding platform prototype. The project currently includes:

- A console-based question runner and code evaluator
- A Swing-based UI prototype with intro, login, sign-up, and response screens
- Basic database helper code for student and teacher accounts

## Project Structure

```text
FinalProjectDMOJ/
|-- src/
|   |-- DmojBackEnd/     # Question parsing, code evaluation, database helper
|   |-- DmojFrontEnd/    # Swing UI panels and main frame
|   |-- Objects/         # User-related model classes
|   `-- utils/           # Card switching and SQL helper utilities
|-- lib/
|   `-- json-20240303.jar
|-- questions.txt        # Sample question data for the console app
|-- StudentCode.java     # Temporary student code file used by evaluator
`-- build.xml            # NetBeans/Ant build file
```

## Main Features

### 1. Console Question Evaluator

The default NetBeans main class is `DmojBackEnd.ExaminationPlatform`.

This flow:

1. Reads questions from `questions.txt`
2. Displays available questions in the terminal
3. Lets the user select one question
4. Reads a student Java file from a path entered by the user
5. Compiles and runs that code
6. Compares the program output against the expected output

### 2. Swing UI Prototype

The project also includes a GUI entry point in `DmojFrontEnd.DmojFrame`.

The UI uses a `CardLayout` to switch between:

- Intro screen
- Login screen
- Sign-up screen
- Response screen

### 3. Database Integration Helper

`DatabaseHelper` and `utils.SQLQueries` contain the project's database-related logic. The helper sends SQL requests to a remote PHP endpoint and is intended to support:

- User existence checks
- Student registration
- Student/teacher login

## Requirements

To run this project locally, you will likely need:

- Java JDK 21
- Apache Ant or NetBeans
- The included JSON library: `FinalProjectDMOJ/lib/json-20240303.jar`

## How to Open the Project

### NetBeans

1. Open NetBeans
2. Choose `Open Project`
3. Select the `FinalProjectDMOJ` folder

### Command Line

If Java and Ant are installed, you can build the NetBeans project from the `FinalProjectDMOJ` directory using Ant.

## Running the Console Version

The configured main class is:

```text
DmojBackEnd.ExaminationPlatform
```

Make sure `questions.txt` is available in the working directory when running the app.

When the program starts, it will ask you to:

- Choose a question number
- Enter the path to the student's `.java` file

The evaluator writes the submitted code into `StudentCode.java`, compiles it, runs it, and reports:

- `Test Passed`
- `Test Failed`
- `Compilation Error`
- `Execution Timed Out`

## Running the Swing UI

To launch the GUI instead, run:

```text
DmojFrontEnd.DmojFrame
```

If you are using NetBeans, you can change the main class in the project properties before running.

## Question File Format

The console app reads questions from `questions.txt` using this pattern:

```text
Question: Write a program to add two numbers.
Input:
4
Output:
15
```

Each question block uses:

- `Question:`
- `Input:`
- `Output:`

## Notes and Current Limitations

- The console evaluator currently only supports Java student submissions.
- `CodeEvaluator` expects the student code to compile as `StudentCode.java`.
- The Swing login screen is still incomplete and does not yet perform full login handling.
- The sign-up screen currently switches screens without fully registering the user because the registration call is commented out.
- The response panel is still a placeholder.
- Database access depends on a remote API endpoint defined in `utils.SQLQueries`.
- The database helper builds SQL statements directly from user input, so it should not be considered secure in its current form.

## Author

Created as a NetBeans Java project by the project contributors listed in the source file headers.
