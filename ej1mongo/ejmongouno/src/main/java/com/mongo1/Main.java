package com.mongo1;

import java.util.ArrayDeque;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        try (MongoProvider provider = new MongoProvider();
            Scanner sc = new Scanner(System.in)) {
            Document doc = new Document();
            doc.append("nombre", "Juan");
            doc.append("ciclo", "ASIR");
            doc.append("edad", 18);

            Document doc2 = new Document();
            doc2.append("nombre", "Marta");
            doc2.append("edad", 21);

            provider.alumnado().insertOne(doc);
            provider.alumnado().insertOne(doc2);
            
            MongoCollection<Document> selected = provider.alumnado();
            ArrayDeque<Document> seleccionados = new ArrayDeque<>();
            

            selected.find().into(seleccionados);

            long daleted = selected.deleteMany(Filters.eq("edad",18)).getDeletedCount();

            System.out.println("Find Into");
            for (Document document : seleccionados) {
                System.out.println(document.toJson());
            }

            System.out.println("Find ForEach");

            selected.find().forEach(docu -> System.out.println(docu.toJson()));

            System.out.println(daleted);
            
            

        } catch (Exception e) {
            System.out.println("parguelon");
        }

        
    }

    public void insertar(String nombre, String ciclo, int edad){
        Document doc = new Document();
        doc.append("nombre", nombre);
        doc.append("ciclo", ciclo);
        doc.append("edad", edad);

        
    }
}




