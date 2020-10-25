package ru.zzemlyanaya.openbagtrecker.main.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.Status
import ru.zzemlyanaya.openbagtrecker.data.model.Bug
import ru.zzemlyanaya.openbagtrecker.data.model.Resource
import ru.zzemlyanaya.openbagtrecker.data.model.UserShortView
import ru.zzemlyanaya.openbagtrecker.databinding.FragmentTrackerBinding
import ru.zzemlyanaya.openbagtrecker.main.MainActivity


class TrackerFragment : Fragment() {

    private lateinit var leaderRecyclerView: RecyclerView
    private lateinit var bugsRecyclerView: RecyclerView

    private lateinit var progressLeader: ProgressBar
    private lateinit var progressBugs: ProgressBar

    private val viewModel by lazy { ViewModelProviders.of(this).get(TrackerViewModel::class.java)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTrackerBinding
                = DataBindingUtil.inflate(inflater, R.layout.fragment_tracker, container, false)

        leaderRecyclerView = binding.leaderboardRecylcerView
        with(leaderRecyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = LeaderboardRecyclerViewAdapter(emptyList())
        }

        bugsRecyclerView = binding.bugsRecylcerView
        with(bugsRecyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = BugsRecyclerViewAdapter(emptyList())
        }

        progressLeader = binding.progressLeader
        progressBugs = binding.progressBugs

        binding.leaderboardCard.setOnClickListener {
            go2Leaderboard()
        }

        binding.fabReport.setOnClickListener {
            (requireActivity() as MainActivity).showReportBugFragment()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchTop3LeaderBoard().observe(viewLifecycleOwner, { showLeaderData(it) })
        viewModel.fetchAllBugsLocally().observe(viewLifecycleOwner, { showBugsData(it) })
    }

    private fun showLeaderData(resource: Resource<List<UserShortView>?>){
        when (resource.status) {
            Status.SUCCESS -> {
                leaderRecyclerView.visibility = View.VISIBLE
                progressLeader.visibility = View.INVISIBLE
                resource.data?.let { list ->
                    leaderRecyclerView.adapter =
                        LeaderboardRecyclerViewAdapter(list)
                }
            }
            Status.ERROR -> {
                leaderRecyclerView.visibility = View.VISIBLE
                Toast.makeText(requireContext(), getString(R.string.connection_failed), Toast.LENGTH_SHORT).show()
            }
            Status.LOADING -> {
                leaderRecyclerView.visibility = View.INVISIBLE
                progressLeader.visibility = View.VISIBLE
            }
        }
    }

    private fun showBugsData(resource: Resource<List<Bug>?>){
        when (resource.status) {
            Status.SUCCESS -> {
                bugsRecyclerView.visibility = View.VISIBLE
                progressBugs.visibility = View.INVISIBLE
                resource.data?.let { list ->
                    bugsRecyclerView.adapter =
                        BugsRecyclerViewAdapter(list)
                }
            }
            Status.ERROR -> {
                bugsRecyclerView.visibility = View.VISIBLE
                Toast.makeText(requireContext(), getString(R.string.connection_failed), Toast.LENGTH_SHORT).show()
            }
            Status.LOADING -> {
                bugsRecyclerView.visibility = View.INVISIBLE
                progressBugs.visibility = View.VISIBLE
            }
        }
    }

    private fun go2Leaderboard() {
        Toast.makeText(requireContext(), getString(R.string.no_function), Toast.LENGTH_SHORT).show()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TrackerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}