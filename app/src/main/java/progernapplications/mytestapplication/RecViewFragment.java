package progernapplications.mytestapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;


public class RecViewFragment extends Fragment implements View.OnClickListener{

    CustomAdapter mCustomAdapter;
    ArrayList<Album> albumList;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    FloatingActionButton mFloatingActionButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView =  inflater.inflate(R.layout.fragment_rec_view, container, false);

        albumList = new ArrayList<>();
        mRecyclerView = (RecyclerView) myView.findViewById(R.id.customRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mFloatingActionButton = (FloatingActionButton)myView.findViewById(R.id.floatActionButtonRecView);


        albumList.add(new Album("Anti", "Rihanna", "http://pre-party.com.ua/uploads/2016/April_Tanya/Rihanna_420/003-Rihanna-420.jpg"));
        albumList.add(new Album("Views", "Drake", "http://netstorage.metrolyrics.com/editorials/9232c/600x600.jpg"));
        albumList.add(new Album("Highway To Hell", "AC/DC", "https://upload.wikimedia.org/wikipedia/en/a/ac/Acdc_Highway_to_Hell.JPG"));
        albumList.add(new Album("Our Love to Admire", "Interpol", "https://upload.wikimedia.org/wikipedia/ru/7/77/Interpol_-_Our_Love_To_Admire.jpg"));
        albumList.add(new Album("That’s the Spirit", "BMTH", "http://newnoisemagazine.com/wp-content/uploads/2015/10/Bring-Me-The-Horizon-Thats-The-Spirit-cover-500x500.jpg"));
        albumList.add(new Album("Родны край", "BRUTTO", "http://delaemvmeste.by/wp-content/uploads/2015/09/Cover.jpg"));
        albumList.add(new Album("The Mindsweep", "Enter Shikari", "http://www.nme.com/images/2014EnterShikari_TheMindSweep_081014.jpg"));
        albumList.add(new Album("Night Visions", "Imagine Dragons", "https://upload.wikimedia.org/wikipedia/ru/3/3f/Night_Visions_Album_Cover.jpeg"));
        mCustomAdapter = new CustomAdapter(getActivity().getApplicationContext(),albumList);

        TapTargetView tapTargetView = TapTargetView.showFor(getActivity(),
         TapTarget.forView(myView.findViewById(R.id.floatActionButtonRecView), "Click this button", "To create a new list item"),
        new TapTargetView.Listener()
        {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                view.dismiss(true);
            }
        });

        mRecyclerView.setAdapter(mCustomAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mFloatingActionButton.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.floatActionButtonRecView:
                DialogFragment albumFragment = new AlbumDialogFragment();
                albumFragment.show(getFragmentManager(), "Add new album");
                break;

        }
    }
}
