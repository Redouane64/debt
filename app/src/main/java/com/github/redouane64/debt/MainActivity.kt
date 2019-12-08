package com.github.redouane64.debt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.github.redouane64.debt.internals.FragmentHelper
import com.github.redouane64.debt.models.Debt
import com.github.redouane64.debt.database.DebtsDatabase
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

    private lateinit var database: DebtsDatabase;

    private val CREATE_DEBT_RESULT = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = DebtsDatabase.getInstance(this);

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
        val mainFragmentInstance = MainFragment();
        this.fragmentHelper.show(mainFragmentInstance, R.id.fragment_host).apply { toolbar.setTitle(R.string.menu_item_main) };
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);

        when(resultCode) {
            Activity.RESULT_OK -> {
                if(requestCode == CREATE_DEBT_RESULT) {
                    val debt = data!!.extras!!.getSerializable("debt") as Debt;
                    this.database.Debts().insert(debt);
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()

        val mainFragmentInstance = MainFragment();
        this.fragmentHelper.show(mainFragmentInstance, R.id.fragment_host).apply { toolbar.setTitle(R.string.menu_item_main) };
    }

    fun createNewDebt(view: View) {
        val intent = Intent(this, CreateDebtActivity::class.java);
        startActivityForResult(intent, CREATE_DEBT_RESULT);
    }

}
