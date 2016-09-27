package progernapplications.mytestapplication;

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
    private Button sendData, getData;
    private DBHandler myHandler;
    // TODO User class
    private ArrayList<String> usersList;
    private int incrementOfDB = 0;

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

        myHandler = new DBHandler(getContext());

        sendData.setOnClickListener(this);
        getData.setOnClickListener(this);


        return myView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sendData) {
            myHandler.addUser(loginField.getText().toString(),
                    passwordField.getText().toString(),
                    firstNameField.getText().toString(),
                    secondNameField.getText().toString(),
                    emailField.getText().toString(),
                    dateOfBirthField.getText().toString());
        } else if (view.getId() == R.id.getData) {
            usersList = myHandler.getUsers();
            Toast.makeText(getActivity().getApplicationContext(), "ID: " + incrementOfDB + "\n" + usersList.get(incrementOfDB), Toast.LENGTH_SHORT).show();
            incrementOfDB++;

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

