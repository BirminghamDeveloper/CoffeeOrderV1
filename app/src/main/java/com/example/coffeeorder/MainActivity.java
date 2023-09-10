package com.example.coffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    Button btnPlus, btnMinus, btnOrder;
    CheckBox cbWhippedCream, cbChocolate;
    TextView tvDisplay, tvMessage;

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnOrder = findViewById(R.id.btnOrder);
        cbChocolate = findViewById(R.id.cbChocolate);
        cbWhippedCream = findViewById(R.id.cbWhippedCream);
        tvDisplay = findViewById(R.id.tvDisplay);
        tvMessage = findViewById(R.id.tvMessage);

    }

    public void decrease(View view) {
        if(quantity == 0){
            return;
        }
        quantity -= 1;
        result(quantity);
    }

    public void increase(View view) {
        if(quantity == 20){
            return;
        }
        quantity += 1;
        result(quantity);
    }

    public void calculator(View view) {
        String name = etName.getText().toString();

        boolean isWhiteCream = cbWhippedCream.isChecked();
        boolean isChocolate = cbChocolate.isChecked();

        int price = getPrice(isWhiteCream, isChocolate);

        String msg = messageDisplay(name, isWhiteCream, isChocolate, price);

        if(!msg.isEmpty()){
            tvMessage.setText(msg);
        }
    }

    private String messageDisplay(String name, boolean isWhiteCream,
                                  boolean isChocolate, int price) {
        String message = getString(R.string.name) + name;
        message += "\n" + getString(R.string.whibbedcream_order) + isWhiteCream;
        message += "\n" + getString(R.string.chocolate_Order) +isChocolate;
        message += "\n" + getString(R.string.price_Order)
                + NumberFormat.getCurrencyInstance().format(price);
        message += "\n" + getString(R.string.Thanks);

        return message;
    }

    private int getPrice(boolean isWhiteCream, boolean isChocolate) {
        int basicPrice = 5;
        if (isWhiteCream){
            basicPrice += 5;
        }
        if(isChocolate){
            basicPrice += 2;
        }

        return basicPrice * quantity;
    }

    private void result(int quantity){
        tvDisplay.setText(" " + quantity);
    }
}