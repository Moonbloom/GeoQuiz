package com.moon.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);

		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		
		mTrueButton = (Button)findViewById(R.id.true_button_id);
		mTrueButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
			}
		});
		
		mFalseButton = (Button)findViewById(R.id.false_button_id);
		mFalseButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
			}
		});
			
		mNextButton = (Button)findViewById(R.id.next_button_id);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
				updateQuestion();
			}
		});
		
		updateQuestion();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

}
