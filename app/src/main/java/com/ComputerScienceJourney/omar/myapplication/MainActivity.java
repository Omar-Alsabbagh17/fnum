package com.ComputerScienceJourney.omar.myapplication;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rand = new Random();
    Button goButton;
    TextView result;
    TextView score;
    TextView timer;
    TextView operation;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    Button firstTimer;
    Button secondTimer;
    Button thirdTimer;
    TextView showTimerChoice;

    boolean timerChoosen = false;
    int timerLength = 30100;
    int totalQuestion;
    int gameScore;
    int a;
    int b;
    int typeOfOperation;
    int answer;
    int correctAnswerLocation;
    boolean gameOver = false;


    public void timerChoosen(View v)
    {
        timerChoosen = true;
        if (v.getTag().toString().equals("0"))
        {
            timerLength = 30100;
            showTimerChoice.setText("Timer: 30s");
        }
        else  if (v.getTag().toString().equals("1"))
        {
            timerLength = 60100;
            showTimerChoice.setText("Timer: 60s");


        }
        else if (v.getTag().toString().equals("2"))
        {
            timerLength = 90100;
            showTimerChoice.setText("Timer: 90s");

        }
    }
    public void startGame(View v)
    {
        if (! timerChoosen)
        {
            Toast.makeText(this, "Please Choose Timer first", Toast.LENGTH_SHORT).show();
        }
        else {
            firstTimer.setVisibility(View.INVISIBLE);
            secondTimer.setVisibility(View.INVISIBLE);
            thirdTimer.setVisibility(View.INVISIBLE);
            findViewById(R.id.textView).setVisibility(View.INVISIBLE);
            goButton.setVisibility(View.INVISIBLE);
            gameLayout.setVisibility(View.VISIBLE);
            playAgain(findViewById(R.id.timeText));
        }
    }
    public void playAgain (View v)
    {
        gameOver = false;
        gameScore =0;
        totalQuestion = 0;
        timer.setText("30s");
        result.setText("");
        score.setText("0 / 0");
        playAgainButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(0+timerLength, 1000)
        {

            @Override
            public void onTick(long l) {
                timer.setText(l/1000 + "s");
            }

            @Override
            public void onFinish() {
                result.setTextSize(20);
                result.setText("Correct Answers: " + Integer.toString(gameScore)
                                + " out of " + Integer.toString(totalQuestion));
                timer.setText("0s");
                playAgainButton.setVisibility(View.VISIBLE);
                gameOver = true;

            }

        }.start();
        newQuestion();

    } ///*****************end of Method

    void newQuestion()
    {
        score.setText(Integer.toString(gameScore) + " / " + Integer.toString(totalQuestion));
        int wrongAnswer = 0;
         a = rand.nextInt(51);
         b = rand.nextInt(51);
         typeOfOperation = rand.nextInt(2);
        if (typeOfOperation == 0)
        {
            answer = a + b;
            operation.setText(String.valueOf(a) + " + " + String.valueOf(b));
        }
        else if (typeOfOperation == 1) {
            answer = a - b;
            operation.setText(Integer.toString(a) + " - " + Integer.toString(b));
        }
             correctAnswerLocation = rand.nextInt(4);
            int arr[] = new int[]{0,0,0,0};
            for (int i = 0; i < arr.length; ++i)
            {
                if (i == correctAnswerLocation)
                {
                    arr[i] = answer;
                }
                else if (typeOfOperation == 1)
                {
                     wrongAnswer = rand.nextInt(151)-50;
                    while (wrongAnswer == answer)
                    {
                        wrongAnswer = rand.nextInt(151)-50;
                    }
                    arr[i] = wrongAnswer;
                }
                else if (typeOfOperation == 0) {
                    wrongAnswer = rand.nextInt(101);
                    while (wrongAnswer == answer) {
                        wrongAnswer = rand.nextInt(101);
                    }
                    arr[i] = wrongAnswer;
                }

        }
        button1.setText(Integer.toString(arr[0]));
        button2.setText(Integer.toString(arr[1]));
        button3.setText(Integer.toString(arr[2]));
        button4.setText(Integer.toString(arr[3]));
    }

    public void verifyAnswer(View v)
    {
        if (gameOver) return;
        if (Integer.toString(correctAnswerLocation+1).equals(v.getTag().toString()))
        {
            gameScore++;
            result.setText("Correct :)");
        }
        else
        {
            result.setText("Wrong :(");
        }
        totalQuestion++;
        newQuestion();
        }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.gobutton);
        result = findViewById(R.id.resultText);
        score = findViewById(R.id.scoreText);
        timer = findViewById(R.id.timeText);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playAgainButton = findViewById(R.id.playAgain);
        operation = findViewById(R.id.operationText);
        gameLayout = findViewById(R.id.gameLayout);
        firstTimer = findViewById(R.id.firstTimer);
        secondTimer = findViewById(R.id.secondTimer);
        thirdTimer = findViewById(R.id.thirdTimer);
        showTimerChoice = findViewById(R.id.showTimerChoiceText);


        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);


    }
}