package progernapplications.mytestapplication;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Олег-PC on 18.09.2016.
 */
public class CustomGalleryAdapter extends RecyclerView.Adapter<CustomGalleryAdapter.galleryViewHolder> {

    private Context context;
    private ArrayList<Uri> imageURIs;

    public CustomGalleryAdapter(Context context, ArrayList<Uri> imageURIs)
    {
        this.context = context;
        this.imageURIs = imageURIs;
    }


    @Override
    public galleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_gallery, parent, false);

        galleryViewHolder mGalleryViewHolder = new galleryViewHolder(view);

        return mGalleryViewHolder;
    }

    @Override
    public void onBindViewHolder(galleryViewHolder holder, int position)
    {
        Picasso.with(context)
                .load(imageURIs.get(position))
                .fit()
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.errorplaceholder)
                .into(holder.imageFromGallery);

    }

    @Override
    public int getItemCount() {
        if( imageURIs != null)
        {
            return imageURIs.size();
        }

        else return 0;
    }

    public static class galleryViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView imageFromGallery;


        public galleryViewHolder(View itemView) {
            super(itemView);
            imageFromGallery = (ImageView) itemView.findViewById(R.id.galleryImage);
        }
    }
}
