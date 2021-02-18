//Class 6: EditGameFrame.java
package GameGUI;
import GameDao.WordGameDao;
import GamePOJO.Game;
import GamePOJO.UserProfile;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class EditGameFrame extends javax.swing.JFrame {
 String gameType;
    public EditGameFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblUsername1.setText("Hello "+UserProfile.getUserName()+"!");
    }
    private boolean validateInput() {
        int selectedIndex=jcGameType.getSelectedIndex();
    if(selectedIndex==0)
        return false;
    gameType=jcGameType.getSelectedItem().toString();
    return true;
    }
private void initComponents() {}
private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {                                     
        AdminFrame gts = new AdminFrame();
        gts.setVisible(true);
        this.dispose();// TODO add your handling code here:
    }                                    
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {                                        
        int count=jcGameType.getSelectedIndex();
        if(count==0){
            JOptionPane.showMessageDialog(null, "Please choose a game Type","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        String gameType=jcGameType.getSelectedItem().toString();
        String gameId=jcGameId.getSelectedItem().toString();
        int ans;
        ans=JOptionPane.showConfirmDialog(null, "You have chosen \""+gameType+"\" and \""+gameId+"\" !\nIs this OK ?","Conformation!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(ans==JOptionPane.YES_OPTION) {
            try {
                int totalQuest=WordGameDao.getQuestionCountByGame(gameId.trim());
                System.out.println("Examid:"+gameId+" Total quest:"+totalQuest);
                Game editGame=new Game(gameId,gameType,totalQuest);
                EditGameQuestionFrame questionsFrame=new EditGameQuestionFrame(editGame);
                questionsFrame.setVisible(true);
                this.dispose();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Error while connecting to DB!"+ex,"Exception!",JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }                                       
    private void jcGameTypeActionPerformed(java.awt.event.ActionEvent evt) {                                           
        boolean result=validateInput();
        if(result==false){
            JOptionPane.showMessageDialog(null, "Please choose a Game Type","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            ArrayList<String>gameList=WordGameDao.getGameIdByGameType(gameType);
            if(gameList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Sorry! No Puzzle Set for "+gameType,"Puzzle Not Found!",JOptionPane.ERROR_MESSAGE);
                return;
            }
            jcGameId.removeAllItems();
            for(String gameId: gameList)
            {
                jcGameId.addItem(gameId);
            }
        }
        catch(SQLException ex) {
        JOptionPane.showMessageDialog(null,"SQLException"+ex,"ERROR",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }     
    } 
public static void main(String args[]) {
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditGameFrame().setVisible(true);
            }
        });
    }
private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JComboBox<String> jcGameId;
    private javax.swing.JComboBox<String> jcGameType;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblUsername1; 
}
