package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mysql.fabric.xmlrpc.base.Data;

import model.persistencia.LocalDao;
import model.persistencia.UsuarioDao;



public class SlopeOne { 
	LocalDao localDao = new LocalDao();
 public static void main(String args[]) 
 {  
 } 
 
 public String  getObjetos(int idUsuario, String[] locaisClassificados, String[] notas , String[] locaisTags) {
	 try {		 
		 UsuarioDao userDao = new UsuarioDao();
		 String query = null;
		 String[]      mAllItems = locaisTags;
		 Map<String, Map<String, Double>> data = new HashMap<>(); 
		 
		 List<HashMap<String,Double>> usuarios = new ArrayList<HashMap<String,Double>>();
		 
		 ArrayList<ArrayList<Integer>> notasOutrosUsuarios = userDao.getNotaOutrosUsuarios(idUsuario);
		 ArrayList idsUsuarios = notasOutrosUsuarios.get(0); 
		 ArrayList idsLugares = notasOutrosUsuarios.get(1);
		 ArrayList notasUsuarios = notasOutrosUsuarios.get(2);
		 
		 ArrayList idsUsuariosUnicos = new ArrayList();
		 for (int c=0;c<idsUsuarios.size();c++) {
			 int idU = (int) idsUsuarios.get(c);
			 if (! idsUsuariosUnicos.contains(idU)) {
				 idsUsuariosUnicos.add(idU);
			 }			 
		 }
		 
		 //
		 int qtdUsuarios = idsUsuariosUnicos.size() ;
		 int qtdLocais = locaisTags.length;
		 
		 for (int i = 0; i <qtdUsuarios; i++) {
			 for(int l =0; l<qtdLocais; l++) {
				 int notaU = (int) notasUsuarios.get(l);
				 
				 double nota = new Double( notaU).doubleValue();
				 String idLugar = idsLugares.get(l).toString();
				 
				 usuarios.add(new HashMap<String,Double>());
				 usuarios.get(i).put(idLugar,nota);
				 
			 	}
			 String idUsuarioOutro = idsUsuarios.get(i).toString();
			 data.put(idUsuarioOutro, usuarios.get(i));
			 }
		 
		 SlopeOne so = new SlopeOne(data);
		 HashMap<String, Double> user = new HashMap<>(); 
		 for (int x = 0; x < notas.length; x++) {
			 double nota = Double.parseDouble(notas[x]); 
			 user.put(locaisClassificados[x],nota);
		 }
		  ArrayList result = SlopeOne.print(so.predict(user)); 
		  query = resultadoRecomendacao(result, idUsuario);
		  return query;
		 
	 }catch (Exception e){
		 System.out.println(e.getMessage());
		 return "false";
	 }
		
		 
 }
 
 Map<String, Map<String, Double>> mData; 
 Map<String, Map<String, Double>> diffMatrix; 
 Map<String, Map<String, Integer>> freqMatrix; 
 
 static String[]      mAllItems; 
 
 public SlopeOne(Map<String, Map<String, Double>> data) 
 { 
  mData = data; 
  buildDiffMatrix(); 
 } 
 
 public SlopeOne() {
	// TODO Auto-generated constructor stub
}

/**
 * Based on existing data, and using weights, 
 * try to predict all missing ratings. 
 * The trick to make this more scalable is to consider 
 * only mDiffMatrix entries having a large  (>1) mFreqMatrix 
 * entry. 
 * 
 * It will output the prediction 0 when no prediction is possible. 
 */ 
 public Map<String, Double> predict(Map<String, Double> user) 
 { 
  HashMap<String, Double> predictions = new HashMap<>(); 
  HashMap<String, Integer> frequencies = new HashMap<>(); 
  for (String j : diffMatrix.keySet()) 
  { 
   frequencies.put(j, 0); 
   predictions.put(j, 0.0); 
  } 
  for (String j : user.keySet()) 
  { 
   for (String k : diffMatrix.keySet()) 
   { 
    try 
    { 
     Double newval = (diffMatrix.get(k).get(j) + user.get(j)) * freqMatrix.get(k).get(j).intValue(); 
     predictions.put(k, predictions.get(k) + newval); 
     frequencies.put(k, frequencies.get(k) + freqMatrix.get(k).get(j).intValue()); 
    } catch (NullPointerException e) 
    {} 
   } 
  } 
  HashMap<String, Double> cleanpredictions = new HashMap<>(); 
  for (String j : predictions.keySet()) 
  { 
   if (frequencies.get(j) > 0) 
   { 
    cleanpredictions.put(j, predictions.get(j) / frequencies.get(j).intValue()); 
   } 
  } 
  for (String j : user.keySet()) 
  { 
   cleanpredictions.put(j, user.get(j)); 
  } 
  return cleanpredictions; 
 } 
 
 /**
 * Based on existing data, and not using weights, 
 * try to predict all missing ratings. 
 * The trick to make this more scalable is to consider 
 * only mDiffMatrix entries having a large  (>1) mFreqMatrix 
 * entry. 
 */ 
 public Map<String, Double> weightlesspredict(Map<String, Double> user) 
 { 
  HashMap<String, Double> predictions = new HashMap<>(); 
  HashMap<String, Integer> frequencies = new HashMap<>(); 
  for (String j : diffMatrix.keySet()) 
  { 
   predictions.put(j, 0.0); 
   frequencies.put(j, 0); 
  } 
  for (String j : user.keySet()) 
  { 
   for (String k : diffMatrix.keySet()) 
   { 
    //System.out.println("Average diff between "+j+" and "+ k + " is "+mDiffMatrix.get(k).get(j).floatValue()+" with n = "+mFreqMatrix.get(k).get(j).floatValue()); 
    Double newval = (diffMatrix.get(k).get(j) + user.get(j)); 
    predictions.put(k, predictions.get(k) + newval); 
   } 
  } 
  for (String j : predictions.keySet()) 
  { 
   predictions.put(j, predictions.get(j) / user.size()); 
  } 
  for (String j : user.keySet()) 
  { 
   predictions.put(j, user.get(j)); 
  } 
  return predictions; 
 } 
 
// public void printData() 
// { 
//  for (String user : mData.keySet()) 
//  { 
//   System.out.println(user); 
//   print(mData.get(user)); 
//  } 
//  for (int i = 0; i < mAllItems.length; i++) 
//  { 
//   System.out.print("\n" + mAllItems[i] + ":"); 
//   printMatrixes(diffMatrix.get(mAllItems[i]), freqMatrix.get(mAllItems[i])); 
//  } 
// } 
 
 private void printMatrixes(Map<String, Double> ratings, Map<String, Integer> frequencies) 
 { 
  for (int j = 0; j < mAllItems.length; j++) 
  { 
   System.out.format("%10f", ratings.get(mAllItems[j])); 
   System.out.format("%10d", frequencies.get(mAllItems[j])); 
  } 
  System.out.println(); 
 } 
 
 public static ArrayList print(Map<String, Double> user) 
 { 
	 ArrayList resultado = new ArrayList();
  for (String j : user.keySet()) 
  { 
   if (user.get(j).intValue()>=3) {
	   System.out.println(j + " --> " + user.get(j).intValue());
	   resultado.add(j);
	   resultado.add(user.get(j).intValue());
   }   
  } 
    return resultado;
 } 
 
 public String resultadoRecomendacao(ArrayList lugarNota, int idUser) {
	 String query = "insert into whereverigo.avaliacao (id_usuario,idlugar,nota,dataAvaliacao) values ";
	for (int a=0; a<lugarNota.size();a++) {
		if (a%2==0) {
			int idLugar = localDao.getId( (String) lugarNota.get(a));
			query+= " ("+ idUser + ", "+ idLugar;
			
			
		}else {
			if (a+1!=lugarNota.size()) {
				query += ", "+ lugarNota.get(a) + ", "+ "'2017-08-11'), ";
			}else {
				query += ", "+ lugarNota.get(a) + ", "+ "'2017-08-11'); ";
			}	
		}
		
	}
	return query;
	 
 }
 
 public void buildDiffMatrix() 
 { 
  diffMatrix = new HashMap<>(); 
  freqMatrix = new HashMap<>(); 
  // first iterate through users 
  for (Map<String, Double> user : mData.values()) 
  { 
   // then iterate through user data 
   for (Entry<String, Double> entry : user.entrySet()) 
   { 
    String i1 = entry.getKey(); 
    double r1 = entry.getValue(); 
 
    if (!diffMatrix.containsKey(i1)) 
    { 
     diffMatrix.put(i1, new HashMap<String, Double>()); 
     freqMatrix.put(i1, new HashMap<String, Integer>()); 
    } 
 
    for (Entry<String, Double> entry2 : user.entrySet()) 
    { 
     String i2 = entry2.getKey(); 
     double r2 = entry2.getValue(); 
 
     int cnt = 0; 
     if (freqMatrix.get(i1).containsKey(i2)) cnt = freqMatrix.get(i1).get(i2); 
     double diff = 0.0; 
     if (diffMatrix.get(i1).containsKey(i2)) diff = diffMatrix.get(i1).get(i2); 
     double new_diff = r1 - r2; 
 
     freqMatrix.get(i1).put(i2, cnt + 1); 
     diffMatrix.get(i1).put(i2, diff + new_diff); 
    } 
   } 
  } 
  for (String j : diffMatrix.keySet()) 
  { 
   for (String i : diffMatrix.get(j).keySet()) 
   { 
    Double oldvalue = diffMatrix.get(j).get(i); 
    int count = freqMatrix.get(j).get(i).intValue(); 
    diffMatrix.get(j).put(i, oldvalue / count); 
   } 
  } 
 } 
}
