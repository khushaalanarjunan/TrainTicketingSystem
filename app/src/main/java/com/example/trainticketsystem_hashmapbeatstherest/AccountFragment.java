package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.trainticketsystem_hashmapbeatstherest.object.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment {

    Button btnLogout, btnEditSaveProfile;
    EditText etUserFullName, etUserMyKADNumber, etUserEmail, etUserContactNumber, etUserPassword;
    ToggleButton toggleUserGender;

    List<User> userList = new ArrayList<>();
    DatabaseReference databaseUsers;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Initialize Firebase Realtime Database
        databaseUsers = FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users");

        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogout = view.findViewById(R.id.btn_logout_account);
        btnEditSaveProfile = view.findViewById(R.id.btn_edit_save_profile);
        //
        etUserFullName = view.findViewById(R.id.et_user_fullname);
        etUserMyKADNumber = view.findViewById(R.id.et_user_mykadnumber);
        toggleUserGender = view.findViewById(R.id.toggle_user_gender);
        etUserEmail = view.findViewById(R.id.et_user_email);
        etUserContactNumber = view.findViewById(R.id.et_user_contactnumber);
        etUserPassword = view.findViewById(R.id.et_user_password);


        etUserFullName.setEnabled(false);
        etUserMyKADNumber.setEnabled(false);
        toggleUserGender.setEnabled(false);
        etUserEmail.setEnabled(false);
        etUserContactNumber.setEnabled(false);
        etUserPassword.setEnabled(false);


        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(currentUserUid == null){
            ((MainActivity)getActivity()).logout();
        }
        databaseUsers.child(currentUserUid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    User currentUser = task.getResult().getValue(User.class);
                    etUserFullName.setText(currentUser.getUserFullName());
                    etUserMyKADNumber.setText(currentUser.getUserMYKad());
                    toggleUserGender.setChecked(currentUser.getUserGender());
                    etUserEmail.setText(currentUser.getUserEmail());
                    etUserContactNumber.setText(currentUser.getUserContact());
                    etUserPassword.setText(currentUser.getUserPassword());
                } else {
                    Toast.makeText(getActivity(), "Failed to fetch user data", Toast.LENGTH_SHORT).show();
                }
            }
        });




        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).logout();
            }
        });

        btnEditSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnEditSaveProfile.getText().toString().equals("Edit Profile")){

                    btnEditSaveProfile.setText("Save Profile");
                    etUserFullName.setEnabled(true);
                    etUserMyKADNumber.setEnabled(true);
                    toggleUserGender.setEnabled(true);
                    etUserContactNumber.setEnabled(true);
                    btnLogout.setEnabled(false);
                }else{
                    btnEditSaveProfile.setText("Edit Profile");
                    etUserFullName.setEnabled(false);
                    etUserMyKADNumber.setEnabled(false);
                    toggleUserGender.setEnabled(false);
                    etUserEmail.setEnabled(false);
                    etUserContactNumber.setEnabled(false);
                    etUserPassword.setEnabled(false);
                    Toast.makeText(getActivity(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    btnLogout.setEnabled(true);


                    String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    databaseUsers.child(currentUserUid).child("userBalance").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()) {
                                // Get the current userBalance value
                                String currentBalance = task.getResult().getValue(String.class);

                                // Create an updatedUser object without changing the balance
                                User updatedUser = new User(
                                        currentUserUid,
                                        etUserFullName.getText().toString(),
                                        etUserMyKADNumber.getText().toString(),
                                        toggleUserGender.isChecked(),
                                        etUserEmail.getText().toString(),
                                        etUserContactNumber.getText().toString(),
                                        etUserPassword.getText().toString(),
                                        currentBalance
                                );

                                // Update the user profile except the balance
                                databaseUsers.child(currentUserUid).setValue(updatedUser)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getActivity(), "Profile Updated", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(getActivity(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(getActivity(), "Failed to fetch user balance", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}