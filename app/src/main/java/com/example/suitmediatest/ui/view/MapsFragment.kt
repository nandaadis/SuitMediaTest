package com.example.suitmediatest.ui.view

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatest.R
import com.example.suitmediatest.data.dummy.dummy_event
import com.example.suitmediatest.databinding.FragmentMapsBinding
import com.example.suitmediatest.databinding.FragmentScreen3Binding
import com.example.suitmediatest.ui.adapter.EventAdapter
import com.example.suitmediatest.ui.adapter.MapAdapter

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         *
         *
         */

        var listEvent: MutableList<LatLng> = mutableListOf()

        for(i in dummy_event.dataEvent){
            listEvent.add(LatLng(i.lat, i.long))
            googleMap.addMarker(MarkerOptions().position(LatLng(i.lat, i.long)).title(i.name)).setIcon(
                BitmapDescriptorFactory.fromResource(
                R.drawable.ic_marker_unselected)
            )
        }


        googleMap.moveCamera(CameraUpdateFactory.newLatLng(listEvent[0]))


//        googleMap.setOnMarkerClickListener { marker ->
//            marker.setIcon(BitmapDescriptorFactory.fromResource(
//                R.drawable.ic_marker_selected))
//            true
//        }

        binding.rvEvent.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val adapter = MapAdapter(dummy_event.dataEvent, activity!!, this, googleMap)

        binding.rvEvent.adapter = adapter


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.btnList.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mapsFragment_to_screen3Fragment)
        }

//        binding.rvEvent.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//
//        val adapter = MapAdapter(dummy_event.dataEvent, activity!!, this)
//
//        binding.rvEvent.adapter = adapter

    }
}