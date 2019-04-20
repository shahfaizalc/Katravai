package  com.itravis.tnbooks.myapplication.setup.fragmentSetup

import android.os.Bundle
import com.itravis.tnbooks.myapplication.R
import android.support.v4.app.Fragment
import com.itravis.tnbooks.myapplication.home.FragmentHome
import com.itravis.tnbooks.myapplication.utils.Constants
import java.io.Serializable

open class DrawerNavigation : FragmentInit() {

    protected fun itemSelected(id: Int) {
        when (id) {
            R.id.nav_home -> {
                launchParentFragment(Constants.HOME, FragmentHome())
            }
        }
    }

    private fun launchParentFragment(value: String, fragmentVariations: FragmentBase) {
        val fragment: Fragment = FragmentParent()
        val bundle = Bundle()
        bundle.putSerializable(Constants.USER_MENU, fragmentVariations as Serializable)
        bundle.putString(Constants.USER_VARIANT, value)
        fragment.setArguments(bundle)
        navigateFragment(fragment)

    }

}