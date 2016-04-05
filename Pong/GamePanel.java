import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    
    private Paddle p1, p2;
    private Ball ball;
    
    //Define
    private final int INIT_BALL_POS_X = 300, INIT_BALL_POS_Y = 200;
    private final int INIT_P1_POS_X = 550, INIT_P1_POS_Y = 200;
    private final int INIT_P2_POS_X = 15, INIT_P2_POS_Y = 200;
    
    
    private javax.swing.Timer p1Timer, p2Timer, ballTimer;
    private int p1Score = 0, p2Score = 0;   //Players' scores
    /*
     * Booleans for starting, ending, and pausing the game.
     * Also booleans for checking if the players and the ball
     * are moving.
       */
    private boolean gameStarted = false, gamePaused = true, gameEnded = true, p1MovingUp, p1MovingDown, p2MovingUp, p2MovingDown, ballMoving;
    
    
    
    public GamePanel() {
        setBackground(Color.black);
        setFocusable(true);
        
        p1 = new Paddle(INIT_P1_POS_X, INIT_P1_POS_Y, 30, 100, Color.white);
        p2 = new Paddle(INIT_P2_POS_X, INIT_P2_POS_Y, 30, 100, Color.white);
        
        /* Set the speed of the players */
        p1.setSpeed(5);
        p2.setSpeed(5);
        
        /* Set the boolean values */
        p1MovingUp = false;
        p1MovingDown = false;
        p2MovingUp = false;
        p2MovingDown = false;
        
        /* Set up the timers */
        p1Timer = new javax.swing.Timer(33, new MoveListener());
        p2Timer = new javax.swing.Timer(33, new MoveListener());
        p1Timer.start();
        p2Timer.start();
        
        /* For key events */
        addKeyListener(new MyKeyListener());
        
        /* Set scores to zero */
        p1Score = 0;
        p2Score = 0;
        
        /* Set up the ball */
        ball = new Ball(INIT_BALL_POS_X, INIT_BALL_POS_Y, 15, Color.white);
        ballMoving = true;
        ball.setDirection(30);
        ball.setSpeed(7);
        ballTimer = new javax.swing.Timer(33, new MoveListener());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        p1.fill(g);
        p2.fill(g);
        
        /* Display the players' scores on the screen */
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        g.drawString("" + p2Score, getWidth()/2 - 60, 45);
        g.drawString("" + p1Score, getWidth()/2 + 50, 45);
        
        /* Once the game is started, draw the ball on the screen*/
        if(gameStarted && gameEnded == false) {
            ball.fill(g);
        }
        //Winning the game
        if(p1Score == 10) {
            gameEnded = true;
            g.drawString("Player 1 Wins!", getWidth()/2 - 150, getHeight()/2);
        } else if(p2Score == 10) {
            gameEnded = true;
            g.drawString("Player 2 Wins!", getWidth()/2 - 150, getHeight()/2);
        }
        
        repaint();
    }
    
    private void pointHasBeenScored() {
        /* Set everything back to its original position */
        ball.setPosition(INIT_BALL_POS_X, INIT_BALL_POS_Y);
        ball.setPosition(INIT_BALL_POS_X, INIT_BALL_POS_Y);
        p1.setPosition(new Point(INIT_P1_POS_X, INIT_P1_POS_Y));
        p2.setPosition(new Point(INIT_P2_POS_X, INIT_P2_POS_Y));
        gamePaused = true;
    }
    
    private class MoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //Player 1
            if(p1MovingUp == true) {
                if(p1.getTopRight().getY() >= 0) {
                    p1MovingDown = false;
                    p1.setDirection(270);
                    p1.move();
                }
            }
            if(p1MovingDown == true) {
                if(p1.getBottomRight().getY() < getHeight()) {
                    p1MovingUp = false;
                    p1.setDirection(90);
                    p1.move();
                }
            }
            
            //Player 2
            if(p2MovingUp == true) {
                if(p2.getTopRight().getY() >= 0) {
                    p2MovingDown = false;
                    p2.setDirection(270);
                    p2.move();
                }
            }
            if(p2MovingDown == true) {
                if(p2.getBottomRight().getY() < getHeight()) {
                    p2MovingUp = false;
                    p2.setDirection(90);
                    p2.move();
                }
            }
            
            
            //Ball Logic
            if(gamePaused == false && gameEnded == false) {
                if(ballMoving == true) {
                    ball.move();
                }

                //If the ball hits the paddles
                if(p1.hits(ball)) {
                    ball.setDirection(ball.getDirection() - ((ball.getDirection() - 270) * 2));
                    ball.move();
                }
                if(p2.hits(ball)) {
                    ball.setDirection(ball.getDirection() - ((ball.getDirection() - 270) * 2));
                    ball.move();
                }
                
                //Top boundary
                if(ball.getY() <= 0) {
                    ball.setDirection((360 - ball.getDirection()));
                }
                //Bottom Boundary
                if(ball.getY() >= getHeight()) {
                    ball.setDirection((360 - Math.abs(ball.getDirection())));
                }
            }
            
            //Point Logic
            if(ball.getX() <= 0 && gameStarted) { 
                p1Score++;
                pointHasBeenScored();
            } else if (ball.getX() >= getWidth() && gameStarted) { 
                p2Score++;
                pointHasBeenScored();
            }
            
            repaint();
        }
    }
    
    public class MyKeyListener implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) { 
            //Start the game
            if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                gameStarted = true;
                gamePaused = false;
                gameEnded = false;
            }
            if(gamePaused == false && gameEnded == false) {
                //Up and Down for player 1
                if(e.getKeyCode() == KeyEvent.VK_P) {
                    p1MovingUp = true;
                    p1MovingDown = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_L) {
                    p1MovingUp = false;
                    p1MovingDown = true;
                }
                
                //Up and Down for player 2
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    p2MovingUp = true;
                    p2MovingDown = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    p2MovingUp = false;
                    p2MovingDown = true;
                }
            }
            
            /*
             * EXTRA: Change the difficulty by pressing "e" for easy, 
             * "m" for medium, and "h" for hard.
               */
            if(e.getKeyCode() == KeyEvent.VK_E) {
                p1.setSpeed(5);
                p2.setSpeed(5);
                ball.setSpeed(7);
            } else if (e.getKeyCode() == KeyEvent.VK_M) {
                p1.setSpeed(6);
                p2.setSpeed(6);
                ball.setSpeed(9);
            } else if (e.getKeyCode() == KeyEvent.VK_H) {
                p1.setSpeed(7);
                p2.setSpeed(7);
                ball.setSpeed(10);
            }
        }
        
        public void keyReleased(KeyEvent e) { 
            //Up and Down for player 1
            if(e.getKeyCode() == KeyEvent.VK_P) {
                p1MovingUp = false;
                p1MovingDown = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_L) {
                p1MovingUp = false;
                p1MovingDown = false;
            }
            
            //Up and Down for player 2
            if(e.getKeyCode() == KeyEvent.VK_W) {
                p2MovingUp = false;
                p2MovingDown = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_A) {
                p2MovingUp = false;
                p2MovingDown = false;
            }
        }
    }
}
