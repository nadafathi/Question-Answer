package uniscripts.com.qanda;

public class QuestionModel {
    private String Q_ID, Question, Q_image, Answer1, Answer2, Answer3, Answer4;

    public QuestionModel(String q_ID, String question, String q_image, String answer1, String answer2, String answer3, String answer4) {
        Q_ID = q_ID;
        Question = question;
        Q_image = q_image;
        Answer1 = answer1;
        Answer2 = answer2;
        Answer3 = answer3;
        Answer4 = answer4;
    }

    public String getQ_ID() {
        return Q_ID;
    }

    public void setQ_ID(String q_ID) {
        Q_ID = q_ID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getQ_image() {
        return Q_image;
    }

    public void setQ_image(String q_image) {
        Q_image = q_image;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public void setAnswer1(String answer1) {
        Answer1 = answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer2(String answer2) {
        Answer2 = answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer3(String answer3) {
        Answer3 = answer3;
    }

    public String getAnswer4() {
        return Answer4;
    }

    public void setAnswer4(String answer4) {
        Answer4 = answer4;
    }
}
