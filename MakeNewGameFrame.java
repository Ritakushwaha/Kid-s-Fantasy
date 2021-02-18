//Class 4: MakeNewGameFrame.java
package GameGUI;
import GameDao.WordGameDao;
import GamePOJO.Game;
import GamePOJO.UserProfile;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class MakeNewGameFrame extends javax.swing.JFrame {
    public MakeNewGameFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblUsername.setText("Hello " + UserProfile.getUserName());
    }
    @Override
    public String toString() {
        return "MakeNewGameFrame{" + "jLabel1=" + jLabel1 + ", jLabel2=" + jLabel2 + ", jLabel3=" + jLabel3 + ", jLabel6=" + jLabel6 + ", jPanel1=" + jPanel1 + ", jPanel10=" + jPanel10 + ", jcGameType=" + jcGameType + ", jcQuestion=" + jcQuestion + ", lblBack=" + lblBack + ", lblNext=" + lblNext + ", lblUsername=" + lblUsername + '}';
    } 
    public MakeNewGameFrame(Game newGame) {
        this();
    }
private void initComponents() {}                        
    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {                                     
        AdminFrame gts = new AdminFrame();
        gts.setVisible(true);
        this.dispose();    
}                                    
    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {                                     
        String gameType = jcGameType.getSelectedItem().toString();
        int totalQuestion = Integer.parseInt(jcQuestion.getSelectedItem().toString());
        int ans;
        ans = JOptionPane.showConfirmDialog(null, "You have choosen \"" + gameType + "\"and \"" + totalQuestion + "\"no. of puzzles!\n Is this Ok?", "Conformation!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ans == JOptionPane.YES_OPTION) {
            try {
                String gameId = WordGameDao.getGameId();
                System.out.println("gameid "+gameId);
                System.out.println("gametype "+gameType);
                System.out.println("totalQuestion "+totalQuestion);
                Game newGame = new Game(gameId, gameType, totalQuestion);
                MakeGamePuzzleFrame pf = new MakeGamePuzzleFrame(newGame);
                pf.setVisible(true);
                this.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error while connecting to DB!" + ex, "Exception", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    } 
    public static void main(String args[]) {}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MakeNewGameFrame().setVisible(true);
            }
        });
    }
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JComboBox<String> jcGameType;
    private javax.swing.JComboBox<String> jcQuestion;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblUsername;                 
}
