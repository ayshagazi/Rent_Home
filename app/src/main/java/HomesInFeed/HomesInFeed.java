package HomesInFeed;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rent_home.R;

import Interface.HomesInFeedInterface;

public class HomesInFeed extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView HIFrooms, HIFapartmentname, HIFrent,HIFlocalAreaName;
    public ImageView HIFhomePic;
    public HomesInFeedInterface listener;


    public HomesInFeed(@NonNull View itemView) {
        super(itemView);
        HIFapartmentname=(TextView) itemView.findViewById(R.id.HIFapartmentName);
        HIFrooms=(TextView) itemView.findViewById(R.id.HIFrooms);
        HIFrent=(TextView) itemView.findViewById(R.id.HIFrent);
        HIFlocalAreaName=(TextView)itemView.findViewById(R.id.HIFlocalAreaName);
        HIFhomePic=(ImageView)itemView.findViewById(R.id.HIFhomePic);

    }
    public void setItemClickListener(HomesInFeedInterface listener)
    {
        this.listener=  listener;

    }

    @Override
    public void onClick(View view) {

        listener.onClick(view, getAdapterPosition(), false);
    }
}
