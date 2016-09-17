package progernapplications.mytestapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Олег-PC on 16.09.2016.
 */
public class Album implements Serializable
{
    public String mName;
    public String mSinger;
    public String mImageURL;

    public Album(String name, String singer, String imageURL)
    {
        mName = name;
        mSinger = singer;
        mImageURL = imageURL;
    }







}
