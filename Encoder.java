import java.net.URL; 
import java.net.URLEncoder;
import java.net.MalformedURLException; 
import java.io.UnsupportedEncodingException;

public class Encoder{
 public static void main(String[] args){
  URL url = null; 
  try{
   url = new URL("http://search.barnesandnoble.com/booksearch/results.asp?WRD=Java&userid=rf1xt37CXH&cds2Pid=946");		
  }catch(MalformedURLException mue){
   System.err.println(mue); 
  }
  System.out.println(url + "\n"); 
  try{
   String encodedurl = URLEncoder.encode(url.toString(),"UTF-8"); 
   System.out.println(encodedurl);
  }catch(UnsupportedEncodingException uee){
   System.err.println(uee);
  }
 }
}
