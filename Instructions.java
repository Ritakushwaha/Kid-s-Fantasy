//Class 10: Instructions.java
package GameGUI;
public class Instructions extends javax.swing.JFrame {
    public Instructions() {
        initComponents();
        this.setLocationRelativeTo(null);
        jTextArea1.setEditable(false);
        jTextArea2.setEditable(false);
    }
private void initComponents() {}                        
    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {                                     
        GameTypeSelection gts = new GameTypeSelection();
        gts.setVisible(true);
        this.dispose();// TODO add your handling code here:
    }                                    
    public static void main(String args[]) {}
 java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Instructions().setVisible(true);
            }
        });
    }               
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JLabel lblBack;
    }
