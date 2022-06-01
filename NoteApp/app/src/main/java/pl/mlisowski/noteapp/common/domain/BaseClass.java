package pl.mlisowski.noteapp.common.domain;

import androidx.room.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseClass {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String uuid = UUID.randomUUID().toString();

}
