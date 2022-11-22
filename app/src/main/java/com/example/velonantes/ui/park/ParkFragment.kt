package com.example.velonantes.ui.park

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.velonantes.adapter.ParkAdapter
import com.example.velonantes.adapter.StationAdapter
import com.example.velonantes.api.ParkApi
import com.example.velonantes.api.RetrofitHelper
import com.example.velonantes.databinding.FragmentParkBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ParkFragment : Fragment() {

    private var _binding: FragmentParkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ParkViewModel =
            ViewModelProvider(this).get(ParkViewModel::class.java)

        _binding = FragmentParkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerViewPark
        ParkViewModel.parks.observe(viewLifecycleOwner) {
            val adapter = ParkAdapter(it, requireContext())
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
        }
        val parkApi = RetrofitHelper().getInstance().create(ParkApi::class.java)
        GlobalScope.launch {
            val result = parkApi.getPark()
            Log.d("Park",result.body().toString())
            ParkViewModel.parks.postValue(result.body())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}