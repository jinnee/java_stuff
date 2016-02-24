package grid;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class Main {

    public static void main(String args[]) {
        JFrame frame = new JFrame("Grid Example");

        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TestPanel testPanel = new TestPanel();
        testPanel.setSize(frame.getSize().width, frame.getSize().height);
        testPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        testPanel.setVisible(true);

        frame.add(testPanel);
        frame.setVisible(true);
    }

}
