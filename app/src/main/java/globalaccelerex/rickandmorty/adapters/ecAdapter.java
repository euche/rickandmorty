package globalaccelerex.rickandmorty.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import globalaccelerex.rickandmorty.R;
import globalaccelerex.rickandmorty.models.Result;

public class ecAdapter extends RecyclerView.Adapter<ecAdapter.ecviewHolder>  {


    private Context context;

    private List<String> characters;



    public ecAdapter(Context context, List<String> characters) {
        this.context = context;
        this.characters = characters;
    }


    public static class ecviewHolder extends RecyclerView.ViewHolder{

        private TextView characterId;

        ecviewHolder(View v){

            super(v);

            characterId = v.findViewById(R.id.charResponse);



        }



    }



    @Override
    public ecAdapter.ecviewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.charcter_list,parent,false);

        return new ecAdapter.ecviewHolder(view);
    }



    @Override
    public void onBindViewHolder(ecAdapter.ecviewHolder holder, int position) {

     String ans =  characters.get(position);

     holder.characterId.setText(ans);




    }

    @Override
    public int getItemCount() {


        return characters.size();
    }




}
