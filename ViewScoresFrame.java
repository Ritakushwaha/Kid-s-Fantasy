//Class 13: ViewScoresFrame.java
package GameGUI;
import GameDao.PerformanceDao;
import GamePOJO.Performance;
import GamePOJO.UserProfile;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class ViewScoresFrame extends javax.swing.JFrame {
    public ViewScoresFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblUsername.setText("Hello "+UserProfile.getUserName()+"!");
        showDataInTable();
    }
    public void showDataInTable(){
    try{
        ArrayList<Performance> performanceList=PerformanceDao.getAllData();
        if(performanceList.isEmpty()==true)
        JOptionPane.showMessageDialog(null, "Sorry! No One has played yet ","Error!",JOptionPane.INFORMATION_MESSAGE);  
        else {
            DefaultTableModel model=(DefaultTableModel)jtStudentData.getModel();
            Object[] rows=new Object[7];
            for(Performance pObj: performanceList) {
                rows[0]= pObj.getUserId();
                rows[1]= pObj.getGameId();
                rows[2]= pObj.getGameType();
                rows[3]= pObj.getScore();
                model.addRow(rows);
            }
        }   
    }
    catch(SQLException ex) {
        JOptionPane.showMessageDialog(null, "SQLException"+ex,"Error!",JOptionPane.ERROR_MESSAGE);  
        ex.getStackTrace();
    }
}
   private void initComponents() {}                        
    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {                                     
        LoginUser vsf = new LoginUser();
        vsf.setVisible(true);
        this.dispose();// TODO add your handling code here:
    }
public static void main(String args[]) {}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewScoresFrame().setVisible(true);
            }
        });
    }                
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtStudentData;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblUsername;
}
