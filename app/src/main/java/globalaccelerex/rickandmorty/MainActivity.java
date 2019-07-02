package globalaccelerex.rickandmorty;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = findViewById(R.id.bottom_sheet);
        bt .setOnNavigationItemSelectedListener(nl);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new episodeFragment()).commit();




    }


      BottomNavigationView.OnNavigationItemSelectedListener nl = new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {



              Fragment selectFragment = null;

              switch(item.getItemId()){

                  case R.id.episode:
                      selectFragment = new episodeFragment();
                      break;

                  case R.id.character:
                      selectFragment = new characterFragment();
                      break;

              }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectFragment).commit();

              return true;
          }
      };




}
