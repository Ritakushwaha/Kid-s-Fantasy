//Class 11: GamePlayFrame.java
package GameGUI;
import GameDao.PerformanceDao;
import GameDao.QuestionDao;
import GamePOJO.Answer;
import GamePOJO.AnswerStore;
import GamePOJO.Game;
import GamePOJO.Performance;
import GamePOJO.Question;
import GamePOJO.QuestionStore;
import GamePOJO.UserProfile;
import GamePOJO.User;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class GamePlayFrame extends javax.swing.JFrame {
    private Game game;
    private User user;
    private QuestionStore qstore;
    private AnswerStore astore;
    private int pos=0;
    private int qno;
    public GamePlayFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblUsername.setText("Hello "+UserProfile.getUserName()+"!");
        qstore=new QuestionStore();
        astore =new AnswerStore();
        qno=1;
        lblQno.setText(lblQno.getText()+qno);
        jProgressBar1.setStringPainted(true);
        jProgressBar1.setForeground(Color.black);
        jProgressBar1.setBackground(Color.cyan);
        jProgressBar1.setFont(new Font("Showcard Gothic",Font.BOLD,11));
        TimerThread sp=new TimerThread();
        sp.start();
    }      
 public GamePlayFrame(Game game){
            this();
            this.game=game;
            loadQues();
            showQuestion();
    }
    class TimerThread extends Thread {
        public void run() {
        int count=0;
        while(jProgressBar1.getValue()<jProgressBar1.getMaximum()) {
            try{
                jProgressBar1.setValue(count);
                Thread.sleep(3000);
                count=count+5;
            } 
            catch (InterruptedException ex)  {
                 JOptionPane.showMessageDialog(null, "Exception In Thread:"+ex,"Error!",JOptionPane.ERROR_MESSAGE);
            }
        }
        btnDoneActionPerformed(null);
        dispose();
        ViewScoresFrame pf=new ViewScoresFrame();
        pf.setVisible(true);    
        }
    } 
    private void showQuestion() {
        Question question= qstore.getQuestion(pos);
        lblLetter1.setText(question.getL1());
        lblLetter2.setText(question.getL2());
        lblLetter3.setText(question.getL3());
        lblLetter4.setText(question.getL4());
        lblLetter5.setText(question.getL5());
        lblLetter6.setText(question.getL6());
        lblLetter7.setText(question.getL7());
        Answer answer = astore.getAnswerByQno(qno);
        if(answer==null){
        return;
        }
        String chosenAnswer = answer.getGivenAnswer();
        chosenAnswer = txtGivenAnswer.getText().trim();
   }
    public String getUserAnswer(){
        String givenString = txtGivenAnswer.getText().trim();
        return givenString;
    }
    private void loadQues() {
        try{
        ArrayList<Question> questionList = QuestionDao.getQuestionsByGameId1(game.getGameId());
        for(Question obj : questionList){
        qstore.addQuestion(obj);
        System.out.println(obj);
        }
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"SQLExeption"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);   
        }
      }
private void initComponents() {}                        
    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {                                     
            this.dispose();
            PlayerOptions login=new PlayerOptions();
            login.setVisible(true);
    }
    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {                                        
        String chosenAnswer = getUserAnswer();
        if(chosenAnswer!=null){
            Question question = qstore.getQuestion(pos);
            String correctAnswer=question.getCorrectAnswer();
            Answer newanswer = new Answer(game.getGameId(),game.getGameType(),qno,chosenAnswer,correctAnswer);
            System.out.println("newanswer "+newanswer);
            Answer answer = astore.getAnswerByQno(qno);
            if(answer==null)
            astore.addAnswer(newanswer);
            else{
                if (newanswer.getGivenAnswer().equals(answer.getGivenAnswer())==false){
                    //int apos=astore.removeAnswer(answer);
                    //astore.setAnswerAt(apos, newanswer);
                }
            }
        }
        int right=0,wrong=0;
        for(Question question:qstore.getAllQuestion()){
            int qno=question.getQno();
            Answer answer = astore.getAnswerByQno(qno);
            if(answer==null)
            continue;
            else{
                String chosenAnswer1=answer.getGivenAnswer();
                String correctAnswer=answer.getCorrectAnswer();
                if(chosenAnswer1.equals(correctAnswer))
                ++right;
                else
                ++wrong;
            }
        }
        String reportCard = "Total Question : "+qstore.getCount();
        reportCard+="\nRightAnswer : "+right;
        reportCard+="\nWrongAnswer : "+wrong;
        reportCard+="\nUnattempted : "+(qstore.getCount()-(right+wrong));
        JOptionPane.showMessageDialog(null,reportCard,"Your Result!",JOptionPane.INFORMATION_MESSAGE);
        try{
            Performance performance = new Performance(game.getGameId(),game.getGameType(),UserProfile.getUserName(),right,wrong,(qstore.getCount()-(right+wrong)),right*10);
            PerformanceDao.addPerformance(performance);
            //JOptionPane.showMessageDialog(null,"Your performance has been successfully added to the DB"+","+"Performance added","INFO",JOptionPane.INFORMATION_MESSAGE);
            ViewScoresFrame cpf = new ViewScoresFrame();
            cpf.setVisible(true);
            this.dispose();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "SQLException"+ex, "ERROR",JOptionPane.ERROR_MESSAGE);
            ex.getStackTrace();
        }
    }                                       
    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {                                     
        String chosenAnswer = getUserAnswer();
        if(chosenAnswer!=null){
            Question question = qstore.getQuestion(pos);
            String correctAnswer=question.getCorrectAnswer();
            System.out.println("correct answer checking "+question.getCorrectAnswer());
            Answer newanswer = new Answer(game.getGameId(),game.getGameType(),qno,chosenAnswer,correctAnswer);
            System.out.println(newanswer);
            Answer answer = astore.getAnswerByQno(qno);
            if(answer==null)
               astore.addAnswer(newanswer); 
            else{
                if (newanswer.getGivenAnswer().equals(answer.getGivenAnswer())==false){
                    int apos=astore.removeAnswer(answer);
                    astore.setAnswerAt(apos, newanswer);
                }
            }
        }
        pos++;
        if(pos>=qstore.getCount()){
            pos=0;
        }
        qno++;
        if(qno>qstore.getCount()){
            lblNext.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Press the DONE button to finish","Game Over!",JOptionPane.INFORMATION_MESSAGE);
        }
        txtGivenAnswer.setText("");
       lblQno.setText(""+qno);
        showQuestion();       
    }                                    
    private void lblCancelMouseClicked(java.awt.event.MouseEvent evt) {                                       
        PlayerOptions ss = new PlayerOptions();
        ss.setVisible(true);
        this.dispose();
    } 
public static void main(String args[]) {}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GamePlayFrame().setVisible(true);
            }
        });
    }
private javax.swing.JButton btnDone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel lblCancel;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblLetter1;
    private javax.swing.JLabel lblLetter2;
    private javax.swing.JLabel lblLetter3;
    private javax.swing.JLabel lblLetter4;
    private javax.swing.JLabel lblLetter5;
    private javax.swing.JLabel lblLetter6;
    private javax.swing.JLabel lblLetter7;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblQno;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtGivenAnswer;
}
