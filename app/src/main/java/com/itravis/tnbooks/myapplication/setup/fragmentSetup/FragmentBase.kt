package  com.itravis.tnbooks.myapplication.setup.fragmentSetup

import android.support.v4.app.Fragment
import java.io.Serializable


open class FragmentBase : Fragment(), Serializable {
    fun getRegistrationFragment(): FragmentParent? {
        val fragment = parentFragment
        return if (fragment != null && fragment is FragmentParent) {
            fragment
        } else null
    }
}