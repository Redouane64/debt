package com.github.redouane64.debt

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar;
    private lateinit var drawerLayout: DrawerLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toolbar = findViewById(R.id.toolbar);
        this.drawerLayout = findViewById(R.id.drawer_layout);

        setSupportActionBar(this.toolbar);

        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT);
        }

        nav_view.setNavigationItemSelectedListener(this);
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.main_menu_item -> 0 ;
            R.id.my_debts_menu_item -> 0 ;
            R.id.owes_me_menu_item -> 0;
        }

        drawerLayout.closeDrawer(Gravity.LEFT);
        return true;
    }

}
