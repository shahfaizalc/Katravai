package  com.itravis.tnbooks.myapplication.setup.fragmentSetup

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itravis.tnbooks.myapplication.R
import com.itravis.tnbooks.myapplication.utils.Constants

class FragmentParent : Fragment() {

    private var mFragmentManager: FragmentManager? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var (userFragment: FragmentBase, userVariant: String) = getBundleArguments()

        val view = inflater.inflate(R.layout.fragment_parent, container, false)
        mFragmentManager = childFragmentManager
        if (mFragmentManager!!.backStackEntryCount < 1) {
            loadFirstFragment(userVariant, userFragment)
            Log.d("TAG", "onCreateView : loadFirstFragment is called")
        }
        return view
    }

    private fun getBundleArguments(): Pair<FragmentBase, String> {
        var userFragment: FragmentBase = arguments!!.getSerializable(Constants.USER_MENU) as FragmentBase
        var userVariant: String = arguments!!.getString(Constants.USER_VARIANT)
        return Pair(userFragment, userVariant)
    }

    private fun loadFirstFragment(userVariant: String, userFragment: FragmentBase) {
        try {
            val bundle = Bundle()
            bundle.putString(Constants.USER_VARIANT, userVariant)
            userFragment.setArguments(bundle)
            addFragment(userFragment)
        } catch (e: IllegalStateException) {
            Log.e("loadFirst ", "Exception occured in loadFirstFragment  :" + e.message)
        }

    }

    //public function to add child fragments from anywhere of the application (Launcher fragment acts as by-pass class for adding new fragments)
    fun addFragment(fragment: Fragment) {
        try {
            if (null != mFragmentManager) {
                val fragmentTransaction = mFragmentManager!!.beginTransaction()
                fragmentTransaction.replace(R.id.fl_reg_fragment_container, fragment)
                fragmentTransaction.addToBackStack(fragment.tag)
                fragmentTransaction.commitAllowingStateLoss()
            }
        } catch (e: IllegalStateException) {
            Log.d("TAG", "replaceWithHomeFragment :FragmentTransaction " +
                    "Exception occurred in addFragment  :" + e.message)
        }

    }
}