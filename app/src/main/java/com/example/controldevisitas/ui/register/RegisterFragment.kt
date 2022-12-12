package com.example.controldevisitas.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.controldevisitas.R
import com.example.controldevisitas.TAG
import com.example.controldevisitas.data.model.Register
import com.example.controldevisitas.databinding.FragmentRegisterBinding
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val registerViewModel =
                ViewModelProvider(this).get(RegisterViewModel::class.java)

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val txtUsername:TextView = binding.txtRegUserName
        registerViewModel.username.observe(viewLifecycleOwner) {
            txtUsername.text = it
        }

        val btnRegister: Button = binding.btnRegisterVisit
        btnRegister.setOnClickListener {
            onBtnRegisterClick()
        }

        val txtVisitor:TextView = binding.txtVisitorName
        registerViewModel.visitor.observe(viewLifecycleOwner) {
            txtVisitor.text = it
        }

        val txtFloor:TextView = binding.txtFloor
        registerViewModel.floor.observe(viewLifecycleOwner) {
            txtFloor.text = it.toString()
        }

        val rdoParking:SwitchMaterial = binding.rdoParking
        registerViewModel.requireParking.observe(viewLifecycleOwner) {
            rdoParking.isChecked = it
        }

        val txtPurpose:TextView = binding.txtPurpose
        registerViewModel.purpose.observe(viewLifecycleOwner) {
            txtPurpose.text = it
        }

        val dateView:CalendarView = binding.calendarView
        dateView.setOnDateChangeListener { calendarView: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            calendarView.setDate(calendar.timeInMillis, true, true)
        }

        registerViewModel.date.observe(viewLifecycleOwner) {
            dateView.setDate(it, true, true)
        }

        val txtTime:TextView = binding.txtTime
        registerViewModel.time.observe(viewLifecycleOwner) {
            txtTime.text = it
        }

        return root
    }

    private fun onBtnRegisterClick() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid!!
        val txtVisitor = binding.txtVisitorName
        val txtFloor = binding.txtFloor
        val rdoParking = binding.rdoParking
        val txtPurpose = binding.txtPurpose
        val txtTime = binding.txtTime
        val datePick = binding.calendarView
        Log.d(TAG(), "Date: ${datePick.date}")
        val visitorReg = Register(uid, txtVisitor.text.toString(), txtFloor.text.toString().toLong(),
            rdoParking.isChecked, txtPurpose.text.toString(), false,
            datePick.date, txtTime.text.toString())
        val db = FirebaseDatabase.getInstance().getReference("Registers")
        db.child(uid).setValue(visitorReg).addOnSuccessListener {
            Log.d(TAG(), "createRegisterData:success ${txtVisitor.text}")
            Toast.makeText(
                this.context, this.getString(R.string.action_register_success),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}