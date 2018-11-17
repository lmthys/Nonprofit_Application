package com.example.lmandrew.nonprofit_application;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailto = "mailto:pastordan@victorbaptist.org" +
                        "?cc=" + "patchnelson50@gmail.com";

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse(mailto));
                emailIntent.setType("text/plain");
                try {
                    startActivity(emailIntent);
                    finish();
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ContactActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
            int fragment = getArguments().getInt(ARG_SECTION_NUMBER);
            switch(fragment) {
                case 1:
                    TextView title = (TextView) rootView.findViewById(R.id.title_tv);
                    title.setText("Church Name");
                    TextView textView = (TextView) rootView.findViewById(R.id.name_tv);
                    textView.setText("Victor Baptist Church");
                    TextView numberTv = rootView.findViewById(R.id.phone_tv);
                    numberTv.setText("319-647-3466");
                    TextView email_to_service = rootView.findViewById(R.id.email_constant);
                    email_to_service.setText("Service Times");
                    TextView times = rootView.findViewById(R.id.email_tv);
                    times.setText("Youth - 6:00pm\n" +
                            "\n" +
                            "Worship - 10:00am\n" +
                            "\n" +
                            "Sunday School - 9:00am");
                    break;
                case 2:
                    title = rootView.findViewById(R.id.title_tv);
                    title.setText("Head Pastor");
                    textView = rootView.findViewById(R.id.name_tv);
                    textView.setText("Dan Andrews, PhD.");
                    numberTv = rootView.findViewById(R.id.phone_tv);
                    numberTv.setText("319-936-7420");
                    TextView email = rootView.findViewById(R.id.email_tv);
                    email.setText("pastordan@victorbaptist.org");
                    break;
                case 3:
                    title = rootView.findViewById(R.id.title_tv);
                    title.setText("Youth Paster");
                    textView = rootView.findViewById(R.id.name_tv);
                    textView.setText("Paul Nelson");
                    numberTv = rootView.findViewById(R.id.phone_tv);
                    numberTv.setText("563-663-8461");
                    email = rootView.findViewById(R.id.email_tv);
                    email.setText("patchnelson50@gmail.com");
                    break;
                default:
                    title = (TextView) rootView.findViewById(R.id.title_tv);
                    title.setText("Church Name");
                    textView = (TextView) rootView.findViewById(R.id.name_tv);
                    textView.setText("Victor Baptist Church");
                    numberTv = rootView.findViewById(R.id.phone_tv);
                    numberTv.setText("319-647-3466");
                    email_to_service = rootView.findViewById(R.id.email_constant);
                    email_to_service.setText("Service Times");
                    times = rootView.findViewById(R.id.email_tv);
                    times.setText("Sunday School - 9:00am\n" +
                            "\n" +
                            "Worship - 10:00am\n" +
                            "\n" +
                            "Youth - 6:00pm");
                    break;


            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
