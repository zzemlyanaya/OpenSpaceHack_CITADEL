package ru.zzemlyanaya.openbagtrecker.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import ru.zzemlyanaya.openbagtrecker.Constants.USER
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.model.User
import ru.zzemlyanaya.openbagtrecker.databinding.ActivityMainBinding
import ru.zzemlyanaya.openbagtrecker.login.LoginActivity
import ru.zzemlyanaya.openbagtrecker.main.achiev.AchievementsFragment
import ru.zzemlyanaya.openbagtrecker.main.chats.ChatsFragment
import ru.zzemlyanaya.openbagtrecker.main.editprofile.EditProfileFragment
import ru.zzemlyanaya.openbagtrecker.main.profile.ProfileFragment
import ru.zzemlyanaya.openbagtrecker.main.reportbug.ReportBugFragment
import ru.zzemlyanaya.openbagtrecker.main.shop.ShopFragment
import ru.zzemlyanaya.openbagtrecker.main.tracker.TrackerFragment


class MainActivity : AppCompatActivity() {

    private lateinit var currentUser: User

    private lateinit var binding: ActivityMainBinding

    private var backPressedOnce = false

    fun updateUser(user: User){
        currentUser = user
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            currentUser = it.getSerializableExtra(USER) as User
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_chats -> showChatsFragment()
                R.id.nav_profile -> showProfileFragment(currentUser)
                R.id.nav_achiev -> showAchievementsFragment()
                else -> showTrackerFragment()
            }
            return@setOnNavigationItemSelectedListener true
        }
        binding.navView.setOnNavigationItemReselectedListener {  }

        showTrackerFragment()
    }


    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container_main)
        when(fragment!!.tag) {
            "chats", "profile", "tracker", "achiev" -> onBackPressedDouble()
            "shop", "edit_profile" -> showProfileFragment(currentUser)
            "report_bug" -> showTrackerFragment()
            else -> {}
        }
    }

    private fun onBackPressedDouble(){
        if (backPressedOnce) {
            val intent = Intent(this, LoginActivity::class.java).putExtra("LOGOUT", true)
            startActivity(intent)
            finish()
        }
        else {
            backPressedOnce = true
            Toast.makeText(
                this@MainActivity,
                "Нажмите ещё раз для выхода",
                Toast.LENGTH_SHORT
            ).show()
            Handler().postDelayed({ backPressedOnce = false }, 2000)
        }
    }


    fun showChatsFragment(){
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .replace(R.id.container_main, ChatsFragment(), "chats")
            .commitAllowingStateLoss()

        binding.navView.visibility = View.VISIBLE
    }

    fun showProfileFragment(user: User) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .replace(R.id.container_main, ProfileFragment.newInstance(user), "profile")
            .commitAllowingStateLoss()

        binding.navView.visibility = View.VISIBLE
    }

    fun showAchievementsFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .replace(R.id.container_main, AchievementsFragment.newInstance(currentUser.achievements), "achiev")
            .commitAllowingStateLoss()

        binding.navView.visibility = View.VISIBLE
    }

    fun showTrackerFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .replace(R.id.container_main, TrackerFragment(), "tracker")
            .commitAllowingStateLoss()

        binding.navView.visibility = View.VISIBLE
    }

    fun showShopFragment(){
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container_main, ShopFragment.newInstance((currentUser.coins-currentUser.coins*0.53).toInt().toString()), "shop")
            .commitAllowingStateLoss()

        binding.navView.visibility = View.GONE
    }

    fun showEditProfileFragment(){
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container_main, EditProfileFragment.newInstance(currentUser), "edit_profile")
            .commitAllowingStateLoss()

        binding.navView.visibility = View.GONE
    }

    fun showReportBugFragment(){
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container_main, ReportBugFragment.newInstance(currentUser), "report_bug")
            .commitAllowingStateLoss()

        binding.navView.visibility = View.GONE
    }

}