package bean;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String name;
    int number;

    public Book() {
    }

    public Book(Long id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public Long getBook_id() {
        return id;
    }

    public void setBook_id(Long id) {
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
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}