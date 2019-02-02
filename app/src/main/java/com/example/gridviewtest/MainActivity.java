package com.example.gridviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView gridViewRecyclerView,bottomRecyclerView;
    private GridViewAdapter gridViewAdapter;
    private int x, y;
    private List<String> personList,shiftList;
    private List<GridViewEntity> gridViewEntities;
    private InputAdapter adapter;
    private void fillPersonList(){
        if(personList==null)
            personList = new ArrayList<>();
        if(adapter == null)
            adapter = new InputAdapter(personList);
        for(int i = 1;i<19;i++){
            personList.add("人员"+i);
        }
        adapter.setList(personList);
    }
    private void fillShiftList(){
        if (shiftList == null) {
            shiftList = new ArrayList<>();
        }
        if(adapter == null)
            adapter = new InputAdapter(shiftList);
        for (int i = 1;i<10;i++){
            shiftList.add("名称"+i);
        }
        adapter.setList(shiftList);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x = 0;
        y = 0;
        gridViewRecyclerView = findViewById(R.id.gridview_recyclerview);
        bottomRecyclerView = findViewById(R.id.bottom_recyclerview);
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        gridViewEntities = new ArrayList<>();
        GridViewEntity entity1 = new GridViewEntity("",new String[7],"");
        gridViewEntities.add(entity1);
        gridViewAdapter = new GridViewAdapter(gridViewEntities);
        gridViewAdapter.setGridViewAdapterClickListener(new GridViewAdapter.GridViewAdapterClickListener() {
            @Override
            public void OnClick(int _x, int _y) {
                x = _x;
                y = _y;
                if(y == 0)
                    fillPersonList();
                else if(y ==8)
                    Toast.makeText(MainActivity.this,"设置备注",Toast.LENGTH_SHORT).show();
                else
                    fillShiftList();
            }
        });
        gridViewRecyclerView.setAdapter(gridViewAdapter);
        gridViewRecyclerView.setLayoutManager(manager1);
        fillPersonList();
        adapter = new InputAdapter(personList);
        adapter.setInputClickListener(new InputAdapter.InputClickListener() {
            @Override
            public void OnClick(String s) {
                if(x < 0|| y <0){
                    return;
                }
                GridViewEntity entity = gridViewEntities.get(x);
                if(y == 0) {
                    entity.setName(s);
                    if(x < gridViewEntities.size() - 1)
                        x = x +1;
                }
//                else if(y ==8)
//                    Toast.makeText(MainActivity.this,"设置备注",Toast.LENGTH_SHORT).show();
                else{
                    entity.setShift(y,s);
                    if(y<7){
                        y = y+1;
                    }else if(x < gridViewEntities.size() - 1){
                        x = x +1;
                        y = 1;
                    }
                }
                gridViewAdapter.setCurrentCell(x,y);
            }
        });
        bottomRecyclerView.setAdapter(adapter);
        bottomRecyclerView.setLayoutManager(manager2);
        findViewById(R.id.clear_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GridViewEntity entity = new GridViewEntity("",new String[7],"");
                entity.setChecked(0,true);
                fillPersonList();
                gridViewEntities.add(entity);
                x  = gridViewEntities.size() -1;
                y = 0;
                gridViewAdapter.setCurrentCell(x,y);
                gridViewAdapter.notifyDataSetChanged();
            }
        });
        gridViewAdapter.setCurrentCell( 0 , 0 );
    }
}
