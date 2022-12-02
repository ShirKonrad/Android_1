package com.example.exc1_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;

enum player {
    X,
    O
}

enum gameStates {
    X,
    O,
    Empty
}

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    player activePlayer = player.X;

    gameStates[] gameState = {gameStates.Empty, gameStates.Empty, gameStates.Empty,
                              gameStates.Empty, gameStates.Empty, gameStates.Empty,
                              gameStates.Empty, gameStates.Empty, gameStates.Empty};

    int[][] winPositions = {{0, 1, 2, 6}, {3, 4, 5, 7}, {6, 7, 8, 8},
            {0, 3, 6, 3}, {1, 4, 7, 4}, {2, 5, 8, 5},
            {0, 4, 8, 1}, {2, 4, 6, 2}};
    public int counter = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameActive) {
            gameReset(view);
        }

        if (gameState[tappedImage] == gameStates.Empty) {
            counter++;

            if (counter == 9) {
                gameActive = false;

                Button play_again_btn = findViewById(R.id.play_again_btn);
                play_again_btn.setVisibility(View.VISIBLE);
            }

            gameState[tappedImage] = gameStates.valueOf(activePlayer.name());

            if (activePlayer == player.X) {
                img.setImageResource(R.drawable.x);
                activePlayer = player.O;
                ImageView status_img = findViewById(R.id.status_img);
                status_img.setImageResource(R.drawable.oplay);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = player.X;
                ImageView status_img = findViewById(R.id.status_img);
                status_img.setImageResource(R.drawable.xplay);
            }
        }

        int flag = 0;
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                gameState[winPosition[1]] == gameState[winPosition[2]] &&
                gameState[winPosition[0]] != gameStates.Empty) {
                int winnerImgSrc, markImgSrc;
                flag = 1;

                gameActive = false;
                if (gameState[winPosition[0]] == gameStates.X) {
                    winnerImgSrc = R.drawable.xwin;
                } else {
                    winnerImgSrc = R.drawable.owin;
                }

                markImgSrc = determineMark(winPosition[3]);

                ImageView status_img = findViewById(R.id.status_img);
                status_img.setImageResource(winnerImgSrc);

                ImageView mark_img = findViewById(R.id.win_mark_img);
                mark_img.setImageResource(markImgSrc);

                Button play_again_btn = findViewById(R.id.play_again_btn);
                play_again_btn.setVisibility(View.VISIBLE);
            }
        }

        if (counter == 9 && flag == 0) {
            ImageView status_img = findViewById(R.id.status_img);
            status_img.setImageResource(R.drawable.nowin);
        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = player.X;

        Arrays.fill(gameState, gameStates.Empty);

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

        Button play_again_btn = findViewById(R.id.play_again_btn);
        play_again_btn.setVisibility(View.INVISIBLE);

        counter = 0;
    }

    private int determineMark(int winCode) {
        switch (winCode) {
            case 1:
                return R.drawable.mark1;
            case 2:
                return R.drawable.mark2;
            case 3:
                return R.drawable.mark3;
            case 4:
                return R.drawable.mark4;
            case 5:
                return R.drawable.mark5;
            case 6:
                return R.drawable.mark6;
            case 7:
                return R.drawable.mark7;
            case 8:
                return R.drawable.mark8;
            default:
                return R.drawable.empty;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}