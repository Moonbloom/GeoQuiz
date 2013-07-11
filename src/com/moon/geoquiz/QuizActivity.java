package com.moon.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	private ImageButton mPreviousButton;
	private TextView mQuestionTextView;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[] {
		new TrueFalse(R.string.question_1, true),
		new TrueFalse(R.string.question_2, false),
		new TrueFalse(R.string.question_3, false),
		new TrueFalse(R.string.question_4, true),
		new TrueFalse(R.string.question_5, true),
	};
	
	private int mCurrentIndex = 0;
	
	private void updateQuestion() 
	{
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean userPressedTrue)
	{
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		
		int messageResId = 0;
		
		if (userPressedTrue == answerIsTrue)
		{
			messageResId = R.string.correct_toast;
		}
		else
		{
			messageResId = R.string.incorrect_toast;
		}
		
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);

		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		mQuestionTextView.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
				updateQuestion();
			}
		});
		
		mTrueButton = (Button)findViewById(R.id.true_button_id);
		mTrueButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) 
			{
				checkAnswer(true);
			}
		});
		
		mFalseButton = (Button)findViewById(R.id.false_button_id);
		mFalseButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) 
			{
				checkAnswer(false);
			}
		});
			
		mNextButton = (ImageButton)findViewById(R.id.next_button_id);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
				updateQuestion();
			}
		});
		
		mPreviousButton = (ImageButton)findViewById(R.id.previous_button_id);
		mPreviousButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				if (mCurrentIndex > 0)
				{
					mCurrentIndex = (mCurrentIndex-1);
					updateQuestion();
				}
			}
		});
		
		// This makes a question appear at first when opening the app
		updateQuestion();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

}
