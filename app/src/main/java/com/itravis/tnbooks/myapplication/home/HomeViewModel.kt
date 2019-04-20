package  com.itravis.tnbooks.myapplication.home

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.itravis.tnbooks.myapplication.R
import com.itravis.tnbooks.myapplication.setup.fragmentSetup.FragmentBase
import com.itravis.tnbooks.myapplication.utils.Constants.Companion.USER_VARIANT
import com.itravis.tnbooks.myapplication.utils.NetworkUtil.NetworkChangeHandler

class HomeViewModel(internal var context: Context,
                    internal var fragment: FragmentHome) : NetworkChangeHandler.NetworkChangeListener {

    private var networkStateHandler: NetworkChangeHandler? = null


    init {
        networkHandler()

    }

    private fun networkHandler() {
        networkStateHandler = NetworkChangeHandler()
    }



    //to launch a child fragment.
     fun lauchChildFragment(value: String, mapFragment: FragmentBase) {
        val bundle = Bundle()
        bundle.putString(USER_VARIANT, value)
        mapFragment.setArguments(bundle)
        fragment.getRegistrationFragment()!!.addFragment(mapFragment)
    }


    fun registerListeners() {
        networkStateHandler?.registerNetWorkStateBroadCast(context)
        networkStateHandler?.setNetworkStateListener(this)
    }

    fun unRegisterListeners() {
        networkStateHandler?.unRegisterNetWorkStateBroadCast(context)
    }

    override fun networkChangeReceived(state: Boolean) {
        if (!state) {
            Toast.makeText(context, context.getResources().getString(R.string.network_ErrorMsg), Toast.LENGTH_SHORT).show();
        }
    }

}
