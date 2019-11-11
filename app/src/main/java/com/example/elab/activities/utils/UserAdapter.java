package com.example.elab.activities.utils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elab.R;
import com.example.elab.database.User;
import com.example.elab.main.activities.SearchActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserViewHolder pHolder = (UserViewHolder)holder;
        if( users != null ){
            User u = users.get(position);
            Picasso.get()
                    .load(u.profile_image)
                    .into(pHolder.productImage);
            pHolder.productName.setText(u.user_tag);
            pHolder.productRating.setRating(u.ranking);

        }else{
            pHolder.productName.setText("Producto no encontrado");
        }
    }

    @Override
    public int getItemCount(){
        if( users != null){
            return users.size();
        }
        return 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView productName;
        private ImageView productImage;
        private  RatingBar productRating;

        public UserViewHolder(View itemView){
            super(itemView);
            // change this!
            productName = itemView.findViewById(R.id.recyclerCompanyName);
            productImage = itemView.findViewById(R.id.recyclerCompanyThumbnail);
            productRating = itemView.findViewById(R.id.recyclerCompanyRating);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.d("CLICK!!" , "CLICKED ON ME: " + productName.getText());
                    callTo.goToNextScreen(getAdapterPosition());
                }
            });
        }
    }

    private final LayoutInflater inflater;
    private List<User> users;

    SearchActivity callTo;
    public UserAdapter(SearchActivity context){
        inflater = LayoutInflater.from(context);
        callTo = context;
    }

    public void setProducts(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }
}
