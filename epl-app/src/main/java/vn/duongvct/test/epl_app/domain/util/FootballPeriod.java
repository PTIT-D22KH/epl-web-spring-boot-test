package vn.duongvct.test.epl_app.domain.util;

import java.time.LocalTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FootballPeriod {
    private LocalTime startDate;
    private LocalTime endDate;

    @Enumerated(EnumType.STRING)
    private Detail detail;

    @Getter
    public static enum Detail {
        MAIN_CLUB, 
        LOAN_CLUB
    }
}
