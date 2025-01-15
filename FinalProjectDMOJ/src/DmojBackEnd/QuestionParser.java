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
import java.io.*;
import java.util.*;


class QuestionParser {
    public static List<Question> parseQuestions(String filePath) throws IOException {
        List<Question> questions = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        StringBuilder questionText = new StringBuilder();
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        boolean isInput = false, isOutput = false;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Question:")) {
                if (questionText.length() > 0) {
                    questions.add(new Question(questionText.toString().trim(), input.toString().trim(), output.toString().trim()));
                    questionText.setLength(0);
                    input.setLength(0);
                    output.setLength(0);
                }
                questionText.append(line.substring(9).trim());
                isInput = false;
                isOutput = false;
            } else if (line.startsWith("Input:")) {
                isInput = true;
                isOutput = false;
                input.setLength(0); // Clear previous input
            } else if (line.startsWith("Output:")) {
                isInput = false;
                isOutput = true;
                output.setLength(0); // Clear previous output
            } else {
                if (isInput) {
                    input.append(line).append("\n");
                } else if (isOutput) {
                    output.append(line).append("\n");
                }
            }
        }

        // Add the last question if there's one pending
        if (questionText.length() > 0) {
            questions.add(new Question(questionText.toString().trim(), input.toString().trim(), output.toString().trim()));
        }

        reader.close();
        return questions;
    }
}
