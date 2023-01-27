package eti.pg.lab.cover.dto;

import eti.pg.lab.author.dto.GetAuthorsResponse;
import eti.pg.lab.author.entity.Author;
import eti.pg.lab.cover.entity.Cover;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCoversResponse {
    @Getter
    @Setter
    @Builder
    //@NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Authority{
        private int id;
        private String desc;
        private String author;
        //private String filename;
    }
    @Singular
    private List<GetCoversResponse.Authority> covers;

    public static Function<Collection<Cover>, GetCoversResponse> entityToDtoMapper(){
        return covers -> {
            GetCoversResponse.GetCoversResponseBuilder response = GetCoversResponse.builder();

            covers.stream()
                    .map(cover -> Authority.builder()
                            .desc(cover.getDescription())
                            .id(cover.getId())
                            .author(cover.getAuthor())
                            //.filename(cover.getFilename())
                            .build())
                    .forEach(response::cover);
            return response.build();
        };
    }
}
