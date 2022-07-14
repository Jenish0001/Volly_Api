package com.example.volly_api

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    var uri = "https://jsonplaceholder.typicode.com/posts"
    var Gson_URL = "https://jsonplaceholder.typicode.com/users"
    var Api_Post = "https://reqres.in/api/users"

    var requestQueue: RequestQueue? = null

    var list = arrayListOf<ModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestQueue = Volley.newRequestQueue(this)

        var button = findViewById<Button>(R.id.button)
        var text1 = findViewById<TextView>(R.id.text1)
        var text2 = findViewById<TextView>(R.id.text2)
        button.setOnClickListener {

//            apicallinguser()
            text1.text="jenish"
            text2.text="devoper"

            post_Api()

        }

    }

    fun apicalling() {

        var jsonArrayRequest = JsonArrayRequest(Request.Method.GET, uri, null,
            { response ->
//                Log.e("TAG", "apicalling: ${response}")

                var i = 0
                while (i < response.length()) {

                    var userId = response.getJSONObject(i).getString("userId")
                    var title = response.getJSONObject(i).getString("title")
                    var id = response.getJSONObject(i).getString("id")
                    var body = response.getJSONObject(i).getString("body")


                    var model = ModelClass(userId, title, id, body)

                    list.add(model)

                    Log.e("TAG", "apicalling:============= $userId,$title,$id,$body")

                    i++
                }

            },

            { error ->

                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()

            })

        requestQueue?.add(jsonArrayRequest)

    }

    fun apicallinguser() {
        requestQueue = Volley.newRequestQueue(this)
        var jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, Gson_URL, null,
                { response ->
                    Log.e("TAG", "apicallinguser: $response")
                    var type = object : TypeToken<List<ModelItem>>() {}.type
                    var data = Gson().fromJson<List<ModelItem>>(response.toString(), type)
                    Log.e("TAG", "apicallinguser: $data")

                },
                { error ->
                    Toast.makeText(this, "${error.message}", Toast.LENGTH_SHORT).show()
                })
        requestQueue?.add(jsonArrayRequest)

    }

    fun post_Api() {
        requestQueue = Volley.newRequestQueue(this)
        var stringRequest = object : StringRequest(Request.Method.POST, Api_Post,
            { response ->
                Toast.makeText(this, "succesfully creat job", Toast.LENGTH_SHORT).show()
                Log.e("TAG", "post_Api: $response")
            },
            { error ->
                Log.e("TAG", "post_Api: ${error.message}")
            })
        {


            override fun getParams(): MutableMap<String, String>? {

                var map = HashMap<String, String>()
                val name =""
                map["name"] = "$name"
                val job =  ""
                map["job"] = "$job"
                return map

            }

        }

        requestQueue?.add(stringRequest)

    }


}

