package ru.zzemlyanaya.openbagtrecker.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import ru.zzemlyanaya.openbagtrecker.Constants.USER
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.model.User
import ru.zzemlyanaya.openbagtrecker.databinding.ActivityLoginBinding
import ru.zzemlyanaya.openbagtrecker.login.signin.IOnLogin
import ru.zzemlyanaya.openbagtrecker.main.MainActivity

class LoginActivity : AppCompatActivity(), IOnLogin {

    private lateinit var binding: ActivityLoginBinding

    private var backPressedOnce = false

    override fun onBackPressed() {
        if (backPressedOnce)
            finish()
        else {
            backPressedOnce = true
            Toast.makeText(
                this@LoginActivity,
                "Нажмите ещё раз для выхода",
                Toast.LENGTH_SHORT
            ).show()
            Handler().postDelayed({ backPressedOnce = false }, 2000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        showTabs()
    }

    private fun showTabs(){
        binding.viewPager.adapter = ViewPagerAdapterLogin(this, 2)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position){
                0 -> tab.text = getString(R.string.sign_in)
                else -> tab.text = getString(R.string.sign_up)
            }
        }.attach()

        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            setMarginsInDp(tab, 12, 0, 12, 0)
        }

    }

    private fun setMarginsInDp(view: View, left: Int, top: Int, right: Int, bottom: Int){
        if(view.layoutParams is ViewGroup.MarginLayoutParams){
            val screenDensity: Float = view.context.resources.displayMetrics.density
            val params: ViewGroup.MarginLayoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(left*screenDensity.toInt(), top*screenDensity.toInt(), right*screenDensity.toInt(), bottom*screenDensity.toInt())
            view.requestLayout()
        }
    }

    private fun goOnMain(user: User) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(USER, user)
        startActivity(intent)
        finish()
    }

    override fun onLogin(user: User) {
        goOnMain(user)
    }

}