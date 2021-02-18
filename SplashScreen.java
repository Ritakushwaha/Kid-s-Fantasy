//PACKAGE:GameGUI

//Class 1: SplashScreen.java
package GameGUI; 
import java.util.Random;
import javax.swing.JOptionPane;
public class SplashScreen extends javax.swing.JFrame {
    public SplashScreen() {
        initComponents();
        this.setLocationRelativeTo(null);
        jProgressBar1.setStringPainted(true);
        SplashThread sp=new SplashThread();
        sp.start();
    }
    class SplashThread extends Thread{
        public void run(){
        int count=1;
        Random r=new Random();
        while(jProgressBar1.getValue()<jProgressBar1.getMaximum()){
            try{
                jProgressBar1.setValue(count);
                Thread.sleep(1200);
                count=count+r.nextInt(100);
            } 
            catch (InterruptedException ex){
                 JOptionPane.showMessageDialog(null, "Exception In Thread:"+ex,"Error!",JOptionPane.ERROR_MESSAGE);
            }
        }
        dispose();
        LoginUser pf=new LoginUser();
        pf.setVisible(true);    
        }
    }
    private void initComponents(){}
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplashScreen().setVisible(true);
            }
        });
    } 
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1; 
}
