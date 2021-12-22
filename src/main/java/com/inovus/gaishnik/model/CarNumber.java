package com.inovus.gaishnik.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "carnumber")
public class CarNumber {

    @Id
    String fullNumber;

    Integer number;
    Character firstCharacter;
    Character secondCharacter;
    Character thirdCharacter;

    @Override
    public String toString() {
        StringBuilder fullNumber = new StringBuilder(number.toString());
        if (number < 10) {
            fullNumber.insert(0, "00");
        }
        else if (number < 100) {
            fullNumber.insert(0, "0");
        }

        fullNumber.insert(0, firstCharacter).append(secondCharacter).append(thirdCharacter).append(" 116 RUS");
        return fullNumber.toString();
    }
}
