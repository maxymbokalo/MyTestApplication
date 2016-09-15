package progernapplications.mytestapplication;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    private Toolbar mActionToolBar;
    private Drawer mResultDrawer;
    private AccountHeader mAccountHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionToolBar = (Toolbar) findViewById(R.id.toolbar);
        mActionToolBar.setTitle("");

        setSupportActionBar(mActionToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header_image)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Misko Oleg")
                                .withEmail("olegmiskoo@gmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.profphoto))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return false;
                    }
                }).build();

        mResultDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mActionToolBar)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(mAccountHeader)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.custom_list).withIcon(R.drawable.list_icon).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.images_from_sd).withIcon(R.drawable.camera_icon).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.geolocation_textview).withIcon(R.drawable.location_icon).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.registration_form).withIcon(R.drawable.registration_icon).withIdentifier(4),
                        new SectionDrawerItem().withName(R.string.other),
                        new SecondaryDrawerItem().withName(R.string.settings).withIcon(R.drawable.settings_icon).withIdentifier(5),
                        new SecondaryDrawerItem().withName(R.string.exit).withIcon(R.drawable.close_icon).withIdentifier(6)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            switch (((int) drawerItem.getIdentifier())) {

                                case 1:
                                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new RecViewFragment()).commit();

                                    break;
                                case 2:
                                    // TODO Camera Activity
                                    Toast.makeText(getApplicationContext(), "Camera Activity", Toast.LENGTH_SHORT).show();
                                    mResultDrawer.closeDrawer();
                                    break;
                                case 3:
                                    // TODO Geolocation Activity
                                    Toast.makeText(getApplicationContext(), "Geolocation Activity", Toast.LENGTH_SHORT).show();
                                    mResultDrawer.closeDrawer();
                                    break;
                                case 4:
                                    // TODO registration form
                                    Toast.makeText(getApplicationContext(), "Registration Activity", Toast.LENGTH_SHORT).show();
                                    mResultDrawer.closeDrawer();
                                    break;
                                case 5:
                                    // TODO Settings - not primary task
                                    break;
                                case 6:
                                    // TODO Exit - not primary task
                                    break;

                                default:
                                    // TODO nothing
                                    break;
                            }



                        }
                        return true;
                    }
                })



                .build();
    }



    @Override
    public void onBackPressed() {

        // To hide drawer with backpress button
        if (mResultDrawer.isDrawerOpen())
        {
            mResultDrawer.closeDrawer();
        }
        else super.onBackPressed();
    }

}
