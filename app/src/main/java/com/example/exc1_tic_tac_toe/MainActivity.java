package com.example.exc1_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;

    // State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // put all win positions in a 2D array
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public int counter = 0;

    // this function will be called every time a
    // players tap in an empty box of the grid
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        // game reset function will be called
        // if someone wins or the boxes are full
        if (!gameActive) {
            gameReset(view);
        }

        // if the tapped image is empty
        if (gameState[tappedImage] == 2) {
            // increase the counter
            // after every tap
            counter++;

            // check if its the last box
            if (counter == 9) {
                // reset the game
                gameActive = false;
                // Set play again button visible
                Button play_again_btn = findViewById(R.id.play_again_btn);
                play_again_btn.setVisibility(View.VISIBLE);
            }

            // mark this position
            gameState[tappedImage] = activePlayer;

            // this will give a motion
            // effect to the image
            img.setTranslationY(-1000f);

            // change the active player
            // from 0 to 1 or 1 to 0
            if (activePlayer == 0) {
                // set the image of x
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                ImageView status_img = findViewById(R.id.status_img);

                // change the status
                status_img.setImageResource(R.drawable.oplay);
            } else {
                // set the image of o
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                ImageView status_img = findViewById(R.id.status_img);

                // change the status
                status_img.setImageResource(R.drawable.xplay);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;
        // Check if any player has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;

                // Somebody has won! - Find out who!
                int winnerImgSrc;

                // game reset function be called
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerImgSrc = R.drawable.xwin;
                } else {
                    winnerImgSrc = R.drawable.owin;
                }
                // Update the status bar for winner announcement
                ImageView status_img = findViewById(R.id.status_img);
                status_img.setImageResource(winnerImgSrc);

                // Set play again button visible
                Button play_again_btn = findViewById(R.id.play_again_btn);
                play_again_btn.setVisibility(View.VISIBLE);
            }
        }
        // set the status if the match draw
        if (counter == 9 && flag == 0) {
            ImageView status_img = findViewById(R.id.status_img);
            status_img.setImageResource(R.drawable.nowin);
        }
    }

    // reset the game
    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        // remove all the images from the boxes inside the grid
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        ImageView status_img = findViewById(R.id.status_img);
        status_img.setImageResource(R.drawable.xplay);

        // Set play again button invisible
        Button play_again_btn = findViewById(R.id.play_again_btn);
        play_again_btn.setVisibility(View.INVISIBLE);

        counter = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}