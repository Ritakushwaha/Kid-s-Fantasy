//Class 12: PlayerRegisterFrame.java
package GameGUI;
import GameDao.UserDao;
import GamePOJO.User;
import GamePOJO.UserProfile;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class PlayerRegisterFrame extends javax.swing.JFrame {
String userId,password,username;
    public PlayerRegisterFrame() {
        initComponents();
        this.setLocationRelativeTo(null); 
    }
private void clearAll() {
        txtUserId.setText("");
        txtUsername.setText("");
        txtpassword.setText("");
   }
private int validateInputs() {
       userId=txtUserId.getText();
       char []pwd=txtpassword.getPassword();
       username = txtUsername.getText();
       if(userId.isEmpty()|| pwd.length==0 ||username.isEmpty())
            return 0;
       else {
           password=String.valueOf(pwd);
           if(userId.length()>10 || pwd.length>10||username.length()>20)
               return -2;
           else
                return 1;
       }
    }
private void initComponents() {}                       
private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {                                            
        int response=validateInputs();
        if(response==0){
            JOptionPane.showMessageDialog(null, "UserId or Password cannot be left blank!","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(response==-2){
            JOptionPane.showMessageDialog(null, "Userid and Password should be atleast  10 characters long!","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            try {  
                User user=new User();
                user.setUserId(txtUserId.getText());
                user.setPassword(txtpassword.getText());
                user.setUserName(txtUsername.getText());
                user.setUserType("Player");
                boolean status=UserDao.addPlayer(user);
                if(status) {
                    JOptionPane.showMessageDialog(null, "Userid: " +userId+"\nPassword:"+password,"Registration Done!",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error while connecting to DB!","Exception!",JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
            clearAll();
        }
    }                                           
    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {                                     
        LoginUser vsf = new LoginUser();
        vsf.setVisible(true);
        this.dispose();// TODO add your handling code here:
    }
public static void main(String args[]) {}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayerRegisterFrame().setVisible(true);
            }
        });
    }
private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblBack;
    private javax.swing.JTextField txtUserId;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JPasswordField txtpassword;   
}
