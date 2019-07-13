
package maingame;

import java.awt.BorderLayout;
import javax.swing.JFrame;


public class Main {

    public static JFrame frame;
    public static void main(String[] args) {
        
        frame = new JFrame("Into The Darkness");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(new GamePanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    public  static JFrame getFrame()
    {
        return frame;
    }
}
