package ru.zzemlyanaya.openbagtrecker.main.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.Status
import ru.zzemlyanaya.openbagtrecker.data.model.Resource
import ru.zzemlyanaya.openbagtrecker.databinding.FragmentChatsBinding

class ChatsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout


    private val viewModel by lazy { ViewModelProviders.of(this).get(ChatsViewModel::class.java)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentChatsBinding
                = DataBindingUtil.inflate(inflater, R.layout.fragment_chats, container, false)

        with(binding.chatList) {
            layoutManager = LinearLayoutManager(context)
            adapter = ChatsRecyclerViewAdapter(
                {onChatClick(it)},
                listOf(ChatShortView(0, 0, 1, "OpenSpaceCat", "Hello!"))
            )
        }

        recyclerView = binding.chatList

        refreshLayout = binding.refreshLayout
        refreshLayout.setOnRefreshListener {
            refreshData()
        }

        return binding.root
    }

    private fun onChatClick(it: ChatShortView){
        Toast.makeText(context, "Функция в разработке, но мы страемся", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.chats.observe(viewLifecycleOwner, { showData(Resource.success(it)) })
    }

    private fun refreshData(){
        viewModel.fetchAllChatsRemotely().observe(viewLifecycleOwner, { showData(it) })
    }

    private fun showData(it: Resource<List<ChatShortView>?>){
        when (it.status) {
            Status.SUCCESS -> {
                refreshLayout.isRefreshing = false
                recyclerView.visibility = View.VISIBLE
                it.data?.let { list ->
                    recyclerView.adapter =
                        ChatsRecyclerViewAdapter({onChatClick(it)}, list)
                }
            }
            Status.ERROR -> {
                refreshLayout.isRefreshing = false
                recyclerView.visibility = View.VISIBLE
                Toast.makeText(requireContext(), getString(R.string.connection_failed), Toast.LENGTH_SHORT).show()
            }
            Status.LOADING -> {
                recyclerView.visibility = View.INVISIBLE
            }
        }
    }
}