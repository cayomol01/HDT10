
import java.util.*;


class Driver {
    public static void main(String args[])throws Exception{
        Scanner scan = new Scanner(System.in);
        String lugares;
        String cambio;
        int opcion =0;
        int n = 0;

        System.out.println("Bienvenido al programa para la ruta más corta");
        System.out.println("Por favor especifique el tamaño del grafo con el que se trabajará");
        n = scan.nextInt();
        Grafo graph = new Grafo(n);
        graph.leer_archivo();
        System.out.println("\nBueno, ahora se le mostrará un menú para ver que es lo que desea realizar");
        
        while(opcion!= 3){
            System.out.println("\nMenu:");
            System.out.println("-1. Distancia entre dos lugares especificados");
            System.out.println("-2. Agregar una ruta extra");
            System.out.println("-3. Salir");
            opcion = scan.nextInt();

            if(opcion==1){
                System.out.println("Por favor ingrese los lugares de la ruta que desea seguidos de una coma. Ej. Guatemala,Mixco");
                scan.nextLine();
                lugares = scan.nextLine();
                //Pregunta los lugares
                //Muestra el recorrido que se tomo
                graph.Mostrar_recorrido(lugares);
            }
            else if(opcion==2){
                //Deja agregar una ruta nueva dentro de la cantidad de nodos ya puestos
                System.out.println("Por favor ingrese los lugares con el peso de la ruta separado por espacio todo. Ej. Guatemala Mixco 30");
                scan.nextLine();
                cambio = scan.nextLine();
                graph.Agregar(cambio);
            }
        }

    
    }
}
