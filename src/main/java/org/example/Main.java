package org.example;




import lombok.Builder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
@Builder
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");

        EntityManager em = emf.createEntityManager();
        System.out.println("en marcha Alberto");

           try {
                // Persistir una nueva entidad Person

                em.getTransaction().begin();
                 Factura factura1 = new Factura();
                 factura1.setNumero(12);
                 factura1.setFecha("10/08/2020");

                 Domicilio dom = Domicilio.builder()
                         .nombreCalle("San Martin")
                         .nummero(1222)
                         .build();
                 Cliente cliente = Cliente.builder()

                         .nombre("Pablo")
                         .apellido("Muñoz")
                         .dni(1547157894)
                         .build();
                  cliente.setDomicilio(dom);
                  dom.setCliente(cliente);

                 factura1.setCliente(cliente);

                Categoria perecederos = Categoria.builder()
                        .denominacion("Perecederos")
                        .build();

                Categoria lacteos = Categoria.builder()
                        .denominacion("Lacteos")
                        .build();
                 Categoria limpieza= Categoria.builder()
                         .denominacion("Limpieza")
                         .build();


                Articulo art1 = Articulo.builder()
                        .cantidad(200)
                        .denominacion("Yogurt Ser Sabor Frutilla")
                        .precio(20)
                        .build();
                Articulo art2 = Articulo.builder()
                        .cantidad(300)
                        .denominacion("Detergente Magistral")
                        .precio(80)
                        .build();

                 art1.getCategorias().add(perecederos);
                 art1.getCategorias().add(lacteos);
                 lacteos.getArticulos().add(art1);
                 perecederos.getArticulos().add(art1);

                 art2.getCategorias().add(limpieza);
                 limpieza.getArticulos().add(art2);

                 DetalleFactura det1 = new DetalleFactura();

                    det1.setArticulo(art1);
                     det1.setCantidad(2);
                 det1.setSubtotal(40);

                 art1.getDetalleFactura().add(det1);
                 factura1.getDetalleFactura().add(det1);
                 det1.setFactura(factura1);

                 DetalleFactura det2 = new DetalleFactura();

                 det2.setArticulo(art2);
                 det2.setCantidad(1);
                 det2.setSubtotal(80);

                 art2.getDetalleFactura().add(det2);
                 factura1.getDetalleFactura().add(det2);
                 det2.setFactura(factura1);

                    factura1.setTotal(120);

                  em.persist(factura1);
                  em.getTransaction().commit();


            // Consultar y mostrar la entidad persistida
            //Cliente retrievedPerson = em.find(Cliente.class, Cliente.getID());
           // System.out.println("Retrieved Person: " + retrievedPerson.getName());

            }catch (Exception e){

                em.getTransaction().rollback();
                System.out.println(e.getMessage());
                System.out.println("No se pudo grabar la clase Persona");}

        // Cerrar el EntityManager y el EntityManagerFactory
             em.close();
             em.close();
    }
}
