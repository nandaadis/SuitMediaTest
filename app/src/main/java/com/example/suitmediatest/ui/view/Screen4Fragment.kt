package com.example.suitmediatest.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatest.R
import com.example.suitmediatest.data.dummy.dummy_event
import com.example.suitmediatest.databinding.FragmentScreen3Binding
import com.example.suitmediatest.databinding.FragmentScreen4Binding
import com.example.suitmediatest.ui.adapter.EventAdapter
import com.example.suitmediatest.ui.adapter.GuestAdapter
import com.example.suitmediatest.ui.viewmodel.Screen4ViewModel
import kotlinx.android.synthetic.main.fragment_screen4.*
import android.widget.Toast.LENGTH_SHORT

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Screen4Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Screen4Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentScreen4Binding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: Screen4ViewModel

    var current_page: Int = 1
    var total_page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel = ViewModelProviders.of(this).get(Screen4ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScreen4Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
   }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        current_page = 1
        total_page = 0

        getData(current_page)

        binding.btnPageNext.setOnClickListener {
            if(current_page < total_page) {
                current_page = current_page + 1
                getData(current_page)
            } else (
                    Toast.makeText(
                        activity,
                        "ini adalah halaman terakhir",
                        Toast.LENGTH_SHORT
                    ).show()
            )
        }

        binding.btnPagePrev.setOnClickListener {
            if(current_page > 1) {
                current_page = current_page - 1
                getData(current_page)
            } else (
                    Toast.makeText(
                        activity,
                        "ini adalah halaman pertama",
                                Toast.LENGTH_SHORT
                    ).show()
                    )
        }

    }

    fun getData( page: Int) {
        binding.progressbar.visibility = View.VISIBLE
        viewModel.getGuest(page)!!.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visibility = View.INVISIBLE

            binding.rvGuest.layoutManager = GridLayoutManager(context,2)

            val adapter = GuestAdapter(it.data, activity!!, this)

            binding.rvGuest.adapter = adapter

            binding.tvPage.setText(it.page.toString())

            current_page = it.page
            total_page = it.total_pages

        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Screen4Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Screen4Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}