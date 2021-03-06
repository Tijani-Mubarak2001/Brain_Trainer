package com.adewale.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView sumTextView;
    TextView pointsTextView;
    TextView timerTextView;
    Button playAgainButton;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    ConstraintLayout gameConstraintLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numbersOfQuestions = 0;

    public void playAgain(View view){
        score = 0;
        numbersOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();


        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0");
                resultTextView.setText("Your Score: " + Integer.toString(score) + " / " + Integer.toString(numbersOfQuestions));
            }
        }.start();
    }

    public void generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for(int i=0; i<4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a + b);
            }else{
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }

        button.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        }else{
            resultTextView.setText("Wrong!");
        }
        numbersOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + " / " + Integer.toString(numbersOfQuestions));
        generateQuestion();

    }
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);

        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameConstraintLayout = (ConstraintLayout)findViewById(R.id.gameConstraintLayout);


    }
}