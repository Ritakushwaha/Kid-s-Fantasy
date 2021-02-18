//Class 8: PlayerOptions.java
package GameGUI;
import GameDao.PerformanceDao;
import GamePOJO.UserProfile;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class PlayerOptions extends javax.swing.JFrame {
    public PlayerOptions() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblUsername.setText("Hello "+UserProfile.getUserName()+"!");
    }
    private boolean validateInputs(){
    if(jrbPlayeGame.isSelected()==false && jrbViewScores.isSelected()==false && jrbChangePassword.isSelected()==false)
        return false;
     return true;
    }
private void initComponents() {}                        
    private void lblLogout1MouseClicked(java.awt.event.MouseEvent evt) {                                        
        int ans;
        ans=JOptionPane.showConfirmDialog(null, "Are u sure ?","Logout!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(ans==JOptionPane.YES_OPTION){
            this.dispose();
            LoginUser login=new LoginUser();
            login.setVisible(true);
        }
    }                                       
    private void lblLogout1MouseEntered(java.awt.event.MouseEvent evt) {                                        
        lblLogout1.setForeground(Color.red);
        Font f=new Font("Showcard Gothic",Font.BOLD,12);
        lblLogout1.setFont(f);// TODO add your handling code here:
    }                                       
    private void lblLogout1MouseExited(java.awt.event.MouseEvent evt) {                                       
        lblLogout1.setForeground(Color.white);
        Font f=new Font("Showcard Gothic",Font.ITALIC,12);
        lblLogout1.setFont(f);// TODO add your handling code here:
    }                                      
    private void lblDoTaskMouseClicked(java.awt.event.MouseEvent evt) {                                       
         if(validateInputs()==false){
            JOptionPane.showMessageDialog(null, "Please Choose An Option","NO SELECTION !",JOptionPane.ERROR_MESSAGE);
            return;
        }
         if(jrbPlayeGame.isSelected()){
          GameTypeSelection   gts=new GameTypeSelection();
            gts.setVisible(true);
        }else if(jrbViewScores.isSelected()){
                    ViewScoresFrame marksFrame=new ViewScoresFrame();
                    marksFrame.setVisible(true);
        }else if(jrbChangePassword.isSelected()){
            ChangePassword passwordFrame=new ChangePassword();
            passwordFrame.setVisible(true);
        }
        this.dispose();    // TODO add your handling code here:
    }                                      
    public static void main(String args[]) {}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerOptions().setVisible(true);
            }
        });
    }
private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JRadioButton jrbChangePassword;
    private javax.swing.JRadioButton jrbPlayeGame;
    private javax.swing.JRadioButton jrbViewScores;
    private javax.swing.JLabel lblDoTask;
    private javax.swing.JLabel lblLogout1;
    private javax.swing.JLabel lblUsername;               
}
