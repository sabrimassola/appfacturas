package com.sabrimassola.appfacturas;
import com.sabrimassola.appfacturas.modelo.*;

import java.util.Scanner;

public class EjemploFactura {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setNif("5555-5");
        cliente.setNombre("Irma");

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese detalle de factura: ");
        Factura factura = new Factura(sc.nextLine(),cliente);

        Producto producto;

        System.out.println();
        for(int i = 0 ; i < 2 ; i++){
            producto = new Producto();
            System.out.print("Ingrese el producto n: " + producto.getCodigo() + ": ");
            producto.setNombre(sc.nextLine());

            System.out.print("Ingrese el precio del producto: ");
            producto.setPrecio(sc.nextFloat());

            System.out.print("Ingrese la cantidad del producto: ");

            factura.addItemFactura(new ItemFactura(sc.nextInt(), producto));

            System.out.println();
            sc.nextLine();

        }
        System.out.println(factura);
    }
}
