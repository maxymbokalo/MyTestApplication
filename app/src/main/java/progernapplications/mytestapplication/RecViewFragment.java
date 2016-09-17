package progernapplications.mytestapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RecViewFragment extends Fragment {

    CustomAdapter mCustomAdapter;
    ArrayList<Album> albumList;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rec_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        albumList = new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.customRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        // First album item
        Album rihannaAlbum = new Album("Anti", "Rihanna", "http://pre-party.com.ua/uploads/2016/April_Tanya/Rihanna_420/003-Rihanna-420.jpg");
        Album drakeAlbum = new Album("Views", "Drake", "http://netstorage.metrolyrics.com/editorials/9232c/600x600.jpg");
        Album acDcAlbum = new Album("Highway To Hell", "AC/DC", "https://upload.wikimedia.org/wikipedia/en/a/ac/Acdc_Highway_to_Hell.JPG");
        Album interpolAlbum = new Album("Our Love to Admire", "Interpol", "https://upload.wikimedia.org/wikipedia/ru/7/77/Interpol_-_Our_Love_To_Admire.jpg");
        Album bringMeTheHorizonAlbum = new Album("That’s the Spirit", "Bring Me The Horizon", "https://upload.wikimedia.org/wikipedia/en/a/a2/Bring_Me_The_Horizon_-_That's_The_Spirit.jpg");
        Album bruttoAlbum = new Album("Родны край", "BRUTTO", "http://delaemvmeste.by/wp-content/uploads/2015/09/Cover.jpg");
        albumList.add(rihannaAlbum);
        albumList.add(drakeAlbum);
        albumList.add(acDcAlbum);
        albumList.add(interpolAlbum);
        albumList.add(bringMeTheHorizonAlbum);
        albumList.add(bruttoAlbum);
        mCustomAdapter = new CustomAdapter(getActivity().getApplicationContext(),albumList);

        mRecyclerView.setAdapter(mCustomAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);




    }
}
