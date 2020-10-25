package ru.zzemlyanaya.openbagtrecker.main.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import ru.zzemlyanaya.openbagtrecker.Constants.COINS
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.model.Stuff
import ru.zzemlyanaya.openbagtrecker.databinding.FragmentShopBinding
import ru.zzemlyanaya.openbagtrecker.main.MainActivity


class ShopFragment : Fragment() {

    private var coins = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            coins = it.getString(COINS).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShopBinding
                = DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false)

        binding.textBalance.text = coins

        with(binding.stuffRecyclerView) {
                layoutManager = GridLayoutManager(requireContext(), 2)

                adapter = StuffRecyclerViewAdapter(listOf(
                    Stuff(R.drawable.ic_cup, 100),
                    Stuff(R.drawable.ic_tshirt, 300),
                    Stuff(R.drawable.ic_hoodi, 800),
                ))
        }

        binding.butBack2Profile.setOnClickListener {
            (requireActivity() as MainActivity).onBackPressed()
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(coins: String) =
            ShopFragment().apply {
                arguments = Bundle().apply {
                    putString(COINS, coins)
                }
            }
    }

}