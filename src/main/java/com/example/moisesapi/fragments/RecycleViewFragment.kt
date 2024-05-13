package com.example.moisesapi.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.moisesapi.R
import com.example.moisesapi.activities.MainActivity
import com.example.moisesapi.api.DataApi
import com.example.moisesapi.model.SendData
import com.example.moisesapi.recycleview.RecycleViewAdapaterData
import com.example.moisesapi.recycleview.RecycleViewCallback
import com.example.moisesapi.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class RecycleViewFragment : Fragment(), RecycleViewCallback{
    //lateinit var wordList: MutableList<String>
    var listBreakRow = mutableListOf<String>()
    var ultimoItemRecycle = 0
    var itemsLoad: Boolean = false
    lateinit var mainEditText: EditText
    lateinit var recyclerviewData: RecyclerView
    lateinit var rvButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycle_view, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mainActivity: MainActivity = requireActivity() as MainActivity
        mainEditText = mainActivity.findViewById(R.id.main_activity_MultlineText)
        val mainMessage = mainEditText.text.toString()
        rvButton = view.findViewById(R.id.recycle_view_button)
        rvButton.visibility = View.INVISIBLE
        rvButton.text = "VERSOS"
        rvButton.setOnClickListener {
            if(rvButton.text == "PALAVRAS"){
                rvButton.text = "VERSOS"
                getData(mainMessage, "")
            } else {
                rvButton.text = "PALAVRAS"
                getData(mainMessage, "")
            }

        }

        recyclerviewData = view.findViewById(R.id.fragment_Recycler_View)
        recyclerviewData.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutManager = recyclerviewData.layoutManager as LinearLayoutManager


        recyclerviewData.addOnScrollListener(object:  RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var ultimoItemVisivel = linearLayoutManager.findLastVisibleItemPosition()

                if (ultimoItemVisivel == ultimoItemRecycle ) {


            }

            }
        })



    }

    fun getData(message: String, mood: String) {
        rvButton.visibility = View.VISIBLE

        if (rvButton.text == "VERSOS") {
            getDataLine(message, mood)

        }else {
            getDataWord(message, mood)

        }
    }

    fun getDataWord(message: String, mood: String){
        val clientRetrofit = NetworkUtils.getRetrofitInstance("https://composer-api-production.moises.ai/")
        val dataSend = clientRetrofit.create(DataApi::class.java)
        val callback = dataSend.getDataWord(SendData(message, mood,"General", 10, true, "medium_diversity", "disabled", "disabled"))
        callback.enqueue(object: Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            var list2 = mutableListOf<String>()
                            for (s in it) {
                                list2.add(s)
                            }

                            val adapter = RecycleViewAdapaterData(list2, requireContext() , this@RecycleViewFragment)
                            recyclerviewData.adapter = adapter

                        }

                    }
                } else {
                    Log.e("Fragment", response.message())
                }
            }
        })

    }

    fun getDataLine(message: String, mood: String){
        val clientRetrofit = NetworkUtils.getRetrofitInstance("https://composer-api-production.moises.ai/")
        val dataSend = clientRetrofit.create(DataApi::class.java)
        val callback = dataSend.getDataLine(SendData(message, mood,"General", 10, true, "medium_diversity", "disabled", "disabled"))
        callback.enqueue(object: Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            var list2 = mutableListOf<String>()
                            for (s in it) {
                                listBreakRow.add(s)
                                list2.add(s)
                            }
                            if(itemsLoad == true) {
                                var listListenerScroll = mutableListOf<String>()
                                for (d in list2) {
                                    listListenerScroll.add(d)
                                }

                                var recyclerAdapaterData: RecycleViewAdapaterData? = null


                                recyclerAdapaterData?.pegaLista(listListenerScroll)
                                ultimoItemRecycle = recyclerviewData.size

                                itemsLoad = false
                                return
                            }


                            val adapter = RecycleViewAdapaterData(list2, requireContext() , this@RecycleViewFragment)
                            recyclerviewData.adapter = adapter


                        }

                    }
                } else {
                    Log.e("Fragment", response.message())
                }
            }
        })

    }



    override fun callback(data: String) {

        mainEditText.append(" $data")
    }





}