package globalaccelerex.rickandmorty.services;

import java.util.List;

import globalaccelerex.rickandmorty.models.characterResponse;
import globalaccelerex.rickandmorty.models.response;
import globalaccelerex.rickandmorty.models.singleEpisode;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface rickandmortyAPI {


    @GET
    Call<response> getEpisodes(@Url String url);


    @GET("episode/{id}")
    Call<singleEpisode>getSingleepisode(@Path("id")int id);


    @GET
    Call<characterResponse> getCharacters(@Url String url);

}
