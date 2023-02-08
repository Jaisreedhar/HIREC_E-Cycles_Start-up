package com.example.hirec

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hirec.databinding.FragmentContactfragmentBinding

class contactfragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater,container:ViewGroup?,
        savedInstanceState: Bundle?

    ):
            View? {


        val bind= FragmentContactfragmentBinding.inflate(layoutInflater)

        bind.sendmail.setOnClickListener {
            val inten = Intent(this@contactfragment.requireContext(),contactjava::class.java)
            startActivity(inten)
        }
        bind.insta.setOnClickListener {
            // Open the Instagram account page in the default browser
            val instagramUri = Uri.parse("https://instagram.com/hirec_rec?igshid=ZmZhODViOGI=")
            val intent = Intent(Intent.ACTION_VIEW, instagramUri)
            startActivity(intent)
        }



        return bind.root
    }




}