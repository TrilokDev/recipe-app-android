package com.tiodev.vegtummy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.tiodev.vegtummy.Adapter.SearchAdapter;
import com.tiodev.vegtummy.RoomDB.AppDatabase;
import com.tiodev.vegtummy.RoomDB.User;
import com.tiodev.vegtummy.RoomDB.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    EditText search;
    ImageView back_btn;
    RecyclerView rcview;
    List<User> dataPopular = new ArrayList<>();
    SearchAdapter adapter;
    List<User> recipes;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Find views
        search = findViewById(R.id.search);
        back_btn = findViewById(R.id.back_to_home);
        rcview = findViewById(R.id.rcview);

        // Show and focus the keyboard
        search.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        // Get database
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "db_name").allowMainThreadQueries()
                .createFromAsset("database/recipe.db")
                .build();
        UserDao userDao = db.userDao();

        // Get all recipes from database
        recipes = userDao.getAll();

        // Filter the Popular category on activity start
        for(int i = 0; i<recipes.size(); i++){
            if(recipes.get(i).getCategory().contains("Popular")){
                dataPopular.add(recipes.get(i));
            }
        }

        // Set layout manager to recyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));

        // Hide keyboard when recyclerView item clicked
        rcview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                return false;
            }
        });

        // Set adapter to search recyclerView
        adapter = new SearchAdapter(dataPopular, getApplicationContext());
        rcview.setAdapter(adapter);


        // Search from all recipes when Edittext data changed
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Method required*
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Method required*
            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!s.toString().equals("")){ // Search if new alphabet added
                    filter(s.toString());
                }


            }
        });


        // Exit activity
        back_btn.setOnClickListener(v -> {
            imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
            finish();
        });
    }

    // Filter the searched item from all recipes
    public void filter(String text) {
        List<User> filterList = new ArrayList<>();

        for(int i = 0; i<recipes.size(); i++){ // Loop for check searched item in recipe list
            if(recipes.get(i).getTittle().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))){
                filterList.add(recipes.get(i));
            }
        }

        // Update search recyclerView with new item
        adapter.filterList(filterList);

    }
}