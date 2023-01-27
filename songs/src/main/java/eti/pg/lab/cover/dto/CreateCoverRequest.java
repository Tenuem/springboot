package eti.pg.lab.cover.dto;

import eti.pg.lab.cover.entity.Cover;
import lombok.*;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCoverRequest {

        private String desc;
        private int id;
        private String author;
        //private String filename;

        public CreateCoverRequest(String desc, int id, String author){
                this.desc = desc;
                this.id = id;
                this.author = author;
        }

        public static Function<eti.pg.lab.cover.dto.CreateCoverRequest, Cover> dtoToEntityMapper(){
            return request -> Cover.builder()
                    .id(request.getId())
                    .author(request.getAuthor())
                    .description(request.getDesc())
                    //.filename(request.getFilename())
                    .build();
        }
}