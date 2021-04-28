package com.example.maganaactivity3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView text_playerone_score, text_playertwo_score,text_drawcounter, text_roundcounter;
    private Button[] button = new Button[9];
    private Button button_reset;
    private int playeronescorecount,playertwoscorecount, roundcount, roundCount, drawcount;
    boolean activePlayer;

    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningStates = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        text_playerone_score = (TextView) findViewById(R.id.txt_p1score);
        text_playertwo_score = (TextView) findViewById(R.id.txt_p2score);
        text_drawcounter = (TextView) findViewById(R.id.txt_drawcounter);
        text_roundcounter = (TextView) findViewById(R.id.txt_roundcounter);
        button_reset = (Button) findViewById(R.id.btn_reset);

        for(int i=0; i < button.length; i++){
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName());
            button[i] = (Button) findViewById(resourceID);
            button[i].setOnClickListener(this);

        }

        roundcount = 0;
        roundCount = 0;
        drawcount = 0;
        playeronescorecount = 0;
        playertwoscorecount = 0;
        activePlayer = true;
    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));

        if(activePlayer){
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#ED0909"));
            gameState[gameStatePointer] = 0;
        }else{
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#093AED"));
            gameState[gameStatePointer] = 1;
        }
        roundcount++;

        if(checkWinner()){
            if(activePlayer){
                Toast.makeText(this, "Player One Won", Toast.LENGTH_SHORT).show();
                playeronescorecount++;
                roundCount++;
                updatePlayerScore();
                updateroundCount();
                reset();
            }else{
                Toast.makeText(this, "Player Two Won!", Toast.LENGTH_SHORT).show();
                playertwoscorecount++;
                roundCount++;
                updatePlayerScore();
                updateroundCount();
                reset();
            }
        }else if(roundcount == 9){
            drawcount++;
            roundCount++;
            updateroundCount();
            reset();
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            updateDrawCount();
        }else{
            activePlayer = !activePlayer;
        }

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                playeronescorecount = 0;
                playertwoscorecount = 0;
                drawcount = 0;
                roundCount = 0;
                updatePlayerScore();
                updateDrawCount();
                updateroundCount();
            }
        });
    }

    private void updateroundCount() {
        text_roundcounter.setText(Integer.toString(roundCount));
    }

    private void updateDrawCount() {
        text_drawcounter.setText(Integer.toString(drawcount));
    }

    public boolean checkWinner(){
        boolean winnerResult = false;

        for(int [] winningState:winningStates){
            if(gameState[winningState[0]]==gameState[winningState[1]] &&
                    gameState[winningState[1]]==gameState[winningState[2]] &&
                    gameState[winningState[0]] !=2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    public void updatePlayerScore(){
        text_playerone_score.setText(Integer.toString(playeronescorecount));
        text_playertwo_score.setText(Integer.toString(playertwoscorecount));
    }

    public void reset(){
        roundcount = 0;
        activePlayer = true;

        for(int i = 0; i < button.length; i++){
            gameState[i] = 2;
            button[i].setText("");
        }
    }
}