package entidades;


import javax.persistence.*;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.envers.Audited;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "detalleFactura")
@Audited
public class DetalleFactura implements Serializable {

    @Column(name = "cantidad")
    private int cantidad;
    @Column(name ="subtotal")
    private int subtotal;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="fk_articulo")
    private Articulo articulo;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="fk_factura")
    private Factura factura ;

}

