package com.example.kenny.crunchrecipes;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;


public class HomeScreen extends AppCompatActivity {


    private Button mBtSearch;
    public static final String LOG_TAG = AddRecipe.class.getSimpleName();
    private DbCrunchRDataSource dataSource;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DbCrunchRDataSource(this);
        mBtSearch = (Button) findViewById(R.id.Search);
        mBtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launch_ShowRecipe();
            }
        });
    }

    private void launch_ShowRecipe() {

        Intent intent = new Intent(this, ShowRecipe.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.

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