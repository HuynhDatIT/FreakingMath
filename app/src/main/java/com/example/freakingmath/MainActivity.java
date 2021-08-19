package com.example.freakingmath;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView mTxtPoint, mTxtExpression, mTxtRusult;
    ImageButton mImgRight, mImgWrong;
    RelativeLayout mContener;
    float mNumber1;
    float mNumber2;
    float mResult;
    Random mRandom;
    int mOperator;
    int mPoint;
    boolean mRandomResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView();
        initDeclare();
        setExpression();
        event();
    }

    private void mapView(){
        //Ánh xạ
        mTxtPoint = findViewById(R.id.TextViewPoint);
        mTxtExpression = findViewById(R.id.TextViewExpression);
        mTxtRusult = findViewById(R.id.TextViewRusult);
        mImgRight = findViewById(R.id.ButtonRight);
        mImgWrong = findViewById(R.id.ButtonWrong);
        mContener = findViewById(R.id.RelativeContaner);
    }

    private void initDeclare(){
        mNumber1 = mNumber2 = 0;
        mResult = -1f;
        mRandomResult = true;
        mOperator = -1;
        mRandom = new Random();
        mPoint = 0;
    }

    private void setExpression(){
        mNumber1 = (int) (Math.random() * 100);
        //Cach 2
        mNumber2 = mRandom.nextInt(100 - 1 + 1) + 1;

        mOperator = mRandom.nextInt(4);

        //Opretor random
        // 0 -> +
        // 1 -> -
        // 2 -> *
        // 3 -> /
        // Random result
        mRandomResult = mRandom.nextBoolean();

        mContener.setBackgroundColor(Color.rgb(mRandom.nextInt(200), mRandom.nextInt(200), mRandom.nextInt(200)));

        switch (mOperator){
            case 0:
                setTextExpression(mNumber1, mNumber2, "+");
                mResult = mNumber1 + mNumber2;
                break;
            case 1:
                setTextExpression(mNumber1, mNumber2, "-");
                mResult = mNumber1 - mNumber2;
                break;
            case 2:
                setTextExpression(mNumber1, mNumber2, "*");
                mResult = mNumber1 * mNumber2;
                break;
            case 3:
                setTextExpression(mNumber1, mNumber2, "/");
                mResult = mNumber1 / mNumber2;
                break;
        }
        if(!mRandomResult){
            mResult += mRandom.nextInt(5)+ 1;
        }
        setTextResult(mResult);
    }

    private void setTextExpression(float number1, float number2, String operator){
        mTxtExpression.setText(String.format("%d %s %d",(int) number1, operator, (int)number2));
    }

    private void setTextResult(float result){
        if(Math.floor(mResult) - mResult < 0){
            mTxtRusult.setText(String.format("= %s", new DecimalFormat("#.##").format(result)));
        }else{
            mTxtRusult.setText(String.format("= %d", (int) result));
        }
    }

    private void event(){
        mImgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRandomResult){
                    mPoint += 1;
                }else {
                    Toast.makeText(MainActivity.this, "Kết quả sai , số điểm cao nhất là " + mPoint, Toast.LENGTH_SHORT).show();
                    mPoint = 0;
                }
                mTxtPoint.setText(String.valueOf(mPoint));
                setExpression();
            }
        });

        mImgWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mRandomResult){
                    mPoint += 1;
                }else{
                    Toast.makeText(MainActivity.this, "Kết quả sai , số điểm cao nhất là " + mPoint, Toast.LENGTH_SHORT).show();
                    mPoint = 0;
                }
                mTxtPoint.setText(String.valueOf(mPoint));
                setExpression();
            }
        });
    }
}