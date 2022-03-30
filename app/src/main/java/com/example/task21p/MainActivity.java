package com.example.task21p;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton lengthButton, massButton, tempButton;
    EditText input;
    TextView conv1_name, conv1_out;
    TextView conv2_name, conv2_out;
    TextView conv3_name, conv3_out;

    Spinner convTypesMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //buttons
        lengthButton = findViewById(R.id.lengthButton);
        tempButton = findViewById(R.id.tempButton);
        massButton = findViewById(R.id.massButton);

        //spinner and adapter for drop down menu
        convTypesMenu = findViewById(R.id.conv_drop_menu);
        ArrayAdapter<CharSequence> convTypesAdapter = ArrayAdapter.createFromResource(this, R.array.conv_types, android.R.layout.simple_spinner_item);
        convTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        convTypesMenu.setAdapter(convTypesAdapter);

        //text inputs and outputs
        input = findViewById(R.id.InputText);
        conv1_name = findViewById(R.id.conv1_name);
        conv1_out = findViewById(R.id.conv1_out);
        conv2_name = findViewById(R.id.conv2_name);
        conv2_out = findViewById(R.id.conv2_out);
        conv3_name = findViewById(R.id.conv3_name);
        conv3_out = findViewById(R.id.conv3_out);

        //length button on clicking, using an anonymous class that implements on ClickListener
        lengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer menuOption = convTypesMenu.getSelectedItemPosition();
                if (menuOption == 0) { LengthButton(); } //menuOption of 0 means we have length selected and can do conversion
                else { WrongButton(); } //anything else is incorrect, so send an error message instead
            }
        });

        //temperature button on clicking, using an anonymous class that implements on ClickListener
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer menuOption = convTypesMenu.getSelectedItemPosition();
                if (menuOption == 1) { TempButton(); } //menuOption of 1 means we have temperature selected and can do conversion
                else { WrongButton(); } //anything else is incorrect, so send an error message instead
            }
        });

        //mass button button on clicking, using an anonymous class that implements on ClickListener
        massButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer menuOption = convTypesMenu.getSelectedItemPosition();
                if (menuOption == 2) { MassButton(); } //menuOption of 2 means we have mass selected and can do conversion
                else { WrongButton(); } //anything else is incorrect, so send an error message instead
            }
        });
    }

    //=======================================BUTTON METHODS========================================================================================
    //operations for when the wrong button is hit: clear screen and give error message
    private void WrongButton() {
        ClearScreen();
        Toast.makeText(MainActivity.this, R.string.wrong_button_error, Toast.LENGTH_SHORT).show();
    }

    //operations when the length conversion button is hit
    private void LengthButton()
    {
        String inputString = input.getText().toString();
        double metre;

        //try to cast result as a double
        try { metre = Double.parseDouble(inputString); }

        //give error message and return if it fails
        catch (Exception e) {
            Toast.makeText(MainActivity.this, R.string.no_input_error, Toast.LENGTH_SHORT).show();
            return;
        }

        //apply conversation rates to input
        double cm = 100 * metre;
        double foot = 3.28084 * metre;
        double inch = 39.3701 * metre;

        //display
        conv1_name.setText(R.string.length_cm);
        conv1_out.setText(String.format("%.2f", cm));

        conv2_name.setText(R.string.length_foot);
        conv2_out.setText(String.format("%.2f", foot));

        conv3_name.setText(R.string.length_inch);
        conv3_out.setText(String.format("%.2f", inch));
    }

    //operations when the temperature conversion button is hit
    private void TempButton()
    {
        String inputString = input.getText().toString();
        double celsius;

        //try to cast result as a double
        try { celsius = Double.parseDouble(inputString); }

        //give error message and return if it fails
        catch (Exception e) {
            Toast.makeText(MainActivity.this, R.string.no_input_error, Toast.LENGTH_SHORT).show();
            return;
        }

        double fahren = (celsius * (9/5)) + 32;
        double kelvin = celsius + 273.15 ;

        conv1_name.setText(R.string.temp_fahren);
        conv1_out.setText(String.format("%.2f", fahren));

        conv2_name.setText(R.string.temp_kelvin);
        conv2_out.setText(String.format("%.2f", kelvin));

        //no third conversion, so display nothing
        conv3_name.setText(null);
        conv3_out.setText(null);
    }

    //operations when the mass conversion button is hit
    private void MassButton()
    {
        String inputString = input.getText().toString();
        double kilog;

        //try to cast result as a double
        try { kilog = Double.parseDouble(inputString); }

        //give error message and return if it fails
        catch (Exception e) {
            Toast.makeText(MainActivity.this, R.string.no_input_error, Toast.LENGTH_SHORT).show();
            return;
        }

        double gram = kilog / 1000;
        double ounce = kilog * 35.274;
        double pound = kilog * 2.205;

        conv1_name.setText(R.string.mass_gram);
        conv1_out.setText(String.format("%.2f", gram));

        conv2_name.setText(R.string.mass_ounce);
        conv2_out.setText(String.format("%.2f", ounce));

        conv3_name.setText(R.string.mass_pound);
        conv3_out.setText(String.format("%.2f", pound));
    }

    //empties all text boxes
    private void ClearScreen()
    {
        conv1_name.setText(null);
        conv1_out.setText(null);

        conv2_name.setText(null);
        conv2_out.setText(null);

        conv3_name.setText(null);
        conv3_out.setText(null);
    }

}