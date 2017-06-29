package cn.com.youlove.app.service;

import android.app.Activity;
import android.content.Intent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class echoes a string called from JavaScript.
 */
public class Heartbeat extends CordovaPlugin {

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

    if (action.equals("start")) {

      String url = args.getString(0);

      Intent intent = new Intent(getActivity(), HeartbeatService.class);
      intent.putExtra("url", url);
      getActivity().startService(intent);

      return true;

    } else if (action.equals("stop")) {

      Intent intent = new Intent(getActivity(), HeartbeatService.class);
      getActivity().startService(intent);

    }
    return false;
  }

  private Activity getActivity() {
    return this.cordova.getActivity();
  }
}
