package e.hg.behappy2;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuotesFragment extends Fragment {

    private TextView quoteText,byAuthor;

    private CardView cardView;

    public QuotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        View quoteview= inflater.inflate(R.layout.fragment_quotes, container, false);
        quoteText=quoteview.findViewById(R.id.quoteText);

        byAuthor=quoteview.findViewById(R.id.byAuthor);
        cardView=quoteview.findViewById(R.id.cardview);

        String quote=getArguments().getString("quote");
        String author=getArguments().getString("author");

        int colors[] = new int[] {R.color.blue_500, R.color.pink_900, R.color.green_400,
                R.color.lime_400, R.color.orange_400, R.color.amber_800, R.color.pink_800,
                R.color.grey_700};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardView.setBackgroundResource(getRandomQuote(colors));
        }

        quoteText.setText(quote);
        byAuthor.setText("-" + author);

        return quoteview;


    }

    public static final QuotesFragment newInstance(String quote, String author)
    {
        QuotesFragment fragment=new QuotesFragment();
        Bundle bundle= new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("author",author);
        fragment.setArguments(bundle);

        return fragment;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    int getRandomQuote(int [] colorArray){
        int color;
        int quotesArrayLen = colorArray.length;

        int randomNum=ThreadLocalRandom.current().nextInt(quotesArrayLen);

        color = colorArray[randomNum];

        return  color;


    }

}
