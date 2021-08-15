package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.icu.math.MathContext;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  Button btnTrue , btnFalse;
    private TextView question , answer;
    private  ProgressBar mProgressBar;
    int UserScore=0;
    private  final  String USER_SCORE = "SCORE";
    private final String USER_QUESTION_INDEX = "QUESTION_INDEX";

    private final QuestionAnswer[] mQuestionAnswers = new QuestionAnswer[]{
            new QuestionAnswer(R.string.q1,true),
            new QuestionAnswer(R.string.q2,true),
            new QuestionAnswer(R.string.q3,true),
            new QuestionAnswer(R.string.q4,true),
            new QuestionAnswer(R.string.q5,true),
            new QuestionAnswer(R.string.q6,true),
            new QuestionAnswer(R.string.q7,true),
            new QuestionAnswer(R.string.q8,true),
            new QuestionAnswer(R.string.q9,true),
            new QuestionAnswer(R.string.q10,true),
    };
    int mquestion;
    private int mQuestionIndex=0;
    private final int mProgressBarIncrease = (int)Math.ceil(100.0/mQuestionAnswers.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            mQuestionIndex=savedInstanceState.getInt(USER_QUESTION_INDEX);
            UserScore = savedInstanceState.getInt(USER_SCORE);
        }
        else{

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer_view);
        mProgressBar = findViewById(R.id.progress_bar);
        btnTrue = findViewById(R.id.Anscorrect);
        btnFalse = findViewById(R.id.Answrong);
        mquestion = mQuestionAnswers[mQuestionIndex].getQuestion();
        question.setText(mquestion);
        mProgressBar.incrementProgressBy(mProgressBarIncrease);

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswer(true);
                IncreaseIndex();
            }
        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswer(false);
                IncreaseIndex();
            }
        });


    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {


        savedInstanceState.putInt(USER_SCORE, UserScore);
        savedInstanceState.putInt(USER_QUESTION_INDEX,mQuestionIndex);





        super.onSaveInstanceState(savedInstanceState);
    }


    private void IncreaseIndex() {
        if(mQuestionIndex<mQuestionAnswers.length-1){
            mQuestionIndex+=1;
            mquestion = mQuestionAnswers[mQuestionIndex].getQuestion();
            question.setText(mquestion);
            mProgressBar.incrementProgressBy(mProgressBarIncrease);
        }
        else{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Your Quiz is Over");
            alertDialog.setMessage("Your Score is "+ UserScore);
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();

                }
            });
            alertDialog.show();

        }
    }

    private void CheckAnswer(boolean b) {
        if(mQuestionIndex<mQuestionAnswers.length){
        if(b==mQuestionAnswers[mQuestionIndex].isAnswer()){
            UserScore+=1;
        }
        }
    }
}