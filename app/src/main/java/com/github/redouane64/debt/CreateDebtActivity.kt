package com.github.redouane64.debt

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import java.util.*

class CreateDebtActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var selectDateButton: ImageButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_debt)

        this.selectDateButton = findViewById(R.id.select_date_button);

        this.selectDateButton.setOnClickListener(::selectDate)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun selectDate(view: View) {
        val now = Calendar.getInstance();

        val dialog = DatePickerDialog(this,
            ::onDateSet,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH));

        dialog.show();
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = "$dayOfMonth.$month.$year";
        Log.d("Date Picker", date);
    }
}
