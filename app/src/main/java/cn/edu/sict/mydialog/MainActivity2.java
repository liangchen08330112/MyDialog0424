package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private GestureDetector detector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        GestureDetector detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent m1, MotionEvent m2, float distanceX, float distanceY) {
                Log.i("TAG","onScroll");
                Log.i("TAG","onScroll distanceX"+distanceX);
                Log.i("TAG","onScroll distanceY"+distanceY);

                if(distanceY>0){
                    Toast.makeText(MainActivity2.this,"你上滑了",Toast.LENGTH_SHORT).show();
                }
                if(distanceY<0){
                    Toast.makeText(MainActivity2.this,"你下滑了",Toast.LENGTH_SHORT).show();
                }
                if(distanceX<0){
                    Toast.makeText(MainActivity2.this,"你左滑了",Toast.LENGTH_SHORT).show();
                }
                if(distanceX>0){
                    Toast.makeText(MainActivity2.this,"你右滑了",Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent m1, MotionEvent m2, float velocityX, float velocityY) {
                Log.i("TAG","onFling");
                return false;
            }
        });
    }
}