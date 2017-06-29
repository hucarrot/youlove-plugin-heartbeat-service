package cn.com.youlove.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by carrot on 2016/8/11.
 */
public class HeartbeatService extends Service {

  private static final String TAG = "HeartbeatService";

  @Override
  public void onCreate() {
    super.onCreate();
    Log.i(TAG, "HeartbeatService-->onCreate");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.i(TAG, "HeartbeatService-->onDestroy");
  }

  @Override
  public void onStart(Intent intent, int startId) {

    final String urlStr = intent.getStringExtra("url");

    Thread heartbeat = new Thread(new Runnable() {
      @Override
      public void run() {

        Log.i(TAG, "死循环开始");

        while (true) {
          try {

            Log.i(TAG, "睡10秒");
            Thread.sleep(10 * 1000);

            Log.i(TAG, "发送心跳包");
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setInstanceFollowRedirects(false);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestProperty("content-type", "text/html");
            conn.setRequestProperty("Device-Type", "android");

            conn.connect();

            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String s = br.readLine();

            Log.i(TAG, s);

            br.close();
            isr.close();
            is.close();

            Log.i(TAG, "心跳包发送成功");

          } catch (Throwable e) {
            Log.e(TAG, e.getMessage());
          }
        }
      }
    });

    heartbeat.start();
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
