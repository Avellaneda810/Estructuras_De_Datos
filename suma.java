package estructura_de_datos;
import java.util.Scanner;
public class suma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int arreglo[] = {10,5,3,7,11,1};
        int longi = arreglo.length-1;
        System.out.println("la sumatoria del arreglo es: "+sumaArr(arreglo, longi));
        System.out.println("Su promedio es: "+promedio(arreglo, longi));
    }
    
    public static int sumaArr (int [] arr, int longitud){
        //realiza la sumatoria de los numeros contenidos en un arreglo 
        int suma;
        if (longitud==0){
            suma= arr[longitud];
        }else{
            suma = arr[longitud] + sumaArr(arr,longitud);
        }
        return suma;
    }
    
    public static int promedio(int[]arr, int longitud){
        int divisor=longitud+1;
        int prom = sumaArr(arr, longitud) / divisor;
        return prom;
    }
}
