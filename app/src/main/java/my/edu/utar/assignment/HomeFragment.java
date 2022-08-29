package my.edu.utar.assignment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    DatabaseReference ref;
    Activity activity = getActivity();
    ArrayList<Food> list;
    SearchView searchView;
    RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        searchView = view.findViewById(R.id.search_view);
        searchView.clearFocus();
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
//                view = inflater.inflate(R.layout.activity_main_app, container, false);
//                BottomNavigationView mbottomNavigationView = view.findViewById(R.id.btmNavigationView);
//                mbottomNavigationView.getMenu().findItem(R.id.search).setChecked(true);

                SearchFragment search = new SearchFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), search)
                        .addToBackStack(null)
                        .commit();
            }
        });
//        recyclerView = view.findViewById(R.id.recycle_view_search);


//        recyclerView.setLayoutManager(new LinearLayoutManager(activity));



//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        return view;
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
//                    Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
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

}