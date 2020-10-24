package ru.zzemlyanaya.openbagtrecker.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.databinding.ActivityMainBinding
import ru.zzemlyanaya.openbagtrecker.main.chats.ChatsFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_chats -> showChatsFragment()
                R.id.nav_profile -> showProfileFragment()
                R.id.nav_achiev -> showAchievementsFragment()
                else -> showBugTrackerFragment()
            }
            return@setOnNavigationItemSelectedListener true
        }
        binding.navView.setOnNavigationItemReselectedListener {  }

        showChatsFragment()
    }



    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container_main)
        when(fragment!!.tag) {
            "chats" -> showChatsFragment()
            else -> {}
        }
    }

    private fun onBackPressedDouble(){
       //TODO("on back pressed")
    }

    fun showChatsFragment(){
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .replace(R.id.container_main, ChatsFragment(), "chats")
            .commitAllowingStateLoss()

        binding.navView.visibility = View.VISIBLE
    }

    fun showProfileFragment() {
//        supportFragmentManager.beginTransaction()
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//            .replace(R.id.container_main, ChatsFragment(), "profile")
//            .commitAllowingStateLoss()
//
//        binding.navView.visibility = View.VISIBLE
    }

    fun showAchievementsFragment() {
//        supportFragmentManager.beginTransaction()
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//            .replace(R.id.container_main, ChatsFragment(), "achiev")
//            .commitAllowingStateLoss()
//
//        binding.navView.visibility = View.VISIBLE
    }

    fun showBugTrackerFragment() {
//        supportFragmentManager.beginTransaction()
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//            .replace(R.id.container_main, ChatsFragment(), "tracker")
//            .commitAllowingStateLoss()
//
//        binding.navView.visibility = View.VISIBLE
    }

}