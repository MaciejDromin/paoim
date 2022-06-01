package pl.mlisowski.noteapp.notes.domain;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import lombok.*;
import pl.mlisowski.noteapp.categories.domain.Categories;
import pl.mlisowski.noteapp.common.domain.BaseClass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Note extends BaseClass implements Parcelable {

    private String title;
    private String text;
    private Long categoryId;

    protected Note(Parcel in) {
        title = in.readString();
        text = in.readString();
        if (in.readByte() == 0) {
            categoryId = null;
        } else {
            categoryId = in.readLong();
        }
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(text);
        if (categoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(categoryId);
        }
    }
}
