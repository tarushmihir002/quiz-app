package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quizapp.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import me.ibrahimsn.lib.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,new HomeFragment());
        transaction.commit();

        binding.bottomBar.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    @Override
                    public boolean onItemSelect(int i) {
                        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                        switch (i){
                            case 0:
                                transaction.replace(R.id.content,new HomeFragment());
                                transaction.commit();
                                Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                transaction.replace(R.id.content,new LeaderbordFragment());
                                transaction.commit();
                                Toast.makeText(MainActivity.this, "Rank", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                transaction.replace(R.id.content,new WalletFragment());
                                transaction.commit();
                                break;
                            case 3:
                                transaction.replace(R.id.content,new ProfileFragment());
                                transaction.commit();
                                break;
                        }
                        return false;
                    }
                }
        );
    }
}