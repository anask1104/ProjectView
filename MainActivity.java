package com.anask1104gmail.projectview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;

import static com.anask1104gmail.projectview.R.id.Card;
import static com.anask1104gmail.projectview.R.id.post_image;
import static com.anask1104gmail.projectview.R.id.post_title;
import static com.anask1104gmail.projectview.R.id.snap;
import static com.anask1104gmail.projectview.R.id.start;
import static com.anask1104gmail.projectview.R.id.time;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mBloglist;
    private DatabaseReference mDatabase;
    Bitmap bitmap;
    CardView Card;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        mDatabase.orderByChild("timestamp");
        CardView Card = (CardView) findViewById(R.id.Card);

        mBloglist=(RecyclerView) findViewById(R.id.blog_list);
        mBloglist.setHasFixedSize(true);
        mBloglist.setLayoutManager(new LinearLayoutManager(this));



        // Now set the properties of the LinearLayoutManager

// And now set it to the RecyclerView


    }



    @Override
    protected void onStart() {
        super.onStart();


        final FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter =new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(

            Blog.class,
            R.layout.blog_row,
            BlogViewHolder.class,
            mDatabase.orderByChild("timestamp")



            ) {




            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, final Blog model, int position) {


                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setDate(model.getDate());
                viewHolder.setStatus(model.getStatus());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.Card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                     Intent intent= new Intent(view.getContext(),DetailActivity.class);

                       intent.putExtra("title",model.getTitle());
                        intent.putExtra("description",model.getDesc());
                       // your bitmap
                        ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
                        intent.putExtra("byteArray", _bs.toByteArray());


                        startActivity(intent);
                    }
                });
            }

        };

        mBloglist.setAdapter(firebaseRecyclerAdapter);
    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder {

        View mView;
        CardView Card;

        public BlogViewHolder(View itemView) {
            super(itemView);
            Card=(CardView)itemView.findViewById(R.id.Card);
            mView= itemView;

        }




        public void setTitle(String title){

            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

       public void setDesc(String desc){

           TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);

       }

        public void setDate(String date){

            TextView post_date = (TextView) mView.findViewById(R.id.post_date);
            post_date.setText(date);

        }
        public void setImage(Context ctx, String image){
            ImageView post_image=(ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);

        }
        public void setStatus(String status){

            TextView post_title = (TextView) mView.findViewById(R.id.post_status);
            post_title.setText(status);
        }

    }



    public boolean onCreateOptionMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.action_add){
            startActivity(new Intent(MainActivity.this, PostActivity.class));

    }
    return super.onOptionsItemSelected(item);


    }

}
