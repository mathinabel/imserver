package bean;

import javax.persistence.*;

@Entity

public class Card {

    @Id

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    String name;

    int number;

    public Card(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public Card() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
