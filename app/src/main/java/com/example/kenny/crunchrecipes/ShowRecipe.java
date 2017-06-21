package com.example.kenny.crunchrecipes;

        import android.content.Intent;
        import android.os.Build;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.List;


public class ShowRecipe extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    private DbCrunchRDataSource dataSource;

    public static final String LOG_TAG = AddRecipe.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_recipes);

        // Toolbar einrichten
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.ToolRec);
        setSupportActionBar(toolbar);

        //Wenn höher als API 21 dann ausgeführt
        if(Build.VERSION.SDK_INT >= 21){
        toolbar.setElevation(25);}
        // Toolbar Komonenten
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DbCrunchRDataSource(this);
    }

    private void showAllListEntries () {
        List<DbCrunchR> DbMemoList = dataSource.getAllDbMemos();

        ArrayAdapter<DbCrunchR> DbMemoArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                DbMemoList);

        ListView DbMemosListView = (ListView) findViewById(R.id.listview_db_memos);
        DbMemosListView.setAdapter(DbMemoArrayAdapter);
        Log.d(LOG_TAG, "Test");
    }

    // Menu Einrichten
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menü mit seinen Einstellungen aus menu_recipes.xml einrichten
        getMenuInflater().inflate(R.menu.menu_recipes, menu);
        return true;
    }

    // Menu selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add) {
            launch_addrecipe();
            return true;
        }

        if (id == android.R.id.home){
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Aktivität Rezept hinzufügen starten
    public void launch_addrecipe(){
        Intent intent = new Intent(this, AddRecipe.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        try{
            showAllListEntries();
            dataSource.close();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }
}
