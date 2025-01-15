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

    public static int evaluate(String studentCode, List<TestCase> testCases) throws IOException, InterruptedException {
        // Compile the student's code
//        Process compileProcess = new ProcessBuilder("javac", "studentAnswr.txt").start();
        Process compileProcess = Runtime.getRuntime().exec("javac " + studentCode);
        compileProcess.waitFor();
        if (compileProcess.waitFor() != 0) {
            System.out.println("Compilation Error. Please check the student's code.");
            return -1;
        }

//        // Extract the class name from the file path
//        String className = new File("studentAnswr.txt").getName().replace(".java", "");


        // Execute the student's program and test each case
        int passed = 0;
        for (TestCase testCase : testCases) {
            String[] inputs = testCase.getInput().split(",");
            String expectedOutput = testCase.getOutput();

            // Run the student's compiled code
            Process runProcess = Runtime.getRuntime().exec("java StudentCode");
                    
            BufferedWriter processInput = new BufferedWriter(new OutputStreamWriter(runProcess.getOutputStream()));
            for (String input : inputs) {
                processInput.write(input.trim());
                processInput.newLine();
            }
            processInput.close();

            // Capture the output
            BufferedReader processOutput = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            StringBuilder actualOutput = new StringBuilder();
            String line;
            while ((line = processOutput.readLine()) != null) {
                actualOutput.append(line.trim());
            }

            // Compare actual output with expected output
            if (actualOutput.toString().equals(expectedOutput)) {
                passed++;
            }
        }

        return passed;
    }
}
