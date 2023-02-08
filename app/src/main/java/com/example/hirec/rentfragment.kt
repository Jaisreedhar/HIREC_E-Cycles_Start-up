package com.example.hirec

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hirec.databinding.FragmentRentfragmentBinding

class rentfragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater,container:ViewGroup?,
        savedInstanceState: Bundle?

    ):
            View? {


        val bind= FragmentRentfragmentBinding.inflate(layoutInflater)

        bind.scanqr.setOnClickListener {
            val inten = Intent(this@rentfragment.requireContext(),rentactivity::class.java)
            startActivity(inten)
        }



        return bind.root
    }




}