//Class 9: GameTypeSelectionFrame.java
package GameGUI;
import GameDao.WordGameDao;
import GamePOJO.Game;
import GamePOJO.UserProfile;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class GameTypeSelection extends javax.swing.JFrame {
    private String gameType;
    public GameTypeSelection() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblUsername.setText("Hello "+UserProfile.getUserName()+"!");
    }    
    public boolean validateInput(){
    int selectedIndex=jcGameType.getSelectedIndex();
    if(selectedIndex==0)
        return false;
    gameType=jcGameType.getSelectedItem().toString();
    return true;
}
   private void initComponents() {}                        
    private void jcGameTypeActionPerformed(java.awt.event.ActionEvent evt) {                                           
        boolean result=validateInput();
        if(result==false){
            JOptionPane.showMessageDialog(null, "Please choose a game type","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            ArrayList<String>gameList=WordGameDao.getGameIdByGameType(gameType);
            if(gameList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Sorry! No puzzle set for "+gameType,"Puzzle Not Found!",JOptionPane.ERROR_MESSAGE);
                return;
            }
            for(String gameId: gameList){
                jcGameId.addItem(gameId);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error while connecting to DB!","Exception!",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
private void lblInstructionMouseClicked(java.awt.event.MouseEvent evt) {                                            
        this.dispose();
        Instructions reg=new Instructions();
        reg.setVisible(true);
    }                                           
    private void lblInstructionMouseEntered(java.awt.event.MouseEvent evt) {                                            
        lblInstruction.setForeground(new Color(255,102,153));
        Font f=new Font("Tw Cen MT",Font.ITALIC,20);
        lblInstruction.setFont(f);
    }                                           
    private void lblInstructionMouseExited(java.awt.event.MouseEvent evt) {                                           
        lblInstruction.setForeground(new Color(51,0,102));
        Font f=new Font("Showcard Gothic",Font.BOLD,20);
        lblInstruction.setFont(f);
    }                                          
    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {                                     
        PlayerOptions ss = new PlayerOptions();
        ss.setVisible(true);
        this.dispose();        
    }                                    
    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {                                     
        int count=jcGameType.getSelectedIndex();
        if(count==0) {
            JOptionPane.showMessageDialog(null, "Please choose a Game type","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        count=jcGameId.getItemCount();
        if(count==0) {
            JOptionPane.showMessageDialog(null, "Please select an gameid","Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        String gameType=jcGameType.getSelectedItem().toString();
        String gameId=jcGameId.getSelectedItem().toString();
        int ans;
        ans=JOptionPane.showConfirmDialog(null, "You have chosen \""+gameType+"\" and \""+gameId+"\"!\nIs this OK ?","Confirmation!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(ans==JOptionPane.YES_OPTION) {
            try {
                int total=WordGameDao.getQuestionCountByGame(gameId.trim());
                System.out.println("Gameid:"+gameId+" Total quest:"+total);
                Game game=new Game(gameId,gameType,total);
                GamePlayFrame gf=new GamePlayFrame(game);
                gf.setVisible(true);
                this.dispose();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Error while connecting to DB!","Exception!",JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }    
    }                                    
    private void lblCancelMouseClicked(java.awt.event.MouseEvent evt) {                                       
        PlayerOptions ss = new PlayerOptions();
        ss.setVisible(true);
        this.dispose();     
    }                                      
    public static void main(String args[]) {}
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameTypeSelection().setVisible(true);
            }
        });
    }
private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JComboBox<String> jcGameId;
    private javax.swing.JComboBox<String> jcGameType;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblCancel;
    private javax.swing.JLabel lblInstruction;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblUsername;
}
