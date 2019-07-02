package globalaccelerex.rickandmorty.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import globalaccelerex.rickandmorty.R;
import globalaccelerex.rickandmorty.models.cResult;

public class characterAdapter extends RecyclerView.Adapter<characterAdapter.cviewHolder> {



    private Context context;
    private List<cResult> c;

    public characterAdapter(Context c, List<cResult> episodes) {
        this.context = c;
        this.c = episodes;
    }

    public static class cviewHolder extends RecyclerView.ViewHolder{


        private ImageView charImage;
        private TextView charID;
        private TextView charName;


        cviewHolder(View v){

            super(v);

            charImage = v.findViewById(R.id.char_image);
            charID = v.findViewById(R.id.char_id);
            charName = v.findViewById(R.id.char_name);


        }




    }











    @Override
    public characterAdapter.cviewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View Character = LayoutInflater.from(parent.getContext()).inflate(R.layout.char_card,parent,false);

        return new characterAdapter.cviewHolder(Character) ;

    }

    @Override
    public void onBindViewHolder( characterAdapter.cviewHolder holder, int position) {

        cResult m = c.get(position);


        Picasso.with(context).load(m.getImage()).into(holder.charImage);



        holder.charID.setText(String.valueOf(m.getId()));

        holder.charName.setText(m.getName());


    }

    @Override
    public int getItemCount() {
        return c.size();
    }
}
