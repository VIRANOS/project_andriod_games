package com.example.project_60160101

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager


class profile : Fragment() {
    var PhotoURL : String = ""
    var Name : String = ""

    fun newInstance(url: String,name : String): profile {

        val profile = profile()
        val bundle = Bundle()
        bundle.putString("PhotoURL", url)
        bundle.putString("Name", name)
        profile.setArguments(bundle)

        return profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            PhotoURL = bundle.getString("PhotoURL").toString()
            Name = bundle.getString("Name").toString()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val ivProfilePicture = view.findViewById(R.id.iv_profile) as ImageView
        val tvName = view.findViewById(R.id.tv_name) as TextView
        val login_button2 = view.findViewById(R.id.login_button2) as Button
        val dialog = view.findViewById(R.id.score) as Button


        Glide.with(activity!!.baseContext)
            .load(PhotoURL)
            .into(ivProfilePicture)

        tvName.setText(Name)

        dialog.setOnClickListener {
            val builder: AlertDialog.Builder =  AlertDialog.Builder(this.context)
            builder.setMessage("รับอะไรเพิ่มไหม?")

            builder.setNegativeButton("รับ",
                DialogInterface.OnClickListener{ dialog, id ->
                    Toast.makeText(this.context,"ขอบคุณครับ", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                })

            builder.setPositiveButton("ไม่รับ",
                DialogInterface.OnClickListener{ dialog, id ->
                    Toast.makeText(this.context,"ขอบคุณครับ", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                })
            builder.show();
        }
        login_button2.setOnClickListener{

            LoginManager.getInstance().logOut()
            activity!!.supportFragmentManager.popBackStack()
        }

        this.load_show_data()
        // Inflate the layout for this fragment
        return view
    }

    fun load_show_data(){
        val show_data = show_data()
        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()
        transaction.replace(R.id.Show_layout, show_data,"fragment_show_data")
        transaction.addToBackStack("fragment_show_data")
        transaction.commit()
    }


}
