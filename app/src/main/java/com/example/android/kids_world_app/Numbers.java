package com.example.android.kids_world_app;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Numbers extends FragmentActivity {
MediaPlayer mp;
    ViewPager viewpager;
    CustomswipeAdapter adapter;

    Integer[] images={R.drawable.onepic,R.drawable.twopic,R.drawable.threpic,R.drawable.fourpic,R.drawable.fivpic,R.drawable.sixpic,R.drawable.sevpic,R.drawable.eigtpic,R.drawable.ninpic,R.drawable.tenpic};
Integer[] audio={R.raw.one,R.raw.two,R.raw.three,R.raw.four,R.raw.five,R.raw.six,R.raw.seven,R.raw.eight,R.raw.nine,R.raw.ten};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        viewpager=(ViewPager)findViewById(R.id.numbers_Viewer);
adapter=new CustomswipeAdapter(this);
        viewpager.setAdapter(adapter);
        mp = MediaPlayer.create(Numbers.this, audio[0]);
//        mp.start();
    }

    public class CustomswipeAdapter extends PagerAdapter{
        private Context ctx;
        private LayoutInflater layoutinflater;
        public CustomswipeAdapter(Context ctx) {
        this.ctx=ctx;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }



        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutinflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item_view = layoutinflater.inflate(R.layout.swipe_layout, container, false);
            ImageView imageview = (ImageView) item_view.findViewById(R.id.swipeimages);
            imageview.setBackgroundResource(images[position]);
            //get viewpager form container
            ViewPager vp = (ViewPager) container;
            //here we add the view into one page
            vp.addView(item_view, 0);

            //for Sound
            if (mp != null) {
                mp.reset();
                mp.release();

            }
            // for (int i = 0; i < audio.length; i++)
//the audio files are running here but not in sync with image
                mp = MediaPlayer.create(Numbers.this, audio[position]);
            mp.start();

            return item_view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //code here destroys the slide when it is gone to next slide
            ViewPager vp = (ViewPager) container;
            View v = (View) object;
            //here we get the existing slide object that we convert into the view by using downcasting
            //remove that view
            vp.removeView(v);
        }
    }
}
