package com.example.gridviewtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.List;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.GridViewHolder> {
    class GridViewHolder extends RecyclerView.ViewHolder{
        RadioButton[] shift_buttons;
        RadioButton name,note;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            note = itemView.findViewById(R.id.item_note);
            shift_buttons = new RadioButton[7];
            shift_buttons[0] = itemView.findViewById(R.id.item_shift1);
            shift_buttons[1] = itemView.findViewById(R.id.item_shift2);
            shift_buttons[2] = itemView.findViewById(R.id.item_shift3);
            shift_buttons[3] = itemView.findViewById(R.id.item_shift4);
            shift_buttons[4] = itemView.findViewById(R.id.item_shift5);
            shift_buttons[5] = itemView.findViewById(R.id.item_shift6);
            shift_buttons[6] = itemView.findViewById(R.id.item_shift7);
        }
    }
    public interface GridViewAdapterClickListener{
        void OnClick(int x,int y);
    }
    public void setGridViewAdapterClickListener(GridViewAdapterClickListener gridViewAdapterClickListener) {
        this.gridViewAdapterClickListener = gridViewAdapterClickListener;
    }

    private GridViewAdapterClickListener gridViewAdapterClickListener;
    private List<GridViewEntity> list;
    public GridViewAdapter(List<GridViewEntity> _list){
        list = _list;
    }
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_view_item,viewGroup,false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridViewHolder viewHolder, final int i) {
        final GridViewEntity entity = list.get(i);
        viewHolder.name.setText(entity.getName());
        viewHolder.name.setChecked(entity.isCurrentCell()[0]);
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton button=(RadioButton)v;
                button.setChecked(true);
                entity.setChecked(0,true);
                gridViewAdapterClickListener.OnClick(i,0);
            }
        });
        viewHolder.note.setText(entity.getNote());
        viewHolder.note .setChecked(entity.isCurrentCell()[8]);
        viewHolder.note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton button=(RadioButton)v;
                button.setChecked(true);
                entity.setChecked(8,true);
                gridViewAdapterClickListener.OnClick(i,8);
            }
        });
        for(int j = 1;j<8;j++){
            final int k = j;
            viewHolder.shift_buttons[j-1].setText(entity.getShifts()[j-1]);
            viewHolder.shift_buttons[j-1].setChecked(entity.isCurrentCell()[j]);
            viewHolder.shift_buttons[j-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton button=(RadioButton)v;
                    button.setChecked(true);
                    entity.setChecked(k,true);
                    gridViewAdapterClickListener.OnClick(i,k);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
