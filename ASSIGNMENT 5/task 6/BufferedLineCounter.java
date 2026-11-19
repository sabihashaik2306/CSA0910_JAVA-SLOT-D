import java.io.*;import java.nio.file.*;
public class BufferedLineCounter{
 static long count(Path p,boolean buffered)throws IOException{
  long lines=0;try(InputStream raw=Files.newInputStream(p);
   InputStream in=buffered?new BufferedInputStream(raw,65536):raw){
   if(buffered){byte[] b=new byte[65536];int n;while((n=in.read(b))!=-1)for(int i=0;i<n;i++)if(b[i]=='\n')lines++;}
   else{int b;while((b=in.read())!=-1)if(b=='\n')lines++;}
  }return lines;
 }
 public static void main(String[]a)throws Exception{
  Path p=Files.createTempFile("attendance",".txt");
  try(BufferedWriter w=Files.newBufferedWriter(p)){for(int i=0;i<100000;i++)w.write("192424264,Present\n");}
  long t=System.nanoTime(),x=count(p,false),t1=System.nanoTime(),y=count(p,true),t2=System.nanoTime();
  System.out.println("File bytes: "+Files.size(p));
  System.out.println("Unbuffered lines: "+x+" timeMs="+((t1-t)/1e6));
  System.out.println("Buffered lines: "+y+" timeMs="+((t2-t1)/1e6));
  System.out.println("Counts match: "+(x==y));Files.delete(p);
 }
}
