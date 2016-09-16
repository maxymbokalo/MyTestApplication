package progernapplications.mytestapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gun0912.tedbottompicker.TedBottomPicker;


public class CameraFragment extends Fragment implements View.OnClickListener{


    FloatingActionButton mFloatingActionButton;
    ImageView imageFromGallery;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        // A listener for runtime WRITE_EXTERNAL_STORAGE permission
        PermissionListener mPermissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getActivity().getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(getActivity().getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        };

        // Permission checker
        new TedPermission(getActivity().getApplicationContext())
                .setPermissionListener(mPermissionListener)
                .setDeniedMessage("If you deny this permission, you won't be able to load photos from gallery and camera")
                .setPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();


        return inflater.inflate(R.layout.fragment_camera, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFloatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatActionButton);
        imageFromGallery = (ImageView)view.findViewById(R.id.imageFromGallery);
        mFloatingActionButton.setOnClickListener(this);

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
                            Picasso.with(getActivity().getApplicationContext())
                                    .load(uri)
                                    .into(imageFromGallery);
                        }
                    })
                    .create();


            tedBottomPicker.show(getActivity().getSupportFragmentManager());
        }
    }
}
