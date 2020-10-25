package ru.zzemlyanaya.openbagtrecker.main.reportbug

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.zzemlyanaya.openbagtrecker.Constants.USER
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.data.model.User
import ru.zzemlyanaya.openbagtrecker.databinding.FragmentReportBugBinding
import ru.zzemlyanaya.openbagtrecker.getDevicesFromString
import ru.zzemlyanaya.openbagtrecker.main.MainActivity


class ReportBugFragment : Fragment() {

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
        val binding: FragmentReportBugBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_report_bug, container, false)

        binding.butAddScreens.setOnClickListener {
            addScreens()
        }

        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_spinner_item,
            getDevicesFromString(user.devices).map { "${it.model} / ${it.os}" }
        )
        binding.spinDevice.adapter = spinnerArrayAdapter

        binding.butSendReport.setOnClickListener {
            //viewModel.sendReport(Bug(...))
            if (user.achievements.isBlank()) {
                user.achievements = "Первые шаги_1"
                showCongrats("Первые шаги")
            }
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.magical))
                .setIcon(R.drawable.ic_bug)
                .setMessage(getString(R.string.report_succes))
                .setPositiveButton("OK") { dialog, _ -> run { dialog.cancel() } }
                .create()
                .show()

            (requireActivity() as MainActivity).onBackPressed()
        }
        return binding.root
    }

    private fun showCongrats(achiev: String){
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.congrats))
            .setIcon(R.drawable.ic_trophy)
            .setMessage(getString(R.string.achiev)+achiev)
            .setPositiveButton("Ура!") { dialog, _ -> run { dialog.cancel() } }
            .create()
            .show()
    }

    private fun addScreens(){
        Toast.makeText(requireContext(), getString(R.string.no_function), Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(user: User) =
            ReportBugFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER, user)
                }
            }
    }
}