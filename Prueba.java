import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

public class Prueba {
	Grafo graph = new Grafo(5);
	Double infinito = Double.POSITIVE_INFINITY;
	ArrayList<ArrayList<Double>> prueba_array = new ArrayList<ArrayList<Double>>();
	ArrayList<ArrayList<String>> prueba_rutas = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> rutas = new ArrayList<ArrayList<String>>();
	@Test
	public void Creacion(){
		for(int i = 0; i<5;i++) {
			prueba_array.add(new ArrayList<Double>());
			prueba_rutas.add(new ArrayList<String>());
			for(int j = 0; j<5; j++) {
				if(i==j) {
					prueba_array.get(i).add(Double.NaN);
				}
				else {
					prueba_array.get(i).add(infinito);
				}
				
				prueba_rutas.get(i).add("");
			}
		}
		
		prueba_array.get(0).set(1, 30.0);
		prueba_array.get(1).set(2, 25.0);
		prueba_array.get(2).set(3, 15.0);
		prueba_array.get(2).set(4, 70.0);
		prueba_array.get(3).set(4, 90.0);
		prueba_array.get(4).set(0, 15.0);
		prueba_array.get(4).set(1, 40.0);
		
		assertEquals(prueba_array,graph.matriz);
		
		
		
	}
	
	
	public void Rutas() {
		prueba_rutas.get(0).set(1,"Mixco -> Antigua");
		prueba_rutas.get(1).set(1,"Antigua -> Escuintla");
		prueba_rutas.get(2).set(1,"Escuintla -> Santa-Lucia");
		prueba_rutas.get(2).set(1,"Escuintla -> Guatemala");
		prueba_rutas.get(3).set(1,"Santa-Lucia -> Guatemala");
		prueba_rutas.get(4).set(1,"Guatemala -> Mixco");
		prueba_rutas.get(4).set(1,"Guatemala -> Antigua");
		
		assertEquals(prueba_rutas,graph.routes(prueba_rutas));
		
	}

}
