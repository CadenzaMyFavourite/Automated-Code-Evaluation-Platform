/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author readingsdoc.
 */
public class Response {
    private int questionID;
    private String grade;

    public int getQuestionID() {
        return questionID;
    }

    public String getGrade() {
        return grade;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Response(int questionID, String grade) {
        this.questionID = questionID;
        this.grade = grade;
    }
    
    
}
