package tetris;

import javax.swing.ImageIcon;

public class Square_Piece extends Tetromino {

    Square_Piece() {
        c = 1;
        x[c] = 100;
        y[c] = 25;
        draw();
        ImageIcon iisquare = new ImageIcon(getClass().getResource("square.png"));
        I = iisquare.getImage();
    }

    @Override
    public void draw() {
        x[0] = x[c];
        x[2] = x[c] + 25;
        x[3] = x[c] + 25;
        y[0] = y[c] - 25;
        y[2] = y[c] - 25;
        y[3] = y[c];

    }
}
