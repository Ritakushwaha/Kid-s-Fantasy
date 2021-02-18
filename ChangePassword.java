//Class 14: ChangePassword.java
package GameGUI;
import GameDao.UserDao;
import GamePOJO.UserProfile;
import static java.lang.String.valueOf;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ChangePassword extends javax.swing.JFrame {
    private String username;
    private String pwd; 
    private String newpwd;
    private String confirmPwd;
public ChangePassword() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblUsername.setText("Hello "+UserProfile.getUserName()+"!");
        txtUserId.setText(UserProfile.getUserName());
        txtUserId.setEditable(false);
    }
    public int validateInputs(){
    username = txtUserId.getText();
    char[] pwd = txtOldPass.getPassword();
    char [] newpwd =txtNewPass.getPassword();
    char [] confirmpwd=txtConfirmPass.getPassword();
    if(username.isEmpty()||pwd.length==0||newpwd.length==0||confirmpwd.length==0)
        return 0;
    else if (pwd.length==6||newpwd.length==6||confirmpwd.length==6)
        return 4;
    else if(String.valueOf(pwd).equals(valueOf(newpwd).toString())==true)
        return 1;
    else if(String.valueOf(newpwd).equals(String.valueOf(confirmpwd))==false)
        return 2;
    else
        return -1;
    }
private void initComponents() {}
private void btnUpdatePassActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if(validateInputs()==0){
            JOptionPane.showMessageDialog(null,"UserId and Password \n can't be blank.","Input Required",JOptionPane.ERROR_MESSAGE);
            return;
        }else if (validateInputs()!=4){
            JOptionPane.showMessageDialog(null,"Password length should be 6","PASSWORD ERROR",JOptionPane.ERROR_MESSAGE);
            txtOldPass.setText("");
            txtNewPass.setText("");
            txtConfirmPass.setText("");
        }else if(validateInputs()==1){
            JOptionPane.showMessageDialog(null,"New password Should be different ","",JOptionPane.ERROR_MESSAGE);
            txtNewPass.setText("");
            txtConfirmPass.setText("");
        }else if(validateInputs()==2){
            JOptionPane.showMessageDialog(null,"NEW and CONFIRM Password didn't matched","Invalid password",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                System.out.println(txtNewPass.getPassword());
                String password=String.valueOf(txtNewPass.getPassword());
                boolean status = UserDao.changePassword(username,password);
                if(status){
                    JOptionPane.showMessageDialog(null,"Password changed Succesfully","Success",JOptionPane.INFORMATION_MESSAGE);
                    LoginUser ss =new LoginUser();
                    ss.setVisible(true);
                    this.dispose();
                }
                else
                JOptionPane.showMessageDialog(null, "Password Not changed !","Password changed Denied!", JOptionPane.ERROR_MESSAGE);
            }
            catch (SQLException ex){
             JOptionPane.showMessageDialog(null,"SQLException"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
                ex.getStackTrace();
            }
        }
    }                                             
    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {                                     
        PlayerOptions vsf = new PlayerOptions();
        vsf.setVisible(true);
        this.dispose();
    }                  
    public static void main(String args[]) {}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangePassword().setVisible(true);
            }
        });
    }               
    private javax.swing.JButton btnUpdatePass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JPasswordField txtOldPass;
    private javax.swing.JTextField txtUserId;
}
