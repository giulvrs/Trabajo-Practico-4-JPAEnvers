package entidades;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "factura")
@Audited
public class Factura implements Serializable {

    @Column(name = "fecha")
    private String fecha;
    @Column(name = "número", unique = true)
    private int numero;
    @Column(name = "total")
    private int total;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL,orphanRemoval = true)
    @Builder.Default
    private List<DetalleFactura> detalle = new ArrayList<DetalleFactura>();

}

