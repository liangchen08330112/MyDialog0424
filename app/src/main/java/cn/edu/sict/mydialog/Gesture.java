package cn.edu.sict.mydialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Gesture extends AppCompatActivity implements View.OnTouchListener {

    private GestureDetector detector;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        //新建手势监听类，
        detector = new GestureDetector(this,new MyListener());

        textView = findViewById(R.id.textView);
        textView.setOnTouchListener(this);
        textView.setFocusable(true);
        textView.setClickable(true);
        textView.setLongClickable(true);
    }

    /**
     * 在onTouch()方法中，我们调用GestureDetector的onTouchEvent()方法，
     * 将捕捉到的MotionEvent交给GestureDetector
     * @param view
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return detector.onTouchEvent(motionEvent);
    }
    private class MyListener implements GestureDetector.OnGestureListener{

        //用户轻触摸屏幕，由1个MotionEvent.ACTION_DOWN触发
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            Log.i("TAG","onDown()已被触发");
            Toast.makeText(Gesture.this,"onDown",Toast.LENGTH_SHORT).show();
            return false;
        }

        /**
         * 用户轻触摸屏幕，尚未松开或拖动，由1个MotionEvent.ACTION_DOWN触发
         * 注意和onDown()的区别，强调的是没有松开或拖动的状态
         * @param motionEvent
         *
         * 而onDown也是由一个MotionEvent.ACTION_DOWN触发的，但它没有任何限制
         * 也就是说当用户点击的时候，首先执行onDown()。如果在按下的瞬间没有松开或者拖动的时候，
         * onShowPress()就会执行。如果按下的时间超过瞬间（一般按下去就会执行onShowPress()）拖动了，就不执行。
         */
        @Override
        public void onShowPress(MotionEvent motionEvent) {
            Log.i("TAG","onShowPress()已被触发");
            Toast.makeText(Gesture.this,"onShowPress",Toast.LENGTH_SHORT).show();
        }

        /**
         * 用户在轻触触摸屏后松开，由一个MotionEvent.ACTION_UP触发，
         * 轻点一下屏幕再立即抬起，才会有这个触发。
         * 也可以看出，一次单独的轻击抬起操作，当然，如果除了Down以外还有其他操作，
         * 那就不再算是Single操作了，所以这个事件就不再响应。
         * @param motionEvent
         * @return
         */
        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            Log.i("TAG","onSingleTapUp()已被触发");
            Toast.makeText(Gesture.this,"onSingleTapup",Toast.LENGTH_SHORT).show();
            return true;
        }

        //用户按下触摸屏并拖动，由1个MotionEvent.ACTION_DOWN和多个ACTION_MOVE触发
        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1,
                                float distanceX, float distanceY) {
            Log.i("TAG","onScroll"+(motionEvent.getX()-motionEvent1.getX()+" "+distanceX));
            if(motionEvent1.getX()-motionEvent1.getX()>0){
                textView.setGravity(Gravity.CENTER|Gravity.RIGHT);
            }else{
                textView.setGravity(Gravity.CENTER|Gravity.LEFT);
            }
            return true;
        }
        //用户长按触摸屏，有多个MotionEvent.ACTOWN_DOWN触发
        @Override
        public void onLongPress(MotionEvent motionEvent) {
            Log.i("TAG","onLongPress()已被触发");
            Toast.makeText(Gesture.this,"onLongPress",Toast.LENGTH_SHORT).show();
        }

        /**
         * 用户按下触摸屏、快速移动后松开，由1个MotionEvent.ACTION_DOWN、多个ACTION_MOVE
         * 、和1个ACTION_UP触发
         * @param motionEvent
         * @param motionEvent1
         * @param velocityX
         * @param velocityY
         * @return
         */
        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1,
                               float velocityX, float velocityY) {
            Log.i("TAG","onFling()已被触发");
            Toast.makeText(Gesture.this,"onFling",Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}