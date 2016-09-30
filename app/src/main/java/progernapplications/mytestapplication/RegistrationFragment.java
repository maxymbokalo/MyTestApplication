package progernapplications.mytestapplication;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class RegistrationFragment extends Fragment implements View.OnClickListener {

    private EditText loginField, passwordField, firstNameField, secondNameField, emailField, dateOfBirthField;
    private Button sendData, getData, clearTable;
    private DBHandler myHandler;
    // TODO User class
    private ArrayList<User> usersList;
    private int incrementOfDB = 0;
    private int tableCounter = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_registration, container, false);

        loginField = (EditText) myView.findViewById(R.id.loginField);
        passwordField = (EditText) myView.findViewById(R.id.passwordField);
        firstNameField = (EditText) myView.findViewById(R.id.firstNameField);
        secondNameField = (EditText) myView.findViewById(R.id.secondNameField);
        emailField = (EditText) myView.findViewById(R.id.emailField);
        dateOfBirthField = (EditText) myView.findViewById(R.id.dateOfBirthField);
        sendData = (Button) myView.findViewById(R.id.sendData);
        getData = (Button) myView.findViewById(R.id.getData);
        clearTable = (Button) myView.findViewById(R.id.clearTable);

        myHandler = new DBHandler(getContext());

        sendData.setOnClickListener(this);
        getData.setOnClickListener(this);
        clearTable.setOnClickListener(this);


        return myView;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.sendData:
                if(loginField.getText().toString().equals("")
                        || passwordField.getText().toString().equals("")
                        ||emailField.getText().toString().equals("")) Toast.makeText(getActivity().getApplicationContext(), "Looks like some of the required fields are empty"
                + "\n Login, password and email are required", Toast.LENGTH_LONG).show();
                else {
                    myHandler.addUser(loginField.getText().toString(),
                            passwordField.getText().toString(),
                            firstNameField.getText().toString(),
                            secondNameField.getText().toString(),
                            emailField.getText().toString(),
                            dateOfBirthField.getText().toString());
                }

                break;

            case R.id.getData:
                try {
                    usersList = myHandler.getUsers();
                    if (incrementOfDB < usersList.size()) {
                        User currentUser = usersList.get(incrementOfDB);
                        Toast.makeText(getActivity().getApplicationContext(), "ID: " + incrementOfDB + "\n Login:" + currentUser.getLogin() +
                                "\n Password: " + currentUser.getPassword() + "\n First name: " + currentUser.getName() +
                                "\n Second name: " + currentUser.getSecondName() + "\n Birthday: " + currentUser.getBirthday() , Toast.LENGTH_SHORT).show();
                        incrementOfDB++;
                    } else incrementOfDB = 0;

                    break;
                }catch (SQLiteException ex)
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Looks like table is empty now"
                    + "\n Try to add some data", Toast.LENGTH_SHORT).show();
                }

            case R.id.clearTable:
                    tableCounter++;
                    if(tableCounter > 1) {
                       myHandler.clearTable();
                        Toast.makeText(getActivity().getApplicationContext(), "Table cleared", Toast.LENGTH_LONG).show();
                        tableCounter = 0;
                    }
                else Toast.makeText(getActivity().getApplicationContext(), "Be carefull!" + "\nIf you will clear table"
                            + "\nNo data will be returned", Toast.LENGTH_LONG).show();

        }
         {



        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("data", usersList);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            // TODO User class

        }
    }
}

