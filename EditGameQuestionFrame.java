//Class 7: EditGameQuestionFrame.java
package GameGUI;
import GameDao.QuestionDao;
import GamePOJO.Game;
import GamePOJO.Question;
import GamePOJO.QuestionStore;
import GamePOJO.UserProfile;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane; 
public class EditGameQuestionFrame extends javax.swing.JFrame {
private Game editGame;
    private QuestionStore qstore;
    private int qno,pos=0;
    private String question, l1,l2,l3,l4,l5,l6,l7,correctAns;
    public EditGameQuestionFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
       lblUsername1.setText("Hello "+UserProfile.getUserName());
        qstore = new QuestionStore();
        qno=1;
        lblQno.setText(lblQno.getText()+qno);
    } 
    public EditGameQuestionFrame(Game editGame){
    this();
    this.editGame=editGame;
    lblTitle.setText("Editing "+"' "+editGame.getGameType().toUpperCase()+" '" +"Game");
    loadQuestions();
    showQuestions();
    }
    public void loadQuestions(){
    try{
        ArrayList<Question> questionList=QuestionDao.getQuestionsByGameId(editGame.getGameId(),editGame.getGameType());
            for(Question obj:questionList){
                qstore.addQuestion(obj);
            }
    }
    catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "Error while connecting to DB!","Exception!",JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}
    private void showQuestions() {
        Question question=qstore.getQuestion(pos);
        txtOpt1.setText(question.getL1());
        txtOpt2.setText(question.getL2());
        txtOpt3.setText(question.getL3());
        txtOpt4.setText(question.getL4());
        txtOpt5.setText(question.getL5());
        txtOpt6.setText(question.getL6());
        txtOpt7.setText(question.getL7());
        txtCorrectAnswer.setText(question.getCorrectAnswer());
       correctAns=question.getCorrectAnswer();
    }
    private boolean validateInput() {
        l1=txtOpt1.getText().trim();
        l2=txtOpt2.getText().trim();
        l3=txtOpt3.getText().trim();
        l4=txtOpt4.getText().trim();
        l5=txtOpt5.getText().trim();
        l6=txtOpt6.getText().trim();
        l7=txtOpt7.getText().trim();
        correctAns=txtCorrectAnswer.getText().trim();
        if(l1.isEmpty()||l2.isEmpty()||correctAns.isEmpty()||l3.isEmpty())
            return false;
        else 
            return true;
    }                         
    private void initComponents() {}                        
    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {                                        
        try{
            QuestionDao.updateQuestions(qstore);
            JOptionPane.showMessageDialog(null, "Your puzzles set has been successfully updated","Updation Successful",JOptionPane.INFORMATION_MESSAGE);
            EditGameFrame paperFrame=new EditGameFrame();
            paperFrame.setVisible(true);
            this.dispose();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error while connecting to DB!","Exception!",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }                                       
    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {                                     
        EditGameFrame vsf = new EditGameFrame();
        vsf.setVisible(true);
        this.dispose();// TODO add your handling code here:
    }                                    
    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {                                     
        if(!validateInput()){
            JOptionPane.showMessageDialog(null, "Fill atleast 3 boxes(letter1,letter2,letter3 and correct Option)","Cannot Add Puzzles!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        Question obj=new Question(editGame.getGameId(),qno,editGame.getGameType(),l1,l2,l3,l4,l5,l6,l7,correctAns,question);
        qstore.removeQuestion(pos);
        qstore.setQuestionAt(pos,obj);
        pos++;
        if(pos>=qstore.getCount()){
            pos=0;
        }
        showQuestions();
        qno++;
        if(qno>qstore.getCount()){
             lblNext.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Your Puzzle set has been successfully updated.\nPress the DONE","!",JOptionPane.INFORMATION_MESSAGE);
        }
        lblQno.setText(qno+""); 
    }                                    
    private void lblPreviousMouseClicked(java.awt.event.MouseEvent evt) {                                         
        if(!validateInput()){
            JOptionPane.showMessageDialog(null, "Please fill all the input fields","Cannot Add Puzzles!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        Question obj=new Question(editGame.getGameId(),qno,editGame.getGameType(),l1,l2,l3,l4,l5,l6,l7,correctAns,question);
        System.out.println(obj);
        qstore.removeQuestion(pos);
        qstore.setQuestionAt(pos,obj);
        pos--;
        if(pos<0){   
            lblPrevious.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Your question set has been successfully updated.\nPress the DONE button to send it to the database ","Exam Added!",JOptionPane.INFORMATION_MESSAGE);
            pos=qstore.getCount()-1;
        }
        showQuestions();
        qno--;
        if(qno<=0)
            qno=qstore.getCount();
        lblQno.setText(""+qno);
    }
public static void main(String args[]) {}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditGameQuestionFrame().setVisible(true);
            }
        });
    }                
    private javax.swing.JButton btnDone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblPrevious;
    private javax.swing.JLabel lblQno;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JTextField txtCorrectAnswer;
    private javax.swing.JTextField txtOpt1;
    private javax.swing.JTextField txtOpt2;
    private javax.swing.JTextField txtOpt3;
    private javax.swing.JTextField txtOpt4;
    private javax.swing.JTextField txtOpt5;
    private javax.swing.JTextField txtOpt6;
    private javax.swing.JTextField txtOpt7;
}
