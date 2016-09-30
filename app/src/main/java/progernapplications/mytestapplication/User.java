package progernapplications.mytestapplication;

import java.io.Serializable;

/**
 * Created by Олег-PC on 30.09.2016.
 */
public class User implements Serializable
{
    private String login, password, email, firstName, secondName, birthday;

    public User(String login, String password, String email, String firstName, String secondName, String birthday)
    {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
    }

    public void setLogin(String newLogin)
    {
        login = newLogin;
    }

    public String getLogin()
    {
        return login;
    }

    public void setPassword(String newPassword)
    {
        password = newPassword;
    }

    public String getPassword()
    {
        return password;
    }

    public void setName(String newName)
    {
        firstName = newName;
    }

    public String getName()
    {
        return firstName;
    }

    public void setSecondName(String newSecondName)
    {
        secondName = newSecondName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public void setBirthday(String newBirthday)
    {
        birthday = newBirthday;
    }

    public String getBirthday()
    {
        return birthday;
    }




}
