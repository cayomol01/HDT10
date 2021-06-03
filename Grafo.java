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
                        lpos.put(lugares[0],contador);
                        lugar.add(lugares[0]);

                    }
                    if(!lpos.containsKey(lugares[1])){
                        contador ++;
                        lpos.put(lugares[1],contador);
                        lugar.add(lugares[1]);
                    }
                }
                matriz.get(lpos.get(lugares[0])-1).set(lpos.get(lugares[1])-1,Double.parseDouble(lugares[2]));
            }
        }
        matrizO.addAll(matriz);
        routes();

    }

    public void routes(){
        for(int i = 0; i<size;i++){
            for(int j = 0; j<size; j++){
                if(!matriz.get(i).get(j).equals(Double.POSITIVE_INFINITY)){
                    rutas.get(i).set(j, lugar.get(i) + " -> " + lugar.get(j));
                }
            }
        }
        rutasO.addAll(rutas);
    }

    public void mostrar(){
        System.out.println(lpos.toString());
        for(int i = 0; i<matriz.size();i++){
            System.out.println(matriz.get(i).toString());
        }
    }

    public void mostrarO(){
        Floyd(matrizO);
        for(int i = 0; i<matrizO.size();i++){
            System.out.println(matrizO.get(i).toString());
        }
        for(int i = 0; i<matrizO.size();i++){
            System.out.println(rutas.get(i).toString());
        }
    }


    public void Floyd(ArrayList<ArrayList<Double>> grafo){
        for(int k = 0; k<size;k++){
            for(int i = 0; i <size;i++){
                for(int j = 0; j<size; j++){
                    temp = "";
                    if (matrizO.get(i).get(k) + matrizO.get(k).get(j) < matrizO.get(i).get(j)){
                        matrizO.get(i).set(j, matrizO.get(i).get(k) + matrizO.get(k).get(j));
                        temp += rutas.get(i).get(k) + " -> " + rutas.get(k).get(j);
                        rutas.get(i).set(j, temp);
                    }
                    
                }
            }

        }
    }

    public void Mostrar_recorrido(String input){
        Floyd(matrizO);
        System.out.println("\nEste fue el recorrido que se tomo para llegar al lugar que indicó: \n");
        System.out.println(rutas.get(lugar.indexOf(input.split(",")[0])).get(lugar.indexOf(input.split(",")[1])));
        System.out.println("Esta es la distancia total: " +  matrizO.get(lugar.indexOf(input.split(",")[0])).get(lugar.indexOf(input.split(",")[1])));

    }

    public void Agregar(String input){
        index_1 = lugar.indexOf(input.split(" ")[0]);
        index_2 = lugar.indexOf(input.split(" ")[1]);
        index_3 = Double.parseDouble(input.split(" ")[2]);
        rutasO.clear();
        matrizO.clear();
        matriz.get(index_1).set(index_2, index_3);
        routes();
        matrizO.addAll(matriz);


    }
}
