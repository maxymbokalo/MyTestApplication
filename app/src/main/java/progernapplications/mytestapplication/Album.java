package progernapplications.mytestapplication;

/**
 * Created by Олег-PC on 16.09.2016.
 */
public class Album
{
    private String mName, mSinger;
    private String mImageURL;

    public Album(String name, String singer, String imageURL)
    {
        mName = name;
        mSinger = singer;
        mImageURL = imageURL;
    }

    public String getName()
    {
        return mName;
    }

    public String getSinger()
    {
        return mSinger;
    }




}
