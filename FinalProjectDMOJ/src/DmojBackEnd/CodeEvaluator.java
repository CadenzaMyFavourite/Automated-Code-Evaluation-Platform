/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DmojBackEnd;

/**
 *
 * @author zjiaq
 */
import Objects.TestCase;
import java.io.*;
import java.util.*;


class CodeEvaluator {

    public static int evaluate(String studentCodeFilePath, List<TestCase> testCases) throws IOException, InterruptedException {
         //Write the student code to a file
        String filePath = "StudentCode.java";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(studentCodeFilePath);
        writer.close();
        Process compileProcess = Runtime.getRuntime().exec("javac " + filePath);
        compileProcess.waitFor();
        // Compile the student's code
        
        if (compileProcess.waitFor() != 0) {
            // Capture compilation errors
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                System.err.println("Compilation Error: " + errorLine);
            }
            errorReader.close();
            return -1;
        }

        int passed = 0;

        // Execute the student's program for each test case
        for (TestCase testCase : testCases) {
            Process runProcess = Runtime.getRuntime().exec("java StudentCode");
            // Pass inputs
            BufferedWriter processInput = new BufferedWriter(new OutputStreamWriter(runProcess.getOutputStream()));
            for (String input : testCase.getInput().split(",")) {
                processInput.write(input.trim());
                processInput.newLine();
            }
            processInput.close();

            // Capture output
            BufferedReader processOutput = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            StringBuilder actualOutput = new StringBuilder();
            String line;
            while ((line = processOutput.readLine()) != null) {
                actualOutput.append(line.trim());
            }

            // Debugging logs
            System.out.println("Inputs: " + testCase.getInput());
            System.out.println("Expected: " + testCase.getOutput());
            System.out.println("Actual: " + actualOutput);

            // Compare outputs
            if (actualOutput.toString().equals(testCase.getOutput())) {
                passed++;
            }
        }

        return passed;
    }
}


