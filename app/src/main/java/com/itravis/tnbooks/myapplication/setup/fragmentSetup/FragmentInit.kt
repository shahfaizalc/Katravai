package  com.itravis.tnbooks.myapplication.setup.fragmentSetup

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.itravis.tnbooks.myapplication.R

open class FragmentInit : AppCompatActivity() {

    protected fun navigateFragment(fr: Fragment) {

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fragment_place, fr)
        ft.commit()

    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        for (frag in fm.fragments) {
            if (frag.isVisible) {
                val childFm = frag.childFragmentManager

                if (childFm.backStackEntryCount > 1) {
                    childFm.popBackStack()
                    return
                }
            }
        }
        super.onBackPressed()
    }

}