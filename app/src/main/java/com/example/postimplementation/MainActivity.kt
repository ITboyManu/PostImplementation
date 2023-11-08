package com.example.postimplementation

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        findViewById<Button>(R.id.getPost).setOnClickListener {
            createUser()
        }
        findViewById<Button>(R.id.getdelete).setOnClickListener {
            deleteUser()
        }
        findViewById<Button>(R.id.getupdate).setOnClickListener {
            updateUser()
        }
        findViewById<Button>(R.id.btnget).setOnClickListener {
            getUserByID()
        }
    }

    private fun createUser() {
        lifecycleScope.launch {
            val body=JSONObject().apply {
                put("name","Authenticode")
                put("job"," Android developer ")
            }
            val result=apiService.CreateUser(body)
            if (result.isSuccessful)
            {
                Log.d("show","Create user successfull${result.body()}")
            }
            else
            {
                Log.d("show","Create user failed${result.message()}")
            }
        }
    }

    private fun deleteUser() {
        lifecycleScope.launch {
            showLoading("Deleted, Please wait")
            val result=apiService.deleteUser("2")
            if (result.isSuccessful){
                Log.d("show","Delete user successfull ${result.body()}")
            }else {

            Log.d("show","Delete failed${result.message()}")
            }
            progressDialog.dismiss()
        }
    }

    private fun updateUser() {
        lifecycleScope.launch {
            showLoading("Getting Updated, please wait ")
            val body = JSONObject().apply {
                put("name", "Itboy manish")
                put("job", "Android developer")
            }
            val result = apiService.updataUser("2", body)
            if (result.isSuccessful)
            {
                Log.d("show","UpdateUser success ${result.body()}")
            }
            else
            {
                Log.d("show","Delete user failed ${result.message()}")
            }
            progressDialog.dismiss()
        }
    }

    private fun getUserByID() {
        lifecycleScope.launch {
            showLoading("Getting, please wait ")
            val result = apiService.getUserByid("2")
            if (result.isSuccessful) {
                Log.d("show", "getUserByID success :${result.body()?.data}")
            } else {
                Log.d("show", "getUserByID success :${result.message()}")
            }
            progressDialog.dismiss()
        }

    }

    private fun showLoading(msg: String) {
        progressDialog = ProgressDialog.show(this, null, msg, true)
    }
}