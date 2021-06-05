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
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediatest.data.model.Data
import okhttp3.internal.immutableListOf

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

    lateinit var adapter : RecyclerView.Adapter<GuestAdapter.ViewHolder>

    var showItem: MutableList<Data> = mutableListOf()

    var isLoading = true

    var totalpage = 0

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

        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        var page = 1

        adapter = GuestAdapter(showItem, activity!!, this)
        binding.rvGuest.adapter = adapter

        getData(page)

        binding.swipeRefreshLayout.setOnRefreshListener {
            myUpdateOperation()
            page = 1
        }

        val layoutManager = GridLayoutManager(context,2)
        binding.rvGuest.layoutManager = layoutManager

        binding.rvGuest.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItemCount = recyclerView.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

//                Log.d(
//                    "error",
//                    "visibleitemcount = $visibleItemCount, pastvisible = $pastVisibleItem, total = $total"
//                )

                if(!isLoading) {
                    if ((visibleItemCount+pastVisibleItem) >= total && page < totalpage){
                        isLoading = true
                        page++
                        getData(page)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    fun myUpdateOperation() {
        showItem = mutableListOf()
        getData(1)
        binding.swipeRefreshLayout.setRefreshing(false)
    }


    fun getData( page: Int) {

        Log.d("error", page.toString())
        isLoading = true
        binding.progressbar.visibility = View.VISIBLE
        viewModel.getGuest(page)!!.observe(viewLifecycleOwner, Observer {
            val initialSize = showItem.size
            totalpage = it.total_pages
            for (i in it.data) {
                showItem.add(i)
            }
            val updateSize = showItem.size

            //adapter.notifyItemRangeInserted(initialSize,updateSize)
            adapter.notifyDataSetChanged()
            isLoading = false
            binding.progressbar.visibility = View.INVISIBLE

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