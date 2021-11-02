package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private TextView cancel;
    private MyAdapter adapter;
    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.setDatas(datas);
                } else {
                    adapter.setDatas(search(newText));
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        cancel.setOnClickListener(view -> {
            searchView.setQuery("",false);
            searchView.clearFocus();
            adapter.setDatas(datas);
            adapter.notifyDataSetChanged();
        });
    }

    void initView(){
        searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.recycler_view);
        cancel = findViewById(R.id.cancel);
        adapter = new MyAdapter();
        datas = generateDatas();

        searchView.setIconifiedByDefault(false);
        adapter.setDatas(datas);
    }

    private List<String> search(String query) {
        List<String> filterDatas = new ArrayList<>();
        for (String source : datas) {
            if (source.contains(query)) {
                filterDatas.add(source);
            }
        }
        return filterDatas;
    }

    private List<String> generateDatas() {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < 100; index++) {
            list.add("这是第 " + (index + 1) + " 行");
        }
        return list;
    }
}