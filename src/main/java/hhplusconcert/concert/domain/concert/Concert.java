package hhplusconcert.concert.domain.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Concert {

    private Long concertId;
    private String title;
    private String host;

    @Builder
    public Concert(Long concertId, String title, String host) {
        this.concertId = concertId;
        this.title = title;
        this.host = host;
    }

}
