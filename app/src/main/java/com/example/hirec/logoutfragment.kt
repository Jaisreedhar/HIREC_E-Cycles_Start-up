package com.example.hirec

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hirec.databinding.FragmentLogoutfragmentBinding



class logoutfragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater,container:ViewGroup?,
        savedInstanceState: Bundle?

    ):
            View? {


        val bind= FragmentLogoutfragmentBinding.inflate(layoutInflater)

        bind.logout.setOnClickListener {
            val inten = Intent(this@logoutfragment.requireContext(),logoutaactivity::class.java)
            startActivity(inten)
        }



        return bind.root
    }




}