package com.example.cricfantasy

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cricfantasy.databinding.ActivityMainBinding
import com.example.cricfantasy.ui.gallery.GalleryFragment
import com.example.cricfantasy.ui.home.HomeFragment
import com.example.cricfantasy.ui.slideshow.SlideshowFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    private fun getCurrentVisibleFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.primaryNavigationFragment as NavHostFragment?
        val fragmentManager: FragmentManager = navHostFragment!!.childFragmentManager
        val fragment: Fragment? = fragmentManager.primaryNavigationFragment
        return if (fragment is Fragment) {
            fragment
        } else null
    }

    private fun getCurrentFragmentTextContent(): String{

        val currentFragment : Fragment = getCurrentVisibleFragment()!!
        var contentString = "";
        if(currentFragment is BaseFragment){
            contentString = currentFragment.getViewModel().text.value.toString();
        }
        return contentString;
    }
    private fun setCurrentFragmentTextContent(){

        val currentFragment : Fragment = getCurrentVisibleFragment()!!
        val randomNumber = Random.Default.nextInt(100).toString()
        if(currentFragment is GalleryFragment){
            currentFragment.getViewModel().updateData("Gallery$randomNumber");
        }
        if(currentFragment is HomeFragment){
            currentFragment.getViewModel().updateData("Home$randomNumber");
        }
        if(currentFragment is SlideshowFragment){
            currentFragment.getViewModel().updateData("SlideShow$randomNumber");
        }
        Log.i("INFO: ","clicked fab");
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            val original = "Replace with your own action";
            val content = getCurrentFragmentTextContent();
            if(content.isNotEmpty()) {
                Snackbar.make(view, content, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            else{
                Snackbar.make(view, original, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
        binding.appBarMain.setFab.setOnClickListener {
            setCurrentFragmentTextContent();
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
