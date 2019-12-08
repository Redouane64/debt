package com.github.redouane64.debt

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.github.redouane64.debt.presenters.create.CreateDebtPresenter
import com.github.redouane64.debt.views.create.CreateDebtView
import java.text.SimpleDateFormat
import java.util.*

class CreateDebtActivity : AppCompatActivity(), CreateDebtView, DatePickerDialog.OnDateSetListener {

    private lateinit var selectDateButton: ImageButton;
    private lateinit var saveButton: Button;
    private lateinit var returnDateTextView: TextView;
    private lateinit var iamDebtorSwitch: Switch;
    private lateinit var debtorNameTextView: TextView;
    private lateinit var currencySpinner: Spinner;
    private lateinit var amountEditText: EditText;

    private lateinit var presenter: CreateDebtPresenter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_debt)

        this.selectDateButton = findViewById(R.id.select_date_button);
        this.saveButton = findViewById(R.id.save_button);
        this.returnDateTextView = findViewById(R.id.return_date_text);
        this.iamDebtorSwitch = findViewById(R.id.debtor_switch);
        this.debtorNameTextView = findViewById(R.id.creditor_name);
        this.currencySpinner = findViewById(R.id.currency_selection);
        this.amountEditText = findViewById(R.id.amount);

        this.selectDateButton.setOnClickListener(::selectDate);
        this.saveButton.setOnClickListener(::save);

        this.setPresenter(CreateDebtPresenter(this));
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun selectDate(view: View) = selectDate();

    fun save(view: View) = save();

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        SimpleDateFormat("YYYY.M.d").format(Date()).let {
            returnDateTextView.text = it;
            this.presenter.setDate(it);
        };
    }

    override fun setPresenter(presenter: CreateDebtPresenter) {
        this.presenter = presenter;
    }

    override fun selectDate() {
        val now = Calendar.getInstance();

        val dialog = DatePickerDialog(
            this,
            ::onDateSet,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        );

        dialog.show();
    }

    override fun save() {
        this.presenter.apply {
            setDebtor(debtorNameTextView.text.toString());
            setAmount(amountEditText.text.toString().toFloat());
            setCurrency(currencySpinner.selectedItem.toString());
            setIamDebtor(when (iamDebtorSwitch.isChecked) { true -> 1 else -> 0 })
        }

        val returnIntent = Intent().apply {
            putExtra("debt", presenter.save());
        };

        setResult(Activity.RESULT_OK, returnIntent);

        this.finish();
    }

    override fun onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy();
    }
}
