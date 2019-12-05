package com.github.redouane64.debt

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.github.redouane64.debt.internals.FragmentHelper
import com.github.redouane64.debt.views.debts.DebtsFragment
import com.github.redouane64.debt.views.main.MainFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar;
    private lateinit var drawerLayout: DrawerLayout;
    private lateinit var fabButton: View;
    private lateinit var fragmentHelper: FragmentHelper;
    private lateinit var fab: FloatingActionButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toolbar = findViewById(R.id.toolbar);
        this.drawerLayout = findViewById(R.id.drawer_layout);
        this.fabButton = findViewById(R.id.fab);
        this.fragmentHelper = FragmentHelper(supportFragmentManager);
        this.fab = findViewById(R.id.fab);

        setSupportActionBar(this.toolbar);

        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT);
        }

        nav_view.setNavigationItemSelectedListener(this);

        // attach create debt event listener.
        fab.setOnClickListener(::createNewDebt)

        // start with default main fragment.
        this.fragmentHelper.show(MainFragment(), R.id.fragment_host).apply { toolbar.setTitle(R.string.menu_item_main) };
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // there is a better way to handle navigation but for simplicity
        // i have done it this way to have more control.
        when(item.itemId) {
            R.id.main_menu_item -> this.fragmentHelper.show(MainFragment(), R.id.fragment_host).also {
                fabButton.visibility = View.VISIBLE;
            };
            R.id.my_debts_menu_item -> this.fragmentHelper.show(DebtsFragment(), R.id.fragment_host).also {
                fabButton.visibility = View.INVISIBLE;
            };
            R.id.owes_me_menu_item -> 0;
        }

        toolbar.title = item.title;
        drawerLayout.closeDrawer(Gravity.LEFT);
        return true;
    }

    fun createNewDebt(view: View) {
        val intent = Intent(this, CreateDebtActivity::class.java);
        startActivity(intent);
    }

}
