package  com.itravis.tnbooks.myapplication.utils.NetworkUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;


public class NetworkChangeHandler {

    private Handler networkChangeHandler = new Handler();

    private NetworkChangeListener networkChangeListener;

    public void registerNetWorkStateBroadCast(Context context) {
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(networkStateChangeReceiver, intentFilter);
    }

    public void setNetworkStateListener(NetworkChangeListener networkChangeListener) {
        this.networkChangeListener = networkChangeListener;
    }

    public void unRegisterNetWorkStateBroadCast(Context context) {
        try {
            context.unregisterReceiver(networkStateChangeReceiver);
        } catch (Exception ex) {
        }
    }

    private BroadcastReceiver networkStateChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equalsIgnoreCase(intent.getAction())) {
                final boolean state = (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE));
                networkChangeHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (networkChangeListener != null) {
                            networkChangeListener.networkChangeReceived(!state);
                        }
                    }
                });
            }
        }
    };

    public interface NetworkChangeListener {
        void networkChangeReceived(boolean state);
    }
}