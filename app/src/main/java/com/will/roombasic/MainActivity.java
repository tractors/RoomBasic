package com.will.roombasic;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordDatabase wordDatabase;
    WordDao wordDao;
    TextView textView;
    ViewModel viewModel;
    Button btnInsert, btnClear;
    private LiveData<List<Word>> allWordsLive;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter1;
    private MyAdapter myAdapter2;
    private Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(), this)).get(ViewModel.class);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter1 = new MyAdapter(false);
        myAdapter2 = new MyAdapter(true);
        recyclerView.setAdapter(myAdapter1);

        viewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                myAdapter1.setAllWords(words);
                myAdapter1.notifyDataSetChanged();

                myAdapter2.setAllWords(words);
                myAdapter2.notifyDataSetChanged();
            }
        });
        textView = findViewById(R.id.textViewNumber);
        btnInsert = findViewById(R.id.btn_insert);
        btnClear = findViewById(R.id.btn_clear);
        aSwitch = findViewById(R.id.switch1);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    recyclerView.setAdapter(myAdapter2);
                } else {
                    recyclerView.setAdapter(myAdapter1);
                }
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] english = {
                        "Hello",
                        "World",
                        "Android",
                        "Google",
                        "Studio",
                        "Project",
                        "Database",
                        "Recycler",
                        "View",
                        "String",
                        "Value",
                        "Integer"
                };
                String[] chinese = {
                        "你好",
                        "世界",
                        "安卓系统",
                        "谷歌公司",
                        "工作室",
                        "项目",
                        "数据库",
                        "回收站",
                        "视图",
                        "字符串",
                        "价值",
                        "整数类型"
                };

                int count = english.length;
                for (int i = 0; i < count; i++) {
                    viewModel.insertWords(new Word(english[i],chinese[i]));
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.deleteAllWords();
            }
        });
    }


}