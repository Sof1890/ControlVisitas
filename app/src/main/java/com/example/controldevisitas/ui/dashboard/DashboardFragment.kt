package com.example.controldevisitas.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.controldevisitas.databinding.FragmentDashboardBinding
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private var currentDate:Long = 0
    private var currentTime:String = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val lblUsername:TextView = binding.lblUser2
        dashboardViewModel.username.observe(viewLifecycleOwner) {
            lblUsername.text = it
        }

        dashboardViewModel.time.observe(viewLifecycleOwner) {
            currentTime = it
            updateFromTo()
        }
        dashboardViewModel.date.observe(viewLifecycleOwner) {
            currentDate = it
            updateFromTo()
        }
        val lblVisitor:TextView = binding.lblVisitor2
        dashboardViewModel.visitor.observe(viewLifecycleOwner) {
            lblVisitor.text = it
        }
        val lblFloor:TextView = binding.lblFloor2
        dashboardViewModel.floor.observe(viewLifecycleOwner) {
            if (it < 0)
                lblFloor.text = ""
            else
                lblFloor.text = it.toString()
        }
        
        return root
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateFromTo() {
        val lblFrom2:TextView = binding.lblFrom2
        val lblTo2:TextView = binding.lblTo2

        if (currentTime == "" || currentDate == 0L) {
            lblFrom2.text = ""
            lblTo2.text = ""
            return
        }
        val calendar = Calendar.getInstance()
        val format1 = SimpleDateFormat("dd-MM-yyyy")

        calendar.timeInMillis = currentDate
        var newStr =  "${format1.format(calendar.time)} $currentTime"
        lblFrom2.text = newStr

        calendar.add(Calendar.DATE, 1)
        newStr = "${format1.format(calendar.time)} $currentTime"
        lblTo2.text = newStr
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}