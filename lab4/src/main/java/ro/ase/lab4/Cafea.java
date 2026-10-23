package ro.ase.lab4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Cafea implements Parcelable {

    private String denumire;
    private double pret;
    private String tip;
    private String marime;
    private boolean cuZahar;
    private boolean laPachet;
    private float rating;

    public Cafea(String denumire, double pret, String tip, String marime,
                 boolean cuZahar, boolean laPachet, float rating) {
        this.denumire = denumire;
        this.pret = pret;
        this.tip = tip;
        this.marime = marime;
        this.cuZahar = cuZahar;
        this.laPachet = laPachet;
        this.rating = rating;
    }

    protected Cafea(Parcel in) {
        denumire = in.readString();
        pret = in.readDouble();
        tip = in.readString();
        marime = in.readString();
        cuZahar = in.readByte() != 0;
        laPachet = in.readByte() != 0;
        rating = in.readFloat();
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeDouble(pret);
        dest.writeString(tip);
        dest.writeString(marime);
        dest.writeByte((byte) (cuZahar ? 1 : 0));
        dest.writeByte((byte) (laPachet ? 1 : 0));
        dest.writeFloat(rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cafea> CREATOR = new Creator<Cafea>() {
        @Override
        public Cafea createFromParcel(Parcel in) {
            return new Cafea(in);
        }

        @Override
        public Cafea[] newArray(int size) {
            return new Cafea[size];
        }
    };

    public String getDenumire() { return denumire; }
    public double getPret() { return pret; }
    public String getTip() { return tip; }
    public String getMarime() { return marime; }
    public boolean isCuZahar() { return cuZahar; }
    public boolean isLaPachet() { return laPachet; }
    public float getRating() { return rating; }

    @Override
    public String toString() {
        return "Cafea: " + denumire +
                "\nPret: " + pret + " lei" +
                "\nTip: " + tip +
                "\nMarime: " + marime +
                "\nCu zahar: " + (cuZahar ? "da" : "nu") +
                "\nLa pachet: " + (laPachet ? "da" : "nu") +
                "\nRating: " + rating + "/5";
    }
}