package main.moviesnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import main.models.Movie;

public class MainActivity extends AppCompatActivity implements MovieListener{
    boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout flPanel2 = (FrameLayout) findViewById(R.id.detail_container);
        if (null == flPanel2) {
            mTwoPane = false;
        } else {
            mTwoPane = true;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getFragmentManager().beginTransaction().replace(R.id.detail_container,new DetailsActivityFragment()).commit();
//
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setSelectedmovie(Movie movie) {
        if (mTwoPane) {
            DetailsActivityFragment detailsFragment= new DetailsActivityFragment();
            Bundle extras= new Bundle();
            extras.putString("id", movie.getId());
            detailsFragment.setArguments(extras);
        } else {
            //Case Single Pane UI
            Intent i = new Intent(this, DetailsActivity.class);
            i.putExtra("id", movie.getId());
            startActivity(i);
        }
    }
}
