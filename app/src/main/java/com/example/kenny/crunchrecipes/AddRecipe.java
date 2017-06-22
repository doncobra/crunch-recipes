package com.example.kenny.crunchrecipes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenny on 18.06.2017.
 */
public class AddRecipe extends AppCompatActivity {

    private DbCrunchRDataSource dataSource;
    private Button mBtSearch;
    public static final String LOG_TAG = AddRecipe.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {

        android.support.v7.widget.Toolbar toolbar;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");



        dataSource = new DbCrunchRDataSource(this);
        List<String> RecipeList = new ArrayList<String>();

        // Toolbar einrichten
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.ToolAddRec);
        setSupportActionBar(toolbar);

        //Wenn höher als API 21 dann ausgeführt
        if(Build.VERSION.SDK_INT >= 21){
            toolbar.setElevation(25);}

        // Toolbar Komonenten
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //button
        AddButton();
    }




    //AddButton
    private void AddButton() {
        Button buttonAddRecipe = (Button) findViewById(R.id.Add_Recipe);
        final EditText editTextHeal = (EditText) findViewById(R.id.editText_Heal);
        final EditText editTextRecipe = (EditText) findViewById(R.id.editText_RecName);
        final EditText editTextExtra = (EditText) findViewById(R.id.editText_Extra);

        buttonAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String recipe = editTextRecipe.getText().toString();
                String heartsInt = editTextHeal.getText().toString();
                String bonusInt = editTextExtra.getText().toString();

                if(TextUtils.isEmpty(heartsInt)) {
                    editTextHeal.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if(TextUtils.isEmpty(recipe)) {
                    editTextRecipe.setError(getString(R.string.editText_errorMessage));
                    return;
                }

                int heal = Integer.valueOf(heartsInt);
                int buff = Integer.valueOf(bonusInt);
                editTextHeal.setText("");
                editTextRecipe.setText("");

                try{
                    //Datensatz einfügen in die Datenbanktabelle Rezepte
                    dataSource.open();
                    dataSource.createDbRecipe(recipe, 0, null, null, null, null, null, heal, buff, 0, 0);
                    Log.d(LOG_TAG, "Succesfully inserted.");
                    dataSource.close();
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(),ex.toString(), Toast.LENGTH_LONG).show();
                }



                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                launch_ShowRecipe();
            }
        });

    }

    public void launch_ShowRecipe(){
        Intent intent = new Intent(this, ShowRecipe.class);
        startActivity(intent);
    }

    // Menu selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home){
                Intent intent = new Intent(this, ShowRecipe.class);
                startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }
}