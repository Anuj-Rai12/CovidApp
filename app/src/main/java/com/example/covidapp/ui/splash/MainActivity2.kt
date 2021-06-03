package com.example.covidapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.covidapp.R
import com.example.covidapp.ui.MainActivity

class MainActivity2 : AppCompatActivity() {
    private var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        savedInstanceState?.let {
            onRestoreInstanceState(it)
        }
        if (!flag) {
            flag = true
        } else {
            Log.i("TAG", "Falied Flag== $flag")
        }
        changeScr()
    }

    private fun changeScr() {
        if (flag)
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("FLAG", flag)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        flag = savedInstanceState.getBoolean("FLAG")
        super.onRestoreInstanceState(savedInstanceState)
    }
}