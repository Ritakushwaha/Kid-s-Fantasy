//Class 2: LoginUser.java
package GameGUI;
import GameDButil.DbConnection;
import GameDao.UserDao;
import GamePOJO.User;
import GamePOJO.UserProfile;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
public class LoginUser extends javax.swing.JFrame {
    private String userId;
    private String password;
    public LoginUser() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    private boolean validateInput() {
       userId=txtUserId.getText();
       char []pwd=txtPassword.getPassword();
       if(userId.isEmpty()|| pwd.length==0 )
            return false;
       else{
           password=String.valueOf(pwd);
           return true;
       }
    }
private String getUserType() {
        if(jrbAdmin.isSelected()){
            return jrbAdmin.getText();}
        else if(jrbPlayer.isSelected())
            return jrbPlayer.getText();
        else
            return null;
    } 
    private void initComponents() {}                   
    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {                                        
        int ans;
        ans=JOptionPane.showConfirmDialog(null, "Are u sure ?","Quitting!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(ans==JOptionPane.YES_OPTION)
        {
            DbConnection.closeConnection();
            System.exit(0);
        }
    }                                       
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt){                                         
        boolean isValidInput=validateInput();
        if(isValidInput==false){
            JOptionPane.showMessageDialog(null, "UserId or Password cannot be left blank!","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        String userType=getUserType();
        if(userType==null){
            JOptionPane.showMessageDialog(null, "Please Choose User Type","Error!",JOptionPane.ERROR_MESSAGE);
        }else{
        try{
            User user = new User();
            user.setUserId(userId);
            user.setPassword(password);
            user.setUserType(userType);
            System.out.println("user "+user.toString());
            String username=UserDao.validateUser(user);
            if(username!=null){
                JOptionPane.showMessageDialog(null, "Login Accepted!","Welcome "+username,JOptionPane.INFORMATION_MESSAGE);
                UserProfile.setUserName(username);
                UserProfile.setUserType(userType);
                if(user.getUserType().equalsIgnoreCase("admin")){
                    AdminFrame adminFrame=new AdminFrame();
                    adminFrame.setVisible(true);
                }
                else{
                    PlayerOptions game=new PlayerOptions();
                    game.setVisible(true);
                }
                this.dispose();
            }
            else
            JOptionPane.showMessageDialog(null, "Invalid UserId/Password","Login Denied!",JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception sq){
            JOptionPane.showMessageDialog(null, "Error while connecting to DB!","Exception!",JOptionPane.ERROR_MESSAGE);
            sq.printStackTrace();
        }
        }
    }                                        
    private void lblSignUPMouseClicked(java.awt.event.MouseEvent evt) {                                       
        this.dispose();
        PlayerRegisterFrame reg=new PlayerRegisterFrame();
        reg.setVisible(true);
    }                                      
    private void lblSignUPMouseEntered(java.awt.event.MouseEvent evt) {                                       
        lblSignUP.setForeground(new Color(255,102,153));
        Font f=new Font("Tw Cen MT",Font.ITALIC,20);
        lblSignUP.setFont(f);
    }                                      
    private void lblSignUPMouseExited(java.awt.event.MouseEvent evt) {                                      
        lblSignUP.setForeground(new Color(51,0,102));
        Font f=new Font("Showcard Gothic",Font.BOLD,20);
        lblSignUP.setFont(f);
    }
    public static void main(String args[]) {}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginUser().setVisible(true);
            }
        });
    } 
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnQuit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jrbAdmin;
    private javax.swing.JRadioButton jrbPlayer;
    private javax.swing.JLabel lblSignUP;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserId; 
}
