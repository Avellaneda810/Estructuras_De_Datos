package estructura_de_datos;
/**
 *
 * @author Angel
 */
import java.util.Scanner;
public class explotar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, bomba;
        System.out.println("Ingrese el numero a explotar");
        num= sc.nextInt();
        System.out.println("Ingrese el numero bomba");
        bomba=sc.nextInt();
        System.out.println("La secuencia de numeros explotados es: "+explotar(num, bomba));
    }
    
    public static String explotar (int n, int b ){
        /* metodo recursivo explotar, dado un numero n y un numero bomba b, imprime los pedazos que quedan al explotar n usando b*/
        int n1, n2;
        String numExplosivos ="";
        if (n>b){
            n1=n/b;
            n2=n-(n/b);
            if (n1>b){
                numExplosivos=numExplosivos+explotar(n1,b);
            }else{
                numExplosivos=numExplosivos+" "+n1;
            }
            if(n2>b){
                numExplosivos=numExplosivos+explotar(n2,b);
            }else{
                numExplosivos=numExplosivos+" "+n2;
            }
        }
        return numExplosivos;
    }
}
