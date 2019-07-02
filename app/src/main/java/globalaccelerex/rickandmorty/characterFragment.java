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

import globalaccelerex.rickandmorty.adapters.characterAdapter;
import globalaccelerex.rickandmorty.adapters.episodeAdapter;
import globalaccelerex.rickandmorty.models.cResult;
import globalaccelerex.rickandmorty.models.characterResponse;
import globalaccelerex.rickandmorty.models.response;
import globalaccelerex.rickandmorty.services.rickandmortyAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class characterFragment extends Fragment {


    private static String unique_URL = "https://rickandmortyapi.com/api/";
    private RecyclerView recyclerView;



    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {




         View fragmentView = inflater.inflate(R.layout.fragment_character,container,false);

         recyclerView = fragmentView.findViewById(R.id.char_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);



        return fragmentView;
    }




    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);





        NetworkRequest();
    }










    private void NetworkRequest() {

        Retrofit.Builder  characterBuild = new Retrofit.Builder().baseUrl(unique_URL).addConverterFactory(GsonConverterFactory.create());

        Retrofit r =  characterBuild.build();


        rickandmortyAPI network = r.create(rickandmortyAPI.class);

        String link =  "https://rickandmortyapi.com/api/character/";




        Call<characterResponse> responseCall = network.getCharacters(link);


        responseCall.enqueue(new Callback<characterResponse>() {
            @Override
            public void onResponse(Call<characterResponse> call, Response<characterResponse> response) {

                List<cResult> characters = response.body().getResults();

                Log.d("charactersAnswer","Episodes size =>"+characters.size());

                recyclerView.setAdapter(new characterAdapter(getContext(),characters));


            }

            @Override
            public void onFailure(Call<characterResponse> call, Throwable t) {





            }
        });






    }


}
