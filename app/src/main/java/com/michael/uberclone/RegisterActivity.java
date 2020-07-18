package com.michael.uberclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.michael.uberclone.includes.MyToolbar;
import com.michael.uberclone.models.User;

import dmax.dialog.SpotsDialog;

public class RegisterActivity extends AppCompatActivity {

    public SharedPreferences mPref;
    public FirebaseAuth mAuth;
    public DatabaseReference mDatabase;

    // VIEWS
    private Button mButtonRegister;
    private TextInputEditText mTextInputName;
    private TextInputEditText mTextInputEmail;
    private TextInputEditText mTextInputPassword;
    public AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MyToolbar.show(this, "Registro de usuario", true);


        mButtonRegister = findViewById(R.id.btnRegister);
        mTextInputName = findViewById(R.id.textInputName);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputPassword = findViewById(R.id.textInputPassword);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mPref =  getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);

        mDialog = new SpotsDialog.Builder().setContext(RegisterActivity.this).setMessage("Espere un momento").build();
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
       final String name = mTextInputName.getText().toString();
       final String email = mTextInputEmail.getText().toString();
       final String password = mTextInputPassword.getText().toString();

       if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            if(password.length() >= 6) {
                mDialog.show();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mDialog.hide(); // deje mostrar
                        if(task.isSuccessful()) {
                            String id = mAuth.getCurrentUser().getUid();
                            saveUser(id,name, email);
                        } else {
                            Toast.makeText(RegisterActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(this, "La contraseña debe tener al menos 6 carácteres", Toast.LENGTH_SHORT).show();
            }
       }
       else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
       }
    }

    // Guardar el usuario
    private void saveUser(String id, String name, String email) {
        String selectedUser = mPref.getString("user", "");
        Toast.makeText(this, "El valor que selecciono fue " + selectedUser, Toast.LENGTH_SHORT).show();
        User user = new User();
        user.setEmail(email);
        user.setName(name);

        if (selectedUser.equals("driver")) {
            mDatabase.child("Users").child("Drivers").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if(selectedUser.equals("client")){
            mDatabase.child("Users").child("Clients").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

}