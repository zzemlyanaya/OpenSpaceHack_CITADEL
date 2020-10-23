package ru.zzemlyanaya.openbagtrecker.login

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.zzemlyanaya.openbagtrecker.login.signin.SignInFragment
import ru.zzemlyanaya.openbagtrecker.login.signup.SignUpFragment


class ViewPagerAdapterLogin(activity: AppCompatActivity, private val itemsCount: Int) :
        FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int) = when(position) {
        0 ->  SignInFragment()
        else -> SignUpFragment()
    }
}