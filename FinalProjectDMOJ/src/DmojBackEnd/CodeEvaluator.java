/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DmojBackEnd;

/**
 *
 * @author zjiaq
 */
import java.io.*;

class CodeEvaluator {
    public static String evaluate(String studentCode, String input, String expectedOutput) throws IOException, InterruptedException {
        // Write the student code to a file
        String filePath = "StudentCode.java";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(studentCode);
        writer.close();

        // Compile the student code
        Process compileProcess = Runtime.getRuntime().exec("javac " + filePath);
        compileProcess.waitFor();

        // Check if compilation was successful
        if (compileProcess.exitValue() != 0) {
            return "Compilation Error";
        }

        // Run the compiled code
        Process runProcess = Runtime.getRuntime().exec("java StudentCode");

        // Write all lines of input to the program
        BufferedWriter processInput = new BufferedWriter(new OutputStreamWriter(runProcess.getOutputStream()));
        for (String inputLine : input.split("\n")) {
            processInput.write(inputLine);
            processInput.newLine();
        }
        processInput.close();
        
        boolean finished = runProcess.waitFor(30, java.util.concurrent.TimeUnit.SECONDS);
        if (!finished) {
            runProcess.destroy();
            return "Execution Timed Out";
        }

        // Capture the output from the program
        BufferedReader processOutput = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
        StringBuilder resultBuilder = new StringBuilder();
        String line;
        while ((line = processOutput.readLine()) != null) {
            resultBuilder.append(line).append("\n");
        }
        processOutput.close();

        String result = resultBuilder.toString().trim();

        // Compare the output with the expected output
        if (result.equals(expectedOutput.trim())) {
            return "Test Passed";
        } else {
            return "Test Failed: Expected '" + expectedOutput.trim() + "' but got '" + result + "'";
        }
    }
}
