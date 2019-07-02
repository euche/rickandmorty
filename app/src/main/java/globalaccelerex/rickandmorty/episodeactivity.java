package globalaccelerex.rickandmorty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import globalaccelerex.rickandmorty.adapters.ecAdapter;
import globalaccelerex.rickandmorty.models.singleEpisode;
import globalaccelerex.rickandmorty.services.apiclient;
import globalaccelerex.rickandmorty.services.rickandmortyAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class episodeactivity extends AppCompatActivity {

    private int eID;
    private TextView eName;
    private TextView ereleasedate,episodeno;
    private RecyclerView recyclerView;
    private ListView listview;



    private List<String> holder  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);

        eName = findViewById(R.id.episodeName1);
        ereleasedate = findViewById(R.id.releaseDate1);
        episodeno = findViewById(R.id.episodeNumber1);



        recyclerView = findViewById(R.id.character_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);




        getcardviewId();

        networkRequesst(eID);


    }



    private void getcardviewId(){

        if(getIntent().hasExtra("episodeID")){


            int uniquepisodeID = getIntent().getIntExtra("episodeID",0);



            eID = uniquepisodeID;


            Log.d("episode","clicker ID "+uniquepisodeID);

        }



    }



    private void networkRequesst(int id){


        final rickandmortyAPI episodeactivity = apiclient.getEpisodeLink(this).create(rickandmortyAPI.class);

        Call<singleEpisode> ecall =  episodeactivity.getSingleepisode(id);


        ecall.enqueue(new Callback<singleEpisode>() {
            @Override
            public void onResponse(Call<singleEpisode> call, Response<singleEpisode> response) {


                String test = response.body().getName();

                String test1 = response.body().getAirDate();

                String testairno = response.body().getEpisode();


                List<String> eArray = response.body().getCharacters();



                holder = eArray;


                Log.d("EpisodeResponse","Sure "+test +" "+test1+" "+ eArray.size());

                eName.setText(test);

                ereleasedate.setText(test1);

                episodeno.setText(testairno);


                recyclerView.setAdapter(new ecAdapter(episodeactivity.this,eArray));








            }

            @Override
            public void onFailure(Call<singleEpisode> call, Throwable t) {


                Log.e("Connectivity troubles", "Error");


            }
        });







    }






}
