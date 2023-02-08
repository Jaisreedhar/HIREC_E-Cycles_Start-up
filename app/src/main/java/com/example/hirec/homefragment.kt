package com.example.hirec

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class homefragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_homefragment, container, false)
        view.findViewById<Button>(R.id.rentcycle).setOnClickListener {
            val secondFragment = rentfragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.framelayout, secondFragment)
                //?.addToBackStack(null)
                ?.commit()
    }
        return view
    }


}