package com.github.redouane64.debt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.debts_list.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }
}
