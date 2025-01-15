/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DmojBackEnd;

/**
 *
 * @author zjiaq
 */
import Objects.Question;
import Objects.TestCase;
import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

//class QuestionParser {
//    public static List<Question> parseQuestions(String filePath) throws IOException {
//        List<Question> questions = new ArrayList<>();
//        BufferedReader reader = new BufferedReader(new FileReader(filePath));
//
//        String line;
//        StringBuilder questionText = new StringBuilder();
//        StringBuilder input = new StringBuilder();
//        StringBuilder output = new StringBuilder();
//        boolean isInput = false, isOutput = false;
//
//        while ((line = reader.readLine()) != null) {
//            if (line.startsWith("Question:")) {
//                if (questionText.length() > 0) {
//                    questions.add(new Question(questionText.toString().trim(), input.toString().trim(), output.toString().trim()));
//                    questionText.setLength(0);
//                    input.setLength(0);
//                    output.setLength(0);
//                }
//                questionText.append(line.substring(9).trim());
//            } else if (line.startsWith("Input:")) {
//                isInput = true;
//                isOutput = false;
//                input.setLength(0);
//            } else if (line.startsWith("Output:")) {
//                isInput = false;
//                isOutput = true;
//                output.setLength(0);
//            } else {
//                if (isInput) {
//                    input.append(line).append("\n");
//                } else if (isOutput) {
//                    output.append(line).append("\n");
//                }
//            }
//        }
//        if (questionText.length() > 0) {
//            questions.add(new Question(questionText.toString().trim(), input.toString().trim(), output.toString().trim()));
//        }
//
//        reader.close();
//        return questions;
//    }
//}
class QuestionParser {
    public static List<Question> parseQuestions(String filePath) throws IOException {
        List<Question> questions = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        StringBuilder questionText = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Question:")) {
                if (questionText.length() > 0) {
                    // Parse and add the question
                    questions.add(parseQuestion(questionText.toString().trim()));
                    questionText.setLength(0);
                }
                questionText.append(line.substring(9).trim());
            } else {
                questionText.append(" ").append(line.trim());
            }
        }

        // Add the last question if there's one pending
        if (questionText.length() > 0) {
            questions.add(parseQuestion(questionText.toString().trim()));
        }

        reader.close();
        return questions;
    }

    public static Question parseQuestion(String questionBlock) {
        String[] parts = questionBlock.split("\\.", 2);
        String questionText = parts[0].trim();

        // Extract test cases
        String testCaseBlock = parts[1].trim();
        List<TestCase> testCases = parseTestCase(testCaseBlock);

        return new Question(questionText, testCases);
    }
    
    public static List<TestCase> parseTestCase(String testCaseBlock) {
        List<TestCase> testCases = new ArrayList<>();
        String[] testCasesArray = testCaseBlock.split("(?<=})\\s*(?=\\{)");
        for (String testCase : testCasesArray) {
            testCase = testCase.replaceAll("[{}]", ""); // Remove braces
            String[] ioParts = testCase.split(";");
            String inputs = ioParts[0].trim();
            String expectedOutput = ioParts[1].trim();
            testCases.add(new TestCase(inputs, expectedOutput));
        }
        return testCases;
    }
}