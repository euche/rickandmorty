package globalaccelerex.rickandmorty.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import globalaccelerex.rickandmorty.R;
import globalaccelerex.rickandmorty.episodeactivity;
import globalaccelerex.rickandmorty.models.Result;

public class episodeAdapter extends RecyclerView.Adapter<episodeAdapter.episodeviewHolder> {


    private Context c;
    private List<Result> episodes;





    public episodeAdapter(Context c, List<Result> episodes) {
        this.c = c;
        this.episodes = episodes;
    }

    public static class episodeviewHolder extends RecyclerView.ViewHolder{

       private TextView episodeName;
       private TextView episodeNumber;
        private CardView cardview;

       episodeviewHolder(View v){

           super(v);

           episodeName = v.findViewById(R.id.episodeName);
           episodeNumber = v.findViewById(R.id.episodeNumber);
           cardview = v.findViewById(R.id.epi_card);



       }



    }





    @Override
    public episodeAdapter.episodeviewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View episode = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_card,parent,false);


        return new episodeAdapter.episodeviewHolder(episode);
    }



    @Override
    public void onBindViewHolder( episodeAdapter.episodeviewHolder holder, int position) {

      final Result m = episodes.get(position);

      holder.episodeName.setText(m.getName());
      holder.episodeNumber.setText(m.getEpisode());

      holder.cardview.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


              Log.d("Cardview","clicker ID "+m.getId().toString()+" "+m.getAirDate());


              Intent intent = new Intent(c,episodeactivity.class);
              intent.putExtra("episodeID",m.getId());
              c.startActivity(intent);




          }
      });


    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }
}
