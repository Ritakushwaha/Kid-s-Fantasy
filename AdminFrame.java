//Class 3: AdminFrame.java
package GameGUI;
import GamePOJO.UserProfile;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class AdminFrame extends javax.swing.JFrame {
    public AdminFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblUsername.setText("Welcome "+UserProfile.getUserName()+"!");
    }
private void initComponents() {}                       
    private void lblLogout1MouseClicked(java.awt.event.MouseEvent evt) {                                        
        int ans;
        ans=JOptionPane.showConfirmDialog(null, "Are u sure ?","Logout!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(ans==JOptionPane.YES_OPTION)
        {
            this.dispose();
        LoginUser login=new LoginUser();
        login.setVisible(true);
        }
    }                                       
    private void lblLogout1MouseEntered(java.awt.event.MouseEvent evt) {                                        
        lblLogout1.setForeground(Color.red);
        Font f=new Font("Showcard Gothic",Font.BOLD,12);
        lblLogout1.setFont(f);    
    }                                       
    private void lblLogout1MouseExited(java.awt.event.MouseEvent evt) {                                       
        lblLogout1.setForeground(Color.white);
        Font f=new Font("Showcard Gothic",Font.ITALIC,12);
        lblLogout1.setFont(f);
    }                                      

    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {                                     
        String task=getAction();
        if(task==null){
            JOptionPane.showMessageDialog(null, "Please Choose An Option","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFrame jf=null;
        if(task.equalsIgnoreCase("Make new game"))
        jf=new MakeNewGameFrame ();
        else if(task.equalsIgnoreCase("Edit existing game")){
            jf=new EditGameFrame();
        }
        else{
            jf=new ViewScoresFrame();
        }
        jf.setVisible(true);
        this.dispose();
        }
        private String getAction(){
            if(jrbMakeNewGame.isSelected())
                return jrbMakeNewGame.getText();
            else if(jrbEditGame.isSelected())
                return jrbEditGame.getText();
            else if(jrbViewScores.isSelected())
                return jrbViewScores.getText();
            else
                return null;       
    } 
   public static void main(String args[]) {}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminFrame().setVisible(true);
            }
        });
    }                
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JRadioButton jrbEditGame;
    private javax.swing.JRadioButton jrbMakeNewGame;
    private javax.swing.JRadioButton jrbViewScores;
    private javax.swing.JLabel lblLogout1;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblUsername;
}
