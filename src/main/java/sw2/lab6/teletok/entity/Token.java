package sw2.lab6.teletok.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(max = 45, message = "Debe contener no mas de 45 caracteres")
    @Column(nullable = false)
    @NotBlank
    private String code;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userid;
}
