package com.howfun.android.antguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class AntGuide extends Activity implements OnTouchListener {
   private static final String TAG = "AntGuide";

   private AntView mAntView;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      mAntView = new AntView(this);
      setContentView(mAntView);

      mAntView.setOnTouchListener(new OnTouchListener() {

         @Override
         public boolean onTouch(View v, MotionEvent event) {
            Utils.log(TAG, "ontouch...");
            int action = event.getAction();
            float x = event.getX();
            float y = event.getY();
            switch (action) {
            case MotionEvent.ACTION_DOWN:
               Utils.log(TAG, "Down");
               mAntView.setDownPos(x, y);

               break;
            case MotionEvent.ACTION_MOVE:
               Utils.log(TAG, "Move");
               break;
            case MotionEvent.ACTION_UP:
               Utils.log(TAG, "Up");
               mAntView.setUpPos(x, y);
               mAntView.showBlockLine();
               break;
            }
            return true;
         }

      });
   }

   @Override
   public boolean onTouch(View v, MotionEvent event) {
      return false;
   }
}