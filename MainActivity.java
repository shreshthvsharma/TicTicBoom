package com.softwareengg.tictactoe_ss;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    // 0 - x
    // 1 - o
    int activePlayer = 0;
    int[]   gameState = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };
    //      State Meanings:
    //      0 - x
    //      1 - o
    //      2 - Null
    public static int count=0;
    int[][] winPositions = {{0,1,2},{3,4,5}, {6,7,8},
                            {0,3,6},{1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};



    @SuppressLint("SetTextI18n")
    public void playerTap(View view){
        ImageView img = (ImageView)view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            count++;

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.cross);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's turn : Tap to Play");
            } else {
                img.setImageResource(R.drawable.circle);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's turn : Tap to Play");
            }


            img.animate().translationYBy(1000f).setDuration(300);
        }
        //  Check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] !=2) {
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                } else {
                    winnerStr = "O has won";
                }

                // Update the status bar for the winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }

        }
        if(count==9 && gameActive){

            TextView status = findViewById(R.id.status);
            status.setText("Match Draw ! Play Again");
            gameActive=false;

        }



    }
    public void gameReset(View view){
        gameActive = true;
        count = 0;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's turn : Tap to Play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}