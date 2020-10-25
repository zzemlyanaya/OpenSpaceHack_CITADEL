package ru.zzemlyanaya.openbagtrecker.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.zzemlyanaya.openbagtrecker.Constants.USER
import ru.zzemlyanaya.openbagtrecker.Constants.WIZARD
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.model.User
import ru.zzemlyanaya.openbagtrecker.databinding.FragmentProfileBinding
import ru.zzemlyanaya.openbagtrecker.getAchievListFromString
import ru.zzemlyanaya.openbagtrecker.main.MainActivity
import ru.zzemlyanaya.openbagtrecker.main.achiev.AchievRecyclerViewAdapter

class ProfileFragment : Fragment() {

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable(USER) as User
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProfileBinding
                = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        binding.textProfileName.text = user.name
        binding.textProfileCoins.text = user.coins.toString()
        when(user.type) {
            WIZARD ->  {
                binding.textProfileStatus.text = getString(R.string.wizard)
                binding.profileImage.setImageResource(R.drawable.ic_wizard)
            }
            else -> {
                binding.textProfileStatus.text = getString(R.string.pirate)
                binding.profileImage.setImageResource(R.drawable.ic_pirate)
            }
        }

        binding.butGo2Shop.setOnClickListener {
            (requireActivity() as MainActivity).showShopFragment()
        }

        binding.fabEditProfile.setOnClickListener {
            (requireActivity() as MainActivity).showEditProfileFragment()
        }

        val recyclerView = binding.achievRecyclerProfile.achievRecyclerView
        with(recyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AchievRecyclerViewAdapter(getAchievListFromString(user.achievements).subList(0, 2))
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(user: User) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER, user)
                }
            }
    }
}