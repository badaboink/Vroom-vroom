package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

public class CreditCardActivity extends AppCompatActivity {

    CardForm cardForm;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        cardForm = findViewById(R.id.card_form);
        add = findViewById(R.id.btnAdd);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(false)
                .setup(CreditCardActivity.this);

        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cardForm.isValid())
                {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(CreditCardActivity.this);
                    alertBuilder.setTitle("Sėkmingai pridėta");
                    alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                }
                else
                {
                    Toast.makeText(CreditCardActivity.this,"Neteisingai užpildyta forma", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

    /*CreditCardActivity cardForm = (CreditCardActivity) findViewById(R.id.card_form);
cardForm.cardRequired(true)
        .expirationRequired(true)
        .cvvRequired(true)
        .cardholderName(CardForm.FIELD_REQUIRED)
        .postalCodeRequired(true)
        .mobileNumberRequired(true)
        .mobileNumberExplanation("SMS is required on this number")
        .actionLabel("Purchase")
        .setup(activity);
}*/