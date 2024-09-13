package org.example;

import entidades.Articulo;
import entidades.Cliente;
import entidades.Domicilio;
import entidades.DetalleFactura;
import entidades.Factura;
import entidades.Categoria;
import org.dom4j.tree.DefaultText;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class
Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();


        try {
            entityManager.getTransaction().begin();

            Domicilio domicilio1 = Domicilio.builder()
                    .nombreCalle("Ladislao Segura")
                    .numeroC(351)
                    .build();
            Cliente cliente1 = Cliente.builder()
                    .apellido("Ranzuglia")
                    .nombre("Agustina")
                    .dni(48734563)
                    .build();
            cliente1.setDomicilio(domicilio1);
            domicilio1.setCliente(cliente1);
            Categoria perecederos = Categoria.builder()
                    .denominacion("Perecederos")
                    .build();
            Categoria limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();
            Categoria lacteos = Categoria.builder()
                    .denominacion("Lacteos ")
                    .build();
            Articulo articulo1 = Articulo.builder()
                    .denominacion("Detergente")
                    .cantidad(70)
                    .precio(2800)
                    .build();
            articulo1.getCategorias().add(limpieza);
            limpieza.getArticulos().add(articulo1);
            Articulo articulo2 = Articulo.builder()
                    .denominacion("Queso de rallar")
                    .precio(3000)
                    .build();
            articulo2.getCategorias().add(lacteos);
            lacteos.getArticulos().add(articulo2);
            Factura factura1 = Factura.builder()
                    .numero(1)
                    .fecha("26/02/24")
                    .cliente(cliente1)
                    .build();
            DetalleFactura detalle1 = DetalleFactura.builder()
                    .articulo(articulo1)
                    .cantidad(2)
                    .subtotal(5600)
                    .build();
            DetalleFactura detalle2 = DetalleFactura.builder()
                    .articulo(articulo2)
                    .cantidad(1)
                    .subtotal(3000)
                    .build();
            detalle2.setFactura(factura1);
            detalle1.setFactura(factura1);
            factura1.getDetalle().add(detalle1);
            factura1.getDetalle().add(detalle2);
            factura1.setTotal(8600);
            entityManager.persist(factura1);

            Domicilio domicilio2 = Domicilio.builder()
                    .nombreCalle("Rioja")
                    .numeroC(267)
                    .build();
            Cliente cliente2 = Cliente.builder()
                    .apellido("Hugas")
                    .nombre("Ramiro")
                    .dni(43895612)
                    .build();
            cliente2.setDomicilio(domicilio2);
            domicilio2.setCliente(cliente2);

            Articulo articulo3 = Articulo.builder()
                    .denominacion("Yogurt de frutilla")
                    .cantidad(50)
                    .precio(1000)
                    .build();
            articulo3.getCategorias().add(lacteos);
            lacteos.getArticulos().add(articulo3);

            Articulo articulo4 = Articulo.builder()
                    .denominacion("Lavandina Ayudin")
                    .precio(2500)
                    .build();
            articulo4.getCategorias().add(limpieza);
            limpieza.getArticulos().add(articulo4);

            Factura factura2 = Factura.builder()
                    .numero(2)
                    .fecha("04/04/24")
                    .cliente(cliente2)
                    .build();

            DetalleFactura detalle3 = DetalleFactura.builder()
                    .articulo(articulo3)
                    .cantidad(3)
                    .subtotal(3000)
                    .build();
            DetalleFactura detalle4 = DetalleFactura.builder()
                    .articulo(articulo4)
                    .cantidad(2)
                    .subtotal(5000)
                    .build();

            detalle3.setFactura(factura2);
            detalle4.setFactura(factura2);
            factura2.getDetalle().add(detalle3);
            factura2.getDetalle().add(detalle4);
            factura2.setTotal(8000);

            entityManager.persist(factura2);

            Domicilio domicilio3 = Domicilio.builder()
                    .nombreCalle("Salta")
                    .numeroC(1740)
                    .build();
            Cliente cliente3 = Cliente.builder()
                    .apellido("Rodriguez")
                    .nombre("Sol")
                    .dni(45435231)
                    .build();
            cliente3.setDomicilio(domicilio3);
            domicilio3.setCliente(cliente3);

            Articulo articulo5 = Articulo.builder()
                    .denominacion("Leche")
                    .cantidad(90)
                    .precio(2000)
                    .build();
            articulo5.getCategorias().add(lacteos);
            lacteos.getArticulos().add(articulo5);

            Articulo articulo6 = Articulo.builder()
                    .denominacion("Brillapisos")
                    .precio(3000)
                    .build();
            articulo6.getCategorias().add(limpieza);
            limpieza.getArticulos().add(articulo6);

            Factura factura3 = Factura.builder()
                    .numero(3)
                    .fecha("15/07/24")
                    .cliente(cliente3)
                    .build();

            DetalleFactura detalle5 = DetalleFactura.builder()
                    .articulo(articulo5)
                    .cantidad(2)
                    .subtotal(4000)
                    .build();
            DetalleFactura detalle6 = DetalleFactura.builder()
                    .articulo(articulo6)
                    .cantidad(1)
                    .subtotal(3000)
                    .build();

            detalle5.setFactura(factura3);
            detalle6.setFactura(factura3);
            factura3.getDetalle().add(detalle5);
            factura3.getDetalle().add(detalle6);
            factura3.setTotal(7000);

            entityManager.persist(factura3);
            entityManager.flush();
            entityManager.getTransaction().commit();



        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase");}

        entityManager.close();
        entityManagerFactory.close();
    }
}