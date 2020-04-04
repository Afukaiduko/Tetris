package tetris;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Runner extends JFrame {

    public Runner() {
        GUI();

    }

    public void GUI() {
        add(new Board());
        setResizable(false);
        pack();
        setTitle("Tetris");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Runner r = new Runner();
            r.setVisible(true);
        });
    }
}
