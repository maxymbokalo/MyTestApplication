package progernapplications.mytestapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import gun0912.tedbottompicker.TedBottomPicker;


public class CameraFragment extends Fragment implements View.OnClickListener{


    FloatingActionButton mFloatingActionButton;
    ArrayList<Uri> imageURIs;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    CustomGalleryAdapter mCustomGalleryAdapter;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {





        return inflater.inflate(R.layout.fragment_camera, container, false);

    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final View myView = view;
        Toast.makeText(getActivity().getApplicationContext(), "For now it works only with not big images (under 4096x4096", Toast.LENGTH_LONG);
        // A listener for runtime WRITE_EXTERNAL_STORAGE permission
        PermissionListener mPermissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Snackbar positiveSnackBar = Snackbar.make(myView, "Permission granted", Snackbar.LENGTH_SHORT);
                positiveSnackBar.show();

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Snackbar negativeSnackBar = Snackbar.make(myView, "No permission to read files from storage", Snackbar.LENGTH_LONG);
                negativeSnackBar.show();
            }
        };

        // Permission checker
        new TedPermission(getActivity().getApplicationContext())
                .setPermissionListener(mPermissionListener)
                .setDeniedMessage("If you deny this permission, the application won't be able to load photos from gallery and camera")
                .setPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();

        mFloatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatActionButton);
        mFloatingActionButton.setOnClickListener(this);
        imageURIs = new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.customGalleryRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        mCustomGalleryAdapter = new CustomGalleryAdapter(getActivity().getApplicationContext(), imageURIs);
        mRecyclerView.setAdapter(mCustomGalleryAdapter);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.floatActionButton)
        {
            // Image picker
            TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getActivity().getApplicationContext())
                    .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                        @Override
                        public void onImageSelected(Uri uri) {
                            imageURIs.add(uri);
                            mCustomGalleryAdapter.notifyDataSetChanged();
                        }
                    })
                    .create();


            tedBottomPicker.show(getActivity().getSupportFragmentManager());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("data", imageURIs);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null)
        {
            imageURIs = (ArrayList<Uri>)savedInstanceState.getSerializable("data");
            mLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            mCustomGalleryAdapter = new CustomGalleryAdapter(getActivity().getApplicationContext(), imageURIs);
            mRecyclerView.setAdapter(mCustomGalleryAdapter);
            mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));
            mRecyclerView.setLayoutManager(mLinearLayoutManager);

        }

    }
}
