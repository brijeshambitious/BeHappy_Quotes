package e.hg.behappy2.data;

import java.util.ArrayList;

import e.hg.behappy2.model.Quote;

public interface QuoteListAsyncResponce {

    void processFinished(ArrayList<Quote> quotes);
}
