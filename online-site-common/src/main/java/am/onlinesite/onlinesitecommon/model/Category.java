package am.onlinesite.onlinesitecommon.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    private Type type;

    @OneToMany(mappedBy="category")
    private List<Product> products = new ArrayList<>();

    public enum Type {
        BOOK,CLOTHES,FOR_HOME,SPORT,IT,SCHOOL
    }
}