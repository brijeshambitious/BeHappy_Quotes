package e.hg.behappy2.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import e.hg.behappy2.controller.AppController;
import e.hg.behappy2.model.Quote;

public class QuoteData {
    ArrayList<Quote> quoteArrayList = new ArrayList<>();

    public void getQuotes(final QuoteListAsyncResponce callBack) {
        //String url = "https://raw.githubusercontent.com/pdichone/UIUX-Android-Course/master/Quotes.json%20";

        //String url="https://gist.githubusercontent.com/b1nary/ea8fff806095bcedacce/raw/6e6de20d7514b93dd69b149289264997b49459dd/enterpreneur-quotes.json";

        String url="https://raw.githubusercontent.com/brijeshambitious/Quotes-Json/master/Quotes.json";

        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject quoteObject = response.getJSONObject(i);
                                Quote quote = new Quote();

                               /* quote.setQuote(quoteObject.getString("quote"));
                                quote.setAuthor(quoteObject.getString("name"));*/  // Paulo

                                quote.setQuote(quoteObject.getString("text"));
                                quote.setAuthor(quoteObject.getString("from"));

                                Log.d("STUFFF::", quoteObject.getString("text"));

                                quoteArrayList.add(quote);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        if (null != callBack) callBack.processFinished(quoteArrayList);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }
}
