package e.hg.behappy2;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import e.hg.behappy2.data.QuoteData;

import e.hg.behappy2.data.QuoteListAsyncResponce;
import e.hg.behappy2.data.QuoteViewPagerAdaptor;
import e.hg.behappy2.model.Quote;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private QuoteViewPagerAdaptor quoteViewPagerAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteViewPagerAdaptor = new QuoteViewPagerAdaptor(getSupportFragmentManager(), getFragments());
        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(quoteViewPagerAdaptor);


    }

    private List<Fragment> getFragments() {
        final List<Fragment> fragmentList = new ArrayList<>();

        new QuoteData().getQuotes(new QuoteListAsyncResponce() {
            @Override
            public void processFinished(ArrayList<Quote> quotes) {
                for (int i = 0; i < quotes.size(); i++) {
                    QuotesFragment quoteFragment = QuotesFragment.newInstance(
                            quotes.get(i).getQuote(),
                            quotes.get(i).getAuthor()
                    );
                    fragmentList.add(quoteFragment);
                }
                quoteViewPagerAdaptor.notifyDataSetChanged();/// very important!!

            }
        });




        return fragmentList;
    }
}

