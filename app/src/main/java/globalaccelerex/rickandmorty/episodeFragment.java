package globalaccelerex.rickandmorty;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.zip.Inflater;

import globalaccelerex.rickandmorty.adapters.episodeAdapter;
import globalaccelerex.rickandmorty.models.Result;
import globalaccelerex.rickandmorty.models.response;
import globalaccelerex.rickandmorty.services.rickandmortyAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class episodeFragment extends Fragment {


    private static String unique_URL = "https://rickandmortyapi.com/api/";
    private RecyclerView recyclerView;



    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {



        View fragmentView =  inflater.inflate(R.layout.fragment_episodes,container,false);


        recyclerView = fragmentView.findViewById(R.id.episode_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

       return fragmentView;
    }





    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);





        NetworkRequest();
    }



    private void NetworkRequest(){


        Retrofit.Builder  episodeBuild = new Retrofit.Builder().baseUrl(unique_URL).addConverterFactory(GsonConverterFactory.create());

        Retrofit r =  episodeBuild.build();



        rickandmortyAPI network = r.create(rickandmortyAPI.class);

        String link =  "https://rickandmortyapi.com/api/episode/";


        Call<response> responseCall = network.getEpisodes(link);


        responseCall.enqueue(new Callback<response>() {
            @Override
            public void onResponse(Call<response> call, Response<response> response) {

                List<Result> episodes = response.body().getResults();
                Log.d("Answer","Episodes size =>"+episodes.size());


                recyclerView.setAdapter(new episodeAdapter(getContext(),episodes));



            }

            @Override
            public void onFailure(Call<response> call, Throwable t) {

                Log.e("Error","Connectivity Problems "+ t.getMessage());

            }
        });




    }






}
