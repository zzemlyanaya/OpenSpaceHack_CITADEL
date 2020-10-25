package ru.zzemlyanaya.openbagtrecker.main.editprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import ru.zzemlyanaya.openbagtrecker.Constants.USER
import ru.zzemlyanaya.openbagtrecker.R
import ru.zzemlyanaya.openbagtrecker.SwipeToDeleteCallback
import ru.zzemlyanaya.openbagtrecker.data.model.Device
import ru.zzemlyanaya.openbagtrecker.data.model.User
import ru.zzemlyanaya.openbagtrecker.databinding.FragmentEditProfileBinding
import ru.zzemlyanaya.openbagtrecker.getDevicesFromString
import ru.zzemlyanaya.openbagtrecker.getStringWithDevices
import ru.zzemlyanaya.openbagtrecker.main.MainActivity

class EditProfileFragment : Fragment() {

    private lateinit var user: User

    private lateinit var recyclerView: RecyclerView

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
        val binding: FragmentEditProfileBinding
            = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false)

        binding.butBackFromEdit.setOnClickListener {
            (requireActivity() as MainActivity).onBackPressed()
        }
        binding.butSaveProfile.setOnClickListener {
            user.fio = binding.textFio.text.toString()
            user.devices = getStringWithDevices((recyclerView.adapter as DevicesRecyclerViewAdapter).getData())
            (requireActivity() as MainActivity).updateUser(user)
            (requireActivity() as MainActivity).onBackPressed()
        }

        binding.textFio.setText(user.fio)

        recyclerView = binding.devicesRecylcerView

        val animator = DefaultItemAnimator()
        animator.addDuration = 400
        animator.removeDuration = 400
        animator.moveDuration = 400

        with(recyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = DevicesRecyclerViewAdapter(getDevicesFromString(user.devices) as ArrayList<Device>)
            itemAnimator = animator
        }

        enableSwipeToEditAndUndo()

        binding.butAddDevice.setOnClickListener {
            createAddDialog().show()
        }

        return binding.root
    }


    private fun enableSwipeToEditAndUndo() {
        val swipeToDeleteCallback: SwipeToDeleteCallback = object : SwipeToDeleteCallback(recyclerView.context) {
            override fun onSwiped(@NonNull viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                val item: Device = (recyclerView.adapter as DevicesRecyclerViewAdapter).getData()[position]
                (recyclerView.adapter as DevicesRecyclerViewAdapter).removeItem(position)
                view?.let { createEditDialog(item, position).show() }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun createAddDialog(): AlertDialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_add_device, null)

        val textModel: TextInputEditText = view.findViewById(R.id.textModel)
        val textOS: TextInputEditText = view.findViewById(R.id.textOS)

        builder.setView(view)
            .setPositiveButton("Сохранить") { _, _ ->
                try {
                    val model = textModel.text.toString()
                    val os = textOS.text.toString()

                    if(model == "" || os == "")
                        throw Exception("Заполните все поля!")

                    val device = Device(model, os)
                    (recyclerView.adapter as DevicesRecyclerViewAdapter).addItem(device)
                } catch (e: Exception) {
                    showError(e.message.orEmpty())
                }

            }
            .setNegativeButton("Отмена") {_, _ -> }
        return builder.create()
    }

    private fun createEditDialog(device: Device, position: Int): AlertDialog{
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_add_device, null)

        val textModel: TextInputEditText = view.findViewById(R.id.textModel)
        textModel.setText(device.model)
        val textOS: TextInputEditText = view.findViewById(R.id.textOS)
        textOS.setText(device.os)

        builder.setView(view)
            .setPositiveButton("Сохранить") { _, _ ->
                try {
                    val model = textModel.text.toString()
                    val os = textOS.text.toString()

                    if(model == "" || os == "")
                        throw Exception("Заполните все поля!")

                    val deviceNew = Device(model, os)
                    (recyclerView.adapter as DevicesRecyclerViewAdapter).restoreItem(deviceNew, position)
                } catch (e: Exception) {
                    showError(e.message.orEmpty())
                }

            }
            .setNegativeButton("Отмена") {_, _ ->
                (recyclerView.adapter as DevicesRecyclerViewAdapter).restoreItem(device, position)
                recyclerView.scrollToPosition(position)
            }
            .setNegativeButton(
                "Удалить"
            ) {_, _ ->
                (recyclerView.adapter as DevicesRecyclerViewAdapter).removeItem(position)
                Snackbar
                    .make(recyclerView, "Устройство удалено", Snackbar.LENGTH_SHORT)
                    .setAction(
                        "ВЕРНУТЬ"
                    )  {
                        (recyclerView.adapter as DevicesRecyclerViewAdapter).restoreItem(device, position)
                        recyclerView.scrollToPosition(position)
                    }
                    .setActionTextColor(resources.getColor(R.color.accent_blue))
                    .show()
            }
        return builder.create()
    }

    private fun showError(e: String){
        Toast.makeText(recyclerView.context, e, Toast.LENGTH_SHORT)
            .show()
    }


    companion object {
        @JvmStatic
        fun newInstance(user: User) =
            EditProfileFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER, user)
                }
            }
    }
}