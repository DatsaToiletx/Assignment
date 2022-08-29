package my.edu.utar.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import my.edu.utar.assignment.databinding.ActivityMainAppBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainApp extends AppCompatActivity {

    ActivityMainAppBinding binding;
    DatabaseReference ref;
    SearchView searchView;
    ArrayList<Food> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        BottomNavigationView mbottomNavigationView = findViewById(R.id.btmNavigationView);
        mbottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
//        searchView = findViewById(R.id.search_view);
//        searchView.setOnClickListener(this);
//        recyclerView = new RecyclerView(this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainApp.this));
//        ref = FirebaseDatabase.getInstance().getReference().child("Food");

//        searchView.clearFocus();
//        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                replaceFragment(new SearchFragment());
//                mbottomNavigationView.getMenu().findItem(R.id.search).setChecked(true);
//            }
//        });

        binding.btmNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){

                case R.id.user:
                    replaceFragment(new UserFragment());
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment());
                    break;
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.ingredient_checklist:
                    replaceFragment(new IngredientChecklistFragment());
                    break;
                case R.id.favourite:
                    replaceFragment(new FavouriteFragment());
                    break;

            }

            return true;
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        if (ref != null)
//        {
//            ref.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists())
//                    {
//                        list = new ArrayList<>();
//                        for (DataSnapshot ds : snapshot.getChildren())
//                        {
//                            list.add(ds.getValue(Food.class));
//                        }
//                        AdapterClass adapterClass = new AdapterClass(list);
//                        recyclerView.setAdapter(adapterClass);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(MainApp.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        if (searchView != null)
//        {
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String s) {
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String s) {
//                    search(s);
//                    return true;
//                }
//            });
//        }
//    }
//
//    private void search(String str){
//        ArrayList<Food> myList = new ArrayList<>();
//        for (Food object : list){
//            if (object.getName().toLowerCase().contains(str.toLowerCase())){
//                myList.add(object);
//            }
//        }
//        AdapterClass adapterClass = new AdapterClass(myList);
//        recyclerView.setAdapter(adapterClass);
//    }
//
//
//    @Override
//    public void onClick(View view) {
//        searchView.setIconified(false);
//        replaceFragment(new SearchFragment());
//    }
}