import java.io.*;

public class RASTER_Triangles{

public static void main(String[] param) throws IOException{
InputStreamReader m = new InputStreamReader(System.in);
BufferedReader n = new BufferedReader(m);
System.out.print("Give me the size of each triangle : ");
int size = Integer.parseInt(n.readLine());
System.out.print("The number of triangles you want to output in each row : ");
int noOfTriangles = Integer.parseInt(n.readLine());
System.out.print("The number of rows of triangles you want to output : ");
int noOfRows = Integer.parseInt(n.readLine());
raster(size, noOfTriangles, noOfRows);
}

public static void raster(int a, int b, int c){
String result = "";

//LOOPS
for(int Rows=1; Rows<=c; Rows++){//No of rows of triangles to output in each row.
for(int triangles=1; triangles<=b; triangles++){//No of triangles in each row.//DON'T KNOW HOW TO MAKE SEVERAL TRIANGLES IN 1 ROW..
for(int Size =1; Size<=a; Size++){//Makes a triangle.
for(int i=1; i<=Size; i++){
result = result + "*";
}
result = result + "\n";
}
}
result = result + "\n";
}


System.out.print(result);
}
}