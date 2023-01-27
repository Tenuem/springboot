package eti.pg.lab.cover.dto;


import eti.pg.lab.cover.entity.Cover;
import lombok.*;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCoverResponse {

    private String desc;
    private int id;
    private String author;
    //private String filename;

    public static Function<Cover, GetCoverResponse> entityToDtoMapper(){
        return cover -> GetCoverResponse.builder()
                .id(cover.getId())
                .desc(cover.getDescription())
                .author(cover.getAuthor())
                //.filename(cover.getFilename())
                .build();
    }
}
