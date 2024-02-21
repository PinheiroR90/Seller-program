package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);
    List<Sale> sales = new ArrayList<>();
    System.out.print("Entre o caminho do arquivo: ");
    String path = sc.nextLine();

    try(BufferedReader br = new BufferedReader(new FileReader(path))){
      String line  = br.readLine();
      while(line != null){
        String[] fields = line.split(",");
        sales.add(new Sale(Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),fields[2],Integer.parseInt(fields[3]),Double.parseDouble(fields[4])));
        line = br.readLine();
      }
     Set<String> namesSeller = new HashSet<>();
      for(Sale s: sales){
        namesSeller.add(s.getSeller());
      }
      double sum=0.0;
      for(String name:namesSeller){
        sum = sales.stream().filter(i-> i.getSeller().equals(name)).map(e ->e.getTotal()).reduce(0.0, Double::sum);
        System.out.println(name + " - R$ "+ String.format("%.2f",sum));
      }

    }catch(IOException e){
      System.out.println(e + " O sistema não pode encontrar o arquivo especificado");
    }
    finally {
      sc.close();
    }
  }
}
