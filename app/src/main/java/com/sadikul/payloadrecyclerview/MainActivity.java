package com.sadikul.payloadrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new HelloAdapter());
        DefaultItemAnimator animator = new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        };
        mRecyclerView.setItemAnimator(animator);
    }

    private static class HelloAdapter extends RecyclerView.Adapter<HelloAdapter.HelloViewHolder> {

        private int[] images = {
                R.drawable.sunflower,
                R.drawable.sunflower2,
                R.drawable.sunflower3,
                R.drawable.sunflower4
        };
        public class HelloViewHolder extends RecyclerView.ViewHolder {

            public ImageView ivBack,ivChangable,ivchangable1,ivChangable2,ivchangable3;
            View itemView;

            public HelloViewHolder(View view) {
                super(view);
                ivBack = view.findViewById(R.id.ivchangable1);
                ivChangable = view.findViewById(R.id.ivChangable);
                ivchangable1 = view.findViewById(R.id.ivchangable1);
                ivChangable2 = view.findViewById(R.id.ivChangable2);
                ivchangable3 = view.findViewById(R.id.ivchangable3);
                itemView = view;
            }

        }

        @Override
        public HelloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item, parent, false);
            return new HelloViewHolder(view);
        }

        @Override
        public void onBindViewHolder(HelloViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder: called");
        }

        @Override
        public void onBindViewHolder(final HelloViewHolder holder, int position, List<Object> payloads) {
            Log.d(TAG, "payload >> called pos: "+position+" " + payloads.toString());
            if(!payloads.isEmpty())
                handleDataPayload(holder,payloads);
            else
                onBindViewHolder(holder,position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = holder.getAdapterPosition();
                    Log.d("butt", "click " + position);
                    HelloAdapter.this.notifyItemChanged(position, "payload " + position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        private void handleDataPayload(final HelloViewHolder holder,List<Object> payloads){
            for(Object payload: payloads){
                int val = new Random().nextInt(images.length);
                int val2 = new Random().nextInt(images.length);
                int val3 = new Random().nextInt(images.length);
                int val4 = new Random().nextInt(images.length);
                Log.d(TAG, "handleDataPayload: "+val);
                holder.ivChangable.setImageResource(images[val]);
                holder.ivchangable1.setImageResource(images[val2]);
                holder.ivChangable2.setImageResource(images[val3]);
                holder.ivchangable3.setImageResource(images[val3]);
            }
        }
    }

}
