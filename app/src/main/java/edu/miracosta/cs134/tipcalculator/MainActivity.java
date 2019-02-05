package edu.miracosta.cs134.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Member variables to format as currency or percent (NumberFormat)
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
    NumberFormat percent = NumberFormat.getPercentInstance(Locale.getDefault());

    // Member variables for each component used in the app
    private EditText amountEditText;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private SeekBar percentSeekBar;

    //member variable of the model
    Bill currentBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize all instance variables.
        amountEditText.findViewById(R.id.amountEditText);
        percentTextView.findViewById(R.id.percentTextView);
        tipTextView.findViewById(R.id.tipTextView);
        totalTextView.findViewById(R.id.totalTextView);
        percentSeekBar.findViewById(R.id.percentSeekBar);

        currentBill = new Bill();
        //set tip percent to match the seek bar
        currentBill.setTipPercent(percentSeekBar.getProgress()/100.0);

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                //update our model
                currentBill.setTipPercent(progress/100.0);
                //change label of the tip percent
                percentTextView.setText(percent.format(currentBill.getTipPercent()));
                tipTextView.setText(currency.format(currentBill.getTipAmount()));
                totalTextView.setText(currency.format(currentBill.getTotalAmount()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //does nothing

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //does nothing

            }
        });

        //Connect code to the event onTextChanged for editText
        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //does nothing

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                currentBill.setAmount(Double.parseDouble(amountEditText.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                //does nothing

            }
        });
    }
}
