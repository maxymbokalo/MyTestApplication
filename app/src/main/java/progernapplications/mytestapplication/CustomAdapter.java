package progernapplications.mytestapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Олег-PC on 17.09.2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.mViewHolder> {

    private Context context;
    private ArrayList<Album> albumList;


    public CustomAdapter(Context context, ArrayList<Album> albumList)
    {
        this.context = context;
        this.albumList = albumList;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext())
                .inflate(R.layout.rcview_row, parent, false);

        mViewHolder myViewHolder = new mViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position)
    {
        Album currentAlbum = albumList.get(position);
            holder.albumName.setText(currentAlbum.mName);
            holder.singerName.setText(currentAlbum.mSinger);
            Picasso.with(context)
                    .load(currentAlbum.mImageURL)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.errorplaceholder)
                    .into(holder.albumCover);

    }

    @Override
    public int getItemCount() {
        if (albumList != null) { // To prevent crashes
            return albumList.size();
        }
        else return 0;
    }


    //ViewHolder class
    public static class mViewHolder extends RecyclerView.ViewHolder{

        public TextView singerName, albumName;
        public ImageView albumCover;

        public mViewHolder(View itemView) {
            super(itemView);
            singerName = (TextView) itemView.findViewById(R.id.singerName);
            albumName = (TextView) itemView.findViewById(R.id.albumName);
            albumCover = (ImageView) itemView.findViewById(R.id.albumCover);
        }
    }
}
