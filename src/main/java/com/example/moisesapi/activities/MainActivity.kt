package com.example.moisesapi.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.moisesapi.R
import com.example.moisesapi.api.DataApi
import com.example.moisesapi.fragments.FragmentCallback
import com.example.moisesapi.fragments.RecycleViewFragment
import com.example.moisesapi.model.SendData
import com.example.moisesapi.recycleview.RecycleViewAdapaterData
import com.example.moisesapi.recycleview.RecycleViewCallback
import com.example.moisesapi.recycleview.ViewHolderData
import com.example.moisesapi.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep


class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var fragment:RecycleViewFragment
     var armazenaMood: String = "happy"


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.main_activity_MultlineText)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as RecycleViewFragment
        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*if(p0 !== null) {
                    if(p0[p0.lastIndex] == ' ') {
                        Toast.makeText(baseContext, "fon", Toast.LENGTH_LONG).show()
                    }
                }*/
            }

            override fun afterTextChanged(s: Editable?) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            fragment.getData(editText.text.toString(), armazenaMood)
                        },3000)
            }


        })



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lateinit  var mooditem: String
        when (item.itemId) {
            R.id.toolbar_humor_happy -> {
                mooditem = "happy"
                fragment.getData(editText.text.toString(),  mooditem)
            }


            R.id.toolbar_humor_sad -> {
                mooditem = "sad"
                pegaHumor(mooditem)
                fragment.getData(editText.text.toString(),mooditem )
            }


            R.id.toolbar_humor_angry -> {
                mooditem = "angry"
                pegaHumor(mooditem)
                fragment.getData(editText.text.toString(), mooditem)
            }


            R.id.toolbar_humor_in_love -> {
                mooditem = "in_love"
                pegaHumor(mooditem)
                fragment.getData(editText.text.toString(), mooditem)
            }

            R.id.toolbar_limpar -> {
                editText.text.clear()
            }

        }




        return super.onOptionsItemSelected(item)
    }

    fun pegaHumor (mood: String): String {
        var humorMood = mood
        armazenaMood = humorMood
        return humorMood
    }
}