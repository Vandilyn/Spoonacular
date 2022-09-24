package com.example.spoonacular;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterFragment extends Fragment {
    Button register;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    EditText emailreg,passwordreg,phonenum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        emailreg = root.findViewById(R.id.emailreg);
        passwordreg = root.findViewById(R.id.passwordreg);
        phonenum = root.findViewById(R.id.phonenumreg);
        register = root.findViewById(R.id.registerBtn);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;
                String email = emailreg.getText().toString();
                String password = passwordreg.getText().toString();
                String phone = phonenum.getText().toString();

                if (email.isEmpty()) {
                    emailreg.setError("Email tidak boleh kosong!");
                    emailreg.requestFocus();
                    count++;
                    return;
                }

                if (password.isEmpty()) {
                    passwordreg.setError("Password tidak boleh kosong!");
                    passwordreg.requestFocus();
                    count++;
                    return;
                }

                if (phone.isEmpty()) {
                    phonenum.setError("No HP tidak boleh kosong!");
                    phonenum.requestFocus();
                    count++;
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailreg.setError("Gunakan email yang valid!");
                    emailreg.requestFocus();
                    count++;
                    return;
                }
                if (count==0) {
                    mainPage();
//                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(task.isSuccessful()){
//                                Toast.makeText(getActivity(), "User berhasil didaftarkan", Toast.LENGTH_SHORT).show();
//                                mainPage();
//                            }
//                            else{
//                                Toast.makeText(getActivity(), "User gagal didaftarkan", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
                }
            }
        });
        return root;
    }
    public void mainPage(){
        Intent intent = new Intent(getActivity(), Mainpage.class);
        startActivity(intent);
    }
}