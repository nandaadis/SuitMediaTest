package com.example.suitmediatest.ui.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.navigation.NavArgument
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.suitmediatest.R
import com.example.suitmediatest.data.dummy.dummy_event
import com.example.suitmediatest.databinding.FragmentScreen1Binding
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Screen1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Screen1Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentScreen1Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScreen1Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide
            .with(this)
            .load(R.drawable.bg_bright)
            .into(binding.view3)

        Glide
            .with(this)
            .load(R.drawable.img_avatar)
            .circleCrop()
            .into(binding.ivPhoto)

        binding.btnNext.setOnClickListener {
            var name = binding.etName.text.toString()
            if (name == ""){
                Toast.makeText(
                    activity,
                    "name cannot be empty ",
                    LENGTH_SHORT
                ).show()
            } else {
                var action = Screen1FragmentDirections.actionScreen1FragmentToScreen2Fragment(name)
                view.findNavController().navigate(action)
            }
        }

        binding.btnPalindrome.setOnClickListener {
            val check = binding.etPalindrome.text.toString()
            if (check == ""){
                Toast.makeText(
                    activity,
                    "not palindrome",
                    LENGTH_SHORT
                ).show()
            } else if (checkPalindrome(check)) {
                Toast.makeText(
                    activity,
                    "is palindrome",
                    LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    activity,
                    "not palindrome",
                    LENGTH_SHORT
                ).show()
            }
        }


    }

    fun checkPalindrome(s: String): Boolean {
        var cleanString = s.toLowerCase().replace("[^a-z0-9]".toRegex(),"")

        if (cleanString == cleanString.reversed()){
            return true
        }

        return false
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Screen1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Screen1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}