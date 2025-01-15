/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DmojBackEnd;

import java.util.List;

/**
 *
 * @author zjiaq
 */
//class Question {
//    String questionText;
//    String input;
//    String expectedOutput;
//
//    public Question(String questionText, String input, String expectedOutput) {
//        this.questionText = questionText;
//        this.input = input;
//        this.expectedOutput = expectedOutput; 
//    }
//}
class TestCase {
    String inputs;
    String expectedOutput;

    public TestCase(String inputs, String expectedOutput) {
        this.inputs = inputs;
        this.expectedOutput = expectedOutput;
    }
}

class Question {
    String questionText;
    List<TestCase> testCases;

    public Question(String questionText, List<TestCase> testCases) {
        this.questionText = questionText;
        this.testCases = testCases;
    }
}
