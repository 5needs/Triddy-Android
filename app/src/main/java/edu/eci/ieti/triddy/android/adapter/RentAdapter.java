package edu.eci.ieti.triddy.android.adapter;


import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.eci.ieti.triddy.android.R;
import edu.eci.ieti.triddy.android.model.Rent;

import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class RentAdapter extends RecyclerView.Adapter<RentAdapter.ViewHolder>  {
    List<Rent> rents;
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    OnRentListener onRentListener;

    public RentAdapter(OnRentListener onRentListener)
    {
        this.onRentListener = onRentListener;

    }

    @NonNull
    @Override
    public RentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RentAdapter.ViewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.my_rent_item, parent, false ), this.onRentListener );
    }

    @Override
    public void onBindViewHolder(@NonNull RentAdapter.ViewHolder holder, int position) {
        Rent rent = rents.get(position);
        Drawable d = loadImageFromWebOperations("https://images.freeimages.com/images/large-previews/c56/book-1424031.jpg");
        String dates = this.formatter.format(rent.getInitialDate()) +" - " + this.formatter.format(rent.getFinalDate());
        ((TextView) holder.itemView.findViewById(R.id.my_rent_product)).setText(rent.getProductId());
        ((TextView) holder.itemView.findViewById(R.id.my_rent_owner)).setText(rent.getUserOwner());
        ((TextView) holder.itemView.findViewById(R.id.my_rent_dates)).setText(dates);
        ((TextView) holder.itemView.findViewById(R.id.my_rent_status)).setText(rent.getStatus());
        ((ImageView) holder.itemView.findViewById(R.id.imageView_rent)).setImageDrawable(d);
        if (rent.getStatus().equals("finish")){
            ((Button) holder.itemView.findViewById(R.id.button_rent)).setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return rents != null ? rents.size() : 0;
    }

    public Rent getRent(int position){
        return this.rents.get(position);
    }

    public void updateRents(List<Rent> rents){
        this.rents = rents;
        notifyDataSetChanged();
    }

    public Drawable loadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private RentAdapter.OnRentListener onRentListener;
        ViewHolder( @NonNull View itemView, RentAdapter.OnRentListener onRentListener )
        {
            super( itemView );
            this.onRentListener = onRentListener;
            Button button = itemView.findViewById(R.id.button_rent);
            System.out.println(button);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRentListener.onRentClicked(getAdapterPosition());
        }
    }

    public interface OnRentListener{
        void onRentClicked(int position);
    }
}
