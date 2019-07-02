package globalaccelerex.rickandmorty.services;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiclient {


    private static Retrofit episodeLink = null;

    private static String network_URL = "https://rickandmortyapi.com/api/";






    public static Retrofit getEpisodeLink(Context c){

        if(episodeLink == null){

            episodeLink =  new Retrofit.Builder().baseUrl(network_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }

        return episodeLink;

    }






}
