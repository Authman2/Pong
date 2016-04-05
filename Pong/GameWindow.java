import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GameWindow
{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        frame.setBounds(400,100,600,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel game = new GamePanel();
        
        Container pane = frame.getContentPane();
        pane.add(game);
        
        frame.setVisible(true);
    }
}
