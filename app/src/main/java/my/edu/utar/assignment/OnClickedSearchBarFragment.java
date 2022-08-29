package my.edu.utar.assignment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class OnClickedSearchBarFragment extends Fragment {

    DatabaseReference ref;
    Activity activity = getActivity();
    ArrayList<Food> list;

    SearchView searchView;
    RecyclerView recyclerView;

    public OnClickedSearchBarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ref = FirebaseDatabase.getInstance().getReference().child("Food");

        View view = inflater.inflate(R.layout.fragment_on_clicked_search_bar, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_search_bar_fragment);
        searchView = view.findViewById(R.id.search_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        //searchView.clearFocus();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (ref != null)
        {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists())
                    {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren())
                        {
                            list.add(ds.getValue(Food.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String str){
        ArrayList<Food> myList = new ArrayList<>();
        for (Food object : list){
            if (object.getName().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }
        }
        AdapterClass adapterClass = new AdapterClass(myList);
        recyclerView.setAdapter(adapterClass);
    }
}