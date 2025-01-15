/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.util.List;

/**
 *
 * @author zjiaq
 */
public class Question {
    private String questionText;
    private List<TestCase> testCases;

    public Question(String questionText, List<TestCase> testCases) {
        this.questionText = questionText;
        this.testCases = testCases;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }
    
    
}

