package com.example.hirec

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class buyfragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ):
            View? {


        val bind = com.example.hirec.databinding.FragmentBuyfragmentBinding.inflate(layoutInflater)

        bind.buy.setOnClickListener {
            val inten = Intent(this@buyfragment.requireContext(), contactjava::class.java)
            startActivity(inten)
        }





        return bind.root
    }



}