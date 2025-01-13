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
    private int grade;

    public int getQuestionID() {
        return questionID;
    }

    public int getGrade() {
        return grade;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Response(int questionID, int grade) {
        this.questionID = questionID;
        this.grade = grade;
    }
    
    
}
