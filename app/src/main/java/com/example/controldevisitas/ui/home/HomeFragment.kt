package com.example.controldevisitas.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.controldevisitas.MainActivity
import com.example.controldevisitas.R
import com.example.controldevisitas.databinding.FragmentHomeBinding
import com.example.controldevisitas.ui.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val textUserName: TextView = binding.txtUserName
        homeViewModel.text.observe(viewLifecycleOwner) {
            textUserName.text = it
        }
        //homeViewModel.getUsername().observe(this, Observer { textUserName.text })

        val btnLogOut: Button = binding.btnLogOut
        btnLogOut.setOnClickListener {
            onBtnLogOutClick()
        }

        val btnRegister: Button = binding.btnRegister
        btnRegister.setOnClickListener {
            if (activity is MainActivity) {
                val mainAct = activity as MainActivity
                mainAct.binding.navView.selectedItemId = R.id.navigation_notifications
            }
        }

        val btnQuery: Button = binding.btnQuery
        btnQuery.setOnClickListener {
            if (activity is MainActivity) {
                val mainAct = activity as MainActivity
                mainAct.binding.navView.selectedItemId = R.id.navigation_dashboard
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBtnLogOutClick() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this.context, LoginActivity::class.java))
    }
}