package ro.ase.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class CafeaAdapter extends BaseAdapter {

    private List<Cafea> cafele = null;
    private Context ctx;
    private int resursaLayout;

    public CafeaAdapter(List<Cafea> cafele, Context ctx, int resursaLayout) {
        this.cafele = cafele;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }



    @Override
    public int getCount() {
        return cafele.size();
    }

    @Override
    public Object getItem(int i) {
        return cafele.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, parent, false);

        TextView denumireTV = v.findViewById(R.id.denumireTV);
        TextView pretTV = v.findViewById(R.id.pretTV);
        TextView tipTV = v.findViewById(R.id.tipTV);
        TextView marimeTV = v.findViewById(R.id.marimeTV);
        RatingBar ratingRB = v.findViewById(R.id.ratingRB);

        Cafea cafea = (Cafea) getItem(position);

        denumireTV.setText(cafea.getDenumire());
        pretTV.setText(cafea.getPret() + " lei");
        tipTV.setText(cafea.getTip());
        marimeTV.setText(cafea.getMarime());
        ratingRB.setRating(cafea.getRating());

        return v;
    }

}
