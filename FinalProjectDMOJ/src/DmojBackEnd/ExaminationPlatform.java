/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DmojBackEnd;

/**
 *
 * @author zjiaq
 */
import java.io.*;
import java.util.*;
import java.io.*;
import java.util.*;

public class ExaminationPlatform {
    public static void main(String[] args) {
        try {
            // Parse questions from the file
            String questionFilePath = "questions.txt"; // Ensure this file exists
            List<Question> questions = QuestionParser.parseQuestions(questionFilePath);

            // Display available questions
            System.out.println("Available Questions:");
            for (int i = 0; i < questions.size(); i++) {
                System.out.println((i + 1) + ". " + questions.get(i).questionText);
            }

            // Select a question to answer
            Scanner scanner = new Scanner(System.in);
            System.out.print("Select a question number: ");
            int questionNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            if (questionNumber < 1 || questionNumber > questions.size()) {
                System.out.println("Invalid question number.");
                return;
            }

            Question selectedQuestion = questions.get(questionNumber - 1);
            System.out.println("Selected Question: " + selectedQuestion.questionText);

            // Show test cases (optional for debugging or clarity)
            System.out.println("Test Cases:");
            for (int i = 0; i < selectedQuestion.testCases.size(); i++) {
                TestCase testCase = selectedQuestion.testCases.get(i);
                System.out.println("Test Case " + (i + 1) + ": Input = " + testCase.inputs + ", Expected Output = " + testCase.expectedOutput);
            }

            // Get the path to the student's code file
            System.out.print("Enter the path to the student's code file: ");
            String studentCodeFilePath = scanner.nextLine();

            // Read the student's code from the file
            StringBuilder studentCode = new StringBuilder();
            BufferedReader codeReader = new BufferedReader(new FileReader(studentCodeFilePath));
            String line;
            while ((line = codeReader.readLine()) != null) {
                studentCode.append(line).append("\n");
            }
            codeReader.close();

            // Evaluate the code
            int passedCount = CodeEvaluator.evaluate(studentCode.toString(), selectedQuestion.testCases);
            if (passedCount == -1) {
                System.out.println("Evaluation Result: Compilation Error. Please check the student's code.");
            } else {
                System.out.println("Evaluation Result: Passed " + passedCount + " out of " + selectedQuestion.testCases.size() + " test cases.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}