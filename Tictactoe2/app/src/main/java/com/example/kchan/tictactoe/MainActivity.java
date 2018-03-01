package com.example.kchan.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String message = "";
    int flag = 0;
    int c = 0;
    boolean gameis= true;
    int[] game= {2,2,2,2,2,2,2,2,2};
    public void crossBut(View view) {
        ImageView cross = (ImageView) view;
        System.out.println(cross.getTag().toString());
        cross.setTranslationY(1000f);
        int pos= Integer.parseInt(cross.getTag().toString());
        int[][] win= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        if(game[pos]==2 && gameis) {
            game[pos] = flag;

                if (flag == 0) {
                    cross.setImageResource(R.drawable.red);
                    flag = 1;
                    c++;
                } else {
                    cross.setImageResource(R.drawable.cross);
                    flag = 0;
                    c++;
                }
                cross.animate().translationYBy(-1000f).setDuration(300);



            for(int[] winpos: win) {
                if (game[winpos[0]] == game[winpos[1]] && game[winpos[0]] == game[winpos[2]] && game[winpos[0]] != 2) {
                    String winner = "Cross";
                    if (game[winpos[0]] == 0) {
                        winner = "Circle";
                    }
                    System.out.println(game[winpos[0]]);
                    gameis = false;


                    //somebody has won
                    TextView winnername = (TextView) findViewById(R.id.winnername);
                    winnername.setText(winner + " has won the game.!");
                    LinearLayout text = (LinearLayout) findViewById(R.id.layout);
                    text.setVisibility(View.VISIBLE);


                } else {
                    boolean gameisOver = true;
                    for (int cstate : game) {
                        if (cstate == 2) {
                            gameisOver = false;
                        }
                    }
                    if (gameisOver) {
                        TextView winnername = (TextView) findViewById(R.id.winnername);
                        winnername.setText("The game is drawn");
                        LinearLayout text = (LinearLayout) findViewById(R.id.layout);
                        text.setVisibility(View.VISIBLE);
                    }


                }
            }

                }



            }




public void playAgain(View view) {
    LinearLayout text = (LinearLayout) findViewById(R.id.layout);
    text.setVisibility(View.INVISIBLE);
    flag = 0;
    gameis=true;
    c = 0;
    for (int i = 0; i < game.length; i++)
    {
        game[i]=2;
    }
    GridLayout grid= (GridLayout)findViewById(R.id.board);
    for(int i=0; i<grid.getChildCount(); i++){
        ((ImageView)grid.getChildAt(i)).setImageResource(0);
    }
}




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
