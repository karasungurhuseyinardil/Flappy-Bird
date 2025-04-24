import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 800;
    int frameHeight = 600;

    int birdX = 100;
    int birdY = frameHeight / 2;
    int birdWidth = 30;
    int birdHeight = 30;

    int velocity = 0;
    int gravity = 1;
    int jumpStrength = -12;

    Timer timer;
    ArrayList<Rectangle> pipes;
    Random rand = new Random();

    int pipeWidth = 80;
    int pipeGap = 200;
    int score = 0;
    boolean gameOver = false;

    Image birdImage;
    Image bottomPipeImage;
    Image topPipeImage;
    Image backgroundImage;

    public FlappyBird() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setResizable(false);
        frame.setVisible(true);

        // Load the images
        birdImage = new ImageIcon("assets/bird.png").getImage();
        bottomPipeImage = new ImageIcon("assets/bottompipe.png").getImage();
        topPipeImage = new ImageIcon("assets/toppipe.png").getImage();
        backgroundImage = new ImageIcon("assets/background.png").getImage();

        pipes = new ArrayList<>();
        addPipe(true);
        addPipe(true);
        addPipe(true);
        addPipe(true);

        timer = new Timer(20, this);
        timer.start();
    }

    public void addPipe(boolean start) {
        int space = pipeGap;
        int height = 50 + rand.nextInt(300);
        int x = start ? frameWidth + pipes.size() * 300 : pipes.get(pipes.size() - 1).x + 300;

        pipes.add(new Rectangle(x, 0, pipeWidth, height));
        pipes.add(new Rectangle(x, height + space, pipeWidth, frameHeight - height - space));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, this);

        // Draw the bird image
        g.drawImage(birdImage, birdX, birdY, birdWidth, birdHeight, this);

        // Draw the pipes
        for (Rectangle pipe : pipes) {
            if (pipe.y == 0) {
                g.drawImage(topPipeImage, pipe.x, pipe.y, pipe.width, pipe.height, this);  // Top pipe
            } else {
                g.drawImage(bottomPipeImage, pipe.x, pipe.y, pipe.width, pipe.height, this);  // Bottom pipe
            }
        }

        // Draw the ground (orange)
        g.setColor(Color.orange);
        g.fillRect(0, frameHeight - 100, frameWidth, 100);

        // Draw the score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        if (gameOver) {
            g.drawString("Game Over! Score: " + score, 200, 300);
        } else {
            g.drawString("Score: " + score, 20, 50);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            velocity += gravity;
            birdY += velocity;

            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);
                pipe.x -= 5;
            }

            if (pipes.get(pipes.size() - 1).x < frameWidth - 300) {
                addPipe(false);
            }

            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);
                if (pipe.x + pipe.width < 0) {
                    pipes.remove(pipe);
                }
            }

            for (Rectangle pipe : pipes) {
                if (pipe.intersects(new Rectangle(birdX, birdY, birdWidth, birdHeight))) {
                    gameOver = true;
                }
            }

            if (birdY > frameHeight - 100 || birdY < 0) {
                gameOver = true;
            }

            for (Rectangle pipe : pipes) {
                if (pipe.y == 0 && pipe.x + pipe.width == birdX) {
                    score++;
                }
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            velocity = jumpStrength;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && gameOver) {
            birdY = frameHeight / 2;
            velocity = 0;
            pipes.clear();
            score = 0;
            gameOver = false;
            addPipe(true);
            addPipe(true);
            addPipe(true);
            addPipe(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    public static void main(String[] args) {
        new FlappyBird();
    }
}
