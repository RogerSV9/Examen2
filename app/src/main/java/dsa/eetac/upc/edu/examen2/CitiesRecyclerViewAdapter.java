package dsa.eetac.upc.edu.examen2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CitiesRecyclerViewAdapter extends RecyclerView.Adapter<CitiesRecyclerViewAdapter.ViewHolder>{
    private List<Element> data;
    Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public TextView text2;
        private ImageView photoFollower;

        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.text_view_id);
            text2 = (TextView) v.findViewById(R.id.text_view_id2);
            photoFollower = v.findViewById(R.id.photoFollower);
        }
    }
    public CitiesRecyclerViewAdapter(List<Element> data) {
        this.data = data;
    }

    @Override
    public CitiesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        context = parent.getContext();
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        //LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //v = inflater.inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CitiesRecyclerViewAdapter.ViewHolder holder, int position) {
        Element element = ((Element) data.get(position));
        holder.text.setText(element.getIne());
        holder.text2.setText(element.getMunicipiNom());
        Picasso.with(context).load(element.getMunicipiEscut()).into(holder.photoFollower);
        //holder.itemView.setTag(Track.id);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
