//Class 5: MakeGamePuzzleFrame.java
package GameGUI;
import GameDao.QuestionDao;
import GameDao.WordGameDao;
import GamePOJO.Game;
import GamePOJO.Question;
import GamePOJO.QuestionStore;
import GamePOJO.UserProfile;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class MakeGamePuzzleFrame extends javax.swing.JFrame {
private Game newGame;
    private QuestionStore qstore;
    private int qno;
    private String question, l1,l2,l3,l4,l5,l6,l7,correctAnswer;
    public MakeGamePuzzleFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblUsername.setText("Hello "+UserProfile.getUserName());
        qstore = new QuestionStore();
        qno=1;
        lblQno.setText(lblQno.getText()+qno);
    }
    public MakeGamePuzzleFrame(Game newGame) {
        this();
        System.out.println("newGame "+newGame.toString());
        lblGametitle.setText(newGame.getTotalQuestion()+" QUESTIONS REMAINING");
        this.newGame = newGame;
    }
    private boolean validateInput() {
        question=txtCorrectAnswer.getText().trim();
        l1=txtOpt1.getText().trim();
        l3=txtOpt3.getText().trim();
        l7=txtOpt7.getText().trim();
        l4=txtOpt4.getText().trim();
        l5=txtOpt5.getText().trim();
        l6=txtOpt6.getText().trim();
        l2=txtOpt2.getText().trim();
        correctAnswer=txtCorrectAnswer.getText();
   if(question.isEmpty()||l1.isEmpty()||l2.isEmpty()||l3.isEmpty()||correctAnswer.isEmpty())
            return false;
        else 
            return true;
    } 
public void clearAll(){
    txtOpt1.setText("");
    txtOpt3.setText("");
    txtOpt7.setText("");
    txtOpt4.setText("");
    txtOpt5.setText("");
    txtOpt6.setText("");
    txtOpt2.setText("");
    txtCorrectAnswer.setText("");
    }
    public void disableAll(){
    txtOpt1.setEnabled(false);
    txtOpt3.setEnabled(false);
    txtOpt7.setEnabled(false);
    txtOpt4.setEnabled(false);
    txtOpt5.setEnabled(false);
    txtOpt6.setEnabled(false);
    txtOpt2.setEnabled(false);
    txtCorrectAnswer.setEnabled(false);
    lblNext.setEnabled(false);
    }
private void initComponents() {}                        
    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(qno<=newGame.getTotalQuestion()){
            int remainingQuestions=newGame.getTotalQuestion()-qno+1;
            JOptionPane.showMessageDialog(null, "You still have "+remainingQuestions+" left","Cannot Add Puzzle!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            WordGameDao.addExam(newGame);
            QuestionDao.addQuestions(qstore);
            JOptionPane.showMessageDialog(null, "Your Puzzle set has been successfully added ","Puzzle Added!",JOptionPane.INFORMATION_MESSAGE);
            AdminFrame adminScreen=new AdminFrame();
            adminScreen.setVisible(true);
            this.dispose();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error while connecting to DB!"+ex,"Exception!",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }                                       
    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {                                     
        MakeNewGameFrame vsf = new MakeNewGameFrame();
        vsf.setVisible(true);
        this.dispose();// TODO add your handling code here:
    }                                    
    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {                                     
        if(validateInput()==false){
            JOptionPane.showMessageDialog(null, "Fill atleast 3 boxes(letter 1, letter 2,letter 3) and correct answer","\nCannot Add Puzzle!",JOptionPane.ERROR_MESSAGE);
            return;
        }
Question obj=new Question(newGame.getGameId(),qno,newGame.getGameType(),l1,l2,l3,l4,l5,l6,l7,correctAnswer,question);
        qstore.addQuestion(obj);
        System.out.println("checking process -"+obj);
        clearAll();
        lblGametitle.setText(newGame.getTotalQuestion() - qno+ " GAME QUESTIONS REMAINING");
        qno++;
        if(qno>newGame.getTotalQuestion()){
            disableAll();
            JOptionPane.showMessageDialog(null, "Your Puzzle set has been successfully created.\nPress the DONE button ","Game Puzzle Ended",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        lblQno.setText(""+qno);
    }
public static void main(String args[]) {
 java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MakeGamePuzzleFrame().setVisible(true);
            }
        });
    }
private javax.swing.JButton btnDone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblGametitle;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblQno;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtCorrectAnswer;
    private javax.swing.JTextField txtOpt1;
    private javax.swing.JTextField txtOpt2;
    private javax.swing.JTextField txtOpt3;
    private javax.swing.JTextField txtOpt4;
    private javax.swing.JTextField txtOpt5;
    private javax.swing.JTextField txtOpt6;
    private javax.swing.JTextField txtOpt7; 
}
