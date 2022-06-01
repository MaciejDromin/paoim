package pl.mlisowski.noteapp.categories.domain;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.Index;
import lombok.*;
import pl.mlisowski.noteapp.common.domain.BaseClass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(indices = {@Index(value = {"id", "name"}, unique = true)})
@Builder
public class Categories extends BaseClass implements Parcelable {

    private String name;

    protected Categories(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Categories> CREATOR = new Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel in) {
            return new Categories(in);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
