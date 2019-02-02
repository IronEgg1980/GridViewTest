package com.example.gridviewtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class InputAdapter extends RecyclerView.Adapter<InputAdapter.InputAdapterViewHolder> {
    class InputAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public InputAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textview1);
        }
    }
    public interface InputClickListener{
        void OnClick(String s);
    }

    public void setInputClickListener(InputClickListener inputClickListener) {
        this.inputClickListener = inputClickListener;
    }
    public void setList(List<String> _list){
        list = _list;
        notifyDataSetChanged();
    }
    private InputClickListener inputClickListener;
    private List<String> list ;
    public InputAdapter(List<String> _list){
        list = _list;
    }
    @Override
    public InputAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.input_item,viewGroup,false);
        return new InputAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InputAdapterViewHolder viewHolder, int i) {
        final String s = list.get(i);
        viewHolder.textView.setText(s);
        viewHolder.textView.setClickable(true);
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputClickListener.OnClick(s);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
