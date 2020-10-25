package ru.zzemlyanaya.openbagtrecker.main.achiev

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.zzemlyanaya.openbagtrecker.Constants.LIST
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.model.Achievement
import ru.zzemlyanaya.openbagtrecker.databinding.FragmentAchievementsBinding

class AchievementsFragment : Fragment() {

    private lateinit var data: String

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getString(LIST) as String
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAchievementsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_achievements, container, false)

        recyclerView = binding.achievRecyclerView
        with(recyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AchievRecyclerViewAdapter(getListFromString(data))
        }

        return binding.root
    }

    private fun getListFromString(str: String) = if (str.isBlank()) emptyList()
    else str.split('|').map {
        Achievement(it.split('_')[0], it.split('_')[1].toInt())
    }

    companion object {
        @JvmStatic
        fun newInstance(list: String,) =
            AchievementsFragment().apply {
                arguments = Bundle().apply {
                    putString(LIST, list)
                }
            }
    }
}