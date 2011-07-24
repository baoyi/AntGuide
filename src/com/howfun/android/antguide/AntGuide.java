package com.howfun.android.antguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class AntGuide extends Activity implements OnTouchListener {
   private static final String TAG = "AntGuide";

   private AntView mAntView;
   
   public static int DEVICE_WIDTH;
   public static int DEVICE_HEIGHT;
   
   Intent mIntentService = null;
   Intent mIntentReceiver = null;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      
      DisplayMetrics dm = new DisplayMetrics();
      this.getWindowManager().getDefaultDisplay().getMetrics(dm); 
      DEVICE_WIDTH = dm.widthPixels; 
      DEVICE_HEIGHT = dm.heightPixels;
      
      
      mAntView = new AntView(this);
      setContentView(mAntView);
      
      
      
      Utils.log(TAG, "width="+DEVICE_WIDTH+",height=========:"+DEVICE_HEIGHT);
      mIntentService = new Intent("com.howfun.android.antguide.MusicService");
      mIntentReceiver = new Intent("com.howfun.android.antguide.MusicReceiver");

      mAntView.setOnTouchListener(new OnTouchListener() {

         @Override
         public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            float x = event.getX();
            float y = event.getY();
            switch (action) {
            case MotionEvent.ACTION_DOWN:
               mAntView.setDownPos(x, y);

               break;
            case MotionEvent.ACTION_MOVE:
               break;
            case MotionEvent.ACTION_UP:
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
   
   @Override
   protected void onResume() {
      super.onResume();

      sendBroadcast(mIntentReceiver);
//      startService(mIntentService);
   }

   @Override
   protected void onPause() {
      super.onPause();

      stopService(mIntentService);
   }
}