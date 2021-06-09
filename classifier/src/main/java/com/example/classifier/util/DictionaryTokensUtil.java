package com.example.classifier.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DictionaryTokensUtil {

    public static JsonArray getDictionaryHealth(){
        JsonArray health = new JsonArray();
        health.add("vacunacion");
        health.add("daniel salas");
        health.add("ministro de salud");
        health.add("ministerio de salud");
        health.add("pandemia");
        health.add("covid19");
        health.add("coronavirus");
        health.add("cuidar");
        health.add("salud");
        health.add("hospitales");
        health.add("camillas");
        health.add("inyecciones");
        health.add("inyección");
        health.add("clínica");
        health.add("cirugías");
        health.add("enfermos");
        health.add("enfermo");
        health.add("embarazo");
        health.add("embarazadas");
        health.add("bebé");
        health.add("feto");
        health.add("enfermedad");
        return health;
    }

    public static JsonArray getDictionaryPolitics(){
        JsonArray politics = new JsonArray();
        politics.add("diputados");
        politics.add("diputado");
        politics.add("asamblea legislativa");
        politics.add("rendición de cuentas");
        politics.add("interpelación");
        politics.add("pac");
        politics.add("convención");
        politics.add("fabricio alvarado");
        politics.add("presidencia");
        politics.add("presidencial");
        politics.add("política");
        politics.add("oscar arias");
        politics.add("figueres");
        politics.add("moción");
        politics.add("plazo");
        politics.add("aumento salarial");
        politics.add("pusc");
        return politics;
    }

    public static JsonArray getDictionaryEvents(){
        JsonArray events = new JsonArray();
        events.add("homicidio");
        events.add("accidentes");
        events.add("muertes");
        events.add("violento");
        events.add("violencias");
        events.add("arma blanca");
        events.add("fallecidos");
        events.add("oij");
        events.add("arma de fuego");
        events.add("incendio");
        events.add("fuego");
        events.add("choque");
        events.add("accidente de tránsito");
        events.add("balazos");
        events.add("pandilla");
        events.add("narco");
        events.add("fuerza publica");
        events.add("motocicleta");
        events.add("investigación");
        events.add("ministerio de seguridad pública");
        events.add("desaparecidos");
        events.add("secuestro");
        events.add("asesinos");
        events.add("arma");
        return events;
    }


    public static JsonArray getDictionarySports(){
        JsonArray sports = new JsonArray();
        sports.add("deportes");
        sports.add("saprissa");
        sports.add("deportivo saprissa");
        sports.add("marvin angulo");
        sports.add("ricardo blanco");
        sports.add("equipos");
        sports.add("herediano");
        sports.add("lda");
        sports.add("liga deportiva alajuelense");
        sports.add("club sport herediano");
        sports.add("eliminatorias");
        sports.add("campeonato");
        sports.add("seleccion nacional");
        sports.add("guardameta");
        sports.add("portero");
        sports.add("defensa");
        sports.add("formacion");
        sports.add("cancha");
        sports.add("futbol");
        sports.add("estadio");
        sports.add("limon fc");
        sports.add("limon futbol club");
        sports.add("bryan ruiz");
        sports.add("la sele");
        sports.add("concacaf");
        sports.add("goleada");
        sports.add("goles");
        sports.add("gol");
        sports.add("ciclistas");
        sports.add("andrey amador");
        sports.add("ciclismo");
        sports.add("montain bike");
        sports.add("rally");
        sports.add("carreras");
        sports.add("keylor navas");
        sports.add("copa");
        sports.add("debut");
        return sports;
    }

    public static JsonArray getDictionaryEconomy(){
        JsonArray economy = new JsonArray();
        economy.add("dinero");
        economy.add("consumidores");
        economy.add("supermercados");
        economy.add("banco");
        economy.add("prestamos");
        economy.add("ventas");
        economy.add("millones");
        economy.add("normas niiif");
        economy.add("bolsa de valores");
        economy.add("ocde");
        economy.add("la organización para la cooperación y desarrollo económico");
        economy.add("economia");
        economy.add("finanzas");
        economy.add("liquidez");
        economy.add("credito");
        economy.add("empresas");
        economy.add("gastos");
        economy.add("aumentos");
        economy.add("salarios");
        economy.add("sugef");
        economy.add("vende");
        return economy;
    }

    public static JsonArray getDictionaryEntertainment(){
        JsonArray entertainment = new JsonArray();
        entertainment.add("ricky martin");
        entertainment.add("cantante");
        entertainment.add("artista");
        entertainment.add("noviasgo");
        entertainment.add("farandula");
        entertainment.add("fotos");
        entertainment.add("alagos");
        entertainment.add("redes sociales");
        entertainment.add("estilo");
        entertainment.add("encanto");
        entertainment.add("espectaculo");
        entertainment.add("concierto");
        entertainment.add("boletos");
        entertainment.add("redes");
        entertainment.add("secreto");
        entertainment.add("fans");
        entertainment.add("television");
        entertainment.add("presentador");
        entertainment.add("presentadora");
        entertainment.add("presentadores");
        entertainment.add("pasion");
        entertainment.add("actor");
        entertainment.add("actriz");
        entertainment.add("miss");
        entertainment.add("pasarela");
        entertainment.add("modelaje");
        entertainment.add("ropa");
        entertainment.add("video");
        entertainment.add("camara");
        entertainment.add("famoso");
        return entertainment;
    }

    public static JsonObject getDictionaryTokens(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("health", getDictionaryHealth());
        jsonObject.add("politics", getDictionaryPolitics());
        jsonObject.add("events", getDictionaryEvents());
        jsonObject.add("sports", getDictionarySports());
        jsonObject.add("economy", getDictionaryEconomy());
        return jsonObject;
    }

}
