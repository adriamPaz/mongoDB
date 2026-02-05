package com.mongo1;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.internal.operation.AggregateOperation;

public class PartidaCRUD {
    MongoProvider mongoProvider = new MongoProvider();

    public PartidaCRUD(){

    }
    public void createPartida(Partida partida){
        try {
            Document documento = new Document();
            documento.append("xogador", partida.getXogador());
            documento.append("xogo", partida.getXogo());
            documento.append("puntuacion", partida.getPuntuacion());
            documento.append("duracion", partida.getDuracion());
            documento.append("nivel", partida.getNivel());


            InsertOneResult insert = mongoProvider.partida().insertOne(documento);
            System.out.println(insert.getInsertedId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void removePartida(Partida partida){
        try {
            Bson filter = Filters.eq("xogador",partida.getXogador());
            DeleteResult delete = mongoProvider.partida().deleteOne(filter);
            System.out.println(delete.getDeletedCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void puntuacionTotalXogador(String nomeXogador){
        try {
            Bson filtro = Filters.eq("xogador",nomeXogador);
            AggregateOperation total = mongoProvider.partida().aggregate("$sum",filtro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
