package com.example.gamabubakar.cuonlineapp;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Pair;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder>{
    ArrayList<String> data=new ArrayList<>();
    Context context;
    Data datas;
    ActionMode actionMode;
    int store=0; //store current position long pressed
    public RecycleAdapter(Context context,ArrayList<String> data){
        this.context=context;
        this.data=data;
        datas= (Data) context; //if you want to show contextual toolbar
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item,parent,false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleViewHolder holder, final int position) {
                String text=data.get(position);
                holder.tv.setText(text);
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        store=position;
                        //TODO: implement CONTEXTUAL TOOLBAR
                        if(actionMode != null){
                            return false;
                        }
                        actionMode=datas.startSupportActionMode(startmode); //this is important line
                        return true;
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        store=position;
                        //TODO: implement shared transitions
                        Pair[] pairs=new Pair[2];
                        pairs[0]=new Pair<View,String>(holder.iv,"image-shared");
                        pairs[1]=new Pair<View,String>(holder.tv,"text-shared");
                        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(datas,pairs);
                        Intent i=new Intent(datas,SharedElement.class);
                        i.putExtra("name",data.get(store));
                        datas.startActivity(i,options.toBundle());

                    }
                });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;
        public RecycleViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.textview);
            iv=itemView.findViewById(R.id.imageview);
        }
    }
    ActionMode.Callback startmode=new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.main_menu,menu);
            mode.setTitle(""+store);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.delete:
                    data.remove(store);
                    notifyItemRemoved(store);
                    notifyItemRangeChanged(store,data.size());
                   // notifyDataSetChanged(); // if we use only this then commet above two lines and have no animation and also comment recyclerView.setItemAnimation line
                    mode.finish();
                    return true;
                case R.id.add:
                    data.add(store,data.get(store));
                    notifyItemInserted(store);
                    notifyItemRangeChanged(store,data.size());
                   // notifyDataSetChanged();  // if we use only this then commet above two lines and have no animation and also comment recyclerView.setItemAnimation line
                    mode.finish();
                    return true;
                    default:
                        return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode=null;
        }
    };
}
