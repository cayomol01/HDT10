import java.util.HashMap;

import javax.naming.NameNotFoundException;

import java.util.*;
import java.io.*;

class Grafo {
    String archivo;
    ArrayList<ArrayList<Double>> matriz = new  ArrayList<ArrayList<Double>>();
    ArrayList<ArrayList<Double>> matrizO = new  ArrayList<ArrayList<Double>>();
    ArrayList<ArrayList<String>> rutas = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> rutasO = new ArrayList<ArrayList<String>>();
    HashMap<String,Integer> lpos = new HashMap<String,Integer>();
    ArrayList<String> lugar = new ArrayList<String>();
    String linea = null;
    String[] lugares;
    Scanner leedor1 = null;
    Scanner scan = new Scanner(System.in);
    String temp;
    int index_1;
    int index_2;
    Double index_3;

    boolean fileOpened;
    int cont = 0;
    int contador = 0;
    int size;

    //n = Cantidad de lugares
    public Grafo(int n){
        size = n;
        //inicializa todas las matrices usadas y pone valores necesarios para realizar el algoritmo Floyd
        for(int i = 0; i<n; i++){
            matriz.add(new ArrayList<Double>());
            rutas.add(new ArrayList<String>());
            for(int j = 0; j<n; j ++){
                if(j==i){
                    matriz.get(i).add(Double.NaN);
                }
                else{
                    matriz.get(i).add(Double.POSITIVE_INFINITY);
                }
                
                rutas.get(i).add("");
            }



        }
       
    }

    //Lee el archivo y pone los valores iniciales para las rutas 
    public void leer_archivo()throws Exception{
        String arch1 = new File("guategrafo.txt").getAbsolutePath();
        File archivo_diccionario = new File(arch1);
        try{
            leedor1 = new Scanner(archivo_diccionario);
            fileOpened = true;
        }catch(FileNotFoundException e){
            System.out.println("--- File Not Found! ---");
            fileOpened = false;
        }
        if (fileOpened){
            while(leedor1.hasNextLine()){
                linea = leedor1.nextLine();
                lugares = linea.split(" ");
                if(!lpos.containsKey(lugares[0]) || !lpos.containsKey(lugares[1])){
                    if(!lpos.containsKey(lugares[0])){
                        contador++;
                        //Pone los valores en un diccionario para saber su posicion en la matriz
                        lpos.put(lugares[0],contador);
                        lugar.add(lugares[0]);

                    }
                    if(!lpos.containsKey(lugares[1])){
                        contador ++;
                        lpos.put(lugares[1],contador);
                        lugar.add(lugares[1]);
                    }
                }
                //Mete el valor de la ruta en la matriz de pesos de rutas
                matriz.get(lpos.get(lugares[0])-1).set(lpos.get(lugares[1])-1,Double.parseDouble(lugares[2]));
            }
        }
        //Copia la matriz ya llenada para crear otra matriz que sea ordenada
        matrizO.addAll(matriz);
        //Mete las rutas principales a una matriz
        rutas = routes(rutas);

    }

    public ArrayList<ArrayList<String>> routes(ArrayList<ArrayList<String>> rutas){
        //Recorre la matriz y mete la ruta recorrida en la matriz siempre y cuando no tenga infinito
        for(int i = 0; i<size;i++){
            for(int j = 0; j<size; j++){
                if(!matriz.get(i).get(j).equals(Double.POSITIVE_INFINITY)){
                    rutas.get(i).set(j, lugar.get(i) + " -> " + lugar.get(j));
                }
            }
        }
        //Se copia la matriz de rutas tomadas para luego ordenarla
        rutasO.addAll(rutas);
        return rutas;
    }

    //Muestra la matriz sin todas las rutas
    public void mostrar(){
        System.out.println(lpos.toString());
        for(int i = 0; i<matriz.size();i++){
            System.out.println(matriz.get(i).toString());
        }
    }

    //Muestra la matriz con todas las rutas más cortas
    public void mostrarO(){
        Floyd();
        for(int i = 0; i<matrizO.size();i++){
            System.out.println(matrizO.get(i).toString());
        }
    }

    //Se realiza el algoritmo de Floyd a un grafo dado.
    public void Floyd(){
        //Recorre cada linea y columna donde i y j sean iguales es decir columna 1 fila 1
        for(int k = 0; k<size;k++){
            for(int i = 0; i <size;i++){
                for(int j = 0; j<size; j++){
                    temp = "";
                    //Se verifica que ruta es la más corta
                    if (matrizO.get(i).get(k) + matrizO.get(k).get(j) < matrizO.get(i).get(j)){
                        //Si si es la más corta entonces se suman las rutas necesarias para llegar al destino
                        matrizO.get(i).set(j, matrizO.get(i).get(k) + matrizO.get(k).get(j));
                        //
                        temp += rutas.get(i).get(k) + " -> " + rutas.get(k).get(j);
                        //Se agrega la ruta generada a rutas.
                        rutas.get(i).set(j, temp);
                    }
                    
                }
            }

        }
    }

    //Muestra la ruta de los lugares puestos
    public void Mostrar_recorrido(String input){
        Floyd();
        System.out.println("\nEste fue el recorrido que se tomo para llegar al lugar que indicó: \n");
        System.out.println(rutas.get(lugar.indexOf(input.split(",")[0])).get(lugar.indexOf(input.split(",")[1])));
        System.out.println("Esta es la distancia total: " +  matrizO.get(lugar.indexOf(input.split(",")[0])).get(lugar.indexOf(input.split(",")[1])));

    }

    //Borra las rutas puestas
    //Vuelve a crear la matriz de rutas ordenadas
    //Se agrega la ruta que se deseaba
    //Se vuelven a crear las rutas
    public void Agregar(String input){
        index_1 = lugar.indexOf(input.split(" ")[0]);
        index_2 = lugar.indexOf(input.split(" ")[1]);
        index_3 = Double.parseDouble(input.split(" ")[2]);
        rutasO.clear();
        matrizO.clear();
        matriz.get(index_1).set(index_2, index_3);
        rutas = routes(rutas);
        matrizO.addAll(matriz);


    }
}
